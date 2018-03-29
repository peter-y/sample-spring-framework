package com.ycz.spring.framework.sample.orm.hibernate.service;

import com.ycz.spring.framework.sample.aoptx.po.Foo;
import java.util.List;
import javax.inject.Inject;
import org.hibernate.SessionFactory;

public class HibernateJpaFooService {

    @Inject
    SessionFactory sessionFactory;


    public Foo getFoo(String name) {
        List<Foo> result = sessionFactory.getCurrentSession().createQuery("from Foo foo where foo.name = ?", Foo.class)
            .setParameter(0, name).list();
        return result.size() > 0 ? result.get(0) : null;
    }


    public Foo getFoo(String name, String barName) {
        return null;
    }


    public void updateFoo(Foo foo) {

    }


    public void nestingInsertFoo(Foo foo) {

    }


    public void nestingInsertFooHasException(Foo foo) {

    }


    public void insertFooNesting(Foo foo) {

    }


    public void insertFooNestingHasException(Foo foo) {

    }


    public void insertFoo(Foo foo) {

    }


    public void insertFooHasException(Foo foo) {

    }


    public void doCreateFoo(Foo foo) {

    }


    public void doCreateFooHasException(Foo foo) {

    }


    public Foo doCreateFooReturn(Foo foo) {
        return null;
    }


    public Foo doCreateFooReturnHasException(Foo foo) {
        return null;
    }


    public void doSqlInsert(String sql) {

    }


    public void doSqlInsertHasException(String sql) {

    }
}
