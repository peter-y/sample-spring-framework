package com.ycz.spring.framework.sample.jdbctx;

import com.google.common.base.MoreObjects;
import com.ycz.spring.framework.sample.service.JdbcTestUserService;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
@Configuration
@ContextConfiguration(locations = "classpath:spring/spring-tx.xml")
public class JdbcTxTest extends AbstractTestNGSpringContextTests {

    private Logger logger = LoggerFactory.getLogger(JdbcTxTest.class);

    @Inject
    PlatformTransactionManager transactionManager;

    @Inject
    DataSource dataSource;

    @Inject
    JdbcOperations jdbcOperations;

    //是比jdbc 高级的方法 适合多个参数配置调用
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //更高级 元数据处理基于JDBC的DatabaseMetaData 啥的 貌似给个表名 和 map 参数映射就能行
    SimpleJdbcInsert simpleJdbcInsert;

    SimpleJdbcCall simpleJdbcCall;

    @Autowired
    JdbcTestUserService jdbcTestUserService;

    public void testGet() {
        logger.debug("to do jdbc operation");
        List<TestUser> user = jdbcOperations.query("select * from user", (rs, rowNum) -> {
            TestUser user1 = new TestUser();
            user1.setId(rs.getLong(1));
            user1.setEmail(rs.getString(2));
            user1.setNick_name(rs.getString(3));
            user1.setPassword(rs.getString(4));
            user1.setRegister_time(rs.getString(5));
            user1.setUsername(rs.getString(6));
            return user1;
        });
        Assert.assertTrue(user.size() == 5, "取到的数据不一致");
    }

    public void testSave() throws SQLException {
        logger.debug("to do save test user");
        //想让数据插入 需要事务管理
        jdbcOperations.execute(
            "INSERT INTO test_user (id,email,nick_name,password,register_time,username) VALUES (1,'zhang@gg.com','xiaomi','123','2018-03-22','zhang_gg')");

    }

    public void testInsert() {
        logger.debug("todo testInsert");
        //数据会进入库
        jdbcTestUserService.insertUser(
            "INSERT INTO test_user (id,email,nick_name,password,register_time,username) VALUES (2,'zhang@gg.com','xiaomi','123','2018-03-22','zhang_gg')",
            false);
    }

    public void testInsertHappendError() {
        logger.debug("todo testInsertHappendError");
        //有异常 回滚
        //control + alt + t 增加判断啊 try catch之类的代码
        try {
            jdbcTestUserService.insertUser(
                "INSERT INTO test_user (id,email,nick_name,password,register_time,username) VALUES (5,'zhang@gg.com','xiaomi','123','2018-03-22','zhang_gg')",
                true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //没有自动配置事务，手动的操作事务 数据库的连接和关闭交给了jdbcTemplate
    public void testNoTxInsert() {
        logger.debug("todo testNoTxInsert");
        //0是默认的 这里面也可以定义事务行为
        DefaultTransactionDefinition t1 = new DefaultTransactionDefinition();
        t1.setName("tx1");
        t1.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        t1.setIsolationLevel(TransactionDefinition.ISOLATION_DEFAULT);
        TransactionStatus status = transactionManager.getTransaction(t1);
        try {
            jdbcTestUserService.noTxDefInsertUser(
                "INSERT INTO test_user (id,email,nick_name,password,register_time,username) VALUES (4,'zhang@gg.com','xiaomi','123','2018-03-22','zhang_gg')");
        } catch (Exception e) {
            e.printStackTrace();
            transactionManager.rollback(status);
        }
        transactionManager.commit(status);
    }

    class TestUser {

        private long id;
        private String email;
        private String nick_name;
        private String password;
        private String register_time;
        private String username;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getNick_name() {
            return nick_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getRegister_time() {
            return register_time;
        }

        public void setRegister_time(String register_time) {
            this.register_time = register_time;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("email", email)
                .add("nick_name", nick_name)
                .add("password", password)
                .add("register_time", register_time)
                .add("username", username)
                .toString();
        }
    }
}
