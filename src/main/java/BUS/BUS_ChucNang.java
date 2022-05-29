/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.ChucNang;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Admin
 */
public class BUS_ChucNang {
    DAO dao = new DAO();
    public ArrayList<ChucNang> getListCN(){
        ArrayList<ChucNang> list = new ArrayList<>();
        String sql = "select * from ChucNang";
        
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                ChucNang chucnang = new ChucNang();
                chucnang.setMaCN(rs.getString("MaCN"));
                chucnang.setTenCN(rs.getString("TenCN"));
                
                list.add(chucnang);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean ThemCN(ChucNang chucnang) {
        String sql = "insert into ChucNang(MaCN,TenCN) values(?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1,chucnang.getMaCN());
            ps.setString(2,chucnang.getTenCN());
            return ps.executeUpdate() > 0 ;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean SuaCN(String MaCN, String TenCN) {
        String sql = "UPDATE ChucNang set  TenCN='"+TenCN+"' where MaCN='"+MaCN+"' ";
         try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
         return false;
    }
    
    public boolean XoaCN(String MaCN)   {
        String sql = "delete from ChucNang where MaCN = '"+MaCN+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean checkTenCN(String check)
    {
         String sql = "select * from CHUCNANG where TenCN ='"+check+"'";
       
        try {
            
          
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                 return true;
     
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public String maCN(String tencn)   {
        String sql = "select MaCN from chucnang where TenCN = N'"+tencn+"'";
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
     public String tenCN(String macn)   {
        String sql = "select TenCN from chucnang where MaCN ='"+macn+"'";
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
}
