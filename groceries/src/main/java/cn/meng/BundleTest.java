package cn.meng;

import javax.annotation.Resource;
import java.util.ResourceBundle;

public class BundleTest {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("test");
        System.out.println(bundle.getString("123"));
    }
}
