package cn.thinkjoy.ctd.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by wpliu on 16/3/15.
 */
public class ThreadListener implements Observer {
    private static final Logger logger = LoggerFactory.getLogger(ThreadListener.class);


    @Override
    public void update(Observable o, Object arg) {
        logger.info("回调线程挂起");
        CTDCallBackThread thread = new CTDCallBackThread();
        thread.addObserver(this);
        new Thread(thread).start();
        logger.info("回调线程重启");
    }
}
