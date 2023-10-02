package com.example.mob2041_namtmph17849;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;


import com.example.mob2041_namtmph17849.Fragment.DoiMatKhauFragment;
import com.example.mob2041_namtmph17849.Fragment.QuanLyLoaiFragment;
import com.example.mob2041_namtmph17849.Fragment.QuanLyPhieuMuonFragment;
import com.example.mob2041_namtmph17849.Fragment.QuanLySachFragment;
import com.example.mob2041_namtmph17849.Fragment.QuanLyThanhVienFragment;
import com.example.mob2041_namtmph17849.Fragment.ThemNguoiDungFragment;
import com.example.mob2041_namtmph17849.Fragment.ThongKeFragment;
import com.example.mob2041_namtmph17849.Fragment.Top10Fragment;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mob2041_namtmph17849.databinding.ActivityNavigationBinding;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    TextView tvNameAccount;

    View headerView;
    private DrawerLayout drawerLayout;
     NavigationView navigationView;

     private static final int FRAG_PHIEUMUON = 0;
    private static final int FRAG_LOAI = 1;
    private static final int FRAG_SACH = 2;
    private static final int FRAG_TV = 3;
    private static final int FRAG_TOP = 4;
    private static final int FRAG_THONGKE = 5;
    private static final int FRAG_THEMND = 6;
    private static final int FRAG_DOIMK = 7;

    private int currentFragment = FRAG_PHIEUMUON;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);


        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new QuanLyPhieuMuonFragment());
        navigationView.getMenu().findItem(R.id.navQuanLyPhieuMuon).setChecked(true);

        headerView = navigationView.getHeaderView(0);
        tvNameAccount = headerView.findViewById(R.id.tvTenTK);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        tvNameAccount.setText("Welcome "+user+"!");

        if(user.equalsIgnoreCase("admin")){
            navigationView.getMenu().findItem(R.id.navThemNguoiDung).setVisible(true);
        }

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if(id == R.id.navQuanLyPhieuMuon){
            if(currentFragment != FRAG_PHIEUMUON){
                setTitle("Quản lý phiếu mượn");
                replaceFragment(new QuanLyPhieuMuonFragment());
                currentFragment = FRAG_PHIEUMUON;
            }

        }else if(id == R.id.navQuanlyLoai){
            if(currentFragment != FRAG_LOAI){
                setTitle("Quản lý loại sách");
                replaceFragment(new QuanLyLoaiFragment());
                currentFragment = FRAG_LOAI;
            }

        }else if(id == R.id.navQuanlySach){
            if(currentFragment != FRAG_SACH){
                setTitle("Quản lý sách");
                replaceFragment(new QuanLySachFragment());
                currentFragment = FRAG_SACH;
            }

        }else if(id == R.id.navQuanLyTV){
            if(currentFragment != FRAG_TV){
                setTitle("Quản lý thành viên");
                replaceFragment(new QuanLyThanhVienFragment());
                currentFragment = FRAG_TV;
            }

        }else if(id == R.id.navTopSachMuon){
            if(currentFragment != FRAG_TOP){
                setTitle("Top 10");
                replaceFragment(new Top10Fragment());
                currentFragment = FRAG_TOP;
            }

        }else if(id == R.id.navThongKe){
            if(currentFragment != FRAG_THONGKE){
                setTitle("Thống kê doanh thu");
                replaceFragment(new ThongKeFragment());
                currentFragment = FRAG_THONGKE;
            }

        }else if(id == R.id.navThemNguoiDung){
            if(currentFragment != FRAG_THEMND){
                setTitle("Thêm người dùng");
                replaceFragment(new ThemNguoiDungFragment());
                currentFragment = FRAG_THEMND;
            }

        }else if(id == R.id.navDoiMatKhau) {
            if(currentFragment != FRAG_DOIMK){
                setTitle("Dổi mật khẩu");
                replaceFragment(new DoiMatKhauFragment());
                currentFragment = FRAG_DOIMK;
            }

        }else if(id == R.id.navDangXuat){
            startActivity(new Intent(getApplicationContext(),DangNhapActivity.class));
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.contentFrame, fragment);
        transaction.commit();
    }
}