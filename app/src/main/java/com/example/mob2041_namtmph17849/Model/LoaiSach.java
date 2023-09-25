package com.example.mob2041_namtmph17849.Model;

public class LoaiSach {
    public int maLoai;
    public String hoTen;

    public LoaiSach() {
    }

    public LoaiSach(int maLoai, String hoTen) {
        this.maLoai = maLoai;
        this.hoTen = hoTen;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
}
