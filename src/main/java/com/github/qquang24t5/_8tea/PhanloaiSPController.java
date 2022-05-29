/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_PhanLoaiSP;
import DTO.PhanLoaiSP;
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
public class PhanloaiSPController implements Initializable {

    
    @FXML
    private TableColumn<PhanLoaiSP, String> colMaLoaiSP;
    @FXML
    private TableColumn<PhanLoaiSP, String> colTenLoaiSP;
    @FXML
    private TextField txtMaLoaiSP;
    @FXML
    private TextField txtTenLoaiSP;
    @FXML
    private Text txtBaoLoi;
    @FXML
    private TableView<PhanLoaiSP> tblLoaiSP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTablePLSP();
    }    

    @FXML
    private void backForm(MouseEvent event) throws IOException {
         EightTeaApplication.setRoot("home");
    }

    @FXML
    private void getDuLieu(MouseEvent event) {
        PhanLoaiSP pl = tblLoaiSP.getSelectionModel().getSelectedItem();
        txtMaLoaiSP.setText(pl.getMaLoaiSP());
        txtTenLoaiSP.setText(pl.getTenLoai());
    }

    @FXML
    private void themLoaiSP(ActionEvent event) {
         if(txtTenLoaiSP.getText().isEmpty())
        {
            txtBaoLoi.setText("Vui lòng nhập đầy đủ thông tin !");
        }
        else
        {
            boolean xn =  EightTeaApplication.alertConf("Bạn muốn thêm loại sản phẩm mới ?");
            if(xn == true )
            {
                int value = new Random().nextInt((99999 - 10000) + 1) + 10000;
                String rdma = "LSP"+String.valueOf(value);
                PhanLoaiSP pl = new PhanLoaiSP();
                pl.setMaLoaiSP(rdma);
                pl.setTenLoai(txtTenLoaiSP.getText());
                if(new BUS_PhanLoaiSP().ThemLoaiSP(pl))
                {
                    setReSet();
                    setTablePLSP();
                    EightTeaApplication.alertInf("Thêm thành công !");
                    
                }
                else
                {
                    EightTeaApplication.alertInf("Thêm thất bại !");
                }
            }
        }
    }

    @FXML
    private void suaLoaiSP(ActionEvent event) {
        if(tblLoaiSP.getSelectionModel().getSelectedItem() == null)
        {
            EightTeaApplication.alertInf("Vui lòng chọn dòng cần thực hiện !");
        }
        else
        {
            PhanLoaiSP pl = tblLoaiSP.getSelectionModel().getSelectedItem();
            pl.setMaLoaiSP(txtMaLoaiSP.getText());
            //EightTeaApplication.alertInf(ncc.getMaNCC());
            pl.setTenLoai(txtTenLoaiSP.getText());
            boolean xn = EightTeaApplication.alertConf("Bạn có chắc muốn sửa không ?");
                if(xn == true)
                {
                    if(new BUS_PhanLoaiSP().SuaLoaiSP(pl.getMaLoaiSP(), pl.getTenLoai()))
                    {
                        EightTeaApplication.alertInf("Sửa thành công !");
                        setReSet();
                        setTablePLSP();
                    }
                    else{
                        EightTeaApplication.alertInf("Sửa thất bại !");
                    }
                }
           
        }
    }

    @FXML
    private void xoaLoaiSP(ActionEvent event) {
        if(tblLoaiSP.getSelectionModel().getSelectedItem() == null)
        {
            EightTeaApplication.alertInf("Vui lòng chọn dòng cần thực hiện !");
        }
        else
        {
            boolean xn = EightTeaApplication.alertConf("Bạn có chắc chắn muốn xóa ?");
            if(xn == true )
            {
                if(new BUS_PhanLoaiSP().XoaLoaiSP(txtMaLoaiSP.getText()))
                {
                    setReSet();
                    setTablePLSP();
                    EightTeaApplication.alertInf("Xoá thành công !");
                    
                }
                else 
                {
                    EightTeaApplication.alertInf("Xóa thất bại !");
                }
            }
        }
    }
    public void setTablePLSP()
    {
        tblLoaiSP.getItems().removeAll();
        ObservableList<PhanLoaiSP> li = FXCollections.observableArrayList();
        ArrayList<PhanLoaiSP> listplsp = new BUS_PhanLoaiSP().getListLoaiSP();
        for(PhanLoaiSP pl : listplsp)
        {
            li.add(pl);
        }
        
        colMaLoaiSP.setCellValueFactory(new PropertyValueFactory<PhanLoaiSP, String> ("MaLoaiSP"));
        colTenLoaiSP.setCellValueFactory(new PropertyValueFactory<PhanLoaiSP ,String >("TenLoai"));
        tblLoaiSP.setItems(li);
    }
    public void setReSet()
    {
        txtMaLoaiSP.setText("");
        txtTenLoaiSP.setText("");
        txtBaoLoi.setText("");
    }
}
