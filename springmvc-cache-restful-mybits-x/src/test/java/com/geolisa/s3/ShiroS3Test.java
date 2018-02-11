package com.geolisa.s3;

import com.geolisa.AbstractShiroTest;
import java.util.Arrays;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class ShiroS3Test extends AbstractShiroTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroS3Test.class);

    public void hasRole() {
        login("classpath:3/shiro-role.ini", "zhang", "123");
        Subject subject = SecurityUtils.getSubject();
        //hasRole 不抛出异常
        Assert.assertTrue(subject.hasRole("role1"));
        Assert.assertTrue(subject.hasAllRoles(Arrays.asList("role1", "role2")));
        boolean[] result = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertTrue(result[0]);
        Assert.assertTrue(result[1]);
        Assert.assertFalse(result[2]);
    }

    @Test(expectedExceptions = UnauthorizedException.class)
    public void checkHasRole() {
        login("classpath:3/shiro-role.ini", "zhang", "123");
        //checkRole 抛出异常
        getSubject().checkRole("role1");
        getSubject().checkRoles("role1", "role3");
    }
}
