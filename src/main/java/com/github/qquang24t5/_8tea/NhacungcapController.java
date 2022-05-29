/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_NhaCungCap;
import DTO.NhaCungCap;
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
import static javafx.scene.paint.Color.RED;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class NhacungcapController implements Initializable {

    @FXML
    private TextField txtMaNCC;
    @FXML
    private TextField txtTenNCC;
    @FXML
    private TextField txtSDT;
    @FXML
    private Text txtBaoLoi;
    @FXML
    private TableColumn<NhaCungCap, String> colMaNCC;
    @FXML
    private TableColumn<NhaCungCap, String> colTenNCC;
    @FXML
    private TableColumn<NhaCungCap, String> colSDT;
    @FXML
    private TableView<NhaCungCap> tblNCC;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableNCC();
    }    
    
    public void setTableNCC()
    {
        tblNCC.getItems().removeAll();
        ObservableList<NhaCungCap> li = FXCollections.observableArrayList();
        ArrayList<NhaCungCap> listncc = new BUS_NhaCungCap().getListNCC();
        for(NhaCungCap ncc:listncc)
        {
            li.add(ncc);
        }
        colMaNCC.setCellValueFactory(new PropertyValueFactory<NhaCungCap, String>("MaNCC"));
        colTenNCC.setCellValueFactory(new PropertyValueFactory<NhaCungCap, String>("TenNCC"));
        colSDT.setCellValueFactory(new PropertyValueFactory<NhaCungCap, String>("SDT"));
        tblNCC.setItems(li);
    }
    
    public void setReset(){
        txtMaNCC.setText("");
        txtTenNCC.setText("");
        txtSDT.setText("");
    }

    @FXML
    private void themNCC(ActionEvent event) {
        txtBaoLoi.setText("");
        txtBaoLoi.setFill(RED);
        int value = new Random().nextInt((9999999 - 1000000) + 1) + 1000000;
        String rdma = "NCC"+String.valueOf(value);
        if(txtTenNCC.getText().isEmpty() || txtSDT.getText().isEmpty())
        {
            txtBaoLoi.setText("Vui lòng nhập đầy đủ thông tin !");
        }
        else
        {
            boolean xn = EightTeaApplication.alertConf("Bạn muốn thêm NHÀ CUNG CẤP mới !");
            if(xn == true)
            {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rdma);
                ncc.setTenNCC(txtTenNCC.getText());
                ncc.setSDT(txtSDT.getText());
                if(!ncc.getSDT().matches("0[0-9]{9,10}"))
                {
                    txtBaoLoi.setText("Số điện thoại không hợp lệ");
                }
                else
                {

                    if(new BUS_NhaCungCap().ThemNCC(ncc))
                    {
                        EightTeaApplication.alertInf("Thêm thành công NHÀ CÔNG CẤP");
                        setTableNCC();
                        setReset();
                    }
                    else
                    {
                        EightTeaApplication.alertInf("Thêm thất bại");
                    }
                }
            }
        }
    }
    

    @FXML
    private void suaNCC(ActionEvent event) {
        if(tblNCC.getSelectionModel().getSelectedItem() == null)
        {
            EightTeaApplication.alertInf("Vui lòng chọn dòng cần thực hiện !");
        }
        else
        {
            NhaCungCap ncc = tblNCC.getSelectionModel().getSelectedItem();
            ncc.setMaNCC(txtMaNCC.getText());
            //EightTeaApplication.alertInf(ncc.getMaNCC());
            ncc.setTenNCC(txtTenNCC.getText());
            ncc.setSDT(txtSDT.getText());
            if(!ncc.getSDT().matches("0[0-9]{9,10}"))
            {
                txtBaoLoi.setText("Số điện thoại không hợp lê !");
            }
            else
            {
                boolean xn = EightTeaApplication.alertConf("Bạn có chắc muốn sửa không ?");
                if(xn == true)
                {
                    if(new BUS_NhaCungCap().SuaNCC(ncc.getMaNCC(), ncc.getTenNCC(), ncc.getSDT()))
                    {
                        EightTeaApplication.alertInf("Sửa thành công !");
                        setReset();
                        setTableNCC();
                    }
                    else{
                        EightTeaApplication.alertInf("Sửa thất bại !");
                    }
                }
                
            }
        }
    }

    @FXML
    private void xoaNCC(ActionEvent event) {
        if(tblNCC.getSelectionModel().getSelectedItem() == null)
        {
            EightTeaApplication.alertInf("Vui lòng chọn dòng cần thực hiện !");
        }
        else
        {
            boolean xn = EightTeaApplication.alertConf("Bạn có chắc chắn muốn xóa !");
            if(xn == true)
            {
                if(new BUS_NhaCungCap().XoaNCC(String.valueOf(txtMaNCC.getText())))
                {
                    EightTeaApplication.alertInf("Xóa thành công !");
                    setReset();
                    setTableNCC();
                }
                else
                {
                    EightTeaApplication.alertInf("Xóa thất bại !");
                }
            }
        }
    }

    @FXML
    private void backHome(MouseEvent event) throws IOException {
            EightTeaApplication.setRoot("home");    

    }

    @FXML
    private void getDuLieu(MouseEvent event) {
        if(tblNCC.getSelectionModel().getSelectedItem() != null)
        {
            NhaCungCap ncc = tblNCC.getSelectionModel().getSelectedItem();
            txtMaNCC.setText(ncc.getMaNCC());
            txtTenNCC.setText(ncc.getTenNCC());
            txtSDT.setText(ncc.getSDT());
        }
    }

    
    
}
