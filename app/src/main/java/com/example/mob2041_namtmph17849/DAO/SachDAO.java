package com.example.mob2041_namtmph17849.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.mob2041_namtmph17849.Database.DbHelper;
import com.example.mob2041_namtmph17849.Model.Sach;
import com.example.mob2041_namtmph17849.Model.ThanhVien;

import java.util.ArrayList;
import java.util.List;

public class SachDAO {
    DbHelper dbHelper;
    SQLiteDatabase db;
    public SachDAO(Context context){
        dbHelper = new DbHelper(context);
    }



    public ArrayList<Sach> getDSDauSach(){
        ArrayList<Sach> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(" select * from Sach", null);
        if(cursor.getCount() !=0){
            cursor.moveToFirst();
            do{
                list.add(new Sach(cursor.getInt(0), cursor.getString(1),
                        cursor.getInt(2), cursor.getInt(3)));
            }while (cursor.moveToNext());
        }

        return list;
    }
    public long insert(Sach obj){
        ContentValues values = new ContentValues();
        values.put("maSach", obj.maSach);
        values.put("tenSach", obj.tenSach);
        values.put("giaThue", obj.giaThue);
        values.put("maLoai", obj.maLoai);

        return db.insert("Sach", null, values);
    }

    public int update(Sach obj){
        ContentValues values = new ContentValues();

        values.put("maSach", obj.maSach);
        values.put("tenSach", obj.tenSach);
        values.put("giaThue", obj.giaThue);
        values.put("maLoai", obj.maLoai);
        return db.update("Sach", values, "maSach=?", new String[]{String.valueOf(obj.maSach)});
    }

    public int delete(String id){
        return db.delete("Sach", "maSach=?", new String[]{id});
    }

    public List<Sach> getAll(){
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }

    public Sach getID(String id){
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }

    @SuppressLint("Range")
    private List<Sach> getData(String sql, String...selectionArgs){

        List<Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);
        while (c.moveToNext()){
            Sach obj = new Sach();
            obj.maSach = Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
            obj.tenSach = c.getString(c.getColumnIndex("tenSach"));
            obj.giaThue = Integer.parseInt(c.getString(c.getColumnIndex("giaThue")));
            obj.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
            list.add(obj);
        }
        return list;
    }
}
