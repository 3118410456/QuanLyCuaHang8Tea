/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_ChiTietHoaDon;
import BUS.BUS_HoaDon;
import DTO.ChiTietHoaDon;
import DTO.HoaDon;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MINH TUAN
 */
public class HoadonController implements Initializable {

    @FXML
    private Text txtBaoLoi;
    @FXML
    private TableColumn<HoaDon, String> colMaHD;
    @FXML
    private TableColumn<HoaDon, String> colTenNV;
    @FXML
    private TableColumn<HoaDon, String> colNgayLap;
    @FXML
    private TableColumn<HoaDon, String> colKM;
    @FXML
    private TableColumn<HoaDon, Double> colTongTien;
    @FXML
    private TableColumn<ChiTietHoaDon, String> colTenSP;
    
    @FXML
    private TableColumn<ChiTietHoaDon, Double> colThanhTien;
    @FXML
    private Label lbTongCong;
    @FXML
    private Label lbMaHD;
    @FXML
    private Label lbNgayLap;
    @FXML
    private Label lbKhuyenMai;
    @FXML
    private Label lbTongTien;
    @FXML
    private TableView<HoaDon> tblHD;
    @FXML
    private TableView<ChiTietHoaDon> tblCTHD;
    @FXML
    private TableColumn<ChiTietHoaDon, Integer> colSL;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setTblHoaDon();
    }    

    @FXML
    private void backForm(MouseEvent event) throws IOException {
        EightTeaApplication.setRoot("home");
    }



    @FXML
    private void giaodienlapHD(ActionEvent event) throws IOException {
        EightTeaApplication.setRoot("laphoadon");
    }
    public void setTblHoaDon(){
        tblHD.getItems().removeAll();
        ObservableList<HoaDon> li = FXCollections.observableArrayList();
        ArrayList<HoaDon> listhd = new BUS_HoaDon().getListHD();
        for(HoaDon hd : listhd)
        {
            li.add(hd);
        }
        
        
        colMaHD.setCellValueFactory(new PropertyValueFactory<HoaDon, String> ("MaHD"));
        colTenNV.setCellValueFactory(new PropertyValueFactory<HoaDon, String> ("MaNV"));
        colNgayLap.setCellValueFactory(new PropertyValueFactory<HoaDon, String> ("NgayTao"));
        colKM.setCellValueFactory(new PropertyValueFactory<HoaDon, String> ("MaKM"));
        colTongTien.setCellValueFactory(new PropertyValueFactory<HoaDon, Double> ("TongTien"));
        
        
        tblHD.setItems(li);
    }
    public void setTblCTHD(String mahd){
        tblCTHD.getItems().removeAll();
        ObservableList<ChiTietHoaDon> li = FXCollections.observableArrayList();
        ArrayList<ChiTietHoaDon> listcthd = new BUS_ChiTietHoaDon().getListCTHD(mahd);
        for(ChiTietHoaDon hd : listcthd)
        {
            li.add(hd);
        }
        
        
        colTenSP.setCellValueFactory(new PropertyValueFactory<ChiTietHoaDon, String> ("MaSP"));
        colSL.setCellValueFactory(new PropertyValueFactory<ChiTietHoaDon, Integer> ("SoLuong"));
        colThanhTien.setCellValueFactory(new PropertyValueFactory<ChiTietHoaDon, Double> ("ThanhTien"));
 
        tblCTHD.setItems(li);
    }
    @FXML
    private void getCTHD(MouseEvent event) {
        if(tblHD.getSelectionModel().getSelectedItem()!=null){
            HoaDon hd = tblHD.getSelectionModel().getSelectedItem();
            setTblCTHD(hd.getMaHD());
            lbTongTien.setText(String.valueOf(hd.getTongTien()));
            
            if(hd.getMaKM().equals("Không có")){
                lbKhuyenMai.setTextFill(Color.BLACK);
            }else{
                lbKhuyenMai.setTextFill(Color.GREEN);
            }
            lbKhuyenMai.setText(hd.getMaKM());
            lbNgayLap.setText(hd.getNgayTao());
            lbMaHD.setText(hd.getMaHD());
            lbTongCong.setText(String.valueOf(new BUS_HoaDon().tongtien_Order(hd.getMaHD())));
        }
    }
}
