/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_ChiTietHoaDon;
import BUS.BUS_CongThuc;
import BUS.BUS_PhanLoaiSP;
import BUS.BUS_SanPham;
import BUS.BUS_HoaDon;
import BUS.BUS_Kho;
import BUS.BUS_KhuyenMai;
import DTO.BangCongThuc;
import DTO.ChiTietHoaDon;
import DTO.HoaDon;
import DTO.PhanLoaiSP;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MINH TUAN
 */
public class LaphoadonController implements Initializable {

    @FXML
    private TableView<ChiTietHoaDon> tblHD;
    @FXML
    private TableView<SanPham> tblSP;
    @FXML
    private TextField txtKM;
    @FXML
    private TextField txtThanhToan;
    @FXML
    private Label txtThongbaoKM;
    ArrayList<PhanLoaiSP> list;
    Button[] listcb;
    @FXML
    private VBox vboxLoaiSP;
    @FXML
    private TableColumn<SanPham, String> colTenSP;
    @FXML
    private TableColumn<ChiTietHoaDon, Integer> colSL;
    @FXML
    private TableColumn<ChiTietHoaDon, Double> colDG;
    @FXML
    private TableColumn<ChiTietHoaDon, Double> colTT;
    @FXML
    private TableColumn<ChiTietHoaDon, String> colSP;
    @FXML
    private TableColumn<ChiTietHoaDon, Button> colXoa;
    public static Text txtNhap;
    @FXML
    private Button btnKiemtraKM;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setCBLoaiSP();
        setTblSP();
        setTbOrder();
        setThanhToan();
    }

    @FXML
    private void backForm(MouseEvent event) throws IOException {
        EightTeaApplication.setRoot("hoadon");
        delTblHD();
    }

    @FXML
    private void ktraKM(ActionEvent event) {
       if(btnKiemtraKM.getText().equals("Kiểm tra")){
            String km = txtKM.getText();
        int pt;
        if(!km.isEmpty()){
            pt = new BUS_KhuyenMai().PhanTramKM(km);
            if(new BUS_KhuyenMai().PhanTramKM(km)!=0&&new BUS_KhuyenMai().KiemTraHopLe(km)==false){
                txtThongbaoKM.setTextFill(Color.RED);
                txtThongbaoKM.setText("Mã khuyến mãi nằm ngoài thời hạn sử dụng");
            }
            else if(pt!=0){
                txtThongbaoKM.setTextFill(Color.GREEN);
                txtThongbaoKM.setText("-"+pt+"%");
                txtKM.setDisable(true);
                btnKiemtraKM.setText("Hủy");
                
                
            }else{
                txtThongbaoKM.setTextFill(Color.RED);
                txtThongbaoKM.setText("Mã khuyến mãi không tồn tại");
            }
        }
        }
        else{
            txtKM.setText("");
            txtThongbaoKM.setText("");
            txtKM.setDisable(false);
            btnKiemtraKM.setText("Kiểm tra");
        }
        setThanhToan();
    }

    @FXML
    private void lapHD(ActionEvent event) {
        if (!new BUS_HoaDon().checknullOrder("ORDER_" + EightTeaApplication.userhientai)) {
            EightTeaApplication.alertInf("Hóa đơn không được rỗng");
        } else {
            double thanhtoan = Double.parseDouble(txtThanhToan.getText());
            int value = new Random().nextInt((99999999 - 10000000) + 1) + 10000000;
            String rdmahd = "HD" + String.valueOf(value);
            LocalDate ht = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String today = ht.format(formatter);
            String km;
            if (new BUS_KhuyenMai().PhanTramKM(txtKM.getText()) != 0) {
                km = String.valueOf(txtThongbaoKM.getText());
            } else {
                km = "Không có";
            }

            HoaDon hd = new HoaDon();
            hd.setMaHD(rdmahd);
            hd.setNgayTao(today);
            hd.setTongTien(thanhtoan);
            hd.setMaNV(EightTeaApplication.userhientai);
            hd.setMaKM(km);
            if (new BUS_HoaDon().order_complete("ORDER_" + EightTeaApplication.userhientai, rdmahd)) {
                if (new BUS_HoaDon().ThemHD(hd)) {
                    EightTeaApplication.alertInf("Đã tạo hóa đơn mã " + rdmahd);
                    try {
                        EightTeaApplication.setRoot("laphoadon");
                    } catch (IOException ex) {
                        Logger.getLogger(LaphoadonController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    EightTeaApplication.alertInf("Có lỗi xảy ra, vui lòng thử lại sau");
                }
            } else {
                EightTeaApplication.alertInf("Có lỗi xảy ra, vui lòng thử lại sau");
            }
        }

    }

    public void setCBLoaiSP() {
        list = new BUS_PhanLoaiSP().getListLoaiSP();
        listcb = new Button[list.size() + 1];
        int i = 0;
        for (PhanLoaiSP sp : list) {
            listcb[i] = new Button(sp.getTenLoai());
            listcb[i].setPrefSize(112, 27);
            listcb[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    String text = ((Button) e.getSource()).getText();
                    String maloai = new BUS_PhanLoaiSP().layMaLoaiSP(text);
                    setTblSP(maloai);
                }
            });
            vboxLoaiSP.getChildren().add(listcb[i]);
            i++;
        }
        Button tatca = new Button("Tất cả");
        tatca.setPrefSize(112, 27);
        tatca.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                setTblSP();
            }
        });
        vboxLoaiSP.getChildren().add(tatca);
    }

    public void setTblSP(String maloai) {
        tblSP.getItems().removeAll();
        ObservableList<SanPham> li = FXCollections.observableArrayList();
        ArrayList<SanPham> listsp = new BUS_SanPham().getListSP(maloai);
        for (SanPham sp : listsp) {
            li.add(sp);
        }

        colTenSP.setCellValueFactory(new PropertyValueFactory<SanPham, String>("TenSP"));

        tblSP.setItems(li);
    }

    public void setTblSP() {
        tblSP.getItems().removeAll();
        ObservableList<SanPham> li = FXCollections.observableArrayList();
        ArrayList<SanPham> listsp = new BUS_SanPham().getListSP();
        for (SanPham sp : listsp) {
            li.add(sp);
        }

        colTenSP.setCellValueFactory(new PropertyValueFactory<SanPham, String>("TenSP"));

        tblSP.setItems(li);
    }

    public void setTbOrder() {
        tblHD.getItems().removeAll();
        ObservableList<ChiTietHoaDon> li = FXCollections.observableArrayList();
        ArrayList<ChiTietHoaDon> listcthd = new BUS_HoaDon().loadOrder(EightTeaApplication.userhientai);
        for (ChiTietHoaDon od : listcthd) {
            od.getBtn().setOnAction(e -> {
                String masp = new BUS_SanPham().layMaSP(od.getMaSP());
                String mahd = "ORDER_" + EightTeaApplication.userhientai;

                ArrayList<BangCongThuc> listct = new BUS_CongThuc().getListCT(mahd, masp);
                for (BangCongThuc ct : listct) {
                    //EightTeaApplication.alertInf(ct.getMaNVL());
                    //EightTeaApplication.alertInf(String.valueOf(od.getSoLuong() * ct.getSoLuong()));
                    int soluong = od.getSoLuong() * ct.getSoLuong();
                    new BUS_ChiTietHoaDon().truSoLuongOrder(ct.getMaNVL(), soluong);
                    //EightTeaApplication.alertInf(String.valueOf(sl2*ct.getSoLuong()));
                    //new BUS_ChiTietHoaDon().truSoLuongOrder(ct.getMaNVL(), sl2 * ct.getSoLuong());

                }
                if (new BUS_HoaDon().deleteSP_Order(mahd, masp)) {
                    setTbOrder();
                } else {
                    EightTeaApplication.alertConf("Có lỗi xảy ra, vui lòng thử lại !");
                }
                setThanhToan();
            });
            li.add(od);
        }
        colSP.setCellValueFactory(new PropertyValueFactory<ChiTietHoaDon, String>("MaSP"));
        colSL.setCellValueFactory(new PropertyValueFactory<ChiTietHoaDon, Integer>("SoLuong"));
        colDG.setCellValueFactory(new PropertyValueFactory<ChiTietHoaDon, Double>("DonGia"));
        colTT.setCellValueFactory(new PropertyValueFactory<ChiTietHoaDon, Double>("ThanhTien"));
        colXoa.setCellValueFactory(new PropertyValueFactory<ChiTietHoaDon, Button>("btn"));

        tblHD.setItems(li);

    }

    public static void delTblHD() {
        String mahd = "ORDER_" + EightTeaApplication.userhientai;
        ObservableList<ChiTietHoaDon> li = FXCollections.observableArrayList();
        ArrayList<ChiTietHoaDon> listcthd = new BUS_HoaDon().loadOrder(EightTeaApplication.userhientai);
        for (ChiTietHoaDon od : listcthd) {

            String masp = new BUS_SanPham().layMaSP(od.getMaSP());
            //String mahd = "ORDER_" + EightTeaApplication.userhientai;

            ArrayList<BangCongThuc> listct = new BUS_CongThuc().getListCT(mahd, masp);
            for (BangCongThuc ct : listct) {
//                EightTeaApplication.alertInf(ct.getMaNVL());
//                EightTeaApplication.alertInf(String.valueOf(od.getSoLuong() + "   " + ct.getSoLuong()));
                int soluong = od.getSoLuong() * ct.getSoLuong();
                new BUS_ChiTietHoaDon().truSoLuongOrder(ct.getMaNVL(), soluong);
                //EightTeaApplication.alertInf(String.valueOf(sl2*ct.getSoLuong()));
                //new BUS_ChiTietHoaDon().truSoLuongOrder(ct.getMaNVL(), sl2 * ct.getSoLuong());

            }
            if (new BUS_HoaDon().deleteSP_Order(mahd, masp)) {
                //setTbOrder();
            } else {
                EightTeaApplication.alertConf("Có lỗi xảy ra, vui lòng thử lại !");
            }
            //setThanhToan();

            li.add(od);
        }
        if (new BUS_HoaDon().deleteALL(mahd)) {

        } else {
            EightTeaApplication.alertInf("Error");
        }
    }

    public void setThanhToan() {
        int i =new BUS_KhuyenMai().PhanTramKM(txtKM.getText());
        if(i!=0&&new BUS_KhuyenMai().KiemTraHopLe(txtKM.getText())){
            txtThanhToan.setText(String.valueOf(new BUS_HoaDon().tongtien_Order("ORDER_"+EightTeaApplication.userhientai)*(100-i)/100));
        }else{
            txtThanhToan.setText(String.valueOf(new BUS_HoaDon().tongtien_Order("ORDER_"+EightTeaApplication.userhientai)));
        }
    }

    @FXML
    private void themSPorder(MouseEvent event) {
        tblSP.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
                    if (mouseEvent.getClickCount() == 2) {
                        if (tblSP.getSelectionModel().getSelectedItem() != null) {
                            SanPham sp = tblSP.getSelectionModel().getSelectedItem();
                            String mahd = "ORDER_" + EightTeaApplication.userhientai;
                            String masp = new BUS_SanPham().layMaSP(sp.getTenSP());
                            String sl = EightTeaApplication.alertInput();
                            int sl2 = 0;

                            if (!sl.matches("[0-9]{1,4}")) {
                                EightTeaApplication.alertInf("Hãy nhập số lượng hợp lệ !");
                            } else {
                                int dem = 0;
                                sl2 = Integer.parseInt(sl);

                                if (new BUS_HoaDon().ktraSP_Order(mahd, masp)) {
                                    if (new BUS_HoaDon().updateOrder(mahd, masp, sl2)) {
                                        ArrayList<BangCongThuc> listct = new BUS_CongThuc().getListCT(mahd, masp);
                                        for (BangCongThuc ct : listct) {
                                            int soluongkho = new BUS_Kho().goiSoLuongNVL(ct.getMaNVL());
                                            int soluongconlai = (soluongkho - (sl2 * ct.getSoLuong()));

//                                            EightTeaApplication.alertInf(ct.getMaNVL());
//                                            EightTeaApplication.alertInf(String.valueOf(ct.getSoLuong()));
//                                            EightTeaApplication.alertInf(String.valueOf(sl2*ct.getSoLuong()));
                                            new BUS_ChiTietHoaDon().themSoLuongOrder(ct.getMaNVL(), sl2 * ct.getSoLuong());

                                        }
                                        setTbOrder();
                                    } else {
                                        EightTeaApplication.alertInf("Có lỗi xảy ra, vui lòng thử lại !");
                                    }
                                } else {
                                    if (new BUS_HoaDon().order(mahd, masp, sl2)) {
                                        ArrayList<BangCongThuc> listct = new BUS_CongThuc().getListCT(mahd, masp);
                                        for (BangCongThuc ct : listct) {
                                            int soluongkho = new BUS_Kho().goiSoLuongNVL(ct.getMaNVL());
                                            int soluongconlai = (soluongkho - (sl2 * ct.getSoLuong()));

//                                            EightTeaApplication.alertInf(ct.getMaNVL());
//                                            EightTeaApplication.alertInf(String.valueOf(ct.getSoLuong()));
//                                            EightTeaApplication.alertInf(String.valueOf(sl2*ct.getSoLuong()));
                                            new BUS_ChiTietHoaDon().themSoLuongOrder(ct.getMaNVL(), sl2 * ct.getSoLuong());

                                        }
                                        setTbOrder();
                                    } else {
                                        EightTeaApplication.alertInf("Có lỗi xảy ra, vui lòng thử lại !");
                                    }

                                }

                                setThanhToan();
                            }
                        }
                    }
                }
            }
        }
        );
    }

}
