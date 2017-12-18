package com.sample.jms.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleCheckingAccountService implements CheckingAccountService {

    private final Logger logger = LoggerFactory.getLogger(SimpleCheckingAccountService.class);

    @Override
    public void cancelAccount(Long accountId) {
        logger.debug("cancelling account id [{}]", accountId);
    }
}
