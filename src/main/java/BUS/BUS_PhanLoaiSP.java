/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.PhanLoaiSP;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BUS_PhanLoaiSP {
    DAO dao = new DAO();
    public ArrayList<PhanLoaiSP> getListLoaiSP(){
        ArrayList<PhanLoaiSP> list = new ArrayList<>();
        String sql = "select * from PhanLoaiSP";
        
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                PhanLoaiSP plsp = new PhanLoaiSP();
                plsp.setMaLoaiSP(rs.getString("MaLoaiSP"));
                plsp.setTenLoai(rs.getString("TenLoaiSP"));
                
                list.add(plsp);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean ThemLoaiSP(PhanLoaiSP plsp) {
        String sql = "insert into PhanLoaiSP(MaLoaiSP,TenLoaiSP) values(?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1,plsp.getMaLoaiSP());
            ps.setString(2,plsp.getTenLoai());
            return ps.executeUpdate() > 0 ;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean SuaLoaiSP(String MaLoaiSP , String TenLoaiSP) {
        String sql = "UPDATE PhanLoaiSP set TenLoaiSP='"+TenLoaiSP+"' where MaLoaiSP='"+MaLoaiSP+"' ";
         try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
         return false;
    }
    
    public boolean XoaLoaiSP(String MaLoaiSP)   {
        String sql = "delete from PhanLoaiSP where MaLoaiSP = '"+MaLoaiSP+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public String layTenLoaiSP(String ma)
    {
        String sql = "select TenLoaiSP from phanloaisp where MaLoaiSP = '"+ma+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Chưa có";
    }
    public String layMaLoaiSP(String ten)
    {
        String sql = "select MaLoaiSP from phanloaisp where TenLoaiSP = N'"+ten+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                return rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Chưa có";
    }
}
