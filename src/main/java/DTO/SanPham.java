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
public class SanPham {
    String MaSP, TenSP , MaLoaiSP,Size ;
    Double GiaBan;
    
    public SanPham() {
        
    }

    public SanPham(String MaSP, String TenSP, String MaLoaiSP, String Size, Double GiaBan) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.MaLoaiSP = MaLoaiSP;
        this.Size = Size;
        this.GiaBan = GiaBan;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getMaLoaiSP() {
        return MaLoaiSP;
    }

    public void setMaLoaiSP(String MaLoaiSP) {
        this.MaLoaiSP = MaLoaiSP;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String Size) {
        this.Size = Size;
    }

    public Double getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(Double GiaBan) {
        this.GiaBan = GiaBan;
    }
    
    
    
}
