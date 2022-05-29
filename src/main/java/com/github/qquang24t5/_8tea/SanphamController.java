/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_PhanLoaiSP;
import BUS.BUS_SanPham;
import DTO.PhanLoaiSP;
import DTO.SanPham;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MINH TUAN
 */
public class SanphamController implements Initializable {

    @FXML
    private TableView<SanPham> tblSP;
    @FXML
    private TextField txtMaSP;
    @FXML
    private TextField txtSP;
    @FXML
    private TextField txtGiaBan;
    @FXML
    private ComboBox cbLoaiSP;
    @FXML
    private Text txtBaoLoi;
    @FXML
    private TableColumn<SanPham, String> colMaSP;
    @FXML
    private TableColumn<SanPham, String> colLoaiSP;
    @FXML
    private TableColumn<SanPham, String> colTenSP;
    @FXML
    private TableColumn<SanPham, Double> colGiaBan;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableSP();
        setComboLoaiSP();
    }    

    @FXML
    private void backForm(MouseEvent event) throws IOException {
        EightTeaApplication.setRoot("home");
    }

    @FXML
    private void getDuLieu(MouseEvent event) {
         if(tblSP.getSelectionModel().getSelectedItem() != null)
        {
            SanPham sp = tblSP.getSelectionModel().getSelectedItem();
            txtMaSP.setText(sp.getMaSP());
            txtSP.setText(sp.getTenSP());
            txtGiaBan.setText(String.valueOf(sp.getGiaBan()));
            cbLoaiSP.setValue(sp.getMaLoaiSP());
            
        }
    }

    @FXML
    private void themSP(ActionEvent event) {
         int rd = new Random().nextInt((99999 - 10000) + 1 ) + 10000;
        String maSP = "SP" + rd ;
        
        if(cbLoaiSP.getSelectionModel().getSelectedItem() == null || txtSP.getText().isEmpty() 
                || txtGiaBan.getText().isEmpty())
        {
            txtBaoLoi.setText("Vui lòng nhập đầy đủ thông tin !");
        }
        else
        {
            SanPham s = new SanPham();
            s.setMaSP(maSP);
            s.setTenSP(txtSP.getText());
            s.setGiaBan(Double.parseDouble(txtGiaBan.getText()));
            s.setMaLoaiSP(String.valueOf(cbLoaiSP.getSelectionModel().getSelectedItem()));
            if(new BUS_SanPham().ThemSP(s))
            {
                EightTeaApplication.alertInf("Đã thêm sản phẩm mới.");
                setTableSP();
            }
            else
            {
                EightTeaApplication.alertInf("Có lỗi xảy ra, hãy thử lại !");
            }
            
        }
    }

    @FXML
    private void suaSP(ActionEvent event) {
         if(tblSP.getSelectionModel().getSelectedItem() == null)
        {
            EightTeaApplication.alertInf("Vui lòng chọn dòng cần thực hiện !");
        }
        else
        {
            SanPham sp = tblSP.getSelectionModel().getSelectedItem();
            sp.setMaLoaiSP(new BUS_PhanLoaiSP().layMaLoaiSP(String.valueOf(cbLoaiSP.getSelectionModel().getSelectedItem())));
            //EightTeaApplication.alertInf(ncc.getMaNCC());
            sp.setTenSP(txtSP.getText());
            sp.setGiaBan(Double.parseDouble(txtGiaBan.getText()));
            boolean xn = EightTeaApplication.alertConf("Bạn có chắc muốn sửa không ?");
                if(xn == true)
                {
                    if(new BUS_SanPham().SuaSP(sp))
                    {
                        EightTeaApplication.alertInf("Sửa thành công !");
                        
                        setTableSP();
                    }
                    else{
                        EightTeaApplication.alertInf("Sửa thất bại !");
                    }
                }
           
        }
    }

    @FXML
    private void xoaSP(ActionEvent event) {
         if(tblSP.getSelectionModel().getSelectedItem() == null)
        {
            EightTeaApplication.alertInf("Vui lòng chọn dòng cần thực hiện !");
        }
        else
        {
            boolean xn = EightTeaApplication.alertConf("Bạn có chắc chắn muốn xóa ?");
            if(xn == true )
            {
                if(new BUS_SanPham().XoaSP(txtMaSP.getText()))
                {
                    
                    setTableSP();
                    EightTeaApplication.alertInf("Xoá thành công !");
                    
                }
                else 
                {
                    EightTeaApplication.alertInf("Xóa thất bại !");
                }
            }
        }
    }
    public void setTableSP()
    {
        tblSP.getItems().removeAll();
        ObservableList<SanPham> li = FXCollections.observableArrayList();
        ArrayList<SanPham> listsp = new BUS_SanPham().getListSP();
        for(SanPham sp : listsp)
        {
            li.add(sp);
        }
        
        colMaSP.setCellValueFactory(new PropertyValueFactory<SanPham, String> ("MaSP"));
        colLoaiSP.setCellValueFactory(new PropertyValueFactory<SanPham ,String >("MaLoaiSP"));
        colTenSP.setCellValueFactory(new PropertyValueFactory<SanPham, String> ("TenSP"));
        colGiaBan.setCellValueFactory(new PropertyValueFactory<SanPham ,Double>("GiaBan"));
        tblSP.setItems(li);
    }
    public void setComboLoaiSP()
    {
        ObservableList<String> list = FXCollections.observableArrayList();
        ArrayList<PhanLoaiSP> listplsp = new BUS_PhanLoaiSP().getListLoaiSP();
        
        for(PhanLoaiSP s:listplsp)
        {
            list.add(s.getTenLoai());
        }
        list.add("Chưa có");
        cbLoaiSP.setItems(list);
        cbLoaiSP.getSelectionModel().selectFirst();
        
        
    }
}
