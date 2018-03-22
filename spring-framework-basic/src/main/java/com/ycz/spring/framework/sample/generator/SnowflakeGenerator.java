package com.ycz.spring.framework.sample.generator;

import java.util.HashSet;
import java.util.Set;

public class SnowflakeGenerator {

    //时间戳二进制位数
    //private final static int TIMESTAMP_BITS = 41;
    //主机号二进制位数
    private final static int SERVER_NODE_BITS = 10;
    //序列号二进制位数
    private final static int SEQUENCE_BITS = 12;


    //主机号：0~2**10-1
    private final static int MAX_SERVER_NODE = 1024;
    //序列号：0～2**12-1
    private final static int MAX_SEQUENCE = 4096;

    //当前主机节点[0,1024)
    private static int serverNode = 0;

    //序列号
    private static int sequence = 0;

    //上次生成id的时间
    private static long previousTimestamp = -1L;

    /**
     * 生成唯一主键
     *
     * @return 64位long类型主键
     */
    public static synchronized long generate(int node) {
        //初始化Snowflake主键生成器
        if (node < 0 || node >= MAX_SERVER_NODE) {
            throw new IllegalArgumentException(String.format("主机节点号范围为[%s and %s)", 0, MAX_SERVER_NODE));
        }
        serverNode = node;

        long timestamp;
        timestamp = System.currentTimeMillis();
        if (timestamp > previousTimestamp) {
            sequence = 0;
            previousTimestamp = timestamp;
        } else if (timestamp == previousTimestamp) {
            if (++sequence == MAX_SEQUENCE) {
                //序列号已到最大值，重置为0，此毫秒内不能再生成id,只能等下一毫秒
                sequence = 0;
                timestamp = nextMillisecond();
                previousTimestamp = timestamp;
            }
        } else {
            throw new RuntimeException(String.format("当前时间戳 %s 小于 上次时间戳 %s", timestamp, previousTimestamp));
        }
        return timestamp << SERVER_NODE_BITS << SEQUENCE_BITS | serverNode << SEQUENCE_BITS | sequence;
        //Java中的整型在计算机中是以二进制补码的形式存储的，最后这里需要了解补码及位运算的概念，位运算速度比十进制计算快很多
        //[参考教程] http://www.cnblogs.com/zhangziqiu/archive/2011/03/30/ComputerCode.html
    }

    /**
     * 等待，直到下一毫秒
     *
     * @return nextMillisecond
     */
    private static long nextMillisecond() {
        long nextMillisecond = System.currentTimeMillis();
        while (nextMillisecond <= previousTimestamp) {
            nextMillisecond = System.currentTimeMillis();
        }
        return nextMillisecond;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        Set ids = new HashSet();
        for (int i = 0; i < 1000000; i++) {
            long l = SnowflakeGenerator.generate(1023);
            ids.add(l);
            if (i == 0 || i == 1000000 - 1) {
                System.out.println(l);
            }
        }
        long end = System.currentTimeMillis();
        long diff = end - start;
        System.out.println("用时：" + diff + "毫秒");
        System.out.println("生成数量 " + ids.size());
    }
}
