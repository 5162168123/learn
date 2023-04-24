package cn.meng;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Recursion {
    Integer result;
    LinkedList<Integer> compose = new LinkedList<>();
    public static void main(String[] args) {
        calculate(8,null);
        int a = 3/2;
        System.out.println(a);

    }


    public static void calculate(int result ,LinkedList<Integer> element){
        if(element == null ){
            for (int i = 1; i <= result; i++) {
//                System.out.println(i);
                element = new LinkedList<>();
                element.add(i);
//                System.out.println("first: " + element);
                calculate(result,element);
            }
        }else{
            int temp =1;
            for (Integer integer : element) {
                temp *= integer;
            }
            if(temp > result){
//                System.out.println(temp);
            }
            else if(temp == result){
                System.out.println(element);

            }
            else{
                for (int i = 1; i <= result; i++) {
                    LinkedList<Integer> clone = (LinkedList<Integer>)element.clone();
                    if(i == 1){
                        if(element.contains(1)){
                            continue;
                        }
                        clone.add(i);
//                        System.out.println("second: " + clone);
                        calculate(result,clone);
                    }else{
                        clone.add(i);
//                        System.out.println("third: " + clone);
                        calculate(result,clone);
                    }

                }
            }
        }

    }
}
