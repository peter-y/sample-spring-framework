package com.geolisa.s5;


import java.security.Key;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.SimpleByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class CommonCodeTest {

    Logger logger = LoggerFactory.getLogger(CommonCodeTest.class);

    //解码编码
    public void base64t() {
        String str = "hello";
        String base64string = Base64.encodeToString(str.getBytes());
        logger.debug("base64string {}", base64string);
        String str2 = Base64.decodeToString(base64string);
        Assert.assertEquals(str2, str);
    }

    //散列
    public void md5t() {
        String str = "hello";
        String salt = "123";
        //和 toString 调用的就是 hex
        String md5s = new Md5Hash(str, salt).toString();
        logger.debug("md5s {}", md5s);
        String md5h = new Md5Hash(str, salt).toHex();
        logger.debug("md5h {}", md5h);
        String sha256s = new Sha256Hash(str, salt).toString();
        logger.debug("sha256s {}", sha256s);
        String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
        logger.debug("simpleHash {}", simpleHash);
    }

    //shiro 提供的 hash service
    public void hashService() {
        DefaultHashService hashService = new DefaultHashService();
        //默认配置 当 request 中没有相应设置的时候生效
        hashService.setHashAlgorithmName("SHA-512");
        hashService.setPrivateSalt(new SimpleByteSource("123"));
        hashService.setHashIterations(1);
        //生成公盐
        hashService.setGeneratePublicSalt(true);
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        //hash request 的参数能覆盖 hashService 中的配置
        HashRequest hashRequest = new HashRequest.Builder()
            .setAlgorithmName("MD5").setIterations(1).setSalt(new SimpleByteSource("123"))
            .setSource(new SimpleByteSource("hello"))
            .build();
        String hexs = hashService.computeHash(hashRequest).toHex();
        logger.debug("hexs {}", hexs);
        hashRequest = new HashRequest.Builder()
            .setSource(new SimpleByteSource("hello"))
            .build();
        hexs = hashService.computeHash(hashRequest).toHex();
        logger.debug("hexs2 {}", hexs);
    }

    public void randomGenerator() {
        SecureRandomNumberGenerator randomNumberGenerator =
            new SecureRandomNumberGenerator();
        randomNumberGenerator.setSeed("123".getBytes());
        String hex = randomNumberGenerator.nextBytes().toHex();
        logger.debug("hex {}", hex);
    }

    //加密解密算法
    public void AesCiphert() {
        AesCipherService aesCipherService = new AesCipherService();
        aesCipherService.setKeySize(128);
        Key key = aesCipherService.generateNewKey();
        String str = "hello";
        String ens = aesCipherService.encrypt(str.getBytes(), key.getEncoded()).toHex();
        //直接 decode 拿到的是个乱码
        String dns = new String(aesCipherService.decrypt(Hex.decode(ens), key.getEncoded()).getBytes());
        logger.debug("ens {}", ens);
        logger.debug("dns {}", dns);
        Assert.assertEquals(dns, str);
    }

}
