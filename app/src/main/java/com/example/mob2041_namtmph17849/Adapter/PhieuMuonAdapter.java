package com.example.mob2041_namtmph17849.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mob2041_namtmph17849.DAO.SachDAO;
import com.example.mob2041_namtmph17849.DAO.ThanhVienDAO;
import com.example.mob2041_namtmph17849.Fragment.QuanLyPhieuMuonFragment;
import com.example.mob2041_namtmph17849.Model.PhieuMuon;
import com.example.mob2041_namtmph17849.Model.Sach;
import com.example.mob2041_namtmph17849.Model.ThanhVien;
import com.example.mob2041_namtmph17849.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {

    private Context context;
    QuanLyPhieuMuonFragment fragment;
    private ArrayList<PhieuMuon> lists;
    TextView tvMaPM, tvTenTV, tvTenSach, tvTienThue, tvNgay, tvTraSach;
    ImageView imgDel;
    SachDAO sachDao;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public PhieuMuonAdapter(@NonNull Context context, QuanLyPhieuMuonFragment fragment, ArrayList<PhieuMuon> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
        this.fragment =fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v =convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_phieu_muon,null);
        }
        final PhieuMuon item = lists.get(position);
        if(item != null){
            tvMaPM = v.findViewById(R.id.tvMaPhieu);
            tvMaPM.setText("Mã phiếu: "+item.maPM);
            sachDao = new SachDAO(context);
            Sach sach = sachDao.getID(String.valueOf(item.maSach));
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+ sach.tenSach);
            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(item.maTV));
            tvTenTV = v.findViewById(R.id.tvThanhVien);
            tvTenTV.setText("Tên thành viên: "+ thanhVien.hoTen);
            tvTienThue = v.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền thuê: " + item.tienThue);
            tvNgay = v.findViewById(R.id.tvNgayThue);
            tvNgay.setText("Ngày thuê: "+ item.ngay);
            tvTraSach = v.findViewById(R.id.tvTraSach);
            if(item.traSach == 1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            }else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }

            imgDel = v.findViewById(R.id.imgXoaPhieu);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maPM));
            }
        });

        return v;
    }
}
