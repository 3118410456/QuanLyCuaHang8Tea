/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.qquang24t5._8tea;

import BUS.BUS_ChucVu;
import BUS.BUS_NhanVien;
import DTO.ChucVu;
import DTO.NhanVien;
import com.github.qquang24t5._8tea.EightTeaApplication;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author MINH TUAN
 */
public class NhanvienController implements Initializable {

    @FXML
    private ToggleGroup gioitinh;
    @FXML
    private ComboBox cbChucvu;
    @FXML
    private TableView<NhanVien> tblNhanvien;
    @FXML
    private ComboBox cmTrangthai;
    @FXML
    private TableColumn<NhanVien,String> colMaNV;
    @FXML
    private TableColumn<NhanVien,String> colHoTen;
    @FXML
    private TableColumn<NhanVien,String> colCV;
    @FXML
    private TableColumn<NhanVien,String> colNS;
    @FXML
    private TableColumn<NhanVien,String> colSDT;
    @FXML
    private TableColumn<NhanVien,String> colTT;
    @FXML
    private TextField txtMaNV;
    @FXML
    private TextField txtHoTen;
    @FXML
    private TextField txtSDT;
    @FXML
    private RadioButton rdNam;
    @FXML
    private RadioButton rdNu;
    @FXML
    private DatePicker txtNS;
    @FXML
    private TableColumn<NhanVien,String> colGT;
    @FXML
    private Text lbLoi;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setListCV();
        setTableNV();
    }    



    @FXML
    private void backHome(MouseEvent event) throws IOException {
        EightTeaApplication.setRoot("home");
    }
    public void setListCV()
    {
        ObservableList<String> list = FXCollections.observableArrayList();
        ArrayList<ChucVu> listcv = new BUS_ChucVu().getListCV();
        
        for(ChucVu s:listcv)
        {
            list.add(s.getMaCV()+":" + s.getTenCV());
        }
        list.add("Chưa có");
        cbChucvu.setItems(list);
        cbChucvu.getSelectionModel().selectLast();
        cmTrangthai.getItems().add(String.valueOf("Mở"));
        cmTrangthai.getItems().add(String.valueOf("Khóa"));
        cmTrangthai.getSelectionModel().selectFirst();
        
    }
    public void setTableNV()
    {
        tblNhanvien.getItems().removeAll();
        ObservableList<NhanVien> li = FXCollections.observableArrayList();
        ArrayList<NhanVien> listnv = new BUS_NhanVien().getListNV();
        for(NhanVien nv:listnv)
        {
            li.add(nv);
        }
        colMaNV.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("MaNV"));
        colHoTen.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("HoTen"));
        colCV.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("MaCV"));
        colSDT.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("SDT"));
        colNS.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("NgaySinh"));
        colTT.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("TrangThai"));
        colGT.setCellValueFactory(new PropertyValueFactory<NhanVien, String>("GioiTinh"));
        tblNhanvien.setItems(li);
    }

    @FXML
    private void getDulieu(MouseEvent event) {
      
    // check the table's selected item and get selected item
    if (tblNhanvien.getSelectionModel().getSelectedItem() != null) {
  
        NhanVien nv = tblNhanvien.getSelectionModel().getSelectedItem();
        txtHoTen.setText(nv.getHoTen());
        txtMaNV.setText(nv.getMaNV());
        txtSDT.setText(nv.getSDT());
       
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(nv.getNgaySinh(), formatter);
        txtNS.setValue(localDate);
        if(nv.getGioiTinh().equals("Nam"))
        {
            rdNam.setSelected(true);
            rdNu.setSelected(false);
        }
        else
        {
            rdNu.setSelected(true);
            rdNam.setSelected(false);
        }
        if(nv.getTrangThai().equals("Mở"))
        {
            cmTrangthai.getSelectionModel().selectFirst();
        }
        else
        {
             cmTrangthai.getSelectionModel().selectLast();
        }
        if(nv.getMaCV().equals("Trống"))
        {
            cbChucvu.setValue("Chưa có");
        }
        else
        {
            cbChucvu.setValue(String.valueOf(new BUS_ChucVu().maCV(nv.getMaCV()))+":"+nv.getMaCV());
        }
        
}
    
}

    @FXML
    private void themNV(ActionEvent event) {
        ArrayList<String> ers = new ArrayList<>();
        int value = new Random().nextInt((99999999 - 10000000) + 1) + 10000000;
        String rdma = "NV"+String.valueOf(value);
        txtMaNV.setText(rdma);
        if(txtNS.getValue()==null||gioitinh.getSelectedToggle()==null||txtSDT.getText().isEmpty()||txtHoTen.getText().isEmpty()||cmTrangthai.getSelectionModel().getSelectedItem()==null||cbChucvu.getSelectionModel().getSelectedItem()==null)
        {
            setError("- Hãy nhập đầy đủ thông tin");
        }
        else
        {
        LocalDate ns = txtNS.getValue();//For reference
        LocalDate ht = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        NhanVien nv = new NhanVien();
        
        nv.setMaNV(rdma);
        nv.setHoTen(txtHoTen.getText());
        nv.setSDT(txtSDT.getText());
        nv.setNgaySinh(ns.format(formatter));
        nv.setTrangThai(String.valueOf(cmTrangthai.getSelectionModel().getSelectedItem()));
        if(String.valueOf(cbChucvu.getSelectionModel().getSelectedItem()).equals("Chưa có"))
        {
            nv.setMaCV("TT");
        }
        else
        {
            nv.setMaCV(cat(String.valueOf(cbChucvu.getSelectionModel().getSelectedItem())));
        }
        
        nv.setMatKhau("123456789");
        RadioButton gt = (RadioButton) gioitinh.getSelectedToggle();
        nv.setGioiTinh(gt.getText());
      
        if(!nv.getSDT().matches("0[0-9]{9,10}"))
        {
            ers.add("- Số điện thoại không hợp lệ");
        }
        if(ht.getYear()-ns.getYear()<18)
        {
            ers.add("- Độ tuổi tối thiều phải là 18");
        }
        if(ers.size()!=0)
        {
            String loi="";
            for(String s:ers)
            {
                loi=loi+s+"\n";
            }
            setError(loi);
        }
        else
        {
            if(new BUS_NhanVien().checkMaNV(nv.getMaNV())==true)
            {
                setError("Trùng mã nhân viên, hãy kiểm tra lại");
            }
            else if(new BUS_NhanVien().checkSDT(nv.getSDT())==true)
            {
                setError("Số điện thoại đã tồn tại trong hệ thống");
            }
            else
            {
                if(new BUS_NhanVien().ThemNV(nv))
                {
                    lbLoi.setFill(Color.GREEN);
                    lbLoi.setText("Đã thêm nhân viên mới \nMã nhân viên là : "+rdma+"\nMật khẩu:123456789");
                    setTableNV();
                    ResetForm();
                }
                else
                {
                    EightTeaApplication.alertInf("Lỗi hệ thống, vui lòng thử lại sau !");
                }
                
            }
        }
        }
    }

    @FXML
    private void suaNV(ActionEvent event) {
         ArrayList<String> ers = new ArrayList<>();
        if(tblNhanvien.getSelectionModel().getSelectedItem() == null)
        {
            EightTeaApplication.alertInf("Vui lòng chọn dòng cần thực hiện !");
        }
        else 
        {
            if(txtNS.getValue()==null||gioitinh.getSelectedToggle()==null||txtSDT.getText().isEmpty()||txtHoTen.getText().isEmpty()||cmTrangthai.getSelectionModel().getSelectedItem()==null||cbChucvu.getSelectionModel().getSelectedItem()==null)
        {
           setError("Hãy nhập đầy đủ thông tin !");
        }
        else
        {
        LocalDate ns = txtNS.getValue();//For reference
        LocalDate ht = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String hoten = txtHoTen.getText();
        String SDT =  txtSDT.getText();
        String ngaysinh = ns.format(formatter);
        String tt = (String.valueOf(cmTrangthai.getSelectionModel().getSelectedItem()));
        String chucvu;
        if(!String.valueOf(cbChucvu.getSelectionModel().getSelectedItem()).equals("Chưa có"))
        {
            chucvu = (cat(String.valueOf(cbChucvu.getSelectionModel().getSelectedItem())));
           
        }
        else
        {
           chucvu = "TT";
        }
        
        RadioButton rb = (RadioButton) gioitinh.getSelectedToggle();
        String gt = rb.getText();
        String gioitinh,trangthai;
        if(gt.equals("Nam"))
        {
            gioitinh= ("0");
        }else gioitinh = ("1");
        
        if(tt.equals("Khóa"))
        {
            trangthai= ("0");
        }else trangthai = ("1");
        
        NhanVien nv = tblNhanvien.getSelectionModel().getSelectedItem();
        nv.setHoTen(String.valueOf(hoten));
        nv.setSDT(String.valueOf(SDT));
        nv.setNgaySinh(String.valueOf(ngaysinh));
        nv.setGioiTinh(String.valueOf(gioitinh));
        nv.setMaCV(String.valueOf(chucvu));
        nv.setTrangThai(String.valueOf(trangthai));
        
        
      
        if(!nv.getSDT().matches("0[0-9]{9,10}"))
        {
            ers.add("Số điện thoại không hợp lệ");
        }
        if(ht.getYear()-ns.getYear()<18)
        {
            ers.add("Độ tuổi tối thiều phải là 18");
        }
        if(ers.size()!=0)
        {
            String loi ="";
            for(String s:ers)
            {
                loi=loi+s+"\n";
            }
            setError(loi);
        }
        else
        {
//            if(new BUS_NhanVien().checkSDT(nv.getSDT())==true)
//            {
//                 EightTeaApplication.alertInf("Số điện thoại đã tồn tại trong hệ thống");
//            }
//            else
//            {
                boolean xn ;
                xn = EightTeaApplication.alertConf("Bạn có chắc chắn muốn sửa ?");
                if(xn == true)
                {
                    if(new BUS_NhanVien().SuaNV(nv.getMaNV(), nv.getHoTen(), Integer.parseInt(nv.getGioiTinh()), nv.getSDT(), nv.getNgaySinh(),nv.getMaCV() ,nv.getTrangThai()))
                {
                    setOK("Đã sửa nhân viên thành công");
                    setTableNV();
                    ResetForm();
                }
                else
                {
                    EightTeaApplication.alertInf("Lỗi hệ thống, vui lòng thử lại sau !");
                }
                }
                
            }
//        }
        }
        }
        
        
    }
    
    public String cat(String cv)
    {
        String n="";
        for(int i=0;i<cv.length();i++)
        {
            if(cv.charAt(i)!=':')
            {
                n+=cv.charAt(i);
            }
            else
            {
                break;
            }
        }
        return n;
    }
    public void setError(String error) {
        lbLoi.setFill(Color.RED);
        lbLoi.setText("");
        lbLoi.setText(error);
    }
    public void setOK(String ok) {
        lbLoi.setFill(Color.GREEN);
        lbLoi.setText("");
        lbLoi.setText(ok);
    }
    public void ResetForm()
    {
        txtMaNV.setText("");
                    cbChucvu.getSelectionModel().selectFirst();
                    txtHoTen.setText("");
                    this.gioitinh.selectToggle(rdNam);
                    txtNS.getValue();
                    txtSDT.setText("");
                    cmTrangthai.getSelectionModel().selectFirst();
                    txtNS.setValue(null);
    }
}
