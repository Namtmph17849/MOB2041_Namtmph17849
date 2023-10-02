package com.example.mob2041_namtmph17849.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    static final String dbName = "PNLIB";
    static final int dbVersion = 1;
    public DbHelper(Context context) {
        super(context, dbName, null, dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableThuThu =
                "create table ThuThu (" +
                        "maTT TEXT PRIMARY KEY," +
                        "hoTen TEXT NOT NULL," +
                        "matKhau TEXT NOT NULL)";
        db.execSQL(createTableThuThu);

        String createTableThanhVien =
                "create table ThanhVien (" +
                        "maTV INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "hoTen TEXT NOT NULL," +
                        "namSinh TEXT NOT NULL)";
        db.execSQL(createTableThanhVien);

        String createTableLoaiSach =
                "create table LoaiSach(" +
                        "maLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "hoTen TEXT NOT NULL)";
        db.execSQL(createTableLoaiSach);

        String createTableSach =
                "create table Sach(" +
                        "maSach INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "tenSach TEXT NOT NULL," +
                        "giaThue INTEGER NOT NULL," +
                        "maLoai INTEGER REFERENCES LoaiSach(maLoai))";
        db.execSQL(createTableSach);

        String createTablePhieuMuon =
                "create table PhieuMuon(" +
                        "maPM INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "maTT TEXT REFERENCES ThuThu(maTT)," +
                        "maTV INTEGER REFERENCES ThanhVien(maTV)," +
                        "maSach INTEGER REFERENCES Sach(maSach)," +
                        "ngay DATE NOT NULL," +
                        "traSach INTEGER NOT NULL," +
                        "tienThue INTEGER NOT NULL)";
        db.execSQL(createTablePhieuMuon);

        db.execSQL("insert into LoaiSach values (1,'Thiếu nhi'), (2,'Tình cảm'), (3,'Giáo khoa')");
        db.execSQL("insert into Sach values (1,'Hay doi day', 2500, 1), (2,'Hoa tuyet diem', 2000, 2)");
        db.execSQL("insert into ThuThu values ('namht', 'Nam', '123')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if( oldVersion != newVersion){
            String dropTableThuThu = "drop table if exists ThuThu";
            db.execSQL(dropTableThuThu);
            String dropTableThanhVien = "drop table if exists ThanhVien";
            db.execSQL(dropTableThanhVien);
            String dropTableLoaiSach = "drop table if exists LoaiSach";
            db.execSQL(dropTableLoaiSach);
            String dropTableSach = "drop table if exists Sach";
            db.execSQL(dropTableSach);
            String dropTablePhieuMuon = "drop table if exists PhieuMuon";
            db.execSQL(dropTablePhieuMuon);

            onCreate(db);
        }

    }
}
