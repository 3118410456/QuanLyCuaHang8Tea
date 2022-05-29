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
public class ChucNang {
    String MaCN,TenCN;
    
    public ChucNang()   {
        
    }

    public ChucNang(String MaCN, String TenCN) {
        this.MaCN = MaCN;
        this.TenCN = TenCN;
    }

    public String getMaCN() {
        return MaCN;
    }

    public void setMaCN(String MaCN) {
        this.MaCN = MaCN;
    }

    public String getTenCN() {
        return TenCN;
    }

    public void setTenCN(String TenCN) {
        this.TenCN = TenCN;
    }
    
    
}
