package com.example.mob2041_namtmph17849.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mob2041_namtmph17849.Model.LoaiSach;
import com.example.mob2041_namtmph17849.Model.Sach;
import com.example.mob2041_namtmph17849.R;

import java.util.ArrayList;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {

    private Context context;
    private ArrayList<LoaiSach> lists;
    TextView tvMaLoai, tvTenLoai;
    public LoaiSachSpinnerAdapter(@NonNull Context context, ArrayList<LoaiSach> lists) {
        super(context,0, lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.loai_sach_item_spinner, null);
        }
        final LoaiSach item = lists.get(position);
        if(item !=null){
            tvMaLoai = v.findViewById(R.id.tvMaLoaisp);
            tvMaLoai.setText(item.maLoai + ". ");
            tvTenLoai = v.findViewById(R.id.tvTenLoaisp);
            tvTenLoai.setText(item.hoTen);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.loai_sach_item_spinner, null);
        }
        final LoaiSach item = lists.get(position);
        if(item !=null){
            tvMaLoai = v.findViewById(R.id.tvMaLoaisp);
            tvMaLoai.setText(item.maLoai + ". ");
            tvTenLoai = v.findViewById(R.id.tvTenLoaisp);
            tvTenLoai.setText(item.hoTen);
        }
        return v;
    }
}
