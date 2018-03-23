package com.ycz.spring.framework.sample.aoptx.service;

import com.ycz.spring.framework.sample.aoptx.po.Foo;

public interface FooService {

    public Foo getFoo(String name);

    public Foo getFoo(String name, String barName);

    public void updateFoo(Foo foo);

    /**
     * 嵌套一个原本有事务控制的方法，看出现异常能否回滚
     */
    public void nestingInsertFoo(Foo foo);

    public void nestingInsertFooHasException(Foo foo);

    public void insertFooNesting(Foo foo);

    public void insertFooNestingHasException(Foo foo);

    public void insertFoo(Foo foo);

    public void insertFooHasException(Foo foo);

    public void doCreateFoo(Foo foo);

    public void doCreateFooHasException(Foo foo);

    public Foo doCreateFooReturn(Foo foo);

    public Foo doCreateFooReturnHasException(Foo foo);

    /**
     * 直接执行sql语句 没有事务包含
     *
     * @param sql 语句
     */
    public void doSqlInsert(String sql);

    public void doSqlInsertHasException(String sql);

}
