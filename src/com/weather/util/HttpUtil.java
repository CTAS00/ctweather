package com.weather.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpUtil {
	//一个回调的接口
    public interface HttpCallbackListener{
    	void onFinish(String response);
    	void onError(Exception e);
    	
    	
    }	
	
	

	
	public static void sendHttpRequest(String httpUrl,String httpArg,final HttpCallbackListener listener){
		final String address=httpUrl+"?"+httpArg;
		//final String address=httpUrl;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
					HttpURLConnection connection=null;
					try {
						URL url=new URL(address);
						connection=(HttpURLConnection) url.openConnection();
						connection.setRequestMethod("GET");
						 connection.setRequestProperty("apikey",  "902e198a84439771cd98a9db68ea47b8");
						connection.setConnectTimeout(8000);
						connection.setReadTimeout(8000);
						InputStream inputStream=connection.getInputStream();
						BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
						StringBuilder response=new StringBuilder();
						
						
						String line;
						while((line=reader.readLine())!=null){
							response.append(line);
							response.append("\r\n");
						}
						
						if(listener!=null){
							listener.onFinish(response.toString());
							
							
						}
						
						
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
						if(listener!=null){
							listener.onError(e);
							
							
						}
					} finally{
						
						
						if(connection!=null){
							connection.disconnect();
						}
					}
				
			
				
			}
		}).start();
		
		
	}
	

}
