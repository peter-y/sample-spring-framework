package com.ycz.basic;

import org.testng.annotations.Test;

@Test
public class UrlTest {

    public void testUrlSubstring() {
        String url = "../record/2017-06-06/jyo2o/330-1496810391/1496724439_9.ts";
        url = url.substring(3, url.length());
        System.out.println(url);
    }
}
