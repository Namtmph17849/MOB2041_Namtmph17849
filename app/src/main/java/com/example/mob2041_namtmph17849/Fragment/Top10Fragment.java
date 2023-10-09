package com.example.mob2041_namtmph17849.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mob2041_namtmph17849.Adapter.TopAdapter;
import com.example.mob2041_namtmph17849.DAO.ThongKeDAO;
import com.example.mob2041_namtmph17849.Model.Top;
import com.example.mob2041_namtmph17849.R;

import java.util.ArrayList;

public class Top10Fragment extends Fragment {

    ListView lv;
    ArrayList<Top> list;
    TopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_top10, container, false);
        lv = v.findViewById(R.id.lvTop10);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
        list = (ArrayList<Top>) thongKeDAO.getTop();
        adapter = new TopAdapter(getActivity(),list);
        lv.setAdapter(adapter);

        return v;
    }
}