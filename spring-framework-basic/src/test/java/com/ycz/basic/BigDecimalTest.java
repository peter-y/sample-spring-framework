package com.ycz.basic;

import java.math.BigDecimal;
import java.math.MathContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Test
public class BigDecimalTest {

    private static final Logger logger = LoggerFactory.getLogger(BigDecimalTest.class);

    /**
     * 乘法.
     */
    public void testCF() {
        BigDecimal a = BigDecimal.ZERO;
        BigDecimal result = null;
        result = a.multiply(new BigDecimal(0.15)).setScale(2,BigDecimal.ROUND_HALF_UP);
        logger.debug("结果 {}", result.toString());
        a = new BigDecimal("12.99");
        result = a.multiply(new BigDecimal(0.15)).setScale(2,BigDecimal.ROUND_HALF_UP);
        logger.debug("结果 {}", result.toString());
    }
}
