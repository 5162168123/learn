package groceries;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestCast {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.56.200:3306/ssm?characterEncoding=UTF-8","root","123456");
        PreparedStatement preparedStatement = conn.prepareStatement("select * from goods where id = ?");
        preparedStatement.setString(1,"1001");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(1);
            Object object = resultSet.getObject(1);
            System.out.println(resultSet.getString(1));
            System.out.println(object instanceof Integer? true:false);
            System.out.println(object.getClass().getName());
        }
        resultSet.close();
        preparedStatement.close();
        conn.close();
    }
}

class Student{
    Integer id;
    String name;
    Integer amount;
    Float price;
}
