package groceries;

import java.io.*;

public class PDFTest {
    public static void main(String[] args)  {


        try(
                FileInputStream inputStream = new FileInputStream(new File("C:\\Users\\123\\Desktop\\大唐代驾内部培训资料.pdf"));
                OutputStream outputStream = new FileOutputStream(new File("C:\\Users\\123\\Desktop\\大唐代驾内部培训资料1.pdf"))
                ){
            byte[] bytes = new byte[1024];

            int read = inputStream.read(bytes);
            while (read > -1){
                outputStream.write(bytes);
                read = inputStream.read(bytes);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
