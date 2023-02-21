package cn.meng;

import static org.junit.Assert.assertTrue;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public static void main(String[] args) {
        String a = "/1";
        System.out.println(a.split("/").length);
        List aa = new LinkedList();
        System.out.println(aa.isEmpty());
    }
}
