package top.thread.thead;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.thread.entity.Registry;

import java.util.Map;

/**
 * Job01
 */
public class Job extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(Job.class);
    //此线程标识名
    private String ThreadName;
    //启动或停止线程 默认执行
    private boolean isNo = true;
    //重复间隔时间(秒) 默认2秒
    private int time = 2;

    public Job(String ThreadName) {
        this.ThreadName = ThreadName;

    }

    @Override
    public void run() {
        //去内存取次线程最新指令是否继续循环
        while (isNo) {
            if (!isNo)
                break;
            logger.info(currentThread().getName() + "正在执行。。。");
            Object rAMData = Registry.INSTANCE.getValue().get(ThreadName + "_time");
            //去内存取最新间隔时间
            this.time = rAMData != null ? (int) rAMData : time;
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            rAMData = Registry.INSTANCE.getValue().get(ThreadName + "_isNo");
            this.isNo = rAMData != null ? (boolean) rAMData : isNo;
        }
        logger.info(currentThread().getName() + "停止");
    }
}
