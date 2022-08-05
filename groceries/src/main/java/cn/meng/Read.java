package cn.meng;

import java.io.*;

public class Read{
    public static void readMeasurement() throws Exception{
        BufferedReader reader;
        BufferedWriter writer;
        String s = null;
        StringBuffer sb = new StringBuffer();
        int count = 0;
        int suffix = 1;
        reader = new BufferedReader(new FileReader("D:\\桌面\\123.txt"));

        while((s = reader.readLine())!=null){
            sb.append(s+"\r");

            if((++count%50) == 0 ){

                writer = new BufferedWriter(new FileWriter("D:\\桌面\\123_"+suffix+".txt"));
                writer.write(sb.toString());
                writer.flush();
                writer.close();
                suffix++;
                sb.delete(0,sb.length());
            }
            System.out.println(count%50);

        }
        reader.close();
    }

    public void write() throws Exception {
        BufferedWriter bufferedWriter;
        for (int i = 1; i <= 639; i++) {
            bufferedWriter = new BufferedWriter(new FileWriter("influx"+i+".sh"));
            String s = "#!/bin/bash\n" +
                    "measurements=$(cat measurements_"+i+".txt) \n" +
                    "for measurement in ${measurements[*]}\n" +
                    "do\n" +
                    "\n" +
                    "a=\"select time from  ${measurement}\"\n" +
                    "echo ${measurement}\n" +
                    "influx --database jmeter --execute 'drop measurement \"'${measurement}'\"'\n" +
                    "done";
            bufferedWriter.write(s);
            bufferedWriter.flush();
        }

    }
    public static void main(String[] args) throws Exception {
        Read.readMeasurement();
    }

}