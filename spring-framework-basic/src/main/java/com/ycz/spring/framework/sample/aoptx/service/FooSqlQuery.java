package com.ycz.spring.framework.sample.aoptx.service;

import com.ycz.spring.framework.sample.aoptx.po.Foo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

public class FooSqlQuery extends MappingSqlQuery<Foo> {

    // 这直接能声明成一个query 抽象成一个对象 执行某个方法
    public FooSqlQuery(DataSource ds, String sql) {
        super(ds, sql);
        //这儿是声明参数 比如 select * from foo where id = ?
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
        //准备完成
        compile();
    }

    @Override
    protected Foo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Foo foo = new Foo();
        foo.setId(rs.getLong("id"));
        foo.setName(rs.getString("name"));
        foo.setBarName(rs.getString("barname"));
        return foo;
    }
}
