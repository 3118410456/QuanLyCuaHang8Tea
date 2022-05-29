/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_ChiTietPhieuNhap;
import BUS.BUS_HoaDon;
import BUS.BUS_Kho;
import BUS.BUS_NhaCungCap;
import BUS.BUS_PhanLoaiNVL;
import BUS.BUS_PhieuNhap;
import BUS.BUS_SanPham;
import DTO.ChiTietHoaDon;
import DTO.ChiTietPhieuNhap;
import DTO.Kho;
import DTO.NhaCungCap;
import DTO.PhanLoaiNVL;
import DTO.PhieuNhap;
import DTO.SanPham;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class LapphieunhapController implements Initializable {

    @FXML
    private TableView<ChiTietPhieuNhap> tblPN;
    @FXML
    private TableColumn<ChiTietPhieuNhap, String> colNVL;
    @FXML
    private TableColumn<ChiTietPhieuNhap, String> colDVT;
    @FXML
    private TableColumn<ChiTietPhieuNhap, Integer> colSL;
    @FXML
    private TableColumn<ChiTietPhieuNhap, Double> colDGN;
    @FXML
    private TableColumn<ChiTietPhieuNhap, Double> colTT;
    @FXML
    private VBox vboxLoaiNVL;
    @FXML
    private TableView<Kho> tblNVL;

    ArrayList<PhanLoaiNVL> list;
    Button[] listcb;
    @FXML
    private TableColumn<Kho, String> colTenNVL;
    @FXML
    private TableColumn<ChiTietPhieuNhap, Button> colXoa;
    @FXML
    private Label txtThanhToan;
    @FXML
    private ComboBox cbNCC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setTblNVL();
        setCBLoaiNVL();
        setTbOrder();
        setCBncc();
        setThanhToan();
    }

    public void setCBncc() {
        ObservableList<String> li = FXCollections.observableArrayList();
        ArrayList<NhaCungCap> listncc = new BUS_NhaCungCap().getListNCC();
        for (NhaCungCap ncc : listncc) {
            li.add(ncc.getTenNCC());
        }
        li.add("Chưa có");
        cbNCC.setItems(li);
        cbNCC.getSelectionModel().selectLast();

    }

    public void setCBLoaiNVL() {
        list = new BUS_PhanLoaiNVL().getListLoaiNVL();
        listcb = new Button[list.size() + 1];
        int i = 0;
        for (PhanLoaiNVL nvl : list) {
            listcb[i] = new Button(nvl.getTenLoaiNVL());
            listcb[i].setPrefSize(112, 27);
            listcb[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    String text = ((Button) e.getSource()).getText();
                    String maloai = new BUS_PhanLoaiNVL().maLoaiNVL(text);
                    setTblNVL(maloai);
                }
            });
            vboxLoaiNVL.getChildren().add(listcb[i]);
            i++;
        }
        Button tatca = new Button("Tất cả");
        tatca.setPrefSize(112, 27);
        tatca.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                setTblNVL();
            }
        });
        vboxLoaiNVL.getChildren().add(tatca);
    }

    public void setTblNVL(String maloai) {
        tblNVL.getItems().removeAll();
        ObservableList<Kho> li = FXCollections.observableArrayList();
        ArrayList<Kho> listnvl = new BUS_Kho().getListNVL(maloai);
        for (Kho nvl : listnvl) {
            li.add(nvl);
        }

        colTenNVL.setCellValueFactory(new PropertyValueFactory<Kho, String>("TenNVL"));

        tblNVL.setItems(li);
    }

    public void setTblNVL() {
        tblNVL.getItems().removeAll();
        ObservableList<Kho> li = FXCollections.observableArrayList();
        ArrayList<Kho> listnvl = new BUS_Kho().getListNVL();
        for (Kho nvl : listnvl) {
            li.add(nvl);
        }

        colTenNVL.setCellValueFactory(new PropertyValueFactory<Kho, String>("TenNVL"));

        tblNVL.setItems(li);
    }

    public static void delTblPN() {
        String mapn = "ORDER_" + EightTeaApplication.userhientai;
        ObservableList<ChiTietPhieuNhap> li = FXCollections.observableArrayList();
        ArrayList<ChiTietPhieuNhap> listctpn = new BUS_PhieuNhap().loadOrderNhap(EightTeaApplication.userhientai);
        for (ChiTietPhieuNhap n : listctpn) {
            String manvl = new BUS_Kho().layMaNVL(n.getMaNVL());

            System.out.println("test thoi nha : " + n.getMaNVL());
            //String mapn = "ORDER_" + EightTeaApplication.userhientai;
            if (new BUS_PhieuNhap().deleteNVL_nhap(mapn, manvl)) {
                new BUS_ChiTietPhieuNhap().truSoLuongOrder(manvl, n.getSoLuong());
                //setTbOrder();
                //txtThanhToan.setText(String.valueOf(new BUS_PhieuNhap().tongtien_PN("ORDER_" + EightTeaApplication.userhientai)));
            } else {
                EightTeaApplication.alertConf("Có lỗi xảy ra, vui lòng thử lại !!!");
            }
            //setThanhToan();
            li.add(n);
        }
        if (new BUS_PhieuNhap().deleteALL(mapn)) {

        } else {
            EightTeaApplication.alertInf("Error");
        }
    }

    @FXML
    private void backForm(MouseEvent event) throws IOException {
        EightTeaApplication.setRoot("phieunhap");
        delTblPN();
    }

    @FXML
    private void lapPN(ActionEvent event) {
        if (cbNCC.getSelectionModel().getSelectedItem().equals("Chưa có")) {
            EightTeaApplication.alertInf("Vui lòng chọn nhà cung cấp !!!");
        } else if (!new BUS_PhieuNhap().checknullPN("ORDER_" + EightTeaApplication.userhientai)) {
            EightTeaApplication.alertInf("Phiếu nhập không được rỗng");
        } else {
            double thanhtoan = Double.parseDouble(txtThanhToan.getText());
            int value = new Random().nextInt((99999999 - 10000000) + 1) + 10000000;
            String rdmapn = "PN" + String.valueOf(value);
            LocalDate ht = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String today = ht.format(formatter);
            String ncc = new BUS_NhaCungCap().MaNCC(String.valueOf(cbNCC.getSelectionModel().getSelectedItem()));
            //EightTeaApplication.alertInf(ncc);

            PhieuNhap pn = new PhieuNhap();
            pn.setMaPN(rdmapn);
            pn.setMaNV(EightTeaApplication.userhientai);
            pn.setNgayNhap(today);
            System.out.println(today);
            pn.setMaNCC(ncc);
            pn.setTongTien(thanhtoan);

            if (new BUS_PhieuNhap().order_complete("ORDER_" + EightTeaApplication.userhientai, rdmapn)) {
                if (new BUS_PhieuNhap().themPN(pn)) {
                    EightTeaApplication.alertInf("Đã tạo hóa đơn mã " + rdmapn + " thành công !!!");
                    try {
                        EightTeaApplication.setRoot("phieunhap");
                    } catch (IOException ex) {
                        Logger.getLogger(LapphieunhapController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    EightTeaApplication.alertInf("Có lỗi xảy ra, vui lòng thử lại sau!!");
                }
            } else {
                EightTeaApplication.alertInf("Có lỗi xảy ra, vui lòng thử lại sau");
            }
        }
    }

    @FXML
    private void themNVLnhap(MouseEvent event) {
        tblNVL.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        if (tblNVL.getSelectionModel().getSelectedItem() != null) {
                            Kho k = tblNVL.getSelectionModel().getSelectedItem();
                            String mapn = "ORDER_" + EightTeaApplication.userhientai;
                            String manvl = new BUS_Kho().layMaNVL(k.getTenNVL());
                            String sl = EightTeaApplication.alertInput();
                            String dvt = new BUS_Kho().LayDonViTinh(manvl);

                            int sl2 = 0;
                            double dongianhap2 = 0;
                            if (!sl.matches("[0-9]{1,6}")) {
                                EightTeaApplication.alertInf("Hãy nhập số lượng hợp lệ !");
                            } else {
                                sl2 = Integer.parseInt(sl);
                                if (new BUS_PhieuNhap().ktraNVL_nhap(mapn, manvl)) {
                                    if (new BUS_PhieuNhap().updateSL(mapn, manvl, sl2)) {
                                        new BUS_ChiTietPhieuNhap().themSoLuongOrder(manvl, sl2);
                                        setTbOrder();
                                        setThanhToan();
                                    }
                                } else {

                                    

                                    dongianhap2 = new BUS_ChiTietPhieuNhap().timDonGiaNhap(manvl);
                                    if (new BUS_ChiTietPhieuNhap().nhap(mapn, manvl, dvt, sl2, dongianhap2)) {
                                        new BUS_ChiTietPhieuNhap().themSoLuongOrder(manvl, sl2);
                                        //EightTeaApplication.alertInf(mapn + "     " + new BUS_PhieuNhap().tongtien_PN(mapn));
                                        setThanhToan();
                                        setTbOrder();
                                        
                                    } else {
                                        EightTeaApplication.alertInf("Có lỗi xảy ra, vui lòng thử lại !");
                                    }

                                }
                            }

                        }
                    }
                }
            }
        }
        );
    }

    public void setTbOrder() {
        tblPN.getItems().removeAll();
        ObservableList<ChiTietPhieuNhap> li = FXCollections.observableArrayList();
        ArrayList<ChiTietPhieuNhap> listctpn = new BUS_PhieuNhap().loadOrderNhap(EightTeaApplication.userhientai);
        for (ChiTietPhieuNhap n : listctpn) {
            n.getBtn().setOnAction(e -> {
                String manvl = new BUS_Kho().layMaNVL(n.getMaNVL());

                System.out.println("test thoi nha : " + n.getMaNVL());
                String mapn = "ORDER_" + EightTeaApplication.userhientai;
                if (new BUS_PhieuNhap().deleteNVL_nhap(mapn, manvl)) {
                    new BUS_ChiTietPhieuNhap().themSoLuongOrder(manvl, n.getSoLuong());
                    setTbOrder();
                    //txtThanhToan.setText(String.valueOf(new BUS_PhieuNhap().tongtien_PN("ORDER_" + EightTeaApplication.userhientai)));
                } else {
                    EightTeaApplication.alertConf("Có lỗi xảy ra, vui lòng thử lại !!!");
                }
                setThanhToan();
            });
            li.add(n);
        }
        colNVL.setCellValueFactory(new PropertyValueFactory<ChiTietPhieuNhap, String>("MaNVL"));
        colDVT.setCellValueFactory(new PropertyValueFactory<ChiTietPhieuNhap, String>("DonViTinh"));
        colSL.setCellValueFactory(new PropertyValueFactory<ChiTietPhieuNhap, Integer>("SoLuong"));
        colDGN.setCellValueFactory(new PropertyValueFactory<ChiTietPhieuNhap, Double>("DonGiaNhap"));
        colTT.setCellValueFactory(new PropertyValueFactory<ChiTietPhieuNhap, Double>("ThanhTien"));
        colXoa.setCellValueFactory(new PropertyValueFactory<ChiTietPhieuNhap, Button>("btn"));

        tblPN.setItems(li);

    }

    public void setThanhToan() {
        txtThanhToan.setText(String.valueOf(new BUS_PhieuNhap().tongtien_PN("ORDER_" + EightTeaApplication.userhientai)));

    }

}
