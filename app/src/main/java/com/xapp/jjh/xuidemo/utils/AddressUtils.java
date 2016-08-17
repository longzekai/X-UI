package com.xapp.jjh.xuidemo.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.xapp.jjh.xuidemo.bean.City;
import com.xapp.jjh.xuidemo.bean.County;
import com.xapp.jjh.xuidemo.bean.Province;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Taurus on 2015/12/31.
 */
public class AddressUtils {

    private static final String DB_NAME = "area.db";
    private static Context mContext;
    private static final String TABLE_NAME_PROVINCE = "province";
    private static final String TABLE_NAME_CITY = "city";
    private static final String TABLE_NAME_COUNTY = "county";

    public static void init(Context context){
        mContext = context;
        copyDB(mContext, DB_NAME);
    }

    private static Context getContext(){
        return mContext;
    }

    public static List<Province> getProvinces(){
        StringBuilder sb = new StringBuilder("select");
        sb.append(" province_id,province_name,spell_case");
        sb.append(" from ").append(TABLE_NAME_PROVINCE);
        sb.append(" order by spell_case ASC");
        Cursor cursor = getDataBase().rawQuery(sb.toString(), null);
        List<Province> provinces = new ArrayList<>();
        Province province;
        while(cursor.moveToNext()){
            province = new Province();
            province.setProvince_id(cursor.getInt(0));
            province.setProvince_name(cursor.getString(1));
            province.setSpell_case(cursor.getString(2));
            provinces.add(province);
        }
        cursor.close();
        return provinces;
    }

    public static List<City> getCities(int province_id,boolean order){
        StringBuilder sb = new StringBuilder("select");
        sb.append(" city_id,city_name,spell_case,province_id");
        sb.append(" from ").append(TABLE_NAME_CITY);
        sb.append(province_id>0?(" where province_id = " + province_id):"");
        sb.append(order?" order by spell_case ASC":"");
        Cursor cursor = getDataBase().rawQuery(sb.toString(), null);
        List<City> cities = new ArrayList<>();
        City city;
        while(cursor.moveToNext()){
            city = new City();
            city.setCity_id(cursor.getInt(0));
            city.setCity_name(cursor.getString(1));
            city.setSpell_case(cursor.getString(2));
            city.setProvince_id(cursor.getInt(3));
            cities.add(city);
        }
        cursor.close();
        return cities;
    }

    public static List<County> getCounty(int city_id,boolean order){
        StringBuilder sb = new StringBuilder("select");
        sb.append(" county_id,county_name,spell_case,city_id");
        sb.append(" from ").append(TABLE_NAME_COUNTY);
        sb.append(city_id>0?(" where city_id = " + city_id):"");
        sb.append(order?" order by spell_case ASC":"");
        Cursor cursor = getDataBase().rawQuery(sb.toString(), null);
        List<County> counties = new ArrayList<>();
        County county;
        while(cursor.moveToNext()){
            county = new County();
            county.setCounty_id(cursor.getInt(0));
            county.setCounty_name(cursor.getString(1));
            county.setSpell_case(cursor.getString(2));
            county.setCity_id(cursor.getInt(3));
            counties.add(county);
        }
        cursor.close();
        return counties;
    }

    private static SQLiteDatabase getDataBase(){
        return SQLiteDatabase.openDatabase(getDBPath(),null,SQLiteDatabase.OPEN_READONLY);
    }

    private static String getDBPath(){
        return getContext().getFilesDir() + "/" + DB_NAME;
    }

    /**
     * 将数据库文件拷贝至Files目录下
     *
     * @param dbName
     */
    private static void copyDB(Context context,String dbName) {
        if(isDBExist())
            return;
        try {
            File file = new File(context.getFilesDir(), dbName);
            InputStream in = context.getAssets().open(dbName);
            FileOutputStream fos = new FileOutputStream(file);
            int len;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isDBExist(){
        return new File(getDBPath()).exists();
    }

}
