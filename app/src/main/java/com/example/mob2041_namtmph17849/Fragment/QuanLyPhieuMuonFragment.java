package com.example.mob2041_namtmph17849.Fragment;



import static java.time.Instant.now;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mob2041_namtmph17849.Adapter.PhieuMuonAdapter;
import com.example.mob2041_namtmph17849.Adapter.SachAdapter;
import com.example.mob2041_namtmph17849.Adapter.SachSpinnerAdapter;
import com.example.mob2041_namtmph17849.Adapter.ThanhVienSpinnerAdapter;
import com.example.mob2041_namtmph17849.DAO.PhieuMuonDAO;
import com.example.mob2041_namtmph17849.DAO.SachDAO;
import com.example.mob2041_namtmph17849.DAO.ThanhVienDAO;
import com.example.mob2041_namtmph17849.Model.PhieuMuon;
import com.example.mob2041_namtmph17849.Model.Sach;
import com.example.mob2041_namtmph17849.Model.ThanhVien;
import com.example.mob2041_namtmph17849.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class QuanLyPhieuMuonFragment extends Fragment {
    ListView lv;
    ArrayList<PhieuMuon> list;
    FloatingActionButton fabThemPhieuMuon;
    Dialog dialog;
    EditText edMaPM;
    Spinner spTV, spSach;
    TextView tvNgay, tvTienThue;
    CheckBox chkTraSach;
    Button btnLuu, btnHuy;
    static PhieuMuonDAO dao;
    PhieuMuonAdapter adapter;
    PhieuMuon item;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    ArrayList<ThanhVien> listThanhVien;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;
    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach> listSach;
    SachDAO sachDAO;
    Sach sach;
    int maSach, tienThue;
    int positionTV, positionSach;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quan_ly_phieu_muon, container, false);
        lv = view.findViewById(R.id.lvQuanLyPhieuMuon);
        fabThemPhieuMuon = view.findViewById(R.id.fabThemPM);
        dao = new PhieuMuonDAO(getActivity());
        capNhatLv();


        fabThemPhieuMuon.setOnClickListener(new View.OnClickListener() {
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
        dialog.setContentView(R.layout.dialog_phieu_muon);
        edMaPM = dialog.findViewById(R.id.edMaPhieuMuon);
        spTV = dialog.findViewById(R.id.spThanhVien);
        spSach = dialog.findViewById(R.id.spTenSach);
        tvNgay = dialog.findViewById(R.id.tvNgayDialog);
        tvTienThue = dialog.findViewById(R.id.tvTienThueDialog);
        chkTraSach = dialog.findViewById(R.id.chkTrSach);
        btnLuu = dialog.findViewById(R.id.btnLuu);
        btnHuy = dialog.findViewById(R.id.btnHuy);
        thanhVienDAO = new ThanhVienDAO(context);
        listThanhVien = new ArrayList<ThanhVien>();
        listThanhVien = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context,listThanhVien);
        spTV.setAdapter(thanhVienSpinnerAdapter);
        Date currentDate = Calendar.getInstance().getTime();
        String hienNay = sdf.format(currentDate);

        spTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maThanhVien = listThanhVien.get(position).maTV;
                Toast.makeText(context, "Chọn "+listThanhVien.get(position).hoTen,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        sachDAO = new SachDAO(context);
        listSach = new ArrayList<Sach>();
        listSach =(ArrayList<Sach>) sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context,listSach);
        spSach.setAdapter(sachSpinnerAdapter);

        spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSach = listSach.get(position).maSach;
                tienThue = listSach.get(position).giaThue;
                Toast.makeText(context, "Chọn "+listSach.get(position).tenSach,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        edMaPM.setEnabled(false);
        if(type!=0){
            edMaPM.setText(String.valueOf(item.maPM));
            for (int i=0; i<listThanhVien.size();i++)
                if(item.maTV == (listThanhVien.get(i).maTV)){
                    positionTV =i;
                }
                spTV.setSelection(positionTV);
                for (int i=0; i<listSach.size();i++)
                    if(item.maTV == (listSach.get(i).maSach)){
                        positionSach =i;
                    }
                    spSach.setSelection(positionSach);
                    tvNgay.setText("Ngày thuê: "+item.ngay);
                    tvTienThue.setText("Tiền thuê: "+item.tienThue);
                    if(item.traSach==1){
                        chkTraSach.setChecked(true);
                    }else {
                        chkTraSach.setChecked(false);
                    }
                }
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                item = new PhieuMuon();
                item.maSach= maSach;
                item.maTV = maThanhVien;
                item.ngay = hienNay;
                item.tienThue = tienThue;
                if(chkTraSach.isChecked()){
                    item.traSach=1 ;
                }else {
                    item.traSach =0;
                }
                if(validate() >0){
                    if(type==0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context, "Thêm thành công!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại!",Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        item.maPM =Integer.parseInt(edMaPM.getText().toString());
                        if (dao.update(item)>0) {
                            Toast.makeText(context, "Sửa thành công!",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa thất bại!",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
            }
    public  int validate(){
        int check  = 1;
//        if(edMaPM.getText().length() ==0){
//            Toast.makeText(getContext(),"Bạn phải nhập dầy đủ thông tin!",Toast.LENGTH_SHORT).show();
//            check =-1;
//        }
        return check;
    }
    void capNhatLv(){
        list = (ArrayList<PhieuMuon>) dao.getAll();
        adapter = new PhieuMuonAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
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

        }

