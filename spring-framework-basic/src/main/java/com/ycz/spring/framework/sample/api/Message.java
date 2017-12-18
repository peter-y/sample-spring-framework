package com.ycz.spring.framework.sample.api;

import java.sql.Timestamp;

public interface Message {

    String getMessageHeader();

    String getMessageBody();

    String getFrom();

    String getTo();


}
