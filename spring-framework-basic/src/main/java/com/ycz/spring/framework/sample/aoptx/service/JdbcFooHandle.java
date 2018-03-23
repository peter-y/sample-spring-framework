package com.ycz.spring.framework.sample.aoptx.service;

import com.ycz.spring.framework.sample.aoptx.po.Foo;
import java.util.List;

public interface JdbcFooHandle {

    public int getCountAll();

    public int getCountByName(String name);

    public String getNameById(long id);

    public Foo getByIdAndName(long id, String name);

    public List<Foo> findAll();

    public void updateBarNameById(String barname, long id);

    public void batchUpdate(List<Foo> foos);

    public void add(Foo foo);

    public Foo addReturn(Foo foo);
}
