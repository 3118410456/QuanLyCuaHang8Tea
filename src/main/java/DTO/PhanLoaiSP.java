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
public class PhanLoaiSP {
    String MaLoaiSP ,TenLoai ;
    
    public PhanLoaiSP() {
        
    }

    public PhanLoaiSP(String MaLoaiSP, String TenLoai) {
        this.MaLoaiSP = MaLoaiSP;
        this.TenLoai = TenLoai;
    }

    public String getMaLoaiSP() {
        return MaLoaiSP;
    }

    public void setMaLoaiSP(String MaLoaiSP) {
        this.MaLoaiSP = MaLoaiSP;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }
    
    
}
