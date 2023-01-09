package cn.meng;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {

	int flag = 0;

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public static void main(String[] args) {

		VolatileDemo demo = new VolatileDemo();

		new Thread() {
			
			public void run() {
				int localFlag = demo.getFlag();
				while(true) {
					if(localFlag != demo.getFlag()) {
						System.out.println("读取到了修改后的标志位：" + demo.getFlag());
						localFlag = demo.getFlag();
					}
				}  
			};
			
		}.start();
		
		new Thread() {
			
			public void run() {
				int localFlag = demo.getFlag();
				while(true) { 
					System.out.println("标志位被修改为了：" + ++localFlag);
					demo.setFlag(localFlag);
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
