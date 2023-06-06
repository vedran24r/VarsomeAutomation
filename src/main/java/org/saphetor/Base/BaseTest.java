package org.saphetor.Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {

    Properties properties = new Properties();

    protected String getProperties(String env, String key) {
        InputStream input;
        try {
            input = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/" + env + ".properties");
            properties.load(input);
            return properties.getProperty(key);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected static void pause(long millis, String message) {
        System.out.println(message);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    protected static void pause() {
        System.out.println("Test Pause 1 second");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

}
