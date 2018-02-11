package com.geolisa.s3;

import java.util.Arrays;
import java.util.Collection;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通过角色 解析权限集合
 */
public class MyRolePermissionResolver implements RolePermissionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRolePermissionResolver.class);

    @Override
    public Collection<Permission> resolvePermissionsInRole(String roleString) {
        LOGGER.info("roleString is {}", roleString);
        if ("role1".equals(roleString)) {
            return Arrays.asList((Permission) new WildcardPermission("menu:*"));
        }
        return null;
    }
}
