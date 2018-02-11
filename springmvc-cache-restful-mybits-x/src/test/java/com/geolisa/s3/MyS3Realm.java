package com.geolisa.s3;

import com.google.common.collect.Sets;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyS3Realm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyS3Realm.class);

    //获取用户身份授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(Sets.newHashSet("role1", "role2"));
        authorizationInfo.addObjectPermission(new BitPermission("+user1+10"));
        authorizationInfo.addObjectPermission(new WildcardPermission("user1:*"));
        authorizationInfo.addStringPermission("+user2+10");
        authorizationInfo.addStringPermission("user2:*");
        return authorizationInfo;
    }

    //获取身份验证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String principal = token.getPrincipal().toString(); //username
        //UsernamePasswordToken 会把String 转换成char[]
        String credentials = new String((char[])token.getCredentials()); //password
        LOGGER.info("username is {}", principal);
        LOGGER.info("password is {}", credentials);
        if (!"zhang".equalsIgnoreCase(principal)) {
            throw new UnknownAccountException("未知的账号信息");
        }

        if (!"123".equalsIgnoreCase(credentials)) {
            throw new IncorrectCredentialsException("无效的认证信息");
        }

        return new SimpleAuthenticationInfo(principal, credentials, getName());
    }
}
