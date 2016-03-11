package com.ctweather.ctweather;

import com.weather.util.HttpUtil;
import com.weather.util.HttpUtil.HttpCallbackListener;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {
	private String httpUrl = "http://apis.baidu.com/apistore/weatherservice/cityinfo";
	private String httpArg = "cityname=%E5%8C%97%E4%BA%AC";
	
	
	
	
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HttpUtil.sendHttpRequest(httpUrl,httpArg,  new HttpCallbackListener() {
			
			@Override
			public void onFinish(String response) {
				
				Log.d("haha", response);
				
			}
			
			@Override
			public void onError(Exception e) {
				// TODO Auto-generated method stub
				Log.d("error", e.toString());
			}
		});
     
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
