package cn.meng.demo.register.server.core;


import java.util.Queue;

import cn.meng.demo.register.server.core.ServiceRegistry.RecentlyChangedServiceInstance;
import lombok.Data;

/**
 * 增量注册表
 * @author zhonghuashishan
 *
 */

@Data
public class DeltaRegistry {

	/**
	 * 最近变更列队
	 */
	private Queue<RecentlyChangedServiceInstance> recentlyChangedQueue;
	/**
	 * 当前服务总数
	 */
	private Long serviceInstanceTotalCount;
	
	public DeltaRegistry(Queue<RecentlyChangedServiceInstance> recentlyChangedQueue,
						 Long serviceInstanceTotalCount) {
		this.recentlyChangedQueue = recentlyChangedQueue;
		this.serviceInstanceTotalCount = serviceInstanceTotalCount;
	}

	
}
