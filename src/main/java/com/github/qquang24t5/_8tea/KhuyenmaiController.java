/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;


import BUS.BUS_KhuyenMai;
import DTO.KhuyenMai;
import java.io.IOException;
import java.net.URL;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MINH TUAN
 */
public class KhuyenmaiController implements Initializable {

    @FXML
    private AnchorPane txt;
    @FXML
    private Text txtBaoLoi;
    @FXML
    private TableView<KhuyenMai> tblKM;
    @FXML
    private TableColumn<KhuyenMai, String> colMaKM;
    @FXML
    private TableColumn<KhuyenMai , String> colTenKM;
    @FXML
    private TableColumn<KhuyenMai , String> colNgayBD;
    @FXML
    private TableColumn<KhuyenMai , String> colNgayKT;
    @FXML
    private TableColumn<KhuyenMai , String> colPhanTram;
    @FXML
    private TextField txtMaKM;
    @FXML
    private TextField txtTenKM;
    @FXML
    private DatePicker txtNgayBD;
    @FXML
    private DatePicker txtNgayKT;
    @FXML
    private TextField txtPhanTram;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtBaoLoi.setFill(Color.RED);
        getTable();
        
    }  
    
    public void getTable()  {
        tblKM.getItems().removeAll();
        ObservableList<KhuyenMai> oblist = FXCollections.observableArrayList();
        ArrayList<KhuyenMai> list = new BUS_KhuyenMai().getListKM();
        for(KhuyenMai km : list)
        {
            oblist.add(km);
        }
        colMaKM.setCellValueFactory(new PropertyValueFactory<KhuyenMai , String> ("MaKM"));
        colTenKM.setCellValueFactory(new PropertyValueFactory<KhuyenMai ,String>("TenKM"));
        colNgayBD.setCellValueFactory(new PropertyValueFactory<KhuyenMai ,String> ("NgayBD"));
        colNgayKT.setCellValueFactory(new PropertyValueFactory<KhuyenMai ,String> ("NgayKT"));
        colPhanTram.setCellValueFactory(new PropertyValueFactory<KhuyenMai ,String> ("PhanTramKM"));
        tblKM.setItems(oblist);
    }
    
    @FXML
    public void getDuLieu() {
        
        if(tblKM.getSelectionModel().getSelectedCells() != null)
        {
            KhuyenMai km = tblKM.getSelectionModel().getSelectedItem();
            txtMaKM.setText(km.getMaKM());
            txtTenKM.setText(km.getTenKM());
            txtPhanTram.setText(String.valueOf(km.getPhanTramKM()));
            
            DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate bd = LocalDate.parse(km.getNgayBD() , fm);
            LocalDate kt = LocalDate.parse(km.getNgayKT() , fm);
            
            
            
            txtNgayBD.setValue(bd);
            txtNgayKT.setValue(kt);
            
        }
    }

    @FXML
    private void backHome(MouseEvent event) throws IOException {
        EightTeaApplication.setRoot("home");
    }


    @FXML
    private void themKM(ActionEvent event) {
        txtBaoLoi.setText("");
        int value = new Random().nextInt((99999999 - 10000000) + 1) +10000000;
        String rm = "KM" + String.valueOf(value);
        
        if(txtTenKM.getText().isEmpty() || txtNgayBD.getValue() == null || txtNgayKT.getValue() == null || txtPhanTram.getText().isEmpty())
        {
            txtBaoLoi.setText("Vui lòng nhập đầy đủ thông tin !!!");
        }
        else 
        {
            DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate bd = txtNgayBD.getValue();
            LocalDate kt = txtNgayKT.getValue();
            LocalDate now = LocalDate.now();
            
            KhuyenMai km = new KhuyenMai();
            km.setMaKM(rm);
            km.setTenKM(txtTenKM.getText());
            km.setNgayBD(bd.format(fm));
            km.setNgayKT(kt.format(fm));
            km.setPhanTramKM(Integer.parseInt(txtPhanTram.getText()));
            
            
            if(kt.compareTo(bd) < 0 )
            {
                txtBaoLoi.setText("Ngày kết thúc không được nhỏ hơn ngày bắt đầu !!!");
            }
            if(bd.compareTo(now) < 0)
            {
                txtBaoLoi.setText("Ngày bắt đầu không được nhỏ hơn ngày hiện tại !!!");
            }
            if(Integer.parseInt(txtPhanTram.getText()) > 70)
            {
                txtBaoLoi.setText("Phần trăm khuyến mãi không được hơn 70%");
            }
            if(txtBaoLoi.getText() == "")
            {
                
                if(new BUS_KhuyenMai().themKM(km))
                {
                    EightTeaApplication.alertInf("Thêm thành công !");
                    getTable();
                    ReSet();
                }
                else
                {
                    EightTeaApplication.alertInf("Thêm thất bại !");
                }
            }
        }
        
    }

    @FXML
    private void xoaKM(ActionEvent event) {
        KhuyenMai km = tblKM.getSelectionModel().getSelectedItem();
        if (EightTeaApplication.alertConf("Bạn có chắc chắn muốn xóa KHUYẾN MÃI này ?")) {            
            if (new BUS_KhuyenMai().xoaKM(km.getMaKM()) == true) {
                EightTeaApplication.alertInf("Đã xóa !");
                getTable();
                ReSet();
            }
            else {
                EightTeaApplication.alertInf("Xóa thất bại !");
            }
   
        }
    }
    
    public void ReSet() {
        txtMaKM.setText("");
        txtTenKM.setText("");
        txtNgayBD.setValue(null);
        txtNgayKT.setValue(null);
        txtPhanTram.setText("");
    }
    
}
