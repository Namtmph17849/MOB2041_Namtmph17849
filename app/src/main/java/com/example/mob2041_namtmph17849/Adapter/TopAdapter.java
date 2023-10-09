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

import com.example.mob2041_namtmph17849.Fragment.Top10Fragment;
import com.example.mob2041_namtmph17849.Model.Top;
import com.example.mob2041_namtmph17849.R;

import java.util.ArrayList;


public class TopAdapter extends ArrayAdapter<Top> {

    private Context context;
    Top10Fragment fragment;
    private ArrayList<Top> lists;
    TextView tvSach, tvSoLuong;
    ImageView imageDel;

    public TopAdapter(@NonNull Context context, ArrayList<Top> lists) {
        super(context, 0, lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v== null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v= inflater.inflate(R.layout.item_top10, null);
        }
        final Top item = lists.get(position);
        if(item != null){
            tvSach = v.findViewById(R.id.tvTenSach);
            tvSach.setText("Sách: "+ item.tenSach);
            tvSoLuong = v.findViewById(R.id.tvSoLuong);
            tvSoLuong.setText("Số lượng: "+ item.soLuong);
        }
        return v;
    }
}
