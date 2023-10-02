package com.example.mob2041_namtmph17849.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.mob2041_namtmph17849.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class QuanLyLoaiFragment extends Fragment {

    FloatingActionButton fabThemLoai;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_loai, container, false);

        fabThemLoai = view.findViewById(R.id.fabThemLoai);

        fabThemLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedbackDialog(Gravity.CENTER);
            }
        });
        return view;
    }
    private void openFeedbackDialog(int gravity){
        final Dialog dialog = new Dialog(getActivity());

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_loai_sach);

        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams windowAttribuses = window.getAttributes();
        windowAttribuses.gravity = gravity;
        window.setAttributes(windowAttribuses);

        if(Gravity.BOTTOM == gravity){
            dialog.setCancelable(false);
        }else {
            dialog.setCancelable(true);
        }

        Button btnHuy = dialog.findViewById(R.id.btnHuy);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}