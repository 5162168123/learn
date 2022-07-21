package cn.meng;

import lombok.Data;

import java.util.LinkedList;

/**
 * 增量注册表
 * @author zhonghuashishan
 *
 */

@Data
public class DeltaRegistry {

	private LinkedList<CachedServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue;
	private Long serviceInstanceTotalCount;
	
	public DeltaRegistry(LinkedList<CachedServiceRegistry.RecentlyChangedServiceInstance> recentlyChangedQueue,
                         Long serviceInstanceTotalCount) {
		this.recentlyChangedQueue = recentlyChangedQueue;
		this.serviceInstanceTotalCount = serviceInstanceTotalCount;
	}

	
}
