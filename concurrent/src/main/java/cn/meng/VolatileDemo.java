package cn.meng;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {

	int flag = 0;
	
	public static void main(String[] args) {

		VolatileDemo demo = new VolatileDemo();

		new Thread() {
			
			public void run() {
				int localFlag = demo.flag;
				while(true) {
					if(localFlag != demo.flag) {
						System.out.println("读取到了修改后的标志位：" + demo.flag);
						localFlag = demo.flag;
					}
				}  
			};
			
		}.start();
		
		new Thread() {
			
			public void run() {
				int localFlag = demo.flag;
				while(true) { 
					System.out.println("标志位被修改为了：" + ++localFlag);
					demo.flag = localFlag;
					try {
						TimeUnit.SECONDS.sleep(2); 
					} catch (Exception e) {
						e.printStackTrace(); 
					}
				}
			};
			
		}.start();
	}
	
}
