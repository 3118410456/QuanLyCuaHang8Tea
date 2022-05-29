/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.ChiTietPhieuNhap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class BUS_ChiTietPhieuNhap {
    DAO dao = new DAO();
    
    
    public ArrayList<ChiTietPhieuNhap> getListCTPN(String mapn){
    ArrayList<ChiTietPhieuNhap> list = new ArrayList<>();
    String sql = "SELECT k.TENNVL , k.DONVITINH , ct.SOLUONG , ct.DONGIANHAP , (ct.SOLUONG*ct.DONGIANHAP)AS thanhtien "
            + "FROM kho k ,chitietphieunhap ct "
            + "WHERE k.MANVL = ct.MANVL AND ct.MAPN = '"+mapn+"'";
    try {
        PreparedStatement ps = dao.conn().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap();
        
            ctpn.setMaNVL(rs.getString("TENNVL"));
            ctpn.setDonViTinh(rs.getString("DONVITINH"));
            ctpn.setSoLuong(rs.getInt("SoLuong"));
            ctpn.setDonGiaNhap(rs.getDouble("dongianhap"));
            
            ctpn.setThanhTien(rs.getDouble("ThanhTien"));
            list.add(ctpn);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    } 
    
    public boolean ThemCTPN(ChiTietPhieuNhap ctpn) {
        String sql = "insert into ChiTietPhieuNhap(MaPN,MaNVL,DONVITINH,SoLuong,DonGiaNhap) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1,ctpn.getMaPN());
            ps.setString(2,ctpn.getMaNVL());
            ps.setString(3, ctpn.getDonViTinh());
            ps.setInt(4,ctpn.getSoLuong());
            ps.setDouble(5,ctpn.getDonGiaNhap());
            
            return ps.executeUpdate() > 0 ;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean nhap(String mapn, String manvl,String dvt, int sl,double dongianhap) {
        String sql = "insert into chitietphieunhap(MAPN,MANVL,DONVITINH,SOLUONG,DONGIANHAP) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1, mapn);
            ps.setString(2, manvl);
            ps.setString(3,dvt);
            ps.setInt(4, sl);
            ps.setDouble(5, dongianhap);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean themSoLuongOrder(String manvl , int sl) {
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
    
    public double timDonGiaNhap(String manvl){
        String sql ="SELECT DONGIANHAP FROM kho WHERE MANVL='"+manvl+"'";
        PreparedStatement ps;
        try {
            ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                return rs.getDouble("DONGIANHAP");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BUS_ChiTietPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    } 
}
