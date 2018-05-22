package com.ycz.basic;

import com.ycz.entity.ProState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Test
public class EnumTest {

    private static final Logger logger = LoggerFactory.getLogger(EnumTest.class);

    public void testPrintEnum() {
        //AWAITING(0), PASS(1), REFUSAL(2)
        ProState state = ProState.valueOf("AWAITING");
        logger.debug("state {} value {}", state, state.getValue());
        state = ProState.valueOf("REFUSAL");
        logger.debug("state {} value {}", state, state.getValue());
    }

}
