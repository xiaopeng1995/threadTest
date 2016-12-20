package top.thread.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用于读写线程中控制参数实体类
 * 作者:肖鹏
 * 时间:2016年12月20日15:13:02
 */
public enum Registry {

    // instance
    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(Registry.class);
    public Map<String, Object> value = new ConcurrentHashMap<>();

    // pool
    private final ExecutorService es = Executors.newCachedThreadPool();//Executors.newFixedThreadPool(2);


    // save
    public void saveKey(String key, Object value) {
        this.value.put(key, value);
    }


    //get
    public Map<String, Object> getValue() {
        return this.value;
    }


    // add Thread In ThreadPool
    public void addThreadInPool(Thread thread) {
        this.es.execute(thread);

    }
    //stop
    public void shutdown() {
        this.es.shutdown();
    }
}
