/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_ChiTietPhieuNhap;
import BUS.BUS_PhieuNhap;
import DTO.ChiTietPhieuNhap;
import DTO.PhieuNhap;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class PhieunhapController implements Initializable {

    @FXML
    private TableColumn<ChiTietPhieuNhap, String> colTenNVL;
    @FXML
    private TableColumn<ChiTietPhieuNhap, String> colDVT;
    @FXML
    private TableColumn<ChiTietPhieuNhap, Integer> colSL;
    @FXML
    private TableColumn<ChiTietPhieuNhap, Double> colDonGiaNhap;
    @FXML
    private TableColumn<ChiTietPhieuNhap, Double> colThanhTien;
    @FXML
    private TableColumn<PhieuNhap, String> colMaPN;
    @FXML
    private TableColumn<PhieuNhap, String> colTenNV;
    @FXML
    private TableColumn<PhieuNhap, String> colNgayNhap;
    @FXML
    private TableColumn<PhieuNhap, String> colNCC;
    @FXML
    private TableColumn<PhieuNhap, Double> colTongTien;
    @FXML
    private Label lbTongTien;
    @FXML
    private Label lbMaPN;
    @FXML
    private Label lbNgayNhap;
    @FXML
    private Label lbMaNV;
    @FXML
    private TableView<ChiTietPhieuNhap> tblCTPN;
    @FXML
    private TableView<PhieuNhap> tblPN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setTablePhieuNhap();
    }

    public void setTablePhieuNhap() {
        tblPN.getItems().removeAll();
        ArrayList<PhieuNhap> list = new BUS_PhieuNhap().getListPN();
        ObservableList<PhieuNhap> oblist = FXCollections.observableArrayList();
        for (PhieuNhap pn : list) {
            oblist.add(pn);
        }
        colMaPN.setCellValueFactory(new PropertyValueFactory<PhieuNhap, String>("MaPN"));
        colTenNV.setCellValueFactory(new PropertyValueFactory<PhieuNhap, String>("MaNV"));
        colNgayNhap.setCellValueFactory(new PropertyValueFactory<PhieuNhap, String>("NgayNhap"));
        colNCC.setCellValueFactory(new PropertyValueFactory<PhieuNhap, String>("MaNCC"));
        colTongTien.setCellValueFactory(new PropertyValueFactory<PhieuNhap, Double>("TongTien"));
        tblPN.setItems(oblist);
    }

    @FXML
    private void backForm(MouseEvent event) throws IOException {
        EightTeaApplication.setRoot("home");
    }

    @FXML
    private void giaodienlapPN(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("lapphieunhap");
    }

    @FXML
    private void getCTPN(MouseEvent event) throws ParseException {
        
        if (tblPN.getSelectionModel().getSelectedItem() != null) {
            PhieuNhap pn = tblPN.getSelectionModel().getSelectedItem();
            lbMaPN.setText(pn.getMaPN());
            lbMaNV.setText(pn.getMaNV());
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy");
//            Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(pn.getNgayNhap());  
//            String strDate = formatter.format(date1);
//            System.out.println(String.valueOf(strDate));
            lbNgayNhap.setText(pn.getNgayNhap());

            tblCTPN.getItems().removeAll();
            ArrayList<ChiTietPhieuNhap> list = new BUS_ChiTietPhieuNhap().getListCTPN(pn.getMaPN());
            ObservableList<ChiTietPhieuNhap> oblist = FXCollections.observableArrayList();
            for (ChiTietPhieuNhap ctpn : list) {
                oblist.add(ctpn);
            }
            colTenNVL.setCellValueFactory(new PropertyValueFactory<ChiTietPhieuNhap, String>("MaNVL"));
            colDVT.setCellValueFactory(new PropertyValueFactory<ChiTietPhieuNhap, String>("DonViTinh"));
            colSL.setCellValueFactory(new PropertyValueFactory<ChiTietPhieuNhap, Integer>("SoLuong"));
            colDonGiaNhap.setCellValueFactory(new PropertyValueFactory<ChiTietPhieuNhap, Double>("DonGiaNhap"));
            colThanhTien.setCellValueFactory(new PropertyValueFactory<ChiTietPhieuNhap, Double>("ThanhTien"));
            tblCTPN.setItems(oblist);
//            EightTeaApplication.alertInf(pn.getMaPN());
            Double tongtien = new BUS_PhieuNhap().tongtien_PN(pn.getMaPN());
//            EightTeaApplication.alertInf(String.valueOf(tongtien));
            lbTongTien.setText(String.valueOf(tongtien));
        }

    }

}
