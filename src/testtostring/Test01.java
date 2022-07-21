package testtostring;

public class Test01 {
    public static void main(String[] args) {
        MyTime myTime = new MyTime(1970,1,1);
        System.out.println(myTime.toString());
    }

}

class MyTime{
    int year;
    int month;
    int day;
    public MyTime(){

    }

    public MyTime(int year,int month,int day){
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyTime{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}
