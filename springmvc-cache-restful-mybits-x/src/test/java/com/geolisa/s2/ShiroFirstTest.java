package com.geolisa.s2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ShiroFirstTest {

    /**
     * 使用写死的 ini 文件
     */
    public void testHelloWorldForShiro() {
        //拿到一个securityManager 相当于是一个认证源
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:2/shiro.ini");
        //获取一个 securityManager 的 instance
        SecurityManager securityManager = factory.getInstance();
        //urils中Set securityManager ?? 绑定安全管理器，全局设置
        SecurityUtils.setSecurityManager(securityManager);
        //获取一个subject 自动绑定当前线程，结束后需要解除绑定?
        Subject subject = SecurityUtils.getSubject();
        //生成一个账号token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang", "123");
        //进行登录 实际执行是在 securityManager 中进行
        subject.login(usernamePasswordToken);
        Assert.assertTrue(subject.isAuthenticated(),"登录失败");
        subject.logout();
    }

    /**
     * 使用自定义的 realm
     */
    public void testHelloWorldForShiroRealm() {
        //拿到一个securityManager 相当于是一个认证源
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:2/shiro-realm.ini");
        //获取一个 securityManager 的 instance
        SecurityManager securityManager = factory.getInstance();
        //urils中Set securityManager ?? 绑定安全管理器，全局设置
        SecurityUtils.setSecurityManager(securityManager);
        //获取一个subject 自动绑定当前线程，结束后需要解除绑定?
        Subject subject = SecurityUtils.getSubject();
        //生成一个账号token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang", "123");
        //进行登录 实际执行是在 securityManager 中进行
        subject.login(usernamePasswordToken);
        Assert.assertTrue(subject.isAuthenticated(),"登录失败");
        subject.logout();
    }

    /**
     * 使用jdbc
     */
    public void testHelloWorldForShiroJdbcRealm() {
        //拿到一个securityManager 相当于是一个认证源
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:2/shiro-jdbc-realm.ini");
        //获取一个 securityManager 的 instance
        SecurityManager securityManager = factory.getInstance();
        //urils中Set securityManager ?? 绑定安全管理器，全局设置
        SecurityUtils.setSecurityManager(securityManager);
        //获取一个subject 自动绑定当前线程，结束后需要解除绑定?
        Subject subject = SecurityUtils.getSubject();
        //生成一个账号token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang", "123");
        //进行登录 实际执行是在 securityManager 中进行
        subject.login(usernamePasswordToken);
        Assert.assertTrue(subject.isAuthenticated(),"登录失败");
        subject.logout();
    }
}
