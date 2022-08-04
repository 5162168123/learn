package cn.meng.demo.register.server;

import javax.xml.ws.Service;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ServiceRegistryCache {

    /**
     * 单例
     */
    private static ServiceRegistryCache instance = new ServiceRegistryCache();

    private final static long CACHE_MAP_SYNC_INTERVAL = 30*1000L;
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

    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
    private ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
    private Object lock = new Object();

    /**
     * 只读缓存
     */
    private Map<String,Object> readOnlyMap = new HashMap<>();

    /**
     * 读写缓存
     */
    private Map<String,Object> readWriteMap = new HashMap<>();

    private CacheMapSyncDaemon deamon;

    /**
     * 根据缓存key获取数据
     * @param cacheKey
     * @return
     */
    public Object get(String cacheKey){
        Object cacheValue ;
        try {

            readLock.lock();
            cacheValue = readOnlyMap.get(cacheKey);
            if(cacheValue == null){

                synchronized (lock) {
                    if (readOnlyMap.get(cacheKey) == null) {
                        cacheValue = readWriteMap.get(cacheKey);

                        if(cacheValue == null){
                            cacheValue = getCacheValue(cacheKey);
                            readWriteMap.put(cacheKey,cacheValue);
                        }

                        readOnlyMap.put(cacheKey,cacheValue);
                    }
                }
            }
        } finally {
            readLock.unlock();
        }
        return cacheValue;
    }

    public void invalidate(){
        synchronized (lock){
            readWriteMap.remove(CacheKey.DELTA_SERVICE_REGISTRY);
            readWriteMap.remove(CacheKey.FULL_SERVICE_REGISTRY);
        }



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
        //启动缓存数据同步后台线程
        this.deamon = new CacheMapSyncDaemon();
        this.deamon.setDaemon(true);
        this.deamon.start();
    }
    public static ServiceRegistryCache getInstance(){
        return instance;
    }

    /**
     * 同步两个缓存后台线程
     */
    class CacheMapSyncDaemon extends Thread{
        @Override
        public void run(){
            while(true){

                try{

                    try {
                        writeLock.lock();
                        synchronized (readOnlyMap) {
                            if(readWriteMap.get(CacheKey.FULL_SERVICE_REGISTRY) == null){
                                readOnlyMap.put(CacheKey.FULL_SERVICE_REGISTRY,null);
                            }
                            if(readWriteMap.get(CacheKey.DELTA_SERVICE_REGISTRY) == null){
                                readOnlyMap.put(CacheKey.DELTA_SERVICE_REGISTRY,null);
                            }
                        }
                    } finally {
                        writeLock.unlock();
                    }
                    Thread.sleep(CACHE_MAP_SYNC_INTERVAL);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
