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
public class PhanLoaiNVL {
    String MaLoaiNVL , TenLoaiNVL ;
    
    public PhanLoaiNVL()    {
        
    }

    public PhanLoaiNVL(String MaLoaiNVL, String TenLoaiNVL) {
        this.MaLoaiNVL = MaLoaiNVL;
        this.TenLoaiNVL = TenLoaiNVL;
    }

    public String getMaLoaiNVL() {
        return MaLoaiNVL;
    }

    public void setMaLoaiNVL(String MaLoaiNVL) {
        this.MaLoaiNVL = MaLoaiNVL;
    }

    public String getTenLoaiNVL() {
        return TenLoaiNVL;
    }

    public void setTenLoaiNVL(String TenLoaiNVL) {
        this.TenLoaiNVL = TenLoaiNVL;
    }
    
    
}
