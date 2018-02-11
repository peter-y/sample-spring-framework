package com.geolisa.s2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

@Test
public class ShiroAllRealmTest {

    private static final Logger logger = LoggerFactory.getLogger(ShiroAllRealmTest.class);

    //@BeforeClass 一次测试队列中 每个文件开始会被调用
    //@BeforeTest 一次测试队列中，只会执行一次
    //@BeforeMethod 每个方法之前都会被调用
    //@BeforeSuite 测试队列开始的时候执行一次
    public void login(String iniPath) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniPath);

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        subject.login(token);
        logger.info("go to login");
    }

    @AfterMethod
    public void logout() {
        SecurityUtils.getSubject().logout();
        logger.info("go to logout");
    }

    @Test
    public void testAllSuccessfulStrategyWithSuccess() {
        login("classpath:2/shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();
        //得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
    }

    @Test
    public void testAllSuccessfulStrategyWithFail() {
        login("classpath:2/shiro-authenticator-all-fail.ini");
        Subject subject = SecurityUtils.getSubject();
        //得到一个身份集合，其包含了Realm验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        Assert.assertEquals(2, principalCollection.asList().size());
    }
}
