package cn.meng;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterClientTest {
    public static void main(String[] args) throws Exception {
        RegisterClient client = new RegisterClient();
        client.start();
        Thread.sleep(35 *1000);
        client.shutdown();
    }
}
