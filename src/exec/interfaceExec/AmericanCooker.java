package exec.interfaceExec;

public class AmericanCooker implements FoodMenu{
    @Override
    public void shiZiChaoJiDan() {
        System.out.println("西餐师傅做的西红柿炒鸡蛋");
    }

    @Override
    public void yuXiangRouSi() {
        System.out.println("西餐师傅做的鱼香肉丝");
    }
}
