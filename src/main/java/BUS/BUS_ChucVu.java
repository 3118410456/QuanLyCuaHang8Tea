/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.ChucVu;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Admin
 */
public class BUS_ChucVu {
    DAO dao = new DAO();
    public ArrayList<ChucVu> getListCV(){
        ArrayList<ChucVu> list = new ArrayList<>();
        String sql = "select * from ChucVu where MaCV<>'TT'";
        
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                ChucVu cv = new ChucVu();
                cv.setMaCV(rs.getString("MaCV"));
                cv.setTenCV(rs.getString("TenCV"));
                
                list.add(cv);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean ThemCV(ChucVu cv) {
        String sql = "insert into ChucVu(MaCV,TenCV) values(?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1,cv.getMaCV());
            ps.setString(2,cv.getTenCV());
            return ps.executeUpdate() > 0 ;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean SuaCV(String MaCV , String TenCV) {
        String sql = "UPDATE ChucVu set TenCV='"+TenCV+"' where MaCV='"+MaCV+"' ";
         try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
         return false;
    }
    
    public boolean XoaCV(String MaCV)   {
        String sql = "delete from ChucVu where MaCV = '"+MaCV+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String tenCV(String MaCV)   {
        String sql = "select TenCV from chucvu where MaCV = '"+MaCV+"'";
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
    public String maCV(String tencv)   {
        String sql = "select MaCV from chucvu where TenCV = N'"+tencv+"'";
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
        return "ko tim thay";
    }
    public boolean setTrongCVNV(String MaCV)   {
        String sql = "update nhanvien set MaCV ='TT' where MaCV='"+MaCV+"'";
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
