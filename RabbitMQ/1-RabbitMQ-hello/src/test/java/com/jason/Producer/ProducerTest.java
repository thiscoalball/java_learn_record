package com.jason.Producer;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

public class ProducerTest {

    @Test
    public void createFactory() throws IOException, TimeoutException {
        Producer.sendMessage("helloWorld");
    }

    @Test
    public void testWorker() throws IOException, TimeoutException, InterruptedException {
        Producer.sendMessage("你在处理第" + 1 + "条消息");
        Producer.sendMessage("你在处理第" + 2 + "条消息");
        Producer.sendMessage("你在处理第" + 3 + "条消息");
        Producer.sendMessage("你在处理第" + 4 + "条消息");
        Producer.sendMessage("你在处理第" + 5 + "条消息");
    }
}