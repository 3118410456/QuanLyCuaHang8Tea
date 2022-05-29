/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_CongThuc;
import BUS.BUS_Kho;
import BUS.BUS_SanPham;
import DTO.BangCongThuc;
import DTO.Kho;
import DTO.SanPham;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
public class CongthucController implements Initializable {

    @FXML
    private TableView<SanPham> tblSP;
    @FXML
    private TableColumn<SanPham, String> colMaSP;
    @FXML
    private TableColumn<SanPham, String> colTenSP;
    @FXML
    private ComboBox cbNL;
    @FXML
    private TextField txtSL;
    @FXML
    private TableColumn<BangCongThuc, String> colNL;
    @FXML
    private TableColumn<BangCongThuc, Integer> colSL;
    @FXML
    private TableColumn<BangCongThuc, String> colDVT;
    @FXML
    private TableView<BangCongThuc> tblCT;
    @FXML
    private Text txtChonMASP;
    @FXML
    private Text txtMaSPAN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableSP();
        setComboBoxNL();
    }

    @FXML
    private void backForm(MouseEvent event) throws IOException {
        EightTeaApplication.setRoot("home");
    }

    @FXML
    private void getDuLieuSP(MouseEvent event) {
        txtSL.setText("");
        cbNL.getSelectionModel().selectFirst();
        SanPham sp = tblSP.getSelectionModel().getSelectedItem();
        txtChonMASP.setText(sp.getTenSP().toUpperCase());
        txtMaSPAN.setText(sp.getMaSP());
        setTableCT(sp.getMaSP());
    }

    @FXML
    private void getDuLieuCT(MouseEvent event) {
        BangCongThuc ct = tblCT.getSelectionModel().getSelectedItem();
        txtSL.setText(String.valueOf(ct.getSoLuong()));
        cbNL.setValue(String.valueOf(ct.getMaNVL()));

    }

    public void setTableSP() {
        tblSP.getItems().removeAll();
        ObservableList<SanPham> li = FXCollections.observableArrayList();
        ArrayList<SanPham> listsp = new BUS_SanPham().getListSP();
        for (SanPham sp : listsp) {
            li.add(sp);
        }

        colMaSP.setCellValueFactory(new PropertyValueFactory<SanPham, String>("MaSP"));

        colTenSP.setCellValueFactory(new PropertyValueFactory<SanPham, String>("TenSP"));

        tblSP.setItems(li);
    }

    public void setComboBoxNL() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ArrayList<Kho> listplsp = new BUS_Kho().getListNVL();

        for (Kho s : listplsp) {
            list.add(s.getTenNVL());
        }

        cbNL.setItems(list);
        cbNL.getSelectionModel().selectLast();
    }

    public void setTableCT(String masp) {
        tblCT.getItems().removeAll();
        ObservableList<BangCongThuc> li = FXCollections.observableArrayList();
        ArrayList<BangCongThuc> listct = new BUS_CongThuc().getListCT(masp);
        for (BangCongThuc ct : listct) {
            li.add(ct);
        }

        colNL.setCellValueFactory(new PropertyValueFactory<BangCongThuc, String>("MaNVL"));
        colSL.setCellValueFactory(new PropertyValueFactory<BangCongThuc, Integer>("SoLuong"));
        colDVT.setCellValueFactory(new PropertyValueFactory<BangCongThuc, String>("DVT"));

        tblCT.setItems(li);
    }

    @FXML
    private void themNL(ActionEvent event) {
        if (tblSP.getSelectionModel().getSelectedItem() == null) {
            EightTeaApplication.alertInf("Hãy chọn sản phẩm cần thêm công thức !");
        } else {
            if (new BUS_CongThuc().KiemTraNL(String.valueOf(txtMaSPAN.getText()), new BUS_CongThuc().MaNVL(String.valueOf(cbNL.getValue())))) {
                EightTeaApplication.alertInf("Nguyên liệu đã tồn tại,hãy chọn nguyên liệu khác !");
            } else {
                if (txtSL.getText().isEmpty()) {
                    EightTeaApplication.alertInf("Hãy nhập số lượng cần thêm !");
                } else {
                    BangCongThuc ct = new BangCongThuc();
                    ct.setMaSP(String.valueOf(txtMaSPAN.getText()));
                    ct.setSoLuong(Integer.parseInt(txtSL.getText()));
                    ct.setMaNVL(String.valueOf(cbNL.getValue()));
                    if (new BUS_CongThuc().ThemNL(ct)) {
                        EightTeaApplication.alertInf("Đã thêm nguyên liệu !");
                        setTableCT(String.valueOf(txtMaSPAN.getText()));
                    } else {
                        EightTeaApplication.alertInf("Lỗi hệ thống, vui lòng thử lại sau !");
                    }
                }
            }
        }
    }

    @FXML
    private void xoaNL(ActionEvent event) {

        if (tblCT.getSelectionModel().getSelectedItem() == null) {
            EightTeaApplication.alertInf("Vui lòng chọn dòng cần thực hiện !");
        } else {
            boolean xn = EightTeaApplication.alertConf("Bạn có chắc chắn muốn xóa ?");
            if (xn == true) {
                BangCongThuc ct = tblCT.getSelectionModel().getSelectedItem();

                if (new BUS_CongThuc().XoaNL(String.valueOf(txtMaSPAN.getText()), new BUS_CongThuc().MaNVL(ct.getMaNVL()))) {
                    EightTeaApplication.alertInf("Đã xóa nguyên liệu");
                    setTableCT(String.valueOf(txtMaSPAN.getText()));
                } else {
                    EightTeaApplication.alertInf("Xóa thất bại");
                }
            }

        }
    }

}
