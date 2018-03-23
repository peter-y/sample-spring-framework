package com.ycz.spring.framework.sample.aoptx.service;

import com.ycz.spring.framework.sample.aoptx.UnsupportOperationException;
import com.ycz.spring.framework.sample.aoptx.po.Foo;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionOperations;

public class DefaultFooService implements FooService {

    @Inject
    JdbcOperations jdbcOperations;

    @Inject
    //@Named 效果等同 @Qualifier
    @Named("transactionOperations")
    TransactionOperations transactionOperations;

    @Override
    public Foo getFoo(String name) {
        return null;
    }

    @Override
    public Foo getFoo(String name, String barName) {
        return null;
    }

    @Override
    public void updateFoo(Foo foo) {

    }

    @Override
    public void nestingInsertFoo(Foo foo) {
        //这个方法原本有事务 aop 被加入进来之后也没有事务了
        insertFoo(foo);
    }

    @Override
    public void insertFooNesting(Foo foo) {
        //这个方法没有事务
        doSqlInsert("insert into foo (name,barname) values (\"" + foo.getName() + "\",\"" + foo.getBarName() + "\")");
        //套了一个 手动开事务的 并失败
        doCreateFooHasException(foo);
    }

    @Override
    public void insertFooNestingHasException(Foo foo) {
        //没有事务 无异常
        doSqlInsert("insert into foo (name,barname) values (\"" + foo.getName() + "\",\"" + foo.getBarName() + "\")");
        //没有事务 且 有异常
        doSqlInsertHasException("insert into foo (name,barname) values (\"" + foo.getName() + "\",\"" + foo.getBarName() + "\")");
    }

    @Override
    public void nestingInsertFooHasException(Foo foo) {

    }

    @Override
    public void insertFoo(Foo foo) {
        String sql = "insert into foo (name,barname) values (?,?)";
        jdbcOperations.execute(sql, (PreparedStatementCallback<Boolean>) ps -> {
            ps.setString(1, foo.getName());
            ps.setString(2, foo.getBarName());
            return ps.execute();
        });
    }

    @Override
    public void insertFooHasException(Foo foo) {
        String sql = "insert into foo (name,barname) values (?,?)";
        jdbcOperations.execute(sql, (PreparedStatementCallback<Boolean>) ps -> {
            ps.setString(1, foo.getName());
            ps.setString(2, foo.getBarName());
            return ps.execute();
        });
        throw new UnsupportOperationException();
    }

    @Override
    public void doCreateFoo(Foo foo) {
        //方法没有被aop事务概括 自己开启事务操作
        transactionOperations.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                jdbcOperations.execute("insert into foo (name,barname) values (\"" + foo.getName() + "\",\"" + foo.getBarName() + "\")");
            }
        });
    }

    @Override
    public void doCreateFooHasException(Foo foo) {
        //方法没有被aop事务概括 自己开启事务操作
        transactionOperations.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                try {
                    jdbcOperations
                        .execute("insert into foo (name,barname) values (\"" + foo.getName() + "\",\"" + foo.getBarName() + "\")");
                    throw new UnsupportOperationException();
                } catch (Exception e) {
                    //失败后回滚 表示此次事务的结果 只有回滚这一种可能
                    status.setRollbackOnly();
                    //向上抛
                    throw e;
                }
            }
        });
    }

    @Override
    public Foo doCreateFooReturn(Foo foo) {
        Foo created = transactionOperations.execute(status -> {
            //这里相当于事务内 可以进行相应的持久化操作
            //调用了另外一个没有事务管理的方法
            doSqlInsert("insert into foo (name,barname) values (\"" + foo.getName() + "\",\"" + foo.getBarName() + "\")");
            insertFoo(foo);
            return null;
        });
        return created;
    }

    @Override
    public Foo doCreateFooReturnHasException(Foo foo) {
        Foo created = transactionOperations.execute(status -> {
            //这么处理 貌似都在一个事务内了，一个失败都失败，transactionOperations 中有失败回滚的定义
            doSqlInsert("insert into foo (name,barname) values (\"" + foo.getName() + "\",\"" + foo.getBarName() + "\")");
            insertFooHasException(foo);

            return null;
        });
        return created;
    }

    @Override
    public void doSqlInsert(String sql) {
        jdbcOperations.execute(sql);
    }

    @Override
    public void doSqlInsertHasException(String sql) {
        jdbcOperations.execute(sql);
        throw new UnsupportOperationException();
    }
}
