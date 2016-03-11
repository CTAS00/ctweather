package com.weather.model;

import java.util.ArrayList;
import java.util.List;

import com.weather.db.CTWeatherOpenHelper;
import com.weather.util.HttpUtil;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CTWeatherDB {
	
	public static final String DB_NAME="CT_Weather";
	
	public static final int VERSION=1;
	
	
	
	
//单列设计模式,多线程之间也能稳定
	private SQLiteDatabase db;
	
	private CTWeatherDB(Context context){
		//该数据的对象只会在内存中生成一个
		CTWeatherOpenHelper dbhelper=new CTWeatherOpenHelper(context, DB_NAME, null, VERSION);
		
		    db=dbhelper.getWritableDatabase();//db也只有一个
		

	};
	
	
	
	private static CTWeatherDB ctWeatherDB;
	
	
	public static CTWeatherDB getInstance(Context context) {

		if (ctWeatherDB == null) {

			synchronized (CTWeatherDB.class) {

				if (ctWeatherDB == null) {

					ctWeatherDB = new CTWeatherDB(context);
				

				}

			}

		}
		return ctWeatherDB;

	}
	
	//将province实例存储到数据库

	public void saveProvince(Province province){
		if(province!=null){
			ContentValues values=new ContentValues();
			values.put("province_name", province.getProvinceName());
			values.put("province_code", province.getProvinceCode());
			 db.insert("province", null, values); 

		}
		
		
	}
	
	public List<Province> getProvice(){
		 List<Province> mlist=new ArrayList<Province>();
		 Cursor cursor=db.query(DB_NAME, null, null, null, null, null, null);
		 if(cursor.moveToFirst()){
			 
			 
			 do{
				 Province province=new Province();
				 province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				 province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
				 
				 province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
				 
				 mlist.add(province);
			 
			 
			 
		 }while(cursor.moveToNext());

	}
		 return mlist;
		
		
		
	}
	
	
	//将city信息存取到数据库
	
	public void saveCity(City city){
		if(city!=null){
			ContentValues values=new ContentValues();
			values.put("city_name", city.getCityName());
			values.put("city_code", city.getCityCode());
			values.put("province_id", city.getProvinceId());
			db.insert(DB_NAME, null, values);
			
		}
		
		
		
		
	}
	
	//将city信息从数据库里面取出来
	
	public List<City> getCity(int provinceId){
		List<City> mList=new ArrayList<City>();
		Cursor cursor=db.query(DB_NAME, null, "provinceId=?", new String[]{String.valueOf(provinceId)}, null, null, null);
		
		if(cursor.moveToFirst()){
			do{
				City city=new City();
				
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
				 
				city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
				city.setProvinceId(cursor.getInt(cursor.getColumnIndex("province_id")));
				
				mList.add(city);
			}while(cursor.moveToNext());
			
			
		}
		
		return mList;
	
		
		
		
		
	}
	
	
	

	//将country信息存取到数据库
	
	public void saveCountry(Country country){
		if(country!=null){
			ContentValues values=new ContentValues();
			values.put("country_name", country.getCountryName());
			values.put("country_code", country.getCountryCode());
			values.put("city_id", country.getCityId());
			db.insert(DB_NAME, null, values);
			
		}
		
		
		
		
	}
	
	//将country信息从数据库里面取出来
	
	public List<Country> getCountry(int cityId){
		List<Country> mList=new ArrayList<Country>();
		Cursor cursor=db.query(DB_NAME, null, "cityId=?", new String[]{String.valueOf(cityId)}, null, null, null);
		
		if(cursor.moveToFirst()){
			do{
				Country country=new Country();
				
				country.setId(cursor.getInt(cursor.getColumnIndex("id")));
				country.setCountryName(cursor.getString(cursor.getColumnIndex("country_name")));
				 
				country.setCountryCode(cursor.getString(cursor.getColumnIndex("country_code")));
				country.setCityId(cursor.getInt(cursor.getColumnIndex("cityId")));
				
				mList.add(country);
			}while(cursor.moveToNext());
			
			
		}
		
		return mList;

	}
	

	
	
}
