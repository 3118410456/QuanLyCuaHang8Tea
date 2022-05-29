/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.SanPham;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author Admin
 */
public class BUS_SanPham {
    DAO dao = new DAO();
    public ArrayList<SanPham> getListSP(){
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "select * from SanPham";
        
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setMaLoaiSP(new BUS_PhanLoaiSP().layTenLoaiSP(rs.getString("MaLoaiSP")));
                //sp.setSize(rs.getString("Size"));
                sp.setGiaBan(rs.getDouble("GiaBan"));
                list.add(sp);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<SanPham> getListSP(String maloai){
        ArrayList<SanPham> list = new ArrayList<>();
        String sql = "select * from SanPham where MALOAISP='"+maloai+"'";
        
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setMaLoaiSP(new BUS_PhanLoaiSP().layTenLoaiSP(rs.getString("MaLoaiSP")));
                //sp.setSize(rs.getString("Size"));
                sp.setGiaBan(rs.getDouble("GiaBan"));
                list.add(sp);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public boolean ThemSP(SanPham sp) {
        String sql = "insert into SanPham(MaSP,TenSP,MaLoaiSP,GiaBan) values(?,?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1,sp.getMaSP());
            ps.setString(2,sp.getTenSP());
            ps.setString(3,new BUS_PhanLoaiSP().layMaLoaiSP(sp.getMaLoaiSP()));
            //ps.setString(4,sp.getSize());
            ps.setDouble(4,sp.getGiaBan());
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
    
    public boolean XoaSP(String MaSP)   {
        String sql = "delete from SanPham where MaSP = '"+MaSP+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public String layMaSP(String ten)
    {
        String sql = "select MASP from sanpham where TENSP = N'"+ten+"'";
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
    public ArrayList<SanPham> thongkeSP(String bd,String kt,String manv){
        ArrayList<SanPham> list = new ArrayList<>();
        String sql;
        if(manv.equals("ALL")){
            sql = "select sanpham.TENSP,sum(chitiethoadon.SOLUONG) as Tong from chitiethoadon,hoadon,sanpham\n" +
"where hoadon.MAHD=chitiethoadon.MAHD and sanpham.MASP=chitiethoadon.MASP and STR_TO_DATE(hoadon.NGAYTAO,'%d/%m/%Y') BETWEEN STR_TO_DATE('"+bd+"','%d/%m/%Y') AND STR_TO_DATE('"+kt+"','%d/%m/%Y')\n" +
"GROUP by sanpham.TENSP";
        }
        else{
             sql = "select sanpham.TENSP,sum(chitiethoadon.SOLUONG) as Tong from chitiethoadon,hoadon,sanpham\n" +
"where hoadon.MAHD=chitiethoadon.MAHD and hoadon.MANV='"+manv+"' and sanpham.MASP=chitiethoadon.MASP and STR_TO_DATE(hoadon.NGAYTAO,'%d/%m/%Y') BETWEEN STR_TO_DATE('"+bd+"','%d/%m/%Y') AND STR_TO_DATE('"+kt+"','%d/%m/%Y')\n" +
"GROUP by sanpham.TENSP";
        }
        
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                SanPham sp = new SanPham();
//                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString(1));
                sp.setMaSP(rs.getString(2));
//                sp.setMaLoaiSP(new BUS_PhanLoaiSP().layTenLoaiSP(rs.getString("MaLoaiSP")));
                //sp.setSize(rs.getString("Size"));
//                sp.setGiaBan(rs.getDouble("GiaBan"));
                list.add(sp);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<SanPham> thongkeSP(String manv){
        ArrayList<SanPham> list = new ArrayList<>();
        String sql;
        if(manv.equals("ALL")){
             sql="select sanpham.TENSP,sum(chitiethoadon.SOLUONG) as Tong from chitiethoadon,hoadon,sanpham\n" +
"where hoadon.MAHD=chitiethoadon.MAHD and sanpham.MASP=chitiethoadon.MASP \n" +
"GROUP by sanpham.TENSP";
        }else{
            sql="select sanpham.TENSP,sum(chitiethoadon.SOLUONG) as Tong from chitiethoadon,hoadon,sanpham\n" +
"where hoadon.MAHD=chitiethoadon.MAHD and hoadon.MANV='"+manv+"' and sanpham.MASP=chitiethoadon.MASP \n" +
"GROUP by sanpham.TENSP";
        }
       
       
        
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                SanPham sp = new SanPham();
//                sp.setMaSP(rs.getString("MaSP"));
                sp.setTenSP(rs.getString(1));
                sp.setMaSP(rs.getString(2));
//                sp.setMaLoaiSP(new BUS_PhanLoaiSP().layTenLoaiSP(rs.getString("MaLoaiSP")));
                //sp.setSize(rs.getString("Size"));
//                sp.setGiaBan(rs.getDouble("GiaBan"));
                list.add(sp);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
