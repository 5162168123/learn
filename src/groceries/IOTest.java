package groceries;

import com.sun.org.apache.bcel.internal.generic.FSUB;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.*;

import java.util.concurrent.*;

public class IOTest implements Callable<String> {
    String a;
    public IOTest(String a){
        this.a = a;
    }
    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        FileReader fileReader = new FileReader("123.txt");
        FileWriter fileWriter = new FileWriter("123.txt",true);
        try(BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            ){
            bufferedWriter.write("123");
        }


    }



    @Override
    public String call() throws Exception {
        return a;
    }
}

class Utils{
    public static String getName(){

        return Thread.currentThread().getName();
    }
}
