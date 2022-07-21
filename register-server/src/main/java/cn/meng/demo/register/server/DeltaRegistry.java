package cn.meng.demo.register.server;

import java.util.LinkedList;

import cn.meng.demo.register.server.ServiceRegistry.RecentlyChangedServiceInstance;
import lombok.Data;

/**
 * 增量注册表
 * @author zhonghuashishan
 *
 */

@Data
public class DeltaRegistry {

	private LinkedList<RecentlyChangedServiceInstance> recentlyChangedQueue;
	private Long serviceInstanceTotalCount;
	
	public DeltaRegistry(LinkedList<RecentlyChangedServiceInstance> recentlyChangedQueue,
			Long serviceInstanceTotalCount) {
		this.recentlyChangedQueue = recentlyChangedQueue;
		this.serviceInstanceTotalCount = serviceInstanceTotalCount;
	}

	
}
