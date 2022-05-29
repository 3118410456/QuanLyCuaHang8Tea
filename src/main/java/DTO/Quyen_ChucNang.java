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
public class Quyen_ChucNang {
    String MaCN, MaCV ;
    
    public Quyen_ChucNang() {
        
    }

    public Quyen_ChucNang(String MaCN, String MaCV) {
        this.MaCN = MaCN;
        this.MaCV = MaCV;
    }

    public String getMaCN() {
        return MaCN;
    }

    public void setMaCN(String MaCN) {
        this.MaCN = MaCN;
    }

    public String getMaCV() {
        return MaCV;
    }

    public void setMaCV(String MaCV) {
        this.MaCV = MaCV;
    }
    
    
}
