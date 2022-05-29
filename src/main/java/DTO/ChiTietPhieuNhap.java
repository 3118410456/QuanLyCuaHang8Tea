/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import javafx.scene.control.Button;

/**
 *
 * @author Admin
 */
public class ChiTietPhieuNhap {
    String MaPN , MaNVL , DonViTinh ;
    double DonGiaNhap,ThanhTien  ;
    int SoLuong;
    Button btn;
    
    public ChiTietPhieuNhap()  {
        btn = new Button("Xoá");
    }

    public ChiTietPhieuNhap(String MaPN, String MaNVL, String DonViTinh, double DonGiaNhap, int SoLuong,double ThanhTien) {
        this.MaPN = MaPN;
        this.MaNVL = MaNVL;
        this.DonViTinh = DonViTinh;
        this.DonGiaNhap = DonGiaNhap;
        this.SoLuong = SoLuong;
        this.ThanhTien = ThanhTien;
    }

    public String getMaPN() {
        return MaPN;
    }

    public void setMaPN(String MaPN) {
        this.MaPN = MaPN;
    }

    public String getMaNVL() {
        return MaNVL;
    }

    public void setMaNVL(String MaNVL) {
        this.MaNVL = MaNVL;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String DonViTinh) {
        this.DonViTinh = DonViTinh;
    }

    public double getDonGiaNhap() {
        return DonGiaNhap;
    }

    public void setDonGiaNhap(double DonGiaNhap) {
        this.DonGiaNhap = DonGiaNhap;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
    
    
    
    
}
