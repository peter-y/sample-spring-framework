package com.geolisa.config;

import java.beans.PropertyVetoException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
@ComponentScan(basePackages = "com.geolisa.service")
public class AppConfig {

    /**
     * 配置 PropertySourcesPlaceholderConfigurer 用来读取 properties 文件.
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        //TODO 写法太傻 得看看有没有别的
        ClassPathResource[] pathResources = new ClassPathResource[]{new ClassPathResource("jdbc.properties"),
            new ClassPathResource("app.properties")};
        propertySourcesPlaceholderConfigurer.setLocations(pathResources);
        return propertySourcesPlaceholderConfigurer;
    }

    //ComboPooledDataSource

    /**
     *  一个假的datasource.
     * @throws PropertyVetoException 异常
     */
    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        return new DataSource() {
            @Override
            public Connection getConnection() throws SQLException {
                return null;
            }

            @Override
            public Connection getConnection(String username, String password) throws SQLException {
                return null;
            }

            @Override
            public <T> T unwrap(Class<T> iface) throws SQLException {
                return null;
            }

            @Override
            public boolean isWrapperFor(Class<?> iface) throws SQLException {
                return false;
            }

            @Override
            public PrintWriter getLogWriter() throws SQLException {
                return null;
            }

            @Override
            public void setLogWriter(PrintWriter out) throws SQLException {

            }

            @Override
            public void setLoginTimeout(int seconds) throws SQLException {

            }

            @Override
            public int getLoginTimeout() throws SQLException {
                return 0;
            }

            @Override
            public Logger getParentLogger() throws SQLFeatureNotSupportedException {
                return null;
            }
        };
    }

    //SqlSessionFactoryBean

    /**
     * 配置 sqlsession factory.
     * @throws PropertyVetoException 异常
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws PropertyVetoException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        return sqlSessionFactoryBean;
    }

    //MapperScannerConfigurer

    /**
     * 配置mybatis映射扫描配置.
     * @return
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer configurer = new MapperScannerConfigurer();
        configurer.setBasePackage("com.geolisa.bean.mapper");
        return configurer;
    }

    //DataSourceTransactionManager

    /**
     * datasource transaction 数据源事务管理.
     * @throws PropertyVetoException 异常
     */
    @Bean
    public DataSourceTransactionManager transactionManager() throws PropertyVetoException {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource());
        return transactionManager;
    }
}
