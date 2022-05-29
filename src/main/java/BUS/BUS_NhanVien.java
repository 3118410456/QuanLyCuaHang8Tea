/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.NhanVien;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Admin
 */
public class BUS_NhanVien {

    DAO dao = new DAO();

    public ArrayList<NhanVien> getListNV() {
        ArrayList<NhanVien> list = new ArrayList<>();
        String sql = "SELECT * FROM NHANVIEN";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setMaCV(new BUS_ChucVu().tenCV(rs.getString("MaCV")));
                nv.setHoTen(rs.getString("HoTen"));
                if (rs.getInt("GioiTinh") == 0) {
                    nv.setGioiTinh("Nam");
                } else {
                    nv.setGioiTinh("Nữ");
                }

                nv.setSDT(rs.getString("SDT"));
                nv.setNgaySinh(rs.getString("NgaySinh"));

                nv.setMatKhau(rs.getString("MatKhau"));
                if (rs.getInt("TrangThai") == 1) {
                    nv.setTrangThai("Mở");
                } else {
                    nv.setTrangThai("Khóa");
                }

                list.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean ThemNV(NhanVien nv) {
        String sql = "insert into NhanVien(MaNV,HoTen,GioiTinh,SDT,NgaySinh,MaCV,MatKhau,TrangThai) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getHoTen());
            if (nv.getGioiTinh().equals("Nam")) {
                ps.setInt(3, 0);
            } else {
                ps.setInt(3, 1);
            }

            ps.setString(4, nv.getSDT());
            ps.setString(5, nv.getNgaySinh());
            ps.setString(6, nv.getMaCV());
            ps.setString(7, nv.getMatKhau());
            if (nv.getTrangThai().equals("Mở")) {
                ps.setInt(8, 1);
            } else {
                ps.setInt(8, 2);
            }
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkSDT(String check) {
        String sql = "select * from nhanvien where SDT ='" + check + "'";

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

    public boolean checkMaNV(String check) {
        String sql = "select * from nhanvien where MANV ='" + check + "'";

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

    public NhanVien TimNV(String manv) {
        String sql = "select * from nhanvien where MANV ='" + manv + "'";
        NhanVien nv = new NhanVien();
        try {

            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                nv.setMaNV(rs.getString(1));
                nv.setMaCV(rs.getString(2));
                nv.setHoTen(rs.getString(3));

                if (rs.getInt(4) == 0) {
                    nv.setGioiTinh("Nam");
                } else {
                    nv.setGioiTinh("Nữ");
                }
                nv.setSDT(rs.getString(5));
                nv.setNgaySinh(rs.getString(6));

//                if(nv.getTrangThai().equals("Mở"))
//            {
//                ps.setInt(8,1);
//            }
//            else ps.setInt(8,2);
                if (rs.getString(8).equals("1")) {
                    nv.setTrangThai("Mở");
                } else {
                    nv.setTrangThai("Khóa");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }

    public boolean SuaNV(String MaNV, String HoTen, int GioiTinh, String SDT, String NgaySinh, String MaCV, String TrangThai) {
        String sql = "UPDATE NhanVien set HoTen='" + HoTen + "' ,GioiTinh=" + GioiTinh + ",SDT='" + SDT + "',NgaySinh='" + NgaySinh + "',MaCV='" + MaCV + "',TrangThai='" + TrangThai + "' where MaNV='" + MaNV + "' ";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean DoiMatKhau(String MaNV, String MatKhau) {
        String sql = "UPDATE NhanVien set MatKhau='" + MatKhau + "' where MaNV='" + MaNV + "' ";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean XoaNV(String MaNV) {
        String sql = "delete from NhanVien where MaNV = '" + MaNV + "'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String MK(String MaNV) {
        String mk = "";

        String sql = "select MATKHAU from nhanvien where MaNV = '" + MaNV + "'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }

        } catch (Exception e) {
        }
        return mk;
    }

    public String MaNV(String TenNV) {
        String sql = "select MaNV from NhanVien where HoTen = N'" + TenNV + "'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
