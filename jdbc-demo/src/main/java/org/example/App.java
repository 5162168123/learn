package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://223.72.10.95:3366/test?autoreconnect=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false", "root", "Wangzihe@123..");
            PreparedStatement preparedStatement = connection.prepareStatement("select id, crcrd_crdno, bocgp_inst_no, cur, crcrd_acc_tp_refno, sys_aprv_sn, crcrd_mgt_accno, cusno, crt_dttm, last_upd_dttm, last_mdby_refno, last_upd_no, compl_verf_rslt  from t_base_card_acct_union_info_813 where crcrd_crdno = ? AND crcrd_acc_tp_refno = ? AND bocgp_inst_no = ?");
//            PreparedStatement preparedStatement = connection.prepareStatement("select id from test_1 where id = ?");
            preparedStatement.setString(1, "1");
            preparedStatement.setString(2, "2");
            preparedStatement.setString(3, "3");
        Scanner scanner = new Scanner(System.in);
        while (!Thread.interrupted()){
            System.out.println("input your operation");
            String s = scanner.nextLine();
            if(s.equals("exit")){
                    Thread.currentThread().interrupt();
                    break;
                }
                for (int i = 0; i < 10; i++) {
                    long l = System.currentTimeMillis();

                    ResultSet resultSet = preparedStatement.executeQuery();
                    System.out.println("time cost is : " + (System.currentTimeMillis() - l));
                    Thread.sleep(1000);

                }
            }

        preparedStatement.close();
        connection.close();
    }
}
