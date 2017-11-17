package com.annie.action;

import java.io.IOException;
import java.io.Reader;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.ClientProtocolException;

import sun.awt.windows.ThemeReader;



public class MyTimerTask3 {
	public ForCatch forcatch;
	public static void main(String[] args) {  
        MyTimerTask3 mtt = new MyTimerTask3();  
        mtt.taskBegin();  
        mtt.taskEnd();  
    }  
  
    // newScheduledThreadPool(int corePoolSize),corePoolSize是线程池的大小，  
    //即保持活动或者闲置的线程，它们是任务调度时用于并发执行的线程  
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);  
    ScheduledFuture<?> taskHandle;  
    ScheduledFuture<?> taskHandle2; 
  
    /** 
     * 启动任务 
     */  
    public void taskBegin() {  
        // 定义一个任务  
        final Runnable task = new Runnable() {  
            public void run() {
                System.out.println("执行任务");  
                clearTimedModCode();
            }  
        };  
        final Runnable task2 = new Runnable() {  
            public void run() {
            	System.out.println("执行任务2"); 
            	clearTimedRegCode();
            }  
        }; 
  
        // scheduleAtFixedRate(Runnable command, long initialDelay, long period,  
        // TimeUnit unit):  
        // 通过ScheduledExecutorService的scheduleAtFixedRate来执行任务  
        // 参数  
        // command - 要执行的任务  
        // initialDelay - 首次执行的延迟时间  
        // period - 连续执行之间的周期  
        // unit - initialDelay 和 period 参数的时间单位  
        System.out.println("任务启动………………………………");  
        taskHandle = scheduler.scheduleAtFixedRate(task, 5, 5*60, TimeUnit.SECONDS);  
        taskHandle2 = scheduler.scheduleAtFixedRate(task2, 15, 5*60, TimeUnit.SECONDS); 
    }  
  
    /** 
     * 结束任务 
     */  
    public void taskEnd() {  
        scheduler.shutdown();   //启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用。  
//      List<Runnable> shutdownNow()试图停止所有正在执行的活动任务，暂停处理正在等待的任务，并返回等待执行的任务列表。   
    }
    public void clearTimedModCode(){
    	if(ForCatch.THEBUGTEACHEROne){
    		try {
				ForCatch.doAlert();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
    public void clearTimedRegCode(){
    	if(ForCatch.THEBUGTEACHERTwo){
    		try {
    			ForCatch.doAlert2();
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	
    }
}
