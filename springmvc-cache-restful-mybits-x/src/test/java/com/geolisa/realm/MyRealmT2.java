package com.geolisa.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRealmT2 implements Realm {

    private static final Logger LOG = LoggerFactory.getLogger(MyRealmT2.class);

    @Override
    public String getName() {
        return "myRealm2";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = token.getPrincipal().toString(); //username
        //UsernamePasswordToken 会把String 转换成char[]
        String credentials = new String((char[])token.getCredentials()); //password
        LOG.info("username is {}", principal);
        LOG.info("password is {}", credentials);
        if (!"wang".equalsIgnoreCase(principal)) {
            throw new UnknownAccountException("未知的账号信息");
        }

        if (!"123".equalsIgnoreCase(credentials)) {
            throw new IncorrectCredentialsException("无效的认证信息");
        }

        return new SimpleAuthenticationInfo(principal, credentials, getName());
    }
}
