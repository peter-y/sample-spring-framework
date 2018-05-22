package com.ycz.basic;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Test
public class IfElseIfTest {

    private static final Logger logger = LoggerFactory.getLogger(IfElseIfTest.class);

    public void testif() {
        String code = "1";
        Long id = 0L;
        if (!code.equals("")) {
            logger.debug("code 不是空");
        } else if (!id.equals(0)) {
            logger.debug("id 不是 0 ");
        }
    }

    public void testbj() {
        logger.debug("result {}", false || false);
        logger.debug("result {}", true || false);
        logger.debug("result {}", false || true);
        logger.debug("result {}", true || true);
    }

    public void testDateFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY年MM月DD日 hh点mm分");
        logger.debug(simpleDateFormat.format(new Date()));
    }
}
