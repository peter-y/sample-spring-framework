package com.geolisa;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;

public class AbstractShiroTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractShiroTest.class);

    public Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public void login(String iniPath, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(iniPath);

        SecurityManager securityManager = factory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        subject.login(token);

        LOGGER.info("{} go to login", subject.getPrincipal().toString());
    }

    @AfterMethod
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        String username = subject.getPrincipal().toString();
        //logout 之后 subject 就是空了
        subject.logout();
        LOGGER.info("{} go to logout", username);
    }
}
