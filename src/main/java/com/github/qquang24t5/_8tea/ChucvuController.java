/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_ChucNang;
import BUS.BUS_ChucVu;
import BUS.BUS_Quyen_ChucNang;
import DTO.ChucNang;
import DTO.ChucVu;
import DTO.Quyen_ChucNang;
import com.github.qquang24t5._8tea.EightTeaApplication;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author MINH TUAN
 */
public class ChucvuController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private TableView<ChucVu> tblChucvu;

    @FXML
    private TableColumn<ChucVu, String> colMaCV;
    @FXML
    private TableColumn<ChucVu, String> colTenCV;
    BUS_Quyen_ChucNang bqs = new BUS_Quyen_ChucNang();
    BUS_ChucNang bcn = new BUS_ChucNang();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setDSQuyen();
        setTableCV();
    }

    @FXML
    private void backHome(MouseEvent event) throws IOException {
        EightTeaApplication.setRoot("home");
    }

    
        ArrayList<ChucNang> listcn = bcn.getListCN();
        CheckBox[] list = new CheckBox[listcn.size()];
    public void setDSQuyen() {
        vbox.getChildren().clear();
        int i = 0;
        for (ChucNang cn : listcn) {
            list[i] = new CheckBox(cn.getTenCN());
            vbox.getChildren().add(list[i]);
            i++;
        }
        vbox.setSpacing(10);
    }

    public void setTableCV() {
        tblChucvu.getItems().removeAll();
        ObservableList<ChucVu> li = FXCollections.observableArrayList();
        ArrayList<ChucVu> listcv = new BUS_ChucVu().getListCV();
        for (ChucVu cv : listcv) {
            li.add(cv);
        }
        colMaCV.setCellValueFactory(new PropertyValueFactory<ChucVu, String>("MaCV"));
        colTenCV.setCellValueFactory(new PropertyValueFactory<ChucVu, String>("TenCV"));

        tblChucvu.setItems(li);
    }
    
    @FXML
    private void getDuLieu(MouseEvent event) {
         for(int i=0;i<list.length;i++)
            {
                list[i].setSelected(false);
            }
        ChucVu cv = tblChucvu.getSelectionModel().getSelectedItem();
        
        ArrayList<String> listquyen = bqs.getListQCN(cv.getMaCV());
       
//        for(String s : listquyen){
//            sc+=s;
//        }
        ArrayList<String> listq2 = new ArrayList<>();
        for(String s : listquyen){
            listq2.add(bcn.tenCN(s));
           
        }
       
        
        
            for(int i=0;i<list.length;i++)
            {
                for(String s:listq2)
                {
                    if(list[i].getText().equals(s))
                    {
                        list[i].setSelected(true);
                        break;
                    }
                }
            }
        
        
        
       
        
    }

    @FXML
    private void themCV(ActionEvent event) throws IOException {
         EightTeaApplication.setRoot("themcv");
    }

    @FXML
    private void suaCV(ActionEvent event) {
        if(EightTeaApplication.alertConf("Xác nhận thay đổi quyền của chức vụ ?")){
            String ma = tblChucvu.getSelectionModel().getSelectedItem().getMaCV();
         if(bqs.XoaQCN(ma)){
             for(int i=0;i<listcn.size();i++){
             if(list[i].isSelected()){
                Quyen_ChucNang qcn = new Quyen_ChucNang();
                qcn.setMaCN(bcn.maCN(list[i].getText()));
                qcn.setMaCV(ma);
                if(bqs.ThemQCN(qcn)){
                    
                }
             }
         }
             EightTeaApplication.alertInf("Đã sửa chức vụ");
             setDSQuyen();
             setTableCV();
         }
        }
         
         
        
    }

    @FXML
    private void xoaCV(ActionEvent event) {
        ChucVu cv = tblChucvu.getSelectionModel().getSelectedItem();
        if (EightTeaApplication.alertConf("Bạn có chắc chắn muốn xóa chức vụ này ?")) {
            if (bqs.XoaQCN(cv.getMaCV())) {
                if(new BUS_ChucVu().setTrongCVNV(cv.getMaCV()))
                {
                    if (new BUS_ChucVu().XoaCV(cv.getMaCV())) {
                    EightTeaApplication.alertInf("Đã xóa chức vụ");
                    setTableCV();
                    setDSQuyen();
                }
                }
                
            }

        }
    }

}
