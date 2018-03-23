package com.ycz.spring.framework.sample.aoptx.service;

import com.ycz.spring.framework.sample.aoptx.po.Foo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

public class JdbcTemplateHandle implements JdbcFooHandle {

    private Logger logger = LoggerFactory.getLogger(JdbcTemplateHandle.class);

    @Inject
    private JdbcOperations jdbcOperations;

    @Override
    public int getCountAll() {
        return jdbcOperations.queryForObject("select count(*) from foo where 1=1", Integer.class);
    }

    @Override
    public String getNameById(long id) {
        return jdbcOperations.queryForObject("select name from foo where id = ?", String.class, id);
    }

    @Override
    public Foo getByIdAndName(long id, String name) {
        return jdbcOperations.queryForObject("select name,barname from foo where id = ? and name = ?", new RowMapper<Foo>() {
            @Override
            public Foo mapRow(ResultSet rs, int rowNum) throws SQLException {
                Foo foo = new Foo();
                foo.setName(rs.getString(1));
                foo.setBarName(rs.getString(2));
                return foo;
            }
        }, id, name);
    }

    @Override
    public List<Foo> findAll() {
        return jdbcOperations.query("select name,barname from foo where 1=1", new FooRowMapper());
    }

    @Override
    public void updateBarNameById(String barname, long id) {
        jdbcOperations.update("update foo set barname = ? where id = ?", barname, id);
    }

    @Override
    public void batchUpdate(List<Foo> foos) {
        String sql = "udpate foo set barname = ? where id = ?";
        jdbcOperations.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(i,foos.get(i).getBarName());
                ps.setLong(i,foos.get(i).getId());
            }

            @Override
            public int getBatchSize() {
                return foos.size();
            }
        });
    }

    @Override
    public void add(Foo foo) {

    }

    @Override
    public Foo addReturn(Foo foo) {
        return null;
    }

    @Override
    public int getCountByName(String name) {
        return jdbcOperations.queryForObject("select count(*) from foo where name = ?", Integer.class, name);
    }

    private static final class FooRowMapper implements RowMapper<Foo> {

        @Override
        public Foo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Foo foo = new Foo();
            foo.setName(rs.getString(1));
            foo.setBarName(rs.getString(2));
            return foo;
        }
    }

}
