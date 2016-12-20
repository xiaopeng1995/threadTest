package top.thread;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.thread.entity.Registry;
import top.thread.thead.Job;

/**
 * 线程测试
 */
public class Application {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        logger.info("线程任务开始..");
        for (int i = 0; i < 3; i++) {
            //初始化线程
            Thread job = new Job("job" + i);
            //添加线程进入线程池
            Registry.INSTANCE.addThreadInPool(job);
        }
        //休眠10秒
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("修改所有任务间隔时间为1秒");
        for (int i = 0; i < 3; i++) {
            //修改任务间隔时间为1秒
            Registry.INSTANCE.saveKey("job" + i + "_time", 1);
        }
        //休眠10秒
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("给所有线程下达命令为false");
        for (int i = 0; i < 3; i++) {
            //给所有线程下达命令为false
            Registry.INSTANCE.saveKey("job" + i + "_isNo", false);
        }
        //停止线程池
        Registry.INSTANCE.shutdown();

    }
}
