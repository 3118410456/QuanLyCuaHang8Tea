/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import DAO.DAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MINH TUAN
 */
public class LoginController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private TextField txtTaikhoan;
    @FXML
    private PasswordField txtMatkhau;
    @FXML
    private ImageView lbLogo;
    @FXML
    private Text lbLoi;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @FXML
    public void dangnhap(ActionEvent event) throws IOException {

        
        String manv = txtTaikhoan.getText();
        String pass = txtMatkhau.getText();
        
        if (manv.isEmpty() || pass.isEmpty()) {
            setError("Hãy nhập đẩy đủ thông tin !");
        } else {
            String check = new DAO().ktraDangNhap(manv, pass);

            if (check != null) {
                if (check.equals("LOCK")) {
                    setError("Tài khoản này hiện đang bị khóa");
                } else if (check.equals(manv)) {
                    EightTeaApplication.userhientai = check;
                    EightTeaApplication.setRoot("home");
                }

            }
             else {

                    setError("Sai tài khoản hoặc mật khẩu");

                }
        }
    }

    public void setError(String error) {
        lbLoi.setText("");
        lbLoi.setText(error);
    }
    
    
}
