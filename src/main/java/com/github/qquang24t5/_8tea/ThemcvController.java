/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_ChucNang;
import BUS.BUS_ChucVu;
import BUS.BUS_Quyen_ChucNang;
import DTO.
        ChucNang;
import DTO.ChucVu;
import DTO.Quyen_ChucNang;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author MINH TUAN
 */
public class ThemcvController implements Initializable {

    @FXML
    private TableView<ChucNang> tblQuyen;
    @FXML
    private TableColumn<ChucNang, String> colMaQuyen;
    @FXML
    private TableColumn<ChucNang, String> colTenQuyen;
    @FXML
    private Button btnThem;
    @FXML
    private TextField txtTenQuyen;
    @FXML
    private TextField txtMaQuyen;
    @FXML
    private VBox vbox;
    @FXML
    private TextField txtTenCV;
    @FXML
    private TextField txtMaCV;

    /**
     * Initializes the controller class.
     */
    ArrayList<ChucNang> listcn;
    CheckBox[] list;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setDSQuyen();
        setTableChucNang();
        
    }    

    @FXML
    private void backFormCV(MouseEvent event) throws IOException {
        EightTeaApplication.setRoot("chucvu");
    }
    
    public void setTableChucNang()
    {
        tblQuyen.getItems().removeAll();
        ObservableList<
                ChucNang> li = FXCollections.observableArrayList();
        ArrayList<
                ChucNang> listcn = new BUS_ChucNang().getListCN();
        for(ChucNang cn:listcn)
        {
            li.add(cn);
        }
        colMaQuyen.setCellValueFactory(new PropertyValueFactory<ChucNang, String>("MaCN"));
        colTenQuyen.setCellValueFactory(new PropertyValueFactory<ChucNang, String>("TenCN"));
        
        tblQuyen.setItems(li);
    }

    @FXML
    private void themQuyen(ActionEvent event) {
        ArrayList<String> ers = new ArrayList<>();
        if(txtMaQuyen.getText().isEmpty() || txtTenQuyen.getText().isEmpty())
        {
           EightTeaApplication.alertInf("Hãy nhập đầy đủ thông tin !");
        }
        else
        {
        ChucNang cn = new ChucNang();
        cn.setMaCN(txtMaQuyen.getText());
        cn.setTenCN(txtTenQuyen.getText());
        if(new BUS_ChucNang().checkTenCN(cn.getTenCN()))
        {
            EightTeaApplication.alertInf("Tên quyền bị trùng !");
        }
        else
        {
        if(new BUS_ChucNang().ThemCN(cn))
            {
                EightTeaApplication.alertInf("Đã thêm quyền chức năng mới");
                setTableChucNang();
                setDSQuyen();
            }
            else
            {
                EightTeaApplication.alertInf("Mã quyền bị trùng !");
            }
                
            }
        }
    }
     
    
    public void setDSQuyen()
    {
        vbox.getChildren().clear();
        listcn = new BUS_ChucNang().getListCN();
        list = new CheckBox[listcn.size()];
        int i = 0;
        for(ChucNang cn : listcn)
        {
            list[i] = new CheckBox();
            list[i].setText(cn.getTenCN());
            vbox.getChildren().add(list[i]);
            i++;
        }
        vbox.setSpacing(10);
    }

    @FXML
    private void themCV(ActionEvent event) {
        String ma = txtMaCV.getText();
        String ten = txtTenCV.getText();
        if(ma.isEmpty()||ten.isEmpty())
        {
            EightTeaApplication.alertInf("Hãy nhập đầy đủ thông tin trước");
        }
        else
        {
        BUS_ChucNang buscn = new BUS_ChucNang();
        BUS_Quyen_ChucNang qcn = new BUS_Quyen_ChucNang();
        
       
        
        ArrayList<String> dsmacn = new ArrayList<>();
        for(int i=0;i<listcn.size();i++)
        {
            if(list[i].isSelected())
            {
                dsmacn.add(buscn.maCN(list[i].getText()));
            }
                
        }
        ChucVu vc = new ChucVu();
        vc.setMaCV(ma);
        vc.setTenCV(ten);
        if(new BUS_ChucVu().ThemCV(vc))
        for(String s : dsmacn)
        {
           
            Quyen_ChucNang qc = new Quyen_ChucNang();
            qc.setMaCV(ma);
            qc.setMaCN(s);
            if(qcn.ThemQCN(qc))
            {
                
            }
        }
        EightTeaApplication.alertInf("Đã thêm chức vụ mới");
        }
    }

    @FXML
    private void getDuLieu(MouseEvent event) {
         if(tblQuyen.getSelectionModel().getSelectedItem() != null)
        {
            ChucNang cn = tblQuyen.getSelectionModel().getSelectedItem();
            txtMaQuyen.setText(cn.getMaCN());
            txtTenQuyen.setText(cn.getTenCN());
            txtMaQuyen.setDisable(true);
        }
    }

    @FXML
    private void xoaCN(ActionEvent event) {
        if(tblQuyen.getSelectionModel().getSelectedItem() == null)
        {
            EightTeaApplication.alertInf("Chọn dòng để thực hiện !");
        }
        else{
            boolean xn = EightTeaApplication.alertConf("Bạn có chắc chắn muốn xóa ?");
            if(xn == true)
            {
                ChucNang cn = tblQuyen.getSelectionModel().getSelectedItem();
                if(new BUS_Quyen_ChucNang().XoaDSCN(cn.getMaCN()))
                {
                    new BUS_ChucNang().XoaCN(String.valueOf(cn.getMaCN()));
                setTableChucNang();
                setDSQuyen();
		txtMaQuyen.setText("");
		txtTenQuyen.setText("");
                EightTeaApplication.alertInf("Xóa thành công !");
                }
                
            }
        }
    }

    @FXML
    private void suaQuyen(ActionEvent event) {
         String tenQuyen = txtTenQuyen.getText();
        if(tblQuyen.getSelectionModel().getSelectedItem() == null)
        {
            EightTeaApplication.alertInf("Chọn dòng để thực hiện !");
        }
        else{
            boolean xn = EightTeaApplication.alertConf("Bạn có chắc chắn muốn sửa ?");
            if(xn == true)
            {
                ChucNang cn = tblQuyen.getSelectionModel().getSelectedItem();
                cn.setTenCN(String.valueOf(txtTenQuyen));
                new BUS_ChucNang().SuaCN(String.valueOf(cn.getMaCN()), tenQuyen);
                setTableChucNang();
                setDSQuyen();
		txtMaQuyen.setText("");
		txtTenQuyen.setText("");
                txtMaQuyen.setDisable(false);
                EightTeaApplication.alertInf("Sửa thành công !");
            }
        }
    }

    private void clearvbox(ActionEvent event) {
        vbox.getChildren().clear();
    }
}
