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

import com.example.mob2041_namtmph17849.Fragment.QuanLyThanhVienFragment;
import com.example.mob2041_namtmph17849.Model.ThanhVien;
import com.example.mob2041_namtmph17849.R;

import java.util.ArrayList;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    QuanLyThanhVienFragment fragment;
    private ArrayList<ThanhVien> lists;
    TextView tvMaTV, tvTenTV,tvNamSinh;
    ImageView imgDel;
    public ThanhVienAdapter(@NonNull Context context, QuanLyThanhVienFragment fragment, ArrayList<ThanhVien> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_thanh_vien,null);
        }
        final ThanhVien item = lists.get(position);
        if(item != null){
            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvMaTV.setText("Mã thành viên: "+item.maTV);
            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Tên thành viên: "+item.hoTen);
            tvNamSinh = v.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm sinh: "+item.namSinh);
            imgDel = v.findViewById(R.id.imgXoaTV);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maTV));
            }
        });
        return v;
    }
}
