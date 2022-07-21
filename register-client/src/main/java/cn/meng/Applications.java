package cn.meng;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 完整的服务实例的信息
 * @author zhonghuashishan
 *
 */
@Data
public class Applications {

	private Map<String, Map<String, ServiceInstance>> registry
			= new HashMap<>();

	public Applications() {
		
	}
	
	public Applications(Map<String, Map<String, ServiceInstance>> registry) {
		this.registry = registry;
	}


}
