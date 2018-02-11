package com.geolisa.s3;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 解析字符权限 为相应的 实例
 */
public class BitAndWildPermissionResolver implements PermissionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(BitAndWildPermissionResolver.class);

    @Override
    public Permission resolvePermission(String permissionString) {
        LOGGER.info("permissionString is {}", permissionString);
        if (permissionString.startsWith("+")) {
            return new BitPermission(permissionString);
        }
        return new WildcardPermission(permissionString);
    }
}
