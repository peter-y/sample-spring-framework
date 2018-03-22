package com.ycz.spring.framework.sample.service;

import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.transaction.annotation.Transactional;

//效果等同@Component
@Named
//类上写 所有方法不声明也会带上事务
//@Transactional
public class JdbcTestUserService {

    //效果等同@Autowired
    @Inject
    JdbcOperations jdbcOperations;

    @Transactional
    public void insertUser(String sql, boolean happenError) {
        jdbcOperations.execute(sql);
        if (happenError) {
            throw new RuntimeException("发生错误 需要回滚");
        }
    }

    /**
     * 没有事务管理，被调用了 假如datasource 没有开启自动commit 则数据就提交不到数据库中
     */
    public void noTxDefInsertUser(String sql) {
        jdbcOperations.execute(sql);
    }
}
