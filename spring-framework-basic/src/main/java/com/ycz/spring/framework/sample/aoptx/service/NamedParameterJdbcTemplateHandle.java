package com.ycz.spring.framework.sample.aoptx.service;

import com.ycz.spring.framework.sample.aoptx.po.Foo;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

public class NamedParameterJdbcTemplateHandle implements JdbcFooHandle {

    private Logger logger = LoggerFactory.getLogger(NamedParameterJdbcTemplateHandle.class);

    //里面包装了一个 jdbcOperations
    @Inject
    private NamedParameterJdbcOperations namedParameterJdbcTemplate;

    @Override
    public int getCountAll() {
        String sql = "select count(*) from foo where 1=1";
        Integer i = namedParameterJdbcTemplate.queryForObject(sql, Collections.emptyMap(), Integer.class);
        return i == null ? 0 : i;
    }

    @Override
    public int getCountByName(String name) {
        String sql = "select count(*) from foo where name=:name";
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("name", name);
        Integer i = namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource, Integer.class);
        return i == null ? 0 : i;
    }

    @Override
    public String getNameById(long id) {
        String sql = "select name from foo where id=:id";
        Map<String, Long> params = Collections.singletonMap("id", id);
        return namedParameterJdbcTemplate.queryForObject(sql, params, String.class);
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
        String sql = "update foo set barname = :barname where id = :id";
        namedParameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(foos.toArray()));
    }

    @Override
    public void add(Foo foo) {

    }

    @Override
    public Foo addReturn(Foo foo) {
        return null;
    }
}
