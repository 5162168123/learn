package cn.meng;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.testfan.dubbo.model.Award;
import cn.testfan.dubbo.model.ExchangeRequest;
import cn.testfan.dubbo.model.MarketResult;
import cn.testfan.dubbo.service.MarketService;

public class ExchangeTest {

	/**
	 * dubbo脚本逻辑步骤：
	 * 1、加载xml配置文件，dubbo框架会自动链接zookeeper
	 * 2、获取到远程项目的dubbo接口对象
	 * 3、直接调用远程对象的函数
	 * 4、对函数返回值做判断
	 * @param args
	 */
	public static void main(String[] args) {
		// 通过框架提供的类，来加载配置文件，默认情况下从src根目录下找配置文件
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 通过框架，获取到远程market项目里的对象
		MarketService marketService = (MarketService) context.getBean("marketService");
		// 拿到对象后，直接调用需要测试的函数
		// 调用exchange之前，需要先构造一个请求对象
		ExchangeRequest request = new ExchangeRequest();
		request.setUserId(1);
		request.setActivityId(2);
		request.setAwardId(4);
		request.setExchangeNum(1);
		MarketResult<Award>  result = marketService.exchange(request);
		// 对兑换接口返回的数据做判断
		if (result.isSuccess()){
			System.out.println("调用成功");
			System.out.println("code: "+result.getCode());
			System.out.println("message: "+result.getMessage());
			System.out.println("奖品: "+result.getData());
		}else{
			System.out.println("调用失败");
			System.out.println("code: "+result.getCode());
			System.out.println("message: "+result.getMessage());
		}

	}

}
