package cn.meng.demo.register.server;

import javax.xml.ws.Service;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ServiceRegistryCache {

    private static ServiceRegistryCache instance = new ServiceRegistryCache();
    private ServiceRegistry registry = ServiceRegistry.getInstance();

    public static class CacheKey{
        /**
         * 全量注册表缓存key
         */
        public final static String FULL_SERVICE_REGISTRY = "full_service_registry";

        /**
         * 增量注册表缓存key
         */
        public final static String DELTA_SERVICE_REGISTRY = "delta_service_registry";
    }


    /**
     * 读写锁
     *
     */

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    /**
     * 只读缓存
     */
    private Map<String,Object> readOnlyMap = new HashMap<>();

    /**
     * 读写缓存
     */
    private Map<String,Object> readWriteMap = new HashMap<>();


    /**
     * 根据缓存key获取数据
     * @param cacheKey
     * @return
     */
    public Object get(String cacheKey){
        Object cacheValue = readOnlyMap.get(cacheKey);
        if(cacheValue == null){

            synchronized (readOnlyMap) {
                if (readOnlyMap.get(cacheKey) == null) {
                    cacheValue = readWriteMap.get(cacheKey);

                    if(cacheValue == null){
                        cacheValue = getCacheValue(cacheKey);
                    }

                    readOnlyMap.put(cacheKey,cacheValue);
                }
            }
        }
        return cacheValue;
    }


    /**
     * 获取实际缓存数据
     * @param cacheKey
     * @return
     */
    public Object getCacheValue(String cacheKey){
        try {
            registry.readLock();
            if(CacheKey.FULL_SERVICE_REGISTRY.equals(cacheKey)){
                return new Applications(registry.getRegistry());
            }else if(CacheKey.DELTA_SERVICE_REGISTRY.equals(cacheKey)){
                return registry.getDeltaRegistry();
            }
        } finally {
            registry.unReadLock();
        }
        return null;
    }
    private ServiceRegistryCache(){

    }
    public static ServiceRegistryCache getInstance(){
        return instance;
    }
}
