package com.ycz.spring.framework.sample.aoptx.service;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

public class FooSqlUpdate extends SqlUpdate {

    public FooSqlUpdate(DataSource ds, String sql) {
        //主要也是看sql语句
        super(ds, sql);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }

    public int execute(long id) {
        return update(id);
    }
}
