package cn.meng;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterClientTest {
    public static void main(String[] args) throws Exception {
        RegisterClient client = new RegisterClient();
        client.start();
        Thread.sleep(35 *1000);
        client.shutdown();
    }

    @Test
    public void test(){

    }
}
