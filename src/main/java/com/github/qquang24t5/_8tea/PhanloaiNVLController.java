/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_PhanLoaiNVL;
import DTO.PhanLoaiNVL;
import com.github.qquang24t5._8tea.EightTeaApplication;
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
 * @author Admin
 */
public class PhanloaiNVLController implements Initializable {

    @FXML
    private TableView<PhanLoaiNVL> tblLoaiNVL;
    @FXML
    private TableColumn<PhanLoaiNVL, String> colMaLoaiNVL;
    @FXML
    private TableColumn<PhanLoaiNVL, String> colTenLoaiNVL;
    @FXML
    private TextField txtMaLoaiNVL;
    @FXML
    private TextField txtTenLoaiNVL;
    @FXML
    private Text txtBaoLoi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTablePLNVL();
    }  
    
    public void setTablePLNVL()
    {
        tblLoaiNVL.getItems().removeAll();
        ObservableList<PhanLoaiNVL> li = FXCollections.observableArrayList();
        ArrayList<PhanLoaiNVL> listplnvl = new BUS_PhanLoaiNVL().getListLoaiNVL();
        for(PhanLoaiNVL pl : listplnvl)
        {
            li.add(pl);
        }
        
        colMaLoaiNVL.setCellValueFactory(new PropertyValueFactory<PhanLoaiNVL, String> ("MaLoaiNVL"));
        colTenLoaiNVL.setCellValueFactory(new PropertyValueFactory<PhanLoaiNVL ,String >("TenLoaiNVL"));
        tblLoaiNVL.setItems(li);
    }

    @FXML
    private void backForm(MouseEvent event) throws IOException {
        EightTeaApplication.setRoot("home");
    }

    @FXML
    private void themLoaiNVL(ActionEvent event) {
        if(txtTenLoaiNVL.getText().isEmpty())
        {
            txtBaoLoi.setText("Vui lòng nhập đầy đủ thông tin !");
        }
        else
        {
            boolean xn =  EightTeaApplication.alertConf("Bạn muốn thêm Loại nguyên vật liệu mới ?");
            if(xn == true )
            {
                int value = new Random().nextInt((999999 - 100000) + 1) + 100000;
                String rdma = "LNVL"+String.valueOf(value);
                PhanLoaiNVL pl = new PhanLoaiNVL();
                pl.setMaLoaiNVL(rdma);
                pl.setTenLoaiNVL(txtTenLoaiNVL.getText());
                if(new BUS_PhanLoaiNVL().ThemLoaiNVL(pl))
                {
                    setReSet();
                    setTablePLNVL();
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
    private void suaLoaiNVL(ActionEvent event) {
        if(tblLoaiNVL.getSelectionModel().getSelectedItem() == null)
        {
            EightTeaApplication.alertInf("Vui lòng chọn dòng cần thực hiện !");
        }
        else
        {
            PhanLoaiNVL pl = tblLoaiNVL.getSelectionModel().getSelectedItem();
            pl.setMaLoaiNVL(txtMaLoaiNVL.getText());
            //EightTeaApplication.alertInf(ncc.getMaNCC());
            pl.setTenLoaiNVL(txtTenLoaiNVL.getText());
            boolean xn = EightTeaApplication.alertConf("Bạn có chắc muốn sửa không ?");
                if(xn == true)
                {
                    if(new BUS_PhanLoaiNVL().SuaLoaiNVL(pl.getMaLoaiNVL(), pl.getTenLoaiNVL()))
                    {
                        EightTeaApplication.alertInf("Sửa thành công !");
                        setReSet();
                        setTablePLNVL();
                    }
                    else{
                        EightTeaApplication.alertInf("Sửa thất bại !");
                    }
                }
           
        }
    }

    @FXML
    private void xoaLoaiNVL(ActionEvent event) {
        if(tblLoaiNVL.getSelectionModel().getSelectedItem() == null)
        {
            EightTeaApplication.alertInf("Vui lòng chọn dòng cần thực hiện !");
        }
        else
        {
            boolean xn = EightTeaApplication.alertConf("Bạn có chắc chắn muốn xóa ?");
            if(xn == true )
            {
                if(new BUS_PhanLoaiNVL().XoaLoaiNVL(txtMaLoaiNVL.getText()))
                {
                    setReSet();
                    setTablePLNVL();
                    EightTeaApplication.alertInf("Xoá thành công !");
                    
                }
                else 
                {
                    EightTeaApplication.alertInf("Xóa thất bại !");
                }
            }
        }
    }

    @FXML
    private void getDuLieu(MouseEvent event) {
        PhanLoaiNVL pl = tblLoaiNVL.getSelectionModel().getSelectedItem();
        txtMaLoaiNVL.setText(pl.getMaLoaiNVL());
        txtTenLoaiNVL.setText(pl.getTenLoaiNVL());
    }
    
    public void setReSet()
    {
        txtMaLoaiNVL.setText("");
        txtTenLoaiNVL.setText("");
        txtBaoLoi.setText("");
    }
    
}
