/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_ChucVu;
import BUS.BUS_NhanVien;
import BUS.BUS_Quyen_ChucNang;
import DTO.NhanVien;
import com.github.qquang24t5._8tea.EightTeaApplication;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author MINH TUAN
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnNV;
    @FXML
    private Label txtName;
    @FXML
    private Label txtPer;
    @FXML
    private Button btnSP;
    @FXML
    private Button btnCT;
    @FXML
    private Button btnHD;
    @FXML
    private Button btnCV;
    @FXML
    private Button btnNCC;
    @FXML
    private Button btnKho;
    @FXML
    private Button btnPLNVL;
    @FXML
    private Button btnPLSP;
    @FXML
    private Button btnKM;
    @FXML
    private Button btnNH;
    @FXML
    private VBox vboxtest;
    @FXML
    private Button btnThongKe;
    @FXML
    private Label lbNgay;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        setHome();
        setDSQuyen();
    }

    @FXML
    private void formQLNV(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("nhanvien");
    }

    @FXML
    private void dangxuat(ActionEvent event) throws IOException {
        if (EightTeaApplication.alertConf("Bạn có muốn đăng xuất ?")) {
            EightTeaApplication.setRoot("login");
            EightTeaApplication.userhientai = null;
        }

    }

    @FXML
    private void formCV(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("chucvu");
    }

    public void setHome() {
        NhanVien cu = new BUS_NhanVien().TimNV(EightTeaApplication.userhientai);
        LocalDate td = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        lbNgay.setText(td.format(formatter));
        txtName.setText(cu.getHoTen());
        txtPer.setText(new BUS_ChucVu().tenCV(cu.getMaCV()));
    }

    @FXML
    private void formQLK(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("kho");
    }

    @FXML
    private void formPL(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("phanloaiNVL");
    }

    @FXML
    private void formNCC(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("nhacungcap");
    }

    @FXML
    private void formQLSP(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("sanpham");
    }

    @FXML
    private void formCT(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("congthuc");
    }

    @FXML
    private void formHD(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("hoadon");
        LaphoadonController.delTblHD();
    }

    @FXML
    private void formPLSP(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("phanloaisp");
    }

    public void setDSQuyen() {
        btnCT.setDisable(true);
        btnCV.setDisable(true);
        btnHD.setDisable(true);
        btnKho.setDisable(true);
        btnNCC.setDisable(true);
        btnNV.setDisable(true);
        btnPLNVL.setDisable(true);
        btnSP.setDisable(true);
        btnPLSP.setDisable(true);
        btnKM.setDisable(true);
        btnNH.setDisable(true);
        btnNCC.setDisable(true);
        btnThongKe.setDisable(true);
        ArrayList<String> list = new BUS_Quyen_ChucNang().getListQuyen(EightTeaApplication.userhientai);
        for (String s : list) {
            if (s.equals("CN01")) {
                btnSP.setDisable(false);
                btnPLSP.setDisable(false);
            }
            if (s.equals("CN02")) {
                btnNV.setDisable(false);
            }
            if (s.equals("CN03")) {
                btnHD.setDisable(false);
            }
            if (s.equals("CN04")) {
                btnCT.setDisable(false);
            }
            if (s.equals("CN05")) {
                btnKho.setDisable(false);
                btnPLNVL.setDisable(false);
            }
            if (s.equals("CN06")) {
                btnCV.setDisable(false);
            }
            if (s.equals("CN07")) {
                btnKM.setDisable(false);
            }
            if (s.equals("CN08")) {
                btnNH.setDisable(false);
            }
            if (s.equals("CN09")) {
                btnNCC.setDisable(false);
            }
            if (s.equals("CN10")) {
                btnThongKe.setDisable(false);
            }
        }

    }

    @FXML
    private void formKM(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("khuyenmai");
    }

    @FXML
    private void formNH(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("phieunhap");
        LapphieunhapController.delTblPN();
    }

    @FXML
    private void formDMK(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("doimatkhau");
    }

   

    @FXML
    private void formTK(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("thongke");
    }
}
