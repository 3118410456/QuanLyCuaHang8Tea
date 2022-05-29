/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.Quyen_ChucNang;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Admin
 */
public class BUS_Quyen_ChucNang {
    DAO dao = new DAO();
    
    public ArrayList<String> getListQCN(String MaCV){
        ArrayList<String> list = new ArrayList<>();
        String sql = "select * from Quyen_ChucNang where MaCV='"+MaCV+"'";
        
        try {
            PreparedStatement ps  = dao.conn().prepareStatement(sql);
            ResultSet rs  =  ps.executeQuery();
            while(rs.next())
            {
                
                list.add(rs.getString("MaCN"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean ThemQCN(Quyen_ChucNang qcn){
        String sql = "insert into Quyen_ChucNang(MaCN,MaCV) values(?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1, qcn.getMaCN());
            ps.setString(2, qcn.getMaCV());
            
            return ps.executeUpdate() > 0 ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean SuaQCN(String MaCV ,String MaCN){
        String sql = "update Quyen_ChucNang set MaCN='"+MaCN+"' where MaCV = '"+MaCV+"";
        
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate() ;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    public boolean XoaQCN(String MaCV)
    {
        String sql = "delete from Quyen_ChucNang where MaCV='"+MaCV+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate() ;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
     public boolean XoaDSCN(String MaCN)
    {
        String sql = "delete from Quyen_ChucNang where MaCN='"+MaCN+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate() ;
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public ArrayList<String> getListQuyen(String MaNV){
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT macn from quyen_chucnang where macv = (select macv from nhanvien where manv = '"+MaNV+"')";
        
        try {
            PreparedStatement ps  = dao.conn().prepareStatement(sql);
            ResultSet rs  =  ps.executeQuery();
            while(rs.next())
            {
                
                list.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
