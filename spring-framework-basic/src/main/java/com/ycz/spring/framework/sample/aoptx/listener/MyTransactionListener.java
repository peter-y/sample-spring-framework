package com.ycz.spring.framework.sample.aoptx.listener;

import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.event.TransactionalEventListener;

@Named
public class MyTransactionListener {

    private Logger logger = LoggerFactory.getLogger(MyTransactionListener.class);
    //TODO 没有生效
    @TransactionalEventListener
    public void handleAfterCommit() {
        logger.debug("commit after");
    }
}
