package com.annie.action;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;

public class ForCatch {
	HttpServletResponse httpResponse;
	HttpServletRequest httpRequest;
	public static boolean THEBUGTEACHEROne=true;
	public static boolean THEBUGTEACHERTwo=true;

	public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len = inStream.read(buffer)) !=-1 ){
            outStream.write(buffer, 0, len);
        }
        byte[] data = outStream.toByteArray();//网页的二进制数据
        outStream.close();
        inStream.close();
        String result=new String(data,"utf-8");
        System.out.println(result+"<<<<<<<<<<<<<<<<");
        return data;
    }
	
	public static String getMessage() throws ClientProtocolException, IOException {
        
        String url="http://ccc.com/AJAX/T_JW_MyTopic.ashx?action=select&sTopic=&sSource=&sTeacher=";
        String cookie1="ASP.NET_SessionId=123456; BYLWUser=LoginName=123456&RoleId=";
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("user-agent",  
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/39.0.2171.95 Safari/537.36 MicroMessenger/6.5.2.501 NetType/WIFI WindowsWechat");  
            conn.setRequestProperty("Cookie", cookie1);
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            // 获取URLConnection对象对应的输出流  
            out = new PrintWriter(conn.getOutputStream());  
            // 发送请求参数  
            out.print("page=1&rows=20");  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应  
            byte[] data=readInputStream(conn.getInputStream());
            result=new String(data,"utf-8");
            System.out.println(result);
        } catch (Exception e) {  
            System.out.println("发送POST请求出现异常！" + e);  
            e.printStackTrace();  
        }  
        // 使用finally块来关闭输出流、输入流  
        finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;
	}
	public static void doAlert() throws Exception{
		String data=getMessage();
		JSONObject json=JSONObject.fromObject(data);
		JSONArray array=json.optJSONArray("rows");
		if(array!=null){
			for(int i=0;i<array.size();i++){
				String result=array.optJSONObject(i).optString("AduitTeaName");
				System.out.println(result);
				if(result.equals("lao")){
					THEBUGTEACHEROne=false;
					THEBUGTEACHERTwo=false;
					DoSendMail.sendTextEmail(result);
				}
			}
		}
//		MailSend.tosend();
	}
	public static void doAlert2() throws Exception{
		String data=getMessage();
		JSONObject json=JSONObject.fromObject(data);
		JSONArray array=json.optJSONArray("rows");
		if(array!=null){
			for(int i=0;i<array.size();i++){
				String result=array.optJSONObject(i).optString("AduitTeaName");
				System.out.println(result);
				if(result.equals("xu")){
					THEBUGTEACHERTwo=false;
					DoSendMail.sendTextEmail(result);
				}
			}
		}
//		MailSend.tosend();
	}

}
