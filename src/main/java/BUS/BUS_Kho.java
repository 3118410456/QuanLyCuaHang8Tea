/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.Kho;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BUS_Kho {
    DAO dao = new DAO();
    
    public ArrayList<Kho> getListNVL(){
        ArrayList<Kho> list = new ArrayList<>();
        String sql = "SELECT * FROM KHO";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Kho nv = new Kho();
            nv.setMaNVL(rs.getString("MaNVL"));
            nv.setMaLoaiNVL(new BUS_PhanLoaiNVL().tenLoaiNVL(rs.getString("MaLoaiNVL")));
            nv.setTenNVL(rs.getString("TenNVL"));
            nv.setSoLuong(Integer.parseInt(rs.getString("SoLuong")));
            nv.setDonViTinh(rs.getString("DonViTinh"));
            nv.setDonGiaNhap(Double.parseDouble(rs.getString("DonGiaNhap")));
            nv.setMaNCC(new BUS_NhaCungCap().tenNCC(rs.getString("MaNCC")));
            
            
            
            list.add(nv);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    } 
    
    public ArrayList<Kho> getListNVL(String maloainvl){
        ArrayList<Kho> list = new ArrayList<>();
        String sql = "SELECT * FROM KHO WHERE maloainvl = '"+maloainvl+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            Kho nv = new Kho();
            nv.setMaNVL(rs.getString("MaNVL"));
            nv.setMaLoaiNVL(new BUS_PhanLoaiNVL().tenLoaiNVL(rs.getString("MaLoaiNVL")));
            nv.setTenNVL(rs.getString("TenNVL"));
            nv.setSoLuong(Integer.parseInt(rs.getString("SoLuong")));
            nv.setDonViTinh(rs.getString("DonViTinh"));
            nv.setDonGiaNhap(Double.parseDouble(rs.getString("DonGiaNhap")));
            nv.setMaNCC(new BUS_NhaCungCap().tenNCC(rs.getString("MaNCC")));
            
            
            
            list.add(nv);
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    } 
    
    public boolean ThemNVL(Kho nv) {
        String sql = "insert into Kho(MaNVL,MaLoaiNVL,TenNVL,SoLuong,DonViTinh,DonGiaNhap,MaNCC) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1,nv.getMaNVL());
            ps.setString(2,new BUS_PhanLoaiNVL().maLoaiNVL(nv.getMaLoaiNVL()));
            ps.setString(3,nv.getTenNVL());
            ps.setInt(4,nv.getSoLuong());
            ps.setString(5,nv.getDonViTinh());
            ps.setDouble(6,nv.getDonGiaNhap());
            ps.setString(7,new BUS_NhaCungCap().MaNCC(nv.getMaNCC()));
            
            return ps.executeUpdate() > 0 ;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean SuaNVL(String MaNVL , String MaLoaiNVL, String TenNVL ,int SoLuong , String DonViTinh , double DonGiaNhap ,String MaNCC )  {
        String sql = "UPDATE Kho set MaLoaiNVL='"+MaLoaiNVL+"' ,TenNVL='"+TenNVL+"', SoLuong = "+SoLuong+",DonViTinh='"+DonViTinh+"',DonGiaNhap="+DonGiaNhap+",MaNCC='"+MaNCC+"'  where MaNVL='"+MaNVL+"' ";
         try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
         return false;
    }
    
    public boolean XoaNVL(String MaNVL)   {
        String sql = "delete from Kho where MaNVL = '"+MaNVL+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public String layMaNVL(String ten)
    {
        String sql = "select MANVL from KHO where TENNVL = N'"+ten+"'";
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
    
    public String LayDonViTinh(String manvl)
    {
        String sql = "select donvitinh from KHO where MaNVL = N'"+manvl+"'";
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
    
    public int goiSoLuongNVL(String manvl)
    {
        String sql = "SELECT SOLUONG from kho WHERE MANVL='"+manvl+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                return rs.getInt("SOLUONG");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
