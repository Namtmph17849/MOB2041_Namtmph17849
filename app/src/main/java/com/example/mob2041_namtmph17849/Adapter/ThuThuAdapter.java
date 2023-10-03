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

import com.example.mob2041_namtmph17849.Fragment.ThemNguoiDungFragment;
import com.example.mob2041_namtmph17849.Model.ThanhVien;
import com.example.mob2041_namtmph17849.Model.ThuThu;
import com.example.mob2041_namtmph17849.R;

import java.util.ArrayList;

public class ThuThuAdapter extends ArrayAdapter<ThuThu> {
    private Context context;
    ThemNguoiDungFragment fragment;


    private ArrayList<ThuThu> lists;
    TextView tvMaTT, tvhoTen,tvMatKhau;
    ImageView imgDel;

    public ThuThuAdapter(@NonNull Context context, ThemNguoiDungFragment fragment, ArrayList<ThuThu> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists =lists;
        this.fragment = fragment;
    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_them_nguoi_dung,null);
        }
        final ThuThu item = lists.get(position);
        if(item != null){
            tvMaTT = v.findViewById(R.id.tvMaTT);
            tvMaTT.setText("Mã thủ thư: "+item.maTT);
            tvhoTen = v.findViewById(R.id.tvTenTT);
            tvhoTen.setText("Tên thủ thư: "+item.hoTen);
            tvMatKhau = v.findViewById(R.id.tvMatKhau);
            tvMatKhau.setText("Mật Khẩu: "+item.matKhau);
            imgDel = v.findViewById(R.id.imgXoaNguoiDung);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoa(String.valueOf(item.maTT));
            }
        });
        return v;
    }
}
