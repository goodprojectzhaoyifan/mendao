package cn.thinkjoy.ctd.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by wpliu on 16/3/14.
 */
public class CTDCallBackListener implements ServletContextListener {
    private static final Logger logger = LoggerFactory.getLogger(CTDCallBackListener.class);
     public CTDCallBackThread ctdCallBackThread;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String str=null;
        if (str == null && ctdCallBackThread == null) {
            ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

            ctdCallBackThread=(CTDCallBackThread)context.getBean("ctdCallBackThread");
            ThreadListener listener=new ThreadListener();
            ctdCallBackThread.addObserver(listener);
            new Thread(ctdCallBackThread).start();
            logger.info("回调线程启动");
        }
    }



    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if (ctdCallBackThread != null ) {
            ctdCallBackThread.notifyAll();
        }
    }
}
