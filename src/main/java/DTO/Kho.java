/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Admin
 */
public class Kho {
    String MaNVL, MaLoaiNVL ,TenNVL ,DonViTinh ,MaNCC;
    int SoLuong ;
    double DonGiaNhap;
    
    public Kho()    {
        
    }

    public Kho(String MaNVL, String MaLoaiNVL, String TenNVL, String DonViTinh, String MaNCC, int SoLuong, double DonGiaNhap) {
        this.MaNVL = MaNVL;
        this.MaLoaiNVL = MaLoaiNVL;
        this.TenNVL = TenNVL;
        this.DonViTinh = DonViTinh;
        this.MaNCC = MaNCC;
        this.SoLuong = SoLuong;
        this.DonGiaNhap = DonGiaNhap;
    }

    public String getMaNVL() {
        return MaNVL;
    }

    public void setMaNVL(String MaNVL) {
        this.MaNVL = MaNVL;
    }

    public String getMaLoaiNVL() {
        return MaLoaiNVL;
    }

    public void setMaLoaiNVL(String MaLoaiNVL) {
        this.MaLoaiNVL = MaLoaiNVL;
    }

    public String getTenNVL() {
        return TenNVL;
    }

    public void setTenNVL(String TenNVL) {
        this.TenNVL = TenNVL;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String DonViTinh) {
        this.DonViTinh = DonViTinh;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getDonGiaNhap() {
        return DonGiaNhap;
    }

    public void setDonGiaNhap(double DonGiaNhap) {
        this.DonGiaNhap = DonGiaNhap;
    }
    
    
}
