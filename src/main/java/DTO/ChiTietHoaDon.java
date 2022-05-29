/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import BUS.BUS_HoaDon;
import BUS.BUS_SanPham;
import com.github.qquang24t5._8tea.EightTeaApplication;
import com.github.qquang24t5._8tea.LaphoadonController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDon {
    String MaHD, MaSP;
    int SoLuong;
    double DonGia,ThanhTien;
 
    Button btn;

    public Button getBtn() {
        return btn;
    }
    
    public void setBtn(Button btn) {
        this.btn = btn;
    }
    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String MaHD) {
        this.MaHD = MaHD;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double DonGia) {
        this.DonGia = DonGia;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
    public ChiTietHoaDon()  {
        btn = new Button("Xóa");
//        btn.setOnAction(e->{
//            String masp = new BUS_SanPham().layMaSP(this.MaSP);
//            String mahd = "ORDER_"+EightTeaApplication.userhientai;
//            if(new BUS_HoaDon().deleteSP_Order(mahd, masp)){
//                try {
//                    EightTeaApplication.setRoot("laphoadon");
//                } catch (IOException ex) {
//                    Logger.getLogger(ChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }else{
//                EightTeaApplication.alertConf("Có lỗi xảy ra, vui lòng thử lại !");
//            }
//            
//        });
    }

    
    
    
}

