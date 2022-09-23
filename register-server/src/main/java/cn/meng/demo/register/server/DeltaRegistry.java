package cn.meng.demo.register.server;


import java.util.Queue;

import cn.meng.demo.register.server.ServiceRegistry.RecentlyChangedServiceInstance;
import lombok.Data;

/**
 * 增量注册表
 * @author zhonghuashishan
 *
 */

@Data
public class DeltaRegistry {

	private Queue<RecentlyChangedServiceInstance> recentlyChangedQueue;
	private Long serviceInstanceTotalCount;
	
	public DeltaRegistry(Queue<RecentlyChangedServiceInstance> recentlyChangedQueue,
						 Long serviceInstanceTotalCount) {
		this.recentlyChangedQueue = recentlyChangedQueue;
		this.serviceInstanceTotalCount = serviceInstanceTotalCount;
	}

	
}
