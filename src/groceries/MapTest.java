package groceries;

import java.util.HashMap;
import java.util.Map;

public class MapTest {
    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        map.put("123","abc");
        System.out.println(map.toString());
    }
}
