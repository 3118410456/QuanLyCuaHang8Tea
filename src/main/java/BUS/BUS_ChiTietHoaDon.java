/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.ChiTietHoaDon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BUS_ChiTietHoaDon {
    DAO dao = new DAO();
    
    
    public ArrayList<ChiTietHoaDon> getListCTHD(String mahd){
    ArrayList<ChiTietHoaDon> list = new ArrayList<>();
    String sql = "SELECT sanpham.TENSP,chitiethoadon.SOLUONG,sanpham.GIABAN,(chitiethoadon.SOLUONG*sanpham.GIABAN) as ThanhTien\n" +
"FROM sanpham,chitiethoadon\n" +
"WHERE sanpham.MASP=chitiethoadon.MASP and chitiethoadon.MAHD='"+mahd+"'";
    try {
        PreparedStatement ps = dao.conn().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            ChiTietHoaDon cthd = new ChiTietHoaDon();
        
            cthd.setMaSP(rs.getString("TENSP")+ "\n" + rs.getDouble("GIABAN"));
            cthd.setSoLuong(rs.getInt("SoLuong"));
            
            cthd.setThanhTien(rs.getDouble("ThanhTien"));
            list.add(cthd);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    } 
    
    public boolean ThemCTHD(ChiTietHoaDon cthd) {
        String sql = "insert into ChiTietHoaDon(MaHD,MaNV,NgayTao,MaKM,TongTien) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1,cthd.getMaHD());
            ps.setString(2,cthd.getMaSP());
            ps.setInt(4,cthd.getSoLuong());
            ps.setDouble(5,cthd.getDonGia());
            
            return ps.executeUpdate() > 0 ;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean nhap(String mahd,String manv ,String ngaytao ,String makm ,double tongtien) {
        String sql = "insert into ChiTietHoaDon(MaHD,MaNV,NgayTao,MaKM,TongTien) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1,mahd);
            ps.setString(2,manv);
            ps.setString(4, makm);
            ps.setDouble(5,tongtien);
            
            return ps.executeUpdate() > 0 ;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    
    public boolean themSoLuongOrder(String manvl , int sl) {
        String sql = "UPDATE kho SET SOLUONG=SOLUONG - "+sl+" WHERE MANVL='"+manvl+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean truSoLuongOrder(String manvl , int sl) {
        String sql = "UPDATE kho SET SOLUONG=SOLUONG + "+sl+" WHERE MANVL='"+manvl+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}
