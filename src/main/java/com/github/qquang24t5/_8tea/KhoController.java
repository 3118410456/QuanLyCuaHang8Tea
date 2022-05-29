/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_Kho;
import BUS.BUS_NhaCungCap;
import BUS.BUS_PhanLoaiNVL;
import DTO.Kho;
import DTO.NhaCungCap;
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
 * @author Admin
 */
public class KhoController implements Initializable {

    @FXML
    private TableView<Kho> tblKho;
    @FXML
    private TableColumn<Kho, String> colMaNVL;
    @FXML
    private TableColumn<Kho, String> colLoaiNVL;
    @FXML
    private TableColumn<Kho, String> colTenNVL;
    @FXML
    private TableColumn<Kho, Integer> colSoLuong;
    @FXML
    private TableColumn<Kho, String> colDonViTinh;
    @FXML
    private TableColumn<Kho, Double> colGiaNhap;
    @FXML
    private TextField txtMaNVL;
    @FXML
    private TextField txtTenNVL;
    @FXML
    private TextField txtSoLuong;
    @FXML
    private TextField txtGiaNhap;
    @FXML
    private ComboBox<String> cbLoaiNVL;
    
    @FXML
    private Text txtBaoLoi;
    @FXML
    private ComboBox<String> cbDVT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setList();
        setTable();
        
    }   
    
    public void setTable()   {
        tblKho.getItems().removeAll();
        ArrayList<Kho> listkho = new BUS_Kho().getListNVL();
        ObservableList<Kho> li = FXCollections.observableArrayList();
        
        
        for(Kho nvl : listkho)
        {
            li.add(nvl);
        }
       
        colMaNVL.setCellValueFactory(new PropertyValueFactory<Kho, String>("MaNVL"));
        colLoaiNVL.setCellValueFactory(new PropertyValueFactory<Kho, String>("MaLoaiNVL"));
        colTenNVL.setCellValueFactory(new PropertyValueFactory<Kho, String>("TenNVL"));
        colSoLuong.setCellValueFactory(new PropertyValueFactory<Kho, Integer>("SoLuong"));
        colDonViTinh.setCellValueFactory(new PropertyValueFactory<Kho, String>("DonViTinh"));
        colGiaNhap.setCellValueFactory(new PropertyValueFactory<Kho, Double>("DonGiaNhap"));
        tblKho.setItems(li);
        
    }
    
    public void setList(){
        ArrayList<PhanLoaiNVL> listLoaiNVL = new BUS_PhanLoaiNVL().getListLoaiNVL();
        ObservableList<String> obloai = FXCollections.observableArrayList();
        for(PhanLoaiNVL pl : listLoaiNVL)
        {
            obloai.add(pl.getTenLoaiNVL());
        }
        obloai.add("Chưa có");
        cbLoaiNVL.setItems(obloai);
        cbLoaiNVL.getSelectionModel().selectLast();
        
        //ArrayList<NhaCungCap> listncc = new BUS_NhaCungCap().getListNCC();
        ObservableList<String> obdvt = FXCollections.observableArrayList();
        obdvt.add("ml");
        obdvt.add("gram");
        obdvt.add("Chưa có");
        cbDVT.setItems(obdvt);
        cbDVT.getSelectionModel().selectLast();
        
        
    }
    
    public void setReSet()
    {
        txtMaNVL.setText("");
        cbLoaiNVL.getSelectionModel().selectLast();
        txtTenNVL.setText("");
        txtSoLuong.setText("");
        cbDVT.getSelectionModel().selectLast();
        txtGiaNhap.setText("");
        
    }

    @FXML
    private void backForm(MouseEvent event) throws IOException {
        EightTeaApplication.setRoot("home");
    }

    @FXML
    private void themNVL(ActionEvent event) {
        int rd = new Random().nextInt((9999999 - 1000000) + 1 ) + 1000000;
        String maNVL = "NVL" + rd ;
        
        if(cbLoaiNVL.getSelectionModel().getSelectedItem() == null || txtTenNVL.getText().isEmpty() 
                || txtSoLuong.getText().isEmpty() || cbDVT.getSelectionModel().getSelectedItem().equals("Chưa có")
                || txtGiaNhap.getText().isEmpty() )
        {
            EightTeaApplication.alertInf("Vui lòng nhập đầy đủ thông tin !");
        }
        else
        {
            Kho nvl = new Kho();
            nvl.setMaNVL(maNVL);
            nvl.setMaLoaiNVL(String.valueOf(cbLoaiNVL.getSelectionModel().getSelectedItem()));
            nvl.setTenNVL(txtTenNVL.getText());
            nvl.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
            nvl.setDonViTinh(String.valueOf(cbDVT.getSelectionModel().getSelectedItem()));
            nvl.setDonGiaNhap(Double.parseDouble(txtGiaNhap.getText()));
            
            
            if(!String.valueOf(nvl.getSoLuong()).matches("[0-9]{1,4}"))
            {
                txtBaoLoi.setText("Số lượng không hợp lệ !");
            }
            else{
                boolean xn = EightTeaApplication.alertConf("Bạn có muốn thêm NGUYÊN VẬT LIỆU ?");
                if(xn == true)
                {
                    if(new BUS_Kho().ThemNVL(nvl))
                    {
                        
                        setReSet();
                        setTable();
                        setList();
                        EightTeaApplication.alertInf("Thêm thành công !");
                        
                    }
                    else {
                        EightTeaApplication.alertInf("Thêm thất bại !");
                    }
                }
            }
            
            
        }
    }

    @FXML
    private void SuaNVL(ActionEvent event) {
        if(tblKho.getSelectionModel().getSelectedItem() == null)
        {
            EightTeaApplication.alertInf("Chọn dòng để thực hiện !");
        }
        else
        {
            if(cbLoaiNVL.getSelectionModel().getSelectedItem() == null || txtTenNVL.getText().isEmpty() 
                || txtSoLuong.getText().isEmpty() || cbDVT.getSelectionModel().getSelectedItem() == null 
                || txtGiaNhap.getText().isEmpty() )
        {
            EightTeaApplication.alertInf("Vui lòng nhập đầy đủ thông tin !");
        }
        else
        {
            Kho nvl = new Kho();
            nvl.setMaNVL(txtMaNVL.getText());
            String TenLoai = cbLoaiNVL.getSelectionModel().getSelectedItem();
            nvl.setMaLoaiNVL(new BUS_PhanLoaiNVL().maLoaiNVL(TenLoai));
            nvl.setTenNVL(txtTenNVL.getText());
            nvl.setSoLuong(Integer.parseInt(txtSoLuong.getText()));
            String dvt = cbDVT.getSelectionModel().getSelectedItem();
            nvl.setDonViTinh(dvt);
            nvl.setDonGiaNhap(Double.parseDouble(txtGiaNhap.getText()));
            
            
            
            if(!String.valueOf(nvl.getSoLuong()).matches("[0-9]{1,4}"))
            {
                txtBaoLoi.setText("Số lượng không hợp lệ !");
            }
            else{
                boolean xn = EightTeaApplication.alertConf("Bạn có muốn sửa NGUYÊN VẬT LIỆU ?");
                if(xn == true)
                {
                    if(new BUS_Kho().SuaNVL(nvl.getMaNVL(), nvl.getMaLoaiNVL(), nvl.getTenNVL()
                            , nvl.getSoLuong(), nvl.getDonViTinh(), nvl.getDonGiaNhap(), nvl.getMaNCC()))
                    {
                        
                        setReSet();
                        setTable();
                        setList();
                        EightTeaApplication.alertInf("Sửa thành công !");
                        
                    }
                    else {
                        EightTeaApplication.alertInf("Sửa thất bại !");
                    }
                }
            }
            
            
        }
        }
        
    }

    @FXML
    private void xoaNVL(ActionEvent event) {
        if(tblKho.getSelectionModel().getSelectedItem() == null)
        {
            EightTeaApplication.alertInf("Chọn dòng cần thực hiện !");
        }
        else
        {
            boolean xn = EightTeaApplication.alertConf("Bạn có chắc chắn muốn xóa ?");
            if(xn == true)
            {
                if(new BUS_Kho().XoaNVL(String.valueOf(txtMaNVL.getText())))
                {
                    setReSet();
                    setTable();
                    setList();
                    EightTeaApplication.alertInf("Xóa thành công !");
                }
                else{
                    EightTeaApplication.alertInf("Xóa thất bại !");
                }
            }
        }
    }

    @FXML
    private void getDuLieu(MouseEvent event) {
        if(tblKho.getSelectionModel().getSelectedItem() != null)
        {
            Kho k = tblKho.getSelectionModel().getSelectedItem();
            txtMaNVL.setText(k.getMaNVL());
            cbLoaiNVL.setValue(k.getMaLoaiNVL());
            txtTenNVL.setText(k.getTenNVL());
            txtSoLuong.setText(String.valueOf(k.getSoLuong()));
            cbDVT.setValue(k.getDonViTinh());
            txtGiaNhap.setText(String.valueOf(k.getDonGiaNhap()));
            
        }
    }
    
}
