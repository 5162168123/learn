package cn.meng;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.testfan.dubbo.model.Award;
import cn.testfan.dubbo.model.MarketResult;
import cn.testfan.dubbo.service.MarketService;

public class LotteryTest implements JavaSamplerClient {
	
	ClassPathXmlApplicationContext context;
	MarketService marketService;
	

	@Override
	public Arguments getDefaultParameters() {
		Arguments arguments = new Arguments();
		arguments.addArgument("activityId", "1");
		arguments.addArgument("userId","1");
		return arguments;
	}
	
	@Override
	public void setupTest(JavaSamplerContext arg0) {
		// ���ȼ���applicationContext.xml�����ļ�
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
		// ֱ�ӻ�ȡԶ�̽ӿڵĶ����ɿ���Զ���ɣ�
		marketService = (MarketService) context.getBean("marketService");
		
	}

	@Override
	public SampleResult runTest(JavaSamplerContext jcontext) {
		SampleResult sampleResult = new SampleResult();
		sampleResult.setSampleLabel("lottery");
		try{
			Integer actId = jcontext.getIntParameter("activityId");
			Integer uId = jcontext.getIntParameter("userId");
			
			sampleResult.sampleStart();
			MarketResult<Award> marketResult = marketService.lottery(actId, uId);
//			System.out.println(marketResult);
			if (marketResult.isSuccess()){
				sampleResult.setSuccessful(true);
			}else{
				sampleResult.setSuccessful(false);
			}
		}catch (Exception e){
			e.printStackTrace();
			sampleResult.setSuccessful(false);
		}finally{
			sampleResult.sampleEnd();
		}
		
		return sampleResult;
	}

	

	@Override
	public void teardownTest(JavaSamplerContext arg0) {
		
		if (context != null){
			context.close();
		}
//		System.exit(1);
	}
	
	
	public static void main(String[] args){
		LotteryTest test = new LotteryTest();
		// ����һ����������
		JavaSamplerContext context = new JavaSamplerContext(test.getDefaultParameters());
		// ���ε�����������
		test.setupTest(context);
		test.runTest(context);
		test.teardownTest(context);
	}

}
