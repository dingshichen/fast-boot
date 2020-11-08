package cn.dsc.ss;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.junit.Test;

import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 密钥生成
 * @author dingshichen
 */
public class GenerateKey {

    /**
     * 生成对称加密密钥
     */
    @Test
    public void generateKey() {
        // AES 算法
        SecretKey secretKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), 256);
        // 密钥通常是字节数组形式
        byte[] encoded = secretKey.getEncoded();
        // 转换成 Base64 方便我们存储
        String encode = Base64.encode(encoded);
        System.out.println("密钥转换成 base64 编码后：" + encode);
    }

    /**
     * 加密和解密
     */
    @Test
    public void encryptAndDecrypt() {
        String content = "中国人加油";
        // AES 算法
        SecretKey secretKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), 256);
        // 密钥通常是字节数组形式
        byte[] encoded = secretKey.getEncoded();
        // 对称加密解密的实现类
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, encoded);
        // 使用 UTF-8 编码格式加密为 16 进制字符
        String encryptHex = aes.encryptHex(content);
        System.out.println("使用 UTF-8 编码格式加密为 16 进制字符：" + encryptHex);
        // 解密，默认使用 UTF-8 编码格式，会自动判断字符是 16 进制字符还是 Base64 进制
        String result1 = aes.decryptStr(encryptHex);
        System.out.println("解密后的字符为：" + result1);

        String encryptBase64 = aes.encryptBase64(content);
        System.out.println("使用 UTF-8 编码格式加密为 Base64 编码字符：" + encryptBase64);
        String result2 = aes.decryptStr(encryptBase64);
        System.out.println("解密后的字符为：" + result2);
    }


    /**
     * 生成密钥对
     */
    @Test
    public void	generateKeyPair() {
        // 生成 RSA 算法的密钥对
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();
        System.out.println("私钥：" + Base64.encode(privateKey.getEncoded()));
        System.out.println("公钥：" + Base64.encode(publicKey.getEncoded()));
    }

    /**
     * RSA 密钥对加密解密
     */
    @Test
    public void rsaEncryptAndDecrypt() {
        // 生成 RSA 算法的密钥对
        KeyPair pair = SecureUtil.generateKeyPair("RSA");
        PrivateKey privateKey = pair.getPrivate();
        PublicKey publicKey = pair.getPublic();

        String content = "这就是青春";
        RSA rsa = new RSA(null, publicKey);
        byte[] encrypt = rsa.encrypt(content, KeyType.PublicKey);
        String encode = Base64.encode(encrypt);
        System.out.println("公钥加密后：" + encode);

        rsa = new RSA(privateKey, null);
        byte[] decrypt = rsa.decrypt(encode, KeyType.PrivateKey);
        System.out.println("私钥解密后：" + StrUtil.str(decrypt, "UTF-8"));
    }



}
