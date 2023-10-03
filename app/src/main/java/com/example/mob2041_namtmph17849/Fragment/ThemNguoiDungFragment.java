package com.example.mob2041_namtmph17849.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.ListView;
import android.widget.Toast;

import com.example.mob2041_namtmph17849.Adapter.ThanhVienAdapter;
import com.example.mob2041_namtmph17849.Adapter.ThuThuAdapter;
import com.example.mob2041_namtmph17849.DAO.ThanhVienDAO;
import com.example.mob2041_namtmph17849.DAO.ThuThuDAO;
import com.example.mob2041_namtmph17849.Model.ThanhVien;
import com.example.mob2041_namtmph17849.Model.ThuThu;
import com.example.mob2041_namtmph17849.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class ThemNguoiDungFragment extends Fragment {
    FloatingActionButton fabThemND;
    ListView lv;
    ArrayList<ThuThu> list;
    Dialog dialog;
    Button btnLuu,btnHuy;

    ThuThuAdapter adapter;
    ThuThu item;
    static ThuThuDAO dao;
    EditText edmaTT,edTenTT,edMatKhau,edNhapLai;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_them_nguoi_dung, container, false);

        dao = new ThuThuDAO(getActivity());
        fabThemND = view.findViewById(R.id.fabThemNguoiDung);
        lv = view.findViewById(R.id.lvThemNguoiDung);
        capNhatLv();
        fabThemND.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lv.setOnItemLongClickListener((parent, view1, position, id) -> {
            item = list.get(position);
            openDialog(getActivity(),1);

            return false;
        });
        return view;
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_nguoi_dung);
        edmaTT = dialog.findViewById(R.id.edMaTT);
        edTenTT = dialog.findViewById(R.id.edTenTT);
        edMatKhau = dialog.findViewById(R.id.edMatKhau);
        edNhapLai = dialog.findViewById(R.id.edNhapLai);
        btnLuu = dialog.findViewById(R.id.btnLuu);
        btnHuy = dialog.findViewById(R.id.btnHuy);


        if(type !=0){
            edmaTT.setText(item.maTT);
            edTenTT.setText(item.hoTen);
            edMatKhau.setText(item.matKhau);
        }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new ThuThu();
                item.maTT = edmaTT.getText().toString();
                item.hoTen = edTenTT.getText().toString();
                item.matKhau = edMatKhau.getText().toString();
                if(validate()>0){
                    if(type == 0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm thành công!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Thêm thất bại!",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.maTT = edmaTT.getText().toString();
                        if(dao.updatePass(item)>0){
                            Toast.makeText(context,"Sửa thành công!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Sửa thất bại!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();


    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Có",(dialog1, id) -> {
            dao.delete(Id);
            capNhatLv();
            dialog.cancel();
        });
        builder.setNegativeButton(
                "Không",
                (dialog1, id) ->{
                    dialog.cancel();
                });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    void capNhatLv(){
        list = (ArrayList<ThuThu>) dao.getAll();
        adapter = new ThuThuAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
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
                check =-1;
            }
        }
        return check;
    }
}