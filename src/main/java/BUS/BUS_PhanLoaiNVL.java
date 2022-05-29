/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.PhanLoaiNVL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BUS_PhanLoaiNVL {
    DAO dao = new DAO();
    public ArrayList<PhanLoaiNVL> getListLoaiNVL(){
        ArrayList<PhanLoaiNVL> list = new ArrayList<>();
        String sql = "select * from phanloainvl";
        
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                PhanLoaiNVL pl = new PhanLoaiNVL();
                pl.setMaLoaiNVL(rs.getString("MaLoaiNVL"));
                pl.setTenLoaiNVL(rs.getString("TenLoaiNVL"));
               
                list.add(pl);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean ThemLoaiNVL(PhanLoaiNVL pl) {
        String sql = "insert into PhanLoaiNVL(MaLoaiNVL,TenLoaiNVL) values(?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1,pl.getMaLoaiNVL());
            ps.setString(2,pl.getTenLoaiNVL());
            return ps.executeUpdate() > 0 ;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean SuaLoaiNVL(String MaLoaiNVL , String TenLoaiNVL) {
        String sql = "UPDATE PhanLoaiNVL set TenLoaiNVL='"+TenLoaiNVL+"'  where MaLoaiNVL='"+MaLoaiNVL+"' ";
         try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
         return false;
    }
    
    public boolean XoaLoaiNVL(String MaLoaiNVL)   {
        String sql = "delete from PhanLoaiNVL where MaLoaiNVL = '"+MaLoaiNVL+"' ";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public String tenLoaiNVL(String MaNVL)   {
        String sql = "select TenLoaiNVL from PhanLoaiNVL where MaLoaiNVL = '"+MaNVL+"'";
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
    
    public String maLoaiNVL(String TenLoaiNVL)   {
        String sql = "select MaLoaiNVL from PhanLoaiNVL where TenLoaiNVL = N'"+TenLoaiNVL+"'";
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
