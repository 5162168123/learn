import org.junit.Test;

import java.util.function.Consumer;

public class ConsumerTest {
    @Test
    public void test(){
        con(System.out::println,"aa");
    }

    void con(Consumer<String> consumer,String a){
        consumer.accept(a);
    }

}
