package com.ycz.spring.framework.sample.generator;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import com.fasterxml.uuid.UUIDTimer;
import com.fasterxml.uuid.ext.FileBasedTimestampSynchronizer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Test
public class GeneratorTest {

    private Logger logger = LoggerFactory.getLogger(GeneratorTest.class);

    private SnowFlake snowFlake;

    @BeforeMethod
    public void beforeMethod() {
        logger.debug("befor----");
        long datacenterId = 1L;
        long machineId = 10L;
        snowFlake = new SnowFlake(datacenterId, machineId);
    }

    public void testSnowFlake() {
        logger.debug("get next id {}", snowFlake.nextId());
    }

    public void testUUID() throws UnknownHostException, SocketException, IOException {
        EthernetAddress address = new EthernetAddress(NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress());
        UUIDTimer uuidTimer = new UUIDTimer(new Random(), new FileBasedTimestampSynchronizer());
        NoArgGenerator generator = Generators.timeBasedGenerator(address, uuidTimer);
        logger.debug("generator UUID is {}", generator.generate().toString());
    }
}
