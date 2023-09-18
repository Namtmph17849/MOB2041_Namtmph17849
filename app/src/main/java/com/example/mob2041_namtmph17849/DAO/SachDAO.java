package com.example.mob2041_namtmph17849.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.mob2041_namtmph17849.Database.DbHelper;
import com.example.mob2041_namtmph17849.Model.Sach;

import java.util.ArrayList;

public class SachDAO {
    DbHelper dbHelper;
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
}
