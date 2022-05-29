/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_NhanVien;
import DTO.NhanVien;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MINH TUAN
 */
public class DoimatkhauController implements Initializable {

    @FXML
    private AnchorPane txt;
    @FXML
    private Label txtBaoLoi;
    @FXML
    private PasswordField txtMKCu;
    @FXML
    private PasswordField txtMKMoi;
    @FXML
    private PasswordField txtXacnhanMK;
    @FXML
    private Label lbThongbao1;
    @FXML
    private Label lbThongbao2;
    @FXML
    private Label lbMK;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txtBaoLoi.setTextFill(Color.RED);
        lbThongbao1.setTextFill(Color.RED);
        lbThongbao2.setTextFill(Color.RED);
        lbMK.setTextFill(Color.RED);
    }    

    @FXML
    private void backHome(MouseEvent event) throws IOException {
        EightTeaApplication.setRoot("home");
    }

    @FXML
    private void doiMK(ActionEvent event) {
        String mk = new BUS_NhanVien().MK(EightTeaApplication.userhientai);
        
        
        txtBaoLoi.setText("");
        lbThongbao1.setText("");
        lbThongbao2.setText("");
        lbMK.setText("");
        if(txtMKCu.getText().isEmpty() || txtMKMoi.getText().isEmpty() || txtXacnhanMK.getText().isEmpty())
        {
            txtBaoLoi.setText("Vui lòng nhập đầy đủ thông tin !!!");
        }
        else 
        {
            
            if(txtMKCu.getText().equals(mk))
            {
                
            }
            else{
                lbMK.setText("Mật khẩu cũ không đúng !!!");
            }
            if(txtMKMoi.getText().matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
                {
                    
                }
                else
                {
                    lbThongbao1.setText("Mật khẩu mới không hợp lệ !!!");
                }
            if(txtMKCu.getText().equals(txtMKMoi.getText()))
            {
                
                lbThongbao1.setText("Mật khẩu mới không được TRÙNG mật khẩu cũ");
            }
            else{
                
            }
            
            if(txtMKMoi.getText().equals(txtXacnhanMK.getText()) && lbMK.getText().isEmpty() 
                    && lbThongbao1.getText().isEmpty() && lbThongbao2.getText().isEmpty() && txtBaoLoi.getText().isEmpty() )
                    {
                        boolean xn = EightTeaApplication.alertConf("Bạn có chắn muốn đổi mật khẩu !");
                        if(xn == true)
                        {
                            if(new BUS_NhanVien().DoiMatKhau(EightTeaApplication.userhientai, txtMKMoi.getText()))
                            {
                                EightTeaApplication.alertInf("Đổi mật khẩu thành công !");
                                ReSet();
                            }
                            else
                            {
                                EightTeaApplication.alertInf("Thất bại !!!");
                            }
                        }
                    }
                    else 
                    {
                        lbThongbao2.setText("Xác nhận mật khẩu mới KHÔNG KHỚP !!!");
                    }
            
            
        }
    }
    
    public void ReSet()
    {
        txtMKCu.setText("");
        txtMKMoi.setText("");
        txtXacnhanMK.setText("");
    }
    
}
