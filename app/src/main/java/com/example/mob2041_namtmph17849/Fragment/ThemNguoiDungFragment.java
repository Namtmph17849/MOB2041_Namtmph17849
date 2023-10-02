package com.example.mob2041_namtmph17849.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mob2041_namtmph17849.DAO.ThuThuDAO;
import com.example.mob2041_namtmph17849.Model.ThuThu;
import com.example.mob2041_namtmph17849.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ThemNguoiDungFragment extends Fragment {
    FloatingActionButton fabThemND;

    ThuThuDAO dao;
    Button btnHuy,btnLuu;
    EditText edmaTT,edTenTT,edMatKhau,edNhapLai;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_nguoi_dung, container, false);

        dao = new ThuThuDAO(getActivity());

        fabThemND = view.findViewById(R.id.fabThemNguoiDung);
        fabThemND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackDialog(Gravity.CENTER);
            }
        });
        return view;
    }
    private void openFeedbackDialog(int gravity){
        final Dialog dialog = new Dialog(getActivity());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_them_nguoi_dung);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttribuses = window.getAttributes();
        windowAttribuses.gravity = gravity;
        window.setAttributes(windowAttribuses);

        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(false);
        }else {
            dialog.setCancelable(true);
        }

        btnHuy = dialog.findViewById(R.id.btnHuy);
        btnLuu = dialog.findViewById(R.id.btnLuu);

        edmaTT = dialog.findViewById(R.id.edMaTT);
        edTenTT = dialog.findViewById(R.id.edTenTT);
        edMatKhau = dialog.findViewById(R.id.edMatKhau);
        edNhapLai = dialog.findViewById(R.id.edNhapLai);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThu thuThu = new ThuThu();
                thuThu.maTT = edmaTT.getText().toString();
                thuThu.hoTen = edTenTT.getText().toString();
                thuThu.matKhau = edMatKhau.getText().toString();

                if (validate() >0){
                    if(dao.insert(thuThu) >0){
                        Toast.makeText(getActivity(),"Lưu thành công!",Toast.LENGTH_SHORT).show();
                        edmaTT.setText("");
                        edTenTT.setText("");
                        edMatKhau.setText("");
                        edNhapLai.setText("");
                    }else {
                        Toast.makeText(getActivity(),"Lưu thấy bại!",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        dialog.show();
    }

    public int validate(){
        int check = 1;
        if(edmaTT.getText().length()==0||edTenTT.getText().length()==0
                ||edMatKhau.getText().length()==0||edNhapLai.getText().length()==0){
            Toast.makeText(getActivity(),"Bạn phải nhập đủ thông tin!",Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            String pass = edMatKhau.getText().toString();
            String rePass = edNhapLai.getText().toString();
            if(!pass.equals(rePass)){
                Toast.makeText(getActivity(),"Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
            }
        }
        return check;
    }
}