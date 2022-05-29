/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.ChiTietPhieuNhap;
import DTO.PhieuNhap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class BUS_PhieuNhap {
    DAO dao = new DAO();
    public ArrayList<PhieuNhap> getListPN() {
        ArrayList<PhieuNhap> list = new ArrayList<>();
        String sql = "SELECT pn.MAPN,nv.HOTEN,ncc.TENNCC,pn.NGAYNHAP,pn.tongtien "
                + "FROM phieunhap pn , nhacungcap ncc , nhanvien nv  "
                + "WHERE pn.MANV = nv.MANV AND pn.MANCC = ncc.MANCC ";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PhieuNhap hd = new PhieuNhap();
                hd.setMaPN(rs.getString("MaPN"));
                hd.setMaNV(rs.getString("HoTen"));
                hd.setMaNCC(rs.getString("TenNCC"));
                SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy");
                Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(rs.getString("NgayNhap")); 
                String strDate = formatter.format(date1);
                hd.setNgayNhap(strDate);
                hd.setTongTien(rs.getDouble("tongtien"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean themPN(PhieuNhap pn)
    {
        String sql = "insert into phieunhap(mapn,manv,mancc,ngaynhap,tongtien) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1, pn.getMaPN());
            ps.setString(2, pn.getMaNV());
            ps.setString(3, pn.getMaNCC());
            ps.setString(4, pn.getNgayNhap());
            ps.setDouble(5, pn.getTongTien());
            
            return ps.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<ChiTietPhieuNhap> getListCTPN() {
        ArrayList<ChiTietPhieuNhap> list = new ArrayList<>();
        String sql = "select ct.MAPN , k.TENNVL , ct.DONGIANHAP , ct.SOLUONG , k.DONVITINH "
                + "from chitietphieunhap ct , kho k "
                + "WHERE ct.MANVL = k.MANVL";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietPhieuNhap hd = new ChiTietPhieuNhap();
                hd.setMaPN(rs.getString("MaPN"));
                hd.setMaNVL(rs.getString("TenNVL"));
                hd.setDonViTinh(rs.getString("DonViTinh"));
                hd.setSoLuong(rs.getInt("SoLuong"));
                hd.setDonGiaNhap(rs.getDouble("DonGiaNhap"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean CTPN(ChiTietPhieuNhap ctpn) {
        String sql = "insert into chitietphieunhap(MAPN,MANVL,DONVITINH,SOLUONG,DONGIANHAP) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1, ctpn.getMaPN());
            ps.setString(2, ctpn.getMaNVL());
            ps.setString(3,ctpn.getDonViTinh());
            ps.setInt(4, ctpn.getSoLuong());
            ps.setDouble(5, ctpn.getDonGiaNhap());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public double tongtien_PN(String mapn) {
        String sql = "select sum(soluong*DONGIANHAP) as ThanhTien\n" +
"from chitietphieunhap\n" +
"WHERE MAPN = '"+mapn+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public boolean deleteNVL_nhap(String mapn, String manvl) {
        String sql = "delete from chitietphieunhap where MANVL='"+manvl+"' and MAPN='"+mapn+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean deleteALL(String mapn) {
        String sql = "delete from chitietphieunhap where MAPN='"+mapn+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateSL(String mapn, String manvl, int sl) {
        String sql = "update chitietphieunhap set SOLUONG=SOLUONG+" + sl + " where MAPN='"+mapn+"' and MANVL='"+manvl+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean order_complete(String mapncu,String mapnmoi) {
        String sql = "update chitietphieunhap set MAPN='"+mapnmoi+"' where MAPN='"+mapncu+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public boolean ktraNVL_nhap(String mapn, String manvl) {
        String sql = "select * from chitietphieunhap where MAPN='" + mapn + "' and MANVL='" + manvl + "'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<ChiTietPhieuNhap> loadOrderNhap(String manv) {
        ArrayList<ChiTietPhieuNhap> list = new ArrayList<>();
        String sql = "SELECT kho.tennvl,KHO.DONVITINH,ChiTietPhieuNhap.SOLUONG,ChiTietPhieuNhap.dongianhap,(ChiTietPhieuNhap.SOLUONG*ChiTietPhieuNhap.DONGIANHAP) as ThanhTien\n"
                + "from KHO,ChiTietPhieuNhap\n"
                + "where ChiTietPhieuNhap.MANVL = kho.MANVL and ChiTietPhieuNhap.MAPN ='" + "ORDER_" + manv + "'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietPhieuNhap ct = new ChiTietPhieuNhap();
                ct.setMaNVL(rs.getString(1));
                ct.setDonViTinh(rs.getString(2));
                ct.setSoLuong(rs.getInt(3));
                ct.setDonGiaNhap(rs.getDouble(4));
                ct.setThanhTien(rs.getDouble(5));
                list.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean checknullPN(String mapn) {
        String sql = "select * from chitietphieunhap where MAPN='" + mapn + "'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
