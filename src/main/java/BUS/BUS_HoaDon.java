/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.ChiTietHoaDon;
import DTO.HoaDon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class BUS_HoaDon {

    DAO dao = new DAO();

    public ArrayList<HoaDon> getListHD() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT hoadon.MAHD,nhanvien.HOTEN,hoadon.NGAYTAO,hoadon.MAKM,hoadon.TONGTIEN\n" +
"FROM hoadon,nhanvien\n" +
"where hoadon.MANV=nhanvien.MANV";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMaNV(rs.getString("HoTen"));
                hd.setNgayTao(rs.getString("NgayTao"));
                hd.setMaKM(rs.getString("MaKM"));
                hd.setTongTien(rs.getDouble("TongTien"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<HoaDon> getListHD(String manv) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT hoadon.MAHD,nhanvien.HOTEN,hoadon.NGAYTAO,hoadon.MAKM,hoadon.TONGTIEN\n" +
"FROM hoadon,nhanvien\n" +
"where hoadon.MANV=nhanvien.MANV and hoadon.MANV='"+manv+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString("MaHD"));
                hd.setMaNV(rs.getString("HoTen"));
                hd.setNgayTao(rs.getString("NgayTao"));
                hd.setMaKM(rs.getString("MaKM"));
                hd.setTongTien(rs.getDouble("TongTien"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean ThemHD(HoaDon hd) {
        String sql = "insert into HoaDon(MaHD,MaNV,NgayTao,MaKM,TongTien) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1, hd.getMaHD());
            ps.setString(2, hd.getMaNV());
            ps.setString(3, hd.getNgayTao());
            ps.setString(4, hd.getMaKM());
            ps.setDouble(5, hd.getTongTien());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean order(String mahd, String masp, int sl) {
        String sql = "insert into chitiethoadon(MAHD,MASP,SOLUONG) values(?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1, mahd);
            ps.setString(2, masp);
            ps.setInt(3, sl);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<ChiTietHoaDon> loadOrder(String manv) {
        ArrayList<ChiTietHoaDon> list = new ArrayList<>();
        String sql = "SELECT sanpham.tensp,chitiethoadon.SOLUONG,sanpham.GIABAN,(chitiethoadon.SOLUONG*sanpham.GIABAN) as ThanhTien\n"
                + "from sanpham,chitiethoadon\n"
                + "where chitiethoadon.MASP = sanpham.MASP and chitiethoadon.MAHD ='" + "ORDER_" + manv + "'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietHoaDon ct = new ChiTietHoaDon();
                ct.setMaSP(rs.getString(1));
                ct.setSoLuong(rs.getInt(2));
                ct.setDonGia(rs.getDouble(3));
                ct.setThanhTien(rs.getDouble(4));
                list.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

//    public boolean SuaHD(String MaNV , String HoTen ,String GioiTinh , String SDT , String NgaySinh ,String MaCV )  {
//        String sql = "UPDATE NhanVien set HoTen='"+HoTen+"' ,GioiTinh='"+GioiTinh+"',SDT='"+SDT+"',NgaySinh='"+NgaySinh+"',MaCV='"+MaCV+"' where MaNV='"+MaNV+"' ";
//         try {
//            PreparedStatement ps = dao.conn().prepareStatement(sql);
//            ps.executeUpdate();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//         return false;
//    }
    public boolean ktraSP_Order(String mahd, String masp) {
        String sql = "select * from chitiethoadon where MAHD='" + mahd + "' and MASP='" + masp + "'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean checknullOrder(String mahd) {
        String sql = "select * from chitiethoadon where MAHD='" + mahd + "'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public double tongtien_Order(String mahd) {
        String sql = "SELECT sum(chitiethoadon.SOLUONG*sanpham.GIABAN) as ThanhTien\n" +
"from chitiethoadon,sanpham\n" +
"WHERE chitiethoadon.MASP=sanpham.MASP and chitiethoadon.MAHD='"+mahd+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean updateOrder(String mahd, String masp, int sl) {
        String sql = "update chitiethoadon set SOLUONG=SOLUONG+" + sl + " where MAHD='" + mahd + "' and MASP='" + masp + "'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteSP_Order(String mahd, String masp) {
        String sql = "delete from chitiethoadon where MASP='"+masp+"' and MAHD='"+mahd+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean order_complete(String mahdcu,String mahdmoi) {
        String sql = "update chitiethoadon set MAHD='"+mahdmoi+"' where MAHD='"+mahdcu+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
   public ArrayList<HoaDon> thongkeHD(String bd,String kt,String manv) {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "SELECT MAHD,STR_TO_DATE(NGAYTAO,'%d/%m/%Y') as NGAY,MAKM,TONGTIEN \n" +
"from hoadon\n" +
"where STR_TO_DATE(NGAYTAO,'%d/%m/%Y') BETWEEN STR_TO_DATE('"+bd+"','%d/%m/%Y') AND STR_TO_DATE('"+kt+"','%d/%m/%Y')";
        if(!manv.equals("ALL")){
            sql+=" AND MANV='"+manv+"'";
        }
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHD(rs.getString(1));
                hd.setNgayTao(rs.getString(2));
                hd.setMaKM(rs.getString(3));
                hd.setTongTien(rs.getDouble(4));
                
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
   
   public boolean deleteALL(String mahd) {
        String sql = "delete from chitiethoadon where MAHD='"+mahd+"'";
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
