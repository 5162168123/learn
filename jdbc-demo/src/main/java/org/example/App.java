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

//            Connection connection = DriverManager.getConnection("jdbc:mysql://172.20.10.6:3306/test?autoreconnect=true&characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false", "root", "haikeyi123");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/market?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&serverTimezone=GMT", "root", "haikeyi123");
//            PreparedStatement preparedStatement = connection.prepareStatement("select id, crcrd_crdno, bocgp_inst_no, cur, crcrd_acc_tp_refno, sys_aprv_sn, crcrd_acc_tp_refno, sys_aprv_sn, crcrd_mgt_accno, cusno, crt_dttm,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt, last_upd_dttm,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno, last_mdby_refno, last_upd_no, compl_verf_rslt,id, crcrd_crdno, bocgp_inst_no, cur, crcrd_acc_tp_refno, sys_aprv_sn, crcrd_mgt_accno, cusno, crt_dttm, last_upd_dttm,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno, last_mdby_refno, last_upd_no, compl_verf_rslt,id, crcrd_crdno, bocgp_inst_no, cur, crcrd_acc_tp_refno, sys_aprv_sn, crcrd_mgt_accno, cusno, crt_dttm, last_upd_dttm,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno, last_mdby_refno, last_upd_no, compl_verf_rslt,id, crcrd_crdno, bocgp_inst_no, cur, crcrd_acc_tp_refno, sys_aprv_sn, crcrd_mgt_accno, cusno, crt_dttm, last_upd_dttm,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno, last_mdby_refno,last_upd_no, compl_verf_rslt,id, crcrd_crdno, bocgp_inst_no, cur, crcrd_acc_tp_refno, sys_aprv_sn, crcrd_mgt_accno, cusno, crt_dttm, last_upd_dttm,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno, last_mdby_refno, last_upd_no, compl_verf_rslt,id, crcrd_crdno, bocgp_inst_no, cur, crcrd_acc_tp_refno, sys_aprv_sn, crcrd_mgt_accno, cusno, crt_dttm, last_upd_dttm,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno, last_mdby_refno, last_upd_no, compl_verf_rslt,id, crcrd_crdno, bocgp_inst_no, cur, crcrd_acc_tp_refno, sys_aprv_sn, crcrd_mgt_accno, cusno, crt_dttm, last_upd_dttm,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno, last_mdby_refno, last_upd_no, compl_verf_rslt, last_upd_no, compl_verf_rslt,id, crcrd_crdno, bocgp_inst_no, cur, crcrd_acc_tp_refno, sys_aprv_sn, crcrd_mgt_accno, cusno, crt_dttm, last_upd_dttm,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno, last_mdby_refno, last_upd_no, compl_verf_rslt,id, crcrd_crdno, bocgp_inst_no, cur, crcrd_acc_tp_refno, sys_aprv_sn, crcrd_mgt_accno, cusno, crt_dttm, last_upd_dttm,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno, last_mdby_refno, last_upd_no, compl_verf_rslt,id, crcrd_crdno, bocgp_inst_no, cur, crcrd_acc_tp_refno, sys_aprv_sn, crcrd_mgt_accno, cusno, crt_dttm, last_upd_dttm,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno,last_mdby_refno, last_mdby_refno, last_upd_no,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt,compl_verf_rslt  from t_base_card_acct_union_info_813 where crcrd_crdno = ?");
            PreparedStatement preparedStatement = connection.prepareStatement("select * from activity");
//            PreparedStatement preparedStatement = connection.prepareStatement("select id from test_1 where id = ?");
//            preparedStatement.setString(1, "222222");
//            preparedStatement.setString(2, "5555");
//            preparedStatement.setString(3, "1");
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
                    while(resultSet.next()){
                        System.out.println(resultSet.getString(1));
                    }
                    System.out.println("time cost is : " + (System.currentTimeMillis() - l));
//                    Thread.sleep(1000);

                }
            }

        preparedStatement.close();
        connection.close();
    }
}
