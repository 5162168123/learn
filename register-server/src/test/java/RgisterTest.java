import org.junit.Test;

import java.util.concurrent.LinkedBlockingQueue;

public class RgisterTest {
    @Test
    public void test1() throws InterruptedException {
        LinkedBlockingQueue<String> a = new LinkedBlockingQueue<>();
        String take = a.take();

    }
}
