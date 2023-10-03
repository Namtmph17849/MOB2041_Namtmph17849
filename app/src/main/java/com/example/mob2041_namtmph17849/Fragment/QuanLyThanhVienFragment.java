package com.example.mob2041_namtmph17849.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mob2041_namtmph17849.Adapter.ThanhVienAdapter;
import com.example.mob2041_namtmph17849.DAO.ThanhVienDAO;
import com.example.mob2041_namtmph17849.Model.ThanhVien;
import com.example.mob2041_namtmph17849.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class QuanLyThanhVienFragment extends Fragment {
    ListView lv;
    ArrayList<ThanhVien> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaTV, edTenTV, edNamSinh;
    Button btnLuu,btnHuy;

    static ThanhVienDAO dao;
    ThanhVienAdapter adapter;
    ThanhVien item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_ly_thanh_vien, container, false);
        lv = view.findViewById(R.id.lvQuanLyTV);
        fab = view.findViewById(R.id.fabThemTV);
        dao = new ThanhVienDAO(getActivity());
        capNhatLv();
        fab.setOnClickListener(new View.OnClickListener() {
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
        dialog.setContentView(R.layout.dialog_thanh_vien);
        edMaTV = dialog.findViewById(R.id.edMaTV);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamsinh);
        btnLuu = dialog.findViewById(R.id.btnLuu);
        btnHuy = dialog.findViewById(R.id.btnHuy);

        edMaTV.setEnabled(false);
        if(type !=0){
            edMaTV.setText(String.valueOf(item.maTV));
            edTenTV.setText(item.hoTen);
            edNamSinh.setText(item.namSinh);
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
                item = new ThanhVien();
                item.hoTen = edTenTV.getText().toString();
                item.namSinh = edNamSinh.getText().toString();
                if(validate()>0){
                    if(type == 0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm thành công!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Thêm thất bại!",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.maTV = Integer.parseInt(edMaTV.getText().toString());
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
        list = (ArrayList<ThanhVien>) dao.getAll();
        adapter = new ThanhVienAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }

    public  int validate(){
        int check  = 1;
        if(edTenTV.getText().length() ==0|| edNamSinh.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập dầy đủ thông tin!",Toast.LENGTH_SHORT).show();
            check =-1;
        }
        return check;
    }
}