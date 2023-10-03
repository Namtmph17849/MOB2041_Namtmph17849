package com.example.mob2041_namtmph17849;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mob2041_namtmph17849.DAO.ThuThuDAO;

public class DangNhapActivity extends AppCompatActivity {
    Button btnDangNhap, btnHuy;
    EditText edTenDangNhap, edMatKhau;
    CheckBox chkLuuMK;
    ThuThuDAO dao;
    String strTK, strMK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Đăng nhập");
        setContentView(R.layout.activity_dang_nhap);
        btnDangNhap = findViewById(R.id.btnDangnhap);
        btnHuy = findViewById(R.id.btnHuy);
        edMatKhau = findViewById(R.id.edMatkhau);
        edTenDangNhap = findViewById(R.id.edTenDangNhap);
        chkLuuMK = findViewById(R.id.chkSavePass);

        dao = new ThuThuDAO(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        edTenDangNhap.setText(pref.getString("USERNAME",""));
        edMatKhau.setText(pref.getString("PASSWORD",""));
        chkLuuMK.setChecked(pref.getBoolean("REMEMBER", false));

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edTenDangNhap.setText("");
                edMatKhau.setText("");
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(DangNhapActivity.this, MainActivity.class));
                checkLogin();
            }
        });
    }

    public void checkLogin(){
        strTK = edTenDangNhap.getText().toString();
        strMK = edMatKhau.getText().toString();
        if(strTK.isEmpty() || strMK.isEmpty()){
            Toast.makeText(getApplicationContext(),"Tên đăng nhập hoặc mật khẩu không được để trống!",
                    Toast.LENGTH_SHORT).show();
        }else {
            if(dao.checkLogin(strTK,strMK)>0 || (strTK.equals("admin") && strMK.equals("123"))){
                Toast.makeText(getApplicationContext(),"Đăng nhập thành công!",
                        Toast.LENGTH_SHORT).show();
                rememberUser(strTK,strMK,chkLuuMK.isChecked());
//                saveUser(edTenDangNhap.getText().toString(), edMatKhau.getText().toString());


                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("user", strTK);
                startActivity(i);
                finish();
            }else {
                Toast.makeText(getApplicationContext(),"Tên đăng nhập hoặc mật khẩu không đúng!",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void rememberUser(String u,String p, boolean status){

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if(!status){
            edit.clear();
        }else {
            edit.putString("USERNAME",u);
            edit.putString("PASSWORD",p);
            edit.putBoolean("REMEMBER",status);
        }
        edit.commit();
    }
//    public void saveUser(String u, String p){
//        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
//        SharedPreferences.Editor edit = pref.edit();
//
//        edit.putString("USERNAME",u);
//        edit.putString("PASSWORD",p);
//
//        edit.commit();
//    }
}