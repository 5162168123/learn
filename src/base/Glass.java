package base;

import java.lang.management.GarbageCollectorMXBean;

public class Glass {
    private long capacity = 1L;

    public Glass(){

    }
    public Glass(long capacity){
        this.capacity = capacity;
    }

    public static void main(String[] args) {
        Glass glass = new Glass();
        Glass glass2 = new Glass(2L);
        System.out.println(glass.getCapacity());
        glass.setCapacity(2L);
        System.out.println(glass.getCapacity());
    }

    public    long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }


}
