/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.NhaCungCap;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BUS_NhaCungCap {
    DAO dao = new DAO();
    public ArrayList<NhaCungCap> getListNCC(){
        ArrayList<NhaCungCap> list = new ArrayList<>();
        String sql = "select * from NhaCungCap";
        
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                NhaCungCap ncc = new NhaCungCap();
                ncc.setMaNCC(rs.getString("MaNCC"));
                ncc.setTenNCC(rs.getString("TenNCC"));
                ncc.setSDT(rs.getString("SDT"));
                
                list.add(ncc);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean ThemNCC(NhaCungCap ncc) {
        String sql = "insert into NhaCungCap(MaNCC,TenNCC,SDT) values(?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1,ncc.getMaNCC());
            ps.setString(2,ncc.getTenNCC());
            ps.setString(3,ncc.getSDT());
            return ps.executeUpdate() > 0 ;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean SuaNCC(String MaNCC , String TenNCC,String SDT) {
        String sql = "UPDATE NhaCungCap set TenNCC='"+TenNCC+"' , SDT ='"+SDT+"' where MaNCC='"+MaNCC+"' ";
         try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
         return false;
    }
    
    public boolean XoaNCC(String MaNCC)   {
        String sql = "delete from NhaCungCap where MaNCC = '"+MaNCC+"' ";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public String tenNCC(String MaNCC)   {
        String sql = "select TenNCC from NhaCungCap where MaNCC = '"+MaNCC+"'";
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
    
    public String MaNCC(String TenNCC)   {
        String sql = "select MaNCC from NhaCungCap where TenNCC = N'"+TenNCC+"'";
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
