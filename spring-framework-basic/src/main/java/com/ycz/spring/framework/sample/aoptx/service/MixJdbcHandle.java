package com.ycz.spring.framework.sample.aoptx.service;

import com.ycz.spring.framework.sample.aoptx.po.Foo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class MixJdbcHandle implements JdbcFooHandle {

    private Logger logger = LoggerFactory.getLogger(MixJdbcHandle.class);

    @Inject
    SimpleJdbcInsert simpleJdbcInsert;

    @Inject
    SimpleJdbcCall simpleJdbcCall; //这玩意儿能调用存储过程 函数啊 啥的

    public MixJdbcHandle() {
        simpleJdbcInsert.withTableName("foo");
    }

    @Override
    public int getCountAll() {
        return 0;
    }

    @Override
    public int getCountByName(String name) {
        return 0;
    }

    @Override
    public String getNameById(long id) {
        return null;
    }

    @Override
    public Foo getByIdAndName(long id, String name) {
        return null;
    }

    @Override
    public List<Foo> findAll() {
        return null;
    }

    @Override
    public void updateBarNameById(String barname, long id) {

    }

    @Override
    public void batchUpdate(List<Foo> foos) {

    }

    @Override
    public void add(Foo foo) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("name", foo.getName());
        params.put("barname", foo.getBarName());
        //执行之前 设置了tablename
        simpleJdbcInsert.execute(params);

        //这样也是可以的
        SqlParameterSource source = new BeanPropertySqlParameterSource(foo);
        simpleJdbcInsert.executeAndReturnKey(source);

        //也是可以的
        source = new MapSqlParameterSource().addValue("name", foo.getName()).addValue("barname", foo.getBarName());
        simpleJdbcInsert.executeAndReturnKey(source);
    }

    @Override
    public Foo addReturn(Foo foo) {
        Map<String, Object> params = new HashMap<String, Object>(2);
        params.put("name", foo.getName());
        params.put("barname", foo.getBarName());
        //能设置生成key的名字
        simpleJdbcInsert.setGeneratedKeyName("id");
        Number idkey = simpleJdbcInsert.executeAndReturnKey(params);
        foo.setId(idkey.longValue());
        return foo;
    }
}
