package com.example.mob2041_namtmph17849.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mob2041_namtmph17849.Fragment.QuanLySachFragment;
import com.example.mob2041_namtmph17849.Fragment.QuanLyThanhVienFragment;
import com.example.mob2041_namtmph17849.Model.Sach;
import com.example.mob2041_namtmph17849.Model.ThanhVien;
import com.example.mob2041_namtmph17849.R;

import java.util.ArrayList;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    QuanLySachFragment fragment;
    private ArrayList<Sach> lists;
    TextView tvMaSach, tvTenSach,tvGiaThue, tvMaLoai;
    ImageView imgDel;
    public SachAdapter(@NonNull Context context, QuanLySachFragment fragment, ArrayList<Sach> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_sach,null);
        }
        final Sach item = lists.get(position);
        if(item != null){
            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã sách: "+item.maSach);
            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+item.tenSach);
            tvGiaThue = v.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá thuê: "+item.giaThue);
            tvMaLoai = v.findViewById(R.id.tvMaLoai);
            tvMaLoai.setText("Mã Loại: "+item.maLoai);
            imgDel = v.findViewById(R.id.imgXoaSach);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maSach));
            }
        });
        return v;
    }
}
