package com.jason.Consumer;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

public class ConsumerTest {

    @Test
    public void getMessage() throws IOException, TimeoutException, InterruptedException {
        //原来之前打印不出来是因为网络卡
        Consumer.getMessage();
        Thread.sleep(5000);
    }
}