package com.ycz.spring.framework.sample.aoptx.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//spring 扫描到 @Aspect注解时，会从自动代理中排除??
@Aspect
//据说@Aspect不足够支撑spring classpath的自动扫描，所以添加@Component注解
@Component
public class NotVeryUsefulAspect {

    @Pointcut("execution(* transfer(..))")// the pointcut expression
    private void anyOldTransfer(){
        //方法返回必须是void
    }
}
