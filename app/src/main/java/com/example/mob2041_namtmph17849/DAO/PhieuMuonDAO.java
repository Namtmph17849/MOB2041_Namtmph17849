package com.example.mob2041_namtmph17849.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob2041_namtmph17849.Database.DbHelper;
import com.example.mob2041_namtmph17849.Model.PhieuMuon;
import com.example.mob2041_namtmph17849.Model.ThanhVien;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PhieuMuonDAO {

    private SQLiteDatabase db;

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT", obj.maTT);
        values.put("maTV", obj.maTV);
        values.put("maSach", obj.maSach);
        values.put("ngay", String.valueOf(obj.ngay));
        values.put("traSach", obj.traSach);
        values.put("tienThue", obj.tienThue);

        return db.insert("PhieuMuon", null, values);
    }

    public int update(PhieuMuon obj){
        ContentValues values = new ContentValues();
        values.put("maTT", obj.maTT);
        values.put("maTV", obj.maTV);
        values.put("maSach", obj.maSach);
        values.put("ngay", String.valueOf(obj.ngay));
        values.put("traSach", obj.traSach);
        values.put("tienThue", obj.tienThue);

        return db.update("PhieuMuon", values, "maPM=?", new String[]{String.valueOf(obj.maPM)});
    }

    public int delete(String id){
        return db.delete("PhieuMuon", "maPM=?", new String[]{id});
    }

    public List<PhieuMuon> getAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }

    public PhieuMuon getID(String id){
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }


    @SuppressLint("Range")
    private List<PhieuMuon> getData(String sql, String...selectionArgs){

        List<PhieuMuon> list = new ArrayList<PhieuMuon>();
        Cursor c = db.rawQuery(sql, selectionArgs);
            while (c.moveToNext()){
                PhieuMuon obj = new PhieuMuon();
                obj.maPM = Integer.parseInt(c.getString(c.getColumnIndex("maPM")));
                obj.maTV = Integer.parseInt(c.getString(c.getColumnIndex("maTV")));
                obj.maTT = c.getString(c.getColumnIndex("maTT"));
                obj.maSach = Integer.parseInt(c.getString(c.getColumnIndex("maSach")));
                obj.ngay = c.getString(c.getColumnIndex("ngay"));
                obj.traSach = Integer.parseInt(c.getString(c.getColumnIndex("traSach")));
                obj.tienThue = Integer.parseInt(c.getString(c.getColumnIndex("tienThue")));
                list.add(obj);
            }
        return list;
    }
}
