package com.ycz.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

@Test
public class BitOperationTest {

    private Logger logger = LoggerFactory.getLogger(BitOperationTest.class);

    public void testLeft() {
        //左减位 右补0
        int w = 1;
        for (int i = 0; i <= 100; i++) {
            int r = i << w;
            printParam(i, r);
            printLeftResult(w, r);
        }
    }

    public void testRight() {
        //左补0 右减位
        int b = 4;
        int i = 1;
        int d = i >> b;
        printParam(i, d);
        printRightResult(b, d);

        b = 1;
        i = 10;
        d = i >> b;
        printParam(i, d);
        printRightResult(b, d);
    }

    private void printParam(int base, int result) {
        logger.debug("------------------------------------------");
        logger.debug("原数 二进制 {}", Integer.toBinaryString(base));
        logger.debug("结果 二进制 {}", Integer.toBinaryString(result));
    }

    private void printLeftResult(int wei, int result) {
        logger.debug("左移 {} 结果 {}", wei, result);
    }

    private void printRightResult(int wei, int result) {
        logger.debug("右移 {} 结果 {}", wei, result);
    }
}
