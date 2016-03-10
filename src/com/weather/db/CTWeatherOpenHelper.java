package com.weather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CTWeatherOpenHelper extends SQLiteOpenHelper{
	
	
	//先写出建表语句
	
	//这是省的
	public static final String CREATE_PROVINCE="create table province ("
			+"id integer primary key autoincrement, "
			+"province_name text, "
			+"province_code text)";
	
	//市
	public static final String CREATE_CITY="create table city ("
			+"id integer primary key autoincrement,"
			+"city_name text, "
			+"city_code text, "
			+"province_id integer) " ;
	
	//县
	public static final String CREATE_COUNTRY="create table country ("
			+"id integer primary key autoincrement,"
			+"country_name text, "
			+"country_code text, "
			+"city_id integer) " ;


	public CTWeatherOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_COUNTRY);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
