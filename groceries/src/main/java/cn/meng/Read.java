package cn.meng;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Read {
    public static ArrayList<String> readToken(){
        ArrayList<String> a = new ArrayList<>(125);
        BufferedReader reader;
        String s = null;
        try{
            reader = new BufferedReader(new FileReader("D:\\桌面\\123.txt"));
            while((s = reader.readLine())!=null){
                a.add(s);
            }


        }catch (Exception e){

        }
        finally {
            return a;
        }
    }

    public static void main(String[] args) {

            ArrayList<String> s = Read.readToken();
            System.out.println(s.toString());

    }
}
