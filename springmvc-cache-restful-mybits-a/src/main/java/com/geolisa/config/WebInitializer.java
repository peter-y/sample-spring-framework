package com.geolisa.config;

import javax.servlet.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //spring 结构是区分的，也可以不分，那就是在rootConfig中返回全部。
    //区分为两个 WebApplicationContext
    //父类AbstractDispatcherServletInitializer 是核心
    private final Logger logger = LoggerFactory.getLogger(WebInitializer.class);

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // service bean \ data bean
        if (logger.isDebugEnabled()) {
            logger.debug("add appConfig");
        }
        return new Class<?>[]{AppConfig.class};
    }

    //dispatchservlet 委托特殊bean 处理 请求和 给予相应
    //HandlerMapping\HandlerAdapter\HandlerExceptionResolver\ViewResolver\LocaleResolver, LocaleContextResolver
    //ThemeResolver\MultipartResolver\FlashMapManager
    //dispatchServlet会先检查webApplicationContext下有没有特殊类型的bean
    @Override
    protected Class<?>[] getServletConfigClasses() {
        //ViewResolver \ controller \ handlerMapping
        if (logger.isDebugEnabled()) {
            logger.debug("add webConfig");
        }
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        if (logger.isDebugEnabled()) {
            logger.debug("add servletMappings");
        }
        return new String[]{"/"};
    }

    //可以通过重写 来添加servlet的行为
    @Override
    protected Filter[] getServletFilters() {
        return super.getServletFilters();
    }

    //dispatchServlet 异步支持
    @Override
    protected boolean isAsyncSupported() {
        return super.isAsyncSupported();
    }

    //需要自定义dispatcherServlet的时候，可操作这个方法
    @Override
    protected FrameworkServlet createDispatcherServlet(WebApplicationContext servletAppContext) {
        return super.createDispatcherServlet(servletAppContext);
    }
}
