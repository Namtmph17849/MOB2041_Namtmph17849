package com.example.mob2041_namtmph17849.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob2041_namtmph17849.Database.DbHelper;
import com.example.mob2041_namtmph17849.Model.LoaiSach;
import com.example.mob2041_namtmph17849.Model.ThuThu;

import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {

    private SQLiteDatabase db;

    public LoaiSachDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.hoTen);

        return db.insert("LoaiSach", null, values);
    }

    public int update(LoaiSach obj){
        ContentValues values = new ContentValues();
        values.put("hoTen", obj.hoTen);


        return db.update("LoaiSach", values, "maLoai=?", new String[]{String.valueOf(obj.maLoai)});
    }

    public int delete(String id){
        return db.delete("LoaiSach", "maLoai=?", new String[]{id});
    }

    public List<LoaiSach> getAll(){
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }

    public LoaiSach getID(String id){
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql,id);
        return list.get(0);
    }


    @SuppressLint("Range")
    private List<LoaiSach> getData(String sql, String...selectionArgs){

        List<LoaiSach> list = new ArrayList<LoaiSach>();
        Cursor c = db.rawQuery(sql, selectionArgs);
            while (c.moveToNext()){
                LoaiSach obj = new LoaiSach();
                obj.maLoai = c.getInt(c.getColumnIndex("maLoai"));
                obj.hoTen = c.getString(c.getColumnIndex("hoTen"));
                list.add(obj);
            }
        return list;
    }
}
