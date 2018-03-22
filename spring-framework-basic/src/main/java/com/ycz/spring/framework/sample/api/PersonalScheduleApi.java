package com.ycz.spring.framework.sample.api;

import java.util.Date;

/**
 * 瞬时日程 4w1h.
 */
public interface PersonalScheduleApi {

    /**
     * 消息来源.
     */
    public String getWho();

    /**
     * 事件时间.
     */
    public Date getWhen();

    /**
     * 什么事情.
     */
    public String getWhat();

    /**
     * 原因.
     */
    public String getWhy();

    /**
     * 处理过程.
     */
    public String getHow();
}
