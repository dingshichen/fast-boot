package cn.dsc.cache.config;

import org.springframework.data.redis.cache.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author dingshichen
 */
public class FastRedisCacheManager extends RedisCacheManager {

    private final RedisCacheWriter cacheWriter;
    private final boolean allowInFlightCacheCreation;
    private final RedisCacheConfiguration defaultCacheConfig;

    public FastRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
        this.cacheWriter = cacheWriter;
        this.allowInFlightCacheCreation = true;
        this.defaultCacheConfig = defaultCacheConfiguration;
    }

    public FastRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheNames);
        this.cacheWriter = cacheWriter;
        this.allowInFlightCacheCreation = true;
        this.defaultCacheConfig = defaultCacheConfiguration;
    }

    public FastRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, boolean allowInFlightCacheCreation, String... initialCacheNames) {
        super(cacheWriter, defaultCacheConfiguration, allowInFlightCacheCreation, initialCacheNames);
        this.cacheWriter = cacheWriter;
        this.allowInFlightCacheCreation = allowInFlightCacheCreation;
        this.defaultCacheConfig = defaultCacheConfiguration;
    }

    public FastRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations);
        this.cacheWriter = cacheWriter;
        this.allowInFlightCacheCreation = true;
        this.defaultCacheConfig = defaultCacheConfiguration;
    }

    public FastRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration, Map<String, RedisCacheConfiguration> initialCacheConfigurations, boolean allowInFlightCacheCreation) {
        super(cacheWriter, defaultCacheConfiguration, initialCacheConfigurations, allowInFlightCacheCreation);
        this.cacheWriter = cacheWriter;
        this.allowInFlightCacheCreation = allowInFlightCacheCreation;
        this.defaultCacheConfig = defaultCacheConfiguration;
    }

    @Override
    protected RedisCache getMissingCache(String name) {
        return allowInFlightCacheCreation ? createRedisCache(name, defaultCacheConfig) : null;
    }

    protected RedisCache createRedisCache(String name, @Nullable RedisCacheConfiguration cacheConfig) {
        return new FastRedisCache(name, cacheWriter, cacheConfig != null ? cacheConfig : defaultCacheConfig);
    }


    public static FastRedisCacheManager.FastRedisCacheManagerBuilder builderFast(RedisConnectionFactory connectionFactory) {

        Assert.notNull(connectionFactory, "ConnectionFactory must not be null!");

        return FastRedisCacheManager.FastRedisCacheManagerBuilder.fromConnectionFactory(connectionFactory);
    }


    public static FastRedisCacheManager.FastRedisCacheManagerBuilder builderFast(RedisCacheWriter cacheWriter) {

        Assert.notNull(cacheWriter, "CacheWriter must not be null!");

        return FastRedisCacheManager.FastRedisCacheManagerBuilder.fromCacheWriter(cacheWriter);
    }

    public static class FastRedisCacheManagerBuilder {

        private final RedisCacheWriter cacheWriter;
        private RedisCacheConfiguration defaultCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig();
        private final Map<String, RedisCacheConfiguration> initialCaches = new LinkedHashMap<>();
        private boolean enableTransactions;
        boolean allowInFlightCacheCreation = true;

        private FastRedisCacheManagerBuilder(RedisCacheWriter cacheWriter) {
            this.cacheWriter = cacheWriter;
        }

        public static FastRedisCacheManager.FastRedisCacheManagerBuilder fromConnectionFactory(RedisConnectionFactory connectionFactory) {

            Assert.notNull(connectionFactory, "ConnectionFactory must not be null!");

            return builderFast(new FastRedisCacheWriter(connectionFactory));
        }

        public static FastRedisCacheManager.FastRedisCacheManagerBuilder fromCacheWriter(RedisCacheWriter cacheWriter) {

            Assert.notNull(cacheWriter, "CacheWriter must not be null!");

            return new FastRedisCacheManager.FastRedisCacheManagerBuilder(cacheWriter);
        }

        public FastRedisCacheManager.FastRedisCacheManagerBuilder cacheDefaults(RedisCacheConfiguration defaultCacheConfiguration) {

            Assert.notNull(defaultCacheConfiguration, "DefaultCacheConfiguration must not be null!");

            this.defaultCacheConfiguration = defaultCacheConfiguration;

            return this;
        }

        public FastRedisCacheManager.FastRedisCacheManagerBuilder transactionAware() {

            this.enableTransactions = true;

            return this;
        }

        public FastRedisCacheManager.FastRedisCacheManagerBuilder initialCacheNames(Set<String> cacheNames) {

            Assert.notNull(cacheNames, "CacheNames must not be null!");

            Map<String, RedisCacheConfiguration> cacheConfigMap = new LinkedHashMap<>(cacheNames.size());
            cacheNames.forEach(it -> cacheConfigMap.put(it, defaultCacheConfiguration));

            return withInitialCacheConfigurations(cacheConfigMap);
        }

        public FastRedisCacheManager.FastRedisCacheManagerBuilder withInitialCacheConfigurations(
                Map<String, RedisCacheConfiguration> cacheConfigurations) {

            Assert.notNull(cacheConfigurations, "CacheConfigurations must not be null!");
            cacheConfigurations.forEach((cacheName, configuration) -> Assert.notNull(configuration,
                    String.format("RedisCacheConfiguration for cache %s must not be null!", cacheName)));

            this.initialCaches.putAll(cacheConfigurations);

            return this;
        }

        public FastRedisCacheManager.FastRedisCacheManagerBuilder disableCreateOnMissingCache() {

            this.allowInFlightCacheCreation = false;
            return this;
        }

        public FastRedisCacheManager build() {

            FastRedisCacheManager cm = new FastRedisCacheManager(cacheWriter, defaultCacheConfiguration, initialCaches,
                    allowInFlightCacheCreation);

            cm.setTransactionAware(enableTransactions);

            return cm;
        }
    }
}
