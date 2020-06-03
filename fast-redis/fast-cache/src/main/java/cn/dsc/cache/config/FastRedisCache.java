package cn.dsc.cache.config;

import org.springframework.cache.support.SimpleValueWrapper;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.redis.cache.RedisCache;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.lang.Nullable;

import java.time.Duration;
import java.util.concurrent.Callable;

/**
 * @author dingshichen
 */
public class FastRedisCache extends RedisCache {

    private Duration ttl;

    private String realName;

    private final RedisCacheWriter cacheWriter;
    private final RedisCacheConfiguration cacheConfig;
    private final ConversionService conversionService;

    protected FastRedisCache(String name, RedisCacheWriter cacheWriter, RedisCacheConfiguration cacheConfig) {
        super(name, cacheWriter, cacheConfig);
        this.cacheWriter = cacheWriter;
        this.cacheConfig = cacheConfig;
        this.conversionService = cacheConfig.getConversionService();
        this.setRealNameAndTtl(name);
    }

    private void setRealNameAndTtl(String name) {
        int index = name.indexOf("~");
        this.ttl = Duration.ofSeconds(Long.parseLong(name.substring(0, index)));
        this.realName = name.substring(index + 1);
    }

    private String getRealName() {
        return this.realName;
    }

    @Override
    protected Object lookup(Object key) {

        byte[] value = cacheWriter.get(realName, createAndConvertCacheKey(key));

        if (value == null) {
            return null;
        }

        return deserializeCacheValue(value);
    }

    @Override
    public String getName() {
        return realName;
    }

    @Override
    public RedisCacheWriter getNativeCache() {
        return this.cacheWriter;
    }

    @Override
    @SuppressWarnings("unchecked")
    public synchronized <T> T get(Object key, Callable<T> valueLoader) {

        ValueWrapper result = get(key);

        if (result != null) {
            return (T) result.get();
        }

        T value = valueFromLoader(key, valueLoader);
        put(key, value);
        return value;
    }

    @Override
    public void put(Object key, @Nullable Object value) {

        Object cacheValue = preProcessCacheValue(value);

        if (!isAllowNullValues() && cacheValue == null) {

            throw new IllegalArgumentException(String.format(
                    "Cache '%s' does not allow 'null' values. Avoid storing null via '@Cacheable(unless=\"#result == null\")' or configure RedisCache to allow 'null' via RedisCacheConfiguration.",
                    realName));
        }

        cacheWriter.put(realName, createAndConvertCacheKey(key), serializeCacheValue(cacheValue), ttl);
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, @Nullable Object value) {

        Object cacheValue = preProcessCacheValue(value);

        if (!isAllowNullValues() && cacheValue == null) {
            return get(key);
        }

        byte[] result = cacheWriter.putIfAbsent(realName, createAndConvertCacheKey(key), serializeCacheValue(cacheValue),
                ttl);

        if (result == null) {
            return null;
        }

        return new SimpleValueWrapper(fromStoreValue(deserializeCacheValue(result)));
    }

    @Override
    public void evict(Object key) {
        cacheWriter.remove(realName, createAndConvertCacheKey(key));
    }

    @Override
    public void clear() {

        byte[] pattern = conversionService.convert(createCacheKey("*"), byte[].class);
        cacheWriter.clean(realName, pattern);
    }

    protected String createCacheKey(Object key) {

        String convertedKey = convertKey(key);

        if (!cacheConfig.usePrefix()) {
            return convertedKey;
        }

        return prefixCacheKey(convertedKey);
    }


    private byte[] createAndConvertCacheKey(Object key) {
        return serializeCacheKey(createCacheKey(key));
    }

    private String prefixCacheKey(String key) {

        // allow contextual cache names by computing the key prefix on every call.
        return cacheConfig.getKeyPrefixFor(realName) + key;
    }

    private static <T> T valueFromLoader(Object key, Callable<T> valueLoader) {

        try {
            return valueLoader.call();
        } catch (Exception e) {
            throw new ValueRetrievalException(key, valueLoader, e);
        }
    }

}
