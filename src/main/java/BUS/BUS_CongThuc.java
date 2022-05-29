/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.BangCongThuc;
import DTO.SanPham;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class BUS_CongThuc {
    DAO dao = new DAO();
    public ArrayList<BangCongThuc> getListCT(String masp){
        ArrayList<BangCongThuc> list = new ArrayList<>();
        String sql = "SELECT kho.TENNVL,bangcongthuc.SOLUONG,kho.DONVITINH FROM bangcongthuc,kho where kho.MANVL=bangcongthuc.MANVL and bangcongthuc.MASP='"+masp+"'";
        
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                BangCongThuc ct = new BangCongThuc();
                ct.setMaNVL(rs.getString(1));
                ct.setSoLuong(rs.getInt(2));
                ct.setDVT(rs.getString(3));
                list.add(ct);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public ArrayList<BangCongThuc> getListCT(String mahd ,String masp){
        ArrayList<BangCongThuc> list = new ArrayList<>();
        String sql = "SELECT bangcongthuc.MANVL , SUM(bangcongthuc.SOLUONG) "
                + "FROM bangcongthuc,chitiethoadon "
                + "WHERE bangcongthuc.MASP=chitiethoadon.MASP AND chitiethoadon.MAHD='"+mahd+"' AND chitiethoadon.MASP='"+masp+"' GROUP BY bangcongthuc.MANVL";
        
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                BangCongThuc ct = new BangCongThuc();
                ct.setMaNVL(rs.getString(1));
                ct.setSoLuong(rs.getInt(2));
                
                list.add(ct);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean ThemNL(BangCongThuc ct) {
        String sql = "insert into bangcongthuc(MASP,MaNVL,SOLUONG) values(?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1,ct.getMaSP());
            ps.setString(2,MaNVL(ct.getMaNVL()));
            ps.setInt(3,ct.getSoLuong());
           
            return ps.executeUpdate() > 0 ;
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean SuaSP(SanPham s) {
        String sql = "UPDATE SanPham set TenSP='"+s.getTenSP()+"' , MaLoaiSP='"+s.getMaLoaiSP()+"' , GiaBan="+s.getGiaBan()+" where MaSP='"+s.getMaSP()+"' ";
         try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
         return false;
    }
    
    public boolean XoaNL(String MaSP,String MaNVL)   {
        String sql = "delete from bangcongthuc where MASP = '"+MaSP+"' and MANVL='"+MaNVL+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public String MaNVL(String TenNVL)   {
        String sql = "select MaNVL from Kho where TenNVL = N'"+TenNVL+"'";
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
    public boolean KiemTraNL(String MaSP,String MaNVL)   {
        String sql = "select * from bangcongthuc where MASP = '"+MaSP+"' and MANVL='"+MaNVL+"'";
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
}
