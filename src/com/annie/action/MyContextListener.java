package com.annie.action;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyContextListener implements ServletContextListener {
	private MyTimerTask3 mt3 = new MyTimerTask3();  
	  
    /** 
     * 本方法的描述： 
     * 在所有的filter和servlet都destroyed后通知web应用的所有的ServletContextListeners[that“本店”即将打烊啦] 
     */  
    public void contextDestroyed(ServletContextEvent sce) {  
        System.out.println("contextDestroyed…………");  
        mt3.taskEnd();  
    }  
  
    /** 
     * 这个方法的描述： 
     * 在所有的filter和servlet初始化之前，所有的ServletContextListeners会收到[您所在的web应用的初始化工作开始啦]通知 
     */  
    public void contextInitialized(ServletContextEvent arg0) {  
        System.out.println("contextInitialized……");  
        mt3.taskBegin();  
    }
}
