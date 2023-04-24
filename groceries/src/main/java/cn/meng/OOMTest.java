package cn.meng;

import java.util.LinkedList;

public class OOMTest {


    public static void main(String[] args) {
        String a = "wangshang doushi zai zhizaojiaolv,shijishagn genben mei name yanzhong,wo xiaoxuemei biye,zixuezhuandeqianrushi,0nian jingyan,beijing mianle 11jia ,nale 12 ge offer,zuigao 22K,zuidi 18k,";
        String b ="bushuole,mingtian haiyao lianggemianshi,";
        String c ="handongniannianyou,nianniandoushijisuanji ,jieguo jisuanji gongzi bushi haishi qingqingsongsong nianxin 30wan,xdm,youyishuoyi ,xianzai buxue qianrushi ,yihou jiu meijihui l ,chenzhe xianzai d hongli ,henhen d zhuanyibi.";
        for (int i = 0; i < a.length(); i++) {
            int i1 = (a.charAt(i) - 0);
            System.out.print(Integer.toHexString(i1));
        }
        System.out.println("");
        for (int i = 0; i < b.length(); i++) {
            int i1 = (b.charAt(i) - 0);
            System.out.print(Integer.toHexString(i1));
        }
        System.out.println("");
        for (int i = 0; i < c.length(); i++) {
            int i1 = (c.charAt(i) - 0);
            System.out.print(Integer.toHexString(i1));
        }

    }
}
