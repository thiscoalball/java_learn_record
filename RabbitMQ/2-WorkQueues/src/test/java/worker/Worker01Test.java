package worker;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.*;

public class Worker01Test {

    @org.junit.Test
    public void getMessage() throws IOException, TimeoutException, InterruptedException {
        Worker01.getMessage();
    }

}