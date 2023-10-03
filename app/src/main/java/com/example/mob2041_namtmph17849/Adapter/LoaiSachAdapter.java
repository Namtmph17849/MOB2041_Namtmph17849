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

import com.example.mob2041_namtmph17849.Fragment.QuanLyLoaiFragment;
import com.example.mob2041_namtmph17849.Fragment.ThemNguoiDungFragment;
import com.example.mob2041_namtmph17849.Model.LoaiSach;
import com.example.mob2041_namtmph17849.Model.ThuThu;
import com.example.mob2041_namtmph17849.R;

import java.util.ArrayList;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    QuanLyLoaiFragment fragment;


    private ArrayList<LoaiSach> lists;
    TextView tvMaLoai, tvTenLoai;
    ImageView imgDel;

    public LoaiSachAdapter(@NonNull Context context, QuanLyLoaiFragment fragment, ArrayList<LoaiSach> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists =lists;
        this.fragment = fragment;
    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_loaisach,null);
        }
        final LoaiSach item = lists.get(position);
        if(item != null){
            tvMaLoai = v.findViewById(R.id.tvMaLoai);
            tvMaLoai.setText("Mã Loại: "+item.maLoai);
            tvTenLoai = v.findViewById(R.id.tvTenLoai);
            tvTenLoai.setText("Tên loại: "+item.hoTen);
            imgDel = v.findViewById(R.id.imgXoaLoai);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maLoai));
            }
        });
        return v;
    }
}
