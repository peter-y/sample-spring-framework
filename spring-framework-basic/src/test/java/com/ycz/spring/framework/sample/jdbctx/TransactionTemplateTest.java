package com.ycz.spring.framework.sample.jdbctx;

import com.ycz.spring.framework.sample.aoptx.po.Foo;
import com.ycz.spring.framework.sample.aoptx.service.FooService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@Test
@ContextConfiguration(locations = "classpath:spring/spring-aoptx.xml")
@ComponentScan(basePackages = "com.ycz.spring.framework.sample.aoptx.listener")
public class TransactionTemplateTest extends AbstractTestNGSpringContextTests {

    private Logger logger = LoggerFactory.getLogger(TransactionTemplateTest.class);

    @Autowired
    FooService fooService;

    //总结一下
    //这里的测试 展示事务传播行为的结果
    //aop事务内的操作 一个失败 会都失败，个配置传播行为有关
    //没有被aop代理拦截的事务方法，即使里面使用了 被代理的事务方法，事务并没有生效
    //手动开启的事务需要自己控制，加入有aop事务的方法 会加入父方法的事务中

    public void testHasAopTransaction() {
        logger.debug("do testHasAopTransaction");
        Foo foo = new Foo();
        foo.setName("张晓强");
        foo.setBarName("bar张");
        //有aop事务介入 能自动提交
        fooService.insertFoo(foo);
    }

    public void testHasAopTransactionException() {
        logger.debug("do testHasAopTransaction");
        Foo foo = new Foo();
        foo.setName("张晓强");
        foo.setBarName("bar张");
        //有aop事务介入 能自动提交 能回滚
        try {
            fooService.insertFooHasException(foo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testNotAnyTransaction() {
        logger.debug("do testNotAnyTransaction");
        //啥事务都没有 提交不了
        fooService.doSqlInsert("insert into foo (name,barname) values ('周','小')");
    }

    public void testHasCustomTransaction() {
        logger.debug("do testCustomTransaction");
        Foo foo = new Foo();
        foo.setName("六星1");
        foo.setBarName("星2");
        // 自己进行控制 能提交
        fooService.doCreateFoo(foo);
    }

    public void testHasCustomTransactionException() {
        logger.debug("do testCustomTransaction");
        Foo foo = new Foo();
        foo.setName("六星3");
        foo.setBarName("星3");
        // 自己进行控制 能提交 失败 回滚
        try {
            fooService.doCreateFooHasException(foo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testNestingInsertFoo() {
        Foo foo = new Foo();
        foo.setName("汪汪");
        foo.setBarName("雪饼");
        //本身没事务 但里面的方法有事务
        //提交不了
        fooService.nestingInsertFoo(foo);
    }

    public void testInsertFooNesting() {
        Foo foo = new Foo();
        foo.setName("嵌套-父有事务3");
        foo.setBarName("子没有事务3");
        //也加到事务里面了
        try {
            fooService.insertFooNesting(foo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testInsertFooNestingHasException() {
        Foo foo = new Foo();
        foo.setName("嵌套-父有事务2");
        foo.setBarName("子没有事务-且有异常，一个没有异常");
        //也加到事务里面了 失败 回滚
        try {
            fooService.insertFooNestingHasException(foo);
        } catch (Exception e) {
            logger.debug("捕获到最里面抛出的异常");
            e.printStackTrace();
        }
    }

    public void testDoCreateFooReturn() {
        Foo foo = new Foo();
        foo.setName("父没事务，自己开事务");
        foo.setBarName("子两个方法，一个有事务一个没有事务");
        // 都会在一个事务内提交? 验证一下
        fooService.doCreateFooReturn(foo);
    }

    public void testDoCreateFooReturnHasException() {
        Foo foo = new Foo();
        foo.setName("父没事务，自己开事务");
        foo.setBarName("子两个方法，一个有事务一个没有事务,有事务的抛异常");
        try {
            fooService.doCreateFooReturnHasException(foo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
