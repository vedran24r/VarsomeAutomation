package org.saphetor.Base;

public class BaseTest {

    protected static void pause(long millis, String message) throws InterruptedException {
        System.out.println(message);
        Thread.sleep(millis);
    }

    protected static void pause() throws InterruptedException {
        System.out.println("Test Pause 1 second");
        Thread.sleep(1000);
    }

}
