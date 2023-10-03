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

import com.example.mob2041_namtmph17849.Adapter.LoaiSachAdapter;
import com.example.mob2041_namtmph17849.Adapter.ThanhVienAdapter;
import com.example.mob2041_namtmph17849.DAO.LoaiSachDAO;
import com.example.mob2041_namtmph17849.DAO.ThanhVienDAO;
import com.example.mob2041_namtmph17849.Model.LoaiSach;
import com.example.mob2041_namtmph17849.Model.ThanhVien;
import com.example.mob2041_namtmph17849.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class QuanLyLoaiFragment extends Fragment {

    FloatingActionButton fabThemLoai;
    ListView lv;
    ArrayList<LoaiSach> list;
    Dialog dialog;
    EditText edMaLoai, edTenLoai;
    Button btnLuu,btnHuy;

    static LoaiSachDAO dao;
    LoaiSachAdapter adapter;
    LoaiSach item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_loai, container, false);

        fabThemLoai = view.findViewById(R.id.fabThemLoai);
        lv = view.findViewById(R.id.lvQuanLyLoai);
        dao = new LoaiSachDAO(getActivity());
        capNhatLv();

        fabThemLoai.setOnClickListener(new View.OnClickListener() {
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
        dialog.setContentView(R.layout.dialog_loai_sach);
        edMaLoai = dialog.findViewById(R.id.edMaLoaiSach);
        edTenLoai = dialog.findViewById(R.id.edTenLoaiSach);
        btnLuu = dialog.findViewById(R.id.btnLuu);
        btnHuy = dialog.findViewById(R.id.btnHuy);

        edMaLoai.setEnabled(false);
        if(type !=0){
            edMaLoai.setText(String.valueOf(item.maLoai));
            edTenLoai.setText(item.hoTen);
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
                item = new LoaiSach();
                item.hoTen = edTenLoai.getText().toString();
                if(validate()>0){
                    if(type == 0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm thành công!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Thêm thất bại!",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.maLoai = Integer.parseInt(edMaLoai.getText().toString());
                        if(dao.update(item)>0){
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
//            dialog.cancel();
        });
        builder.setNegativeButton(
                "Không",
                (dialog1, id) ->{
//                    dialog.cancel();
                });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    void capNhatLv(){
        list = (ArrayList<LoaiSach>) dao.getAll();
        adapter = new LoaiSachAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }
    public  int validate(){
        int check  = 1;
        if(edTenLoai.getText().length() ==0){
            Toast.makeText(getContext(),"Bạn phải nhập dầy đủ thông tin!",Toast.LENGTH_SHORT).show();
            check =-1;
        }
        return check;
    }
}
