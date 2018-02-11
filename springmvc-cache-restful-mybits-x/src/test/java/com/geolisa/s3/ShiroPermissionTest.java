package com.geolisa.s3;

import com.geolisa.AbstractShiroTest;
import org.apache.shiro.authz.UnauthorizedException;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ShiroPermissionTest extends AbstractShiroTest {

    public void testIsPermitted() {
        login("classpath:3/shiro-permission.ini", "zhang", "123");
        Assert.assertTrue(getSubject().isPermitted("user:create"));
        Assert.assertTrue(getSubject().isPermittedAll("user:create", "user:delete"));
        Assert.assertFalse(getSubject().isPermitted("user:view"));
    }

    @Test(expectedExceptions = UnauthorizedException.class)
    public void testCheckPermission() {
        login("classpath:3/shiro-permission.ini", "zhang", "123");
        getSubject().checkPermission("user:create");
        getSubject().checkPermissions("user:create", "user:delete");
        getSubject().checkPermission("user:view");
    }

    //自定义的角色权限解析配置
    public void testIsPermitted2() {
        login("classpath:3/shiro-authorizer.ini", "zhang", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(getSubject().isPermitted("user1:update"));
        Assert.assertTrue(getSubject().isPermitted("user2:update"));
        //通过二进制位的方式表示权限
        Assert.assertTrue(getSubject().isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(getSubject().isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(getSubject().isPermitted("+user2+10"));//新增及查看
        Assert.assertFalse(getSubject().isPermitted("+user1+4"));//没有删除权限
        Assert.assertTrue(getSubject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }

}
