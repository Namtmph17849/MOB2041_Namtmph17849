package com.example.mob2041_namtmph17849.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mob2041_namtmph17849.DAO.ThuThuDAO;
import com.example.mob2041_namtmph17849.Model.ThuThu;
import com.example.mob2041_namtmph17849.R;


public class DoiMatKhauFragment extends Fragment {

    EditText edPassCu, edPassMoi, edNhapLai;
    Button btnLuu, btnHuy;

    ThuThuDAO dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
        btnLuu = view.findViewById(R.id.btnLuu);
        btnHuy = view.findViewById(R.id.btnHuy);
        edPassCu = view.findViewById(R.id.edMatKhauCu);
        edPassMoi = view.findViewById(R.id.edMatkhauMoi);
        edNhapLai = view.findViewById(R.id.edNhapLai);

        dao = new ThuThuDAO(getActivity());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassMoi.setText("");
                edPassCu.setText("");
                edNhapLai.setText("");
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME","");
                if(validate()>0){
                    ThuThu thuThu = dao.getID(user);
                    thuThu.matKhau = edPassMoi.getText().toString();
                    dao.updatePass(thuThu);
                    if(dao.updatePass(thuThu)>0){
                        Toast.makeText(getActivity(),"Thay đổi mật khẩu thành công!",Toast.LENGTH_SHORT).show();
                        edPassCu.setText("");
                        edPassMoi.setText("");
                        edNhapLai.setText("");
                    }else {
                        Toast.makeText(getActivity(),"Thay đổi mật khẩu thất bại!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }

    public int validate(){
        int check = 1;
        if(edPassCu.getText().length()==0 || edPassMoi.getText().length()==0 || edNhapLai.getText().length()==0){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
            String passCu = pref.getString("PASSWORD","");
            String pass = edPassMoi.getText().toString();
            String nhapLai = edNhapLai.getText().toString();

            if(!passCu.equals(edPassCu.getText().toString())){
                Toast.makeText(getContext(),"Mật khẩu cũ sai",Toast.LENGTH_SHORT).show();
                check=-1;
                return check;
            }
            if(!pass.equals(nhapLai)){
                Toast.makeText(getContext(),"Mật khẩu không trung khớp",Toast.LENGTH_SHORT).show();
                check=-1;
            }
        }
        return check;
    }
}