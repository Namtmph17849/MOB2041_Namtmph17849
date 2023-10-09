package com.example.mob2041_namtmph17849.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mob2041_namtmph17849.Adapter.LoaiSachSpinnerAdapter;
import com.example.mob2041_namtmph17849.Adapter.SachAdapter;
import com.example.mob2041_namtmph17849.Adapter.ThanhVienAdapter;
import com.example.mob2041_namtmph17849.DAO.LoaiSachDAO;
import com.example.mob2041_namtmph17849.DAO.SachDAO;
import com.example.mob2041_namtmph17849.DAO.ThanhVienDAO;
import com.example.mob2041_namtmph17849.Model.LoaiSach;
import com.example.mob2041_namtmph17849.Model.Sach;
import com.example.mob2041_namtmph17849.Model.ThanhVien;
import com.example.mob2041_namtmph17849.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanLySachFragment extends Fragment {
    ListView lv;
    ArrayList<Sach> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaSach, edTenSach, edGiaThue;
    Button btnLuu,btnHuy;
    Spinner spMaLoai;
    static SachDAO dao;
    SachAdapter adapter;
    Sach item;
    LoaiSachSpinnerAdapter loaiSachSpinnerAdapter;
    ArrayList<LoaiSach> listLoaiSach;
    LoaiSachDAO loaiSachDAO;

    int maLoai, positionMaLoai;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);
        lv = view.findViewById(R.id.lvQuanLySach);
        fab = view.findViewById(R.id.fabThemSach);
        dao = new SachDAO(getActivity());
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
        dialog.setContentView(R.layout.dialog_sach);
        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTenSach);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spMaLoai = dialog.findViewById(R.id.spMaLoai);
        btnLuu = dialog.findViewById(R.id.btnLuu);
        btnHuy = dialog.findViewById(R.id.btnHuy);
        loaiSachDAO = new LoaiSachDAO(context);
        listLoaiSach = new ArrayList<LoaiSach>();
        listLoaiSach = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        loaiSachSpinnerAdapter = new LoaiSachSpinnerAdapter(context,listLoaiSach);
        spMaLoai.setAdapter(loaiSachSpinnerAdapter);

        spMaLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoai = listLoaiSach.get(position).maLoai;
                Toast.makeText(context, "Chọn "+listLoaiSach.get(position).hoTen,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        edMaSach.setEnabled(false);
        if(type !=0){
            edMaSach.setText(String.valueOf(item.maSach));
            edTenSach.setText(item.tenSach);
            edGiaThue.setText(String.valueOf(item.giaThue));
            spMaLoai.setSelection(positionMaLoai);
            for(int i = 0; i < listLoaiSach.size(); i++)
            if(item.maLoai == (listLoaiSach.get(i).maLoai)){
                positionMaLoai = i;
            }
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
                item = new Sach();
                item.tenSach = edTenSach.getText().toString();
                item.giaThue = Integer.parseInt(edGiaThue.getText().toString());
                item.maLoai = maLoai;
                if(validate()>0){
                    if(type == 0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm thành công!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Thêm thất bại!",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.maSach = Integer.parseInt(edMaSach.getText().toString());
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
        list = (ArrayList<Sach>) dao.getAll();
        adapter = new SachAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }

    public  int validate(){
        int check  = 1;
        if(edTenSach.getText().length() ==0|| edGiaThue.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập dầy đủ thông tin!",Toast.LENGTH_SHORT).show();
            check =-1;
        }
        return check;
    }
}