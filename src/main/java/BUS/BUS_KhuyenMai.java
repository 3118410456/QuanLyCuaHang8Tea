/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.DAO;
import DTO.KhuyenMai;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class BUS_KhuyenMai {
    DAO dao = new DAO();
    
    public ArrayList<KhuyenMai> getListKM()   {
        ArrayList<KhuyenMai> list = new ArrayList<>();
        String sql = "SELECT * FROM KHUYENMAI";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
//            DateTimeFormatter fm = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//            LocalDate bd = LocalDate.parse(String.valueOf(rs.getString(4)) , fm);
//            LocalDate kt = LocalDate.parse(String.valueOf(rs.getString(5)) , fm);
            
            KhuyenMai km = new KhuyenMai();
            km.setMaKM(rs.getString(1));
            km.setTenKM(rs.getString(2));
            km.setPhanTramKM(rs.getInt(3));
            km.setNgayBD(rs.getString(4));
            km.setNgayKT(rs.getString(5));
            
            list.add(km);
            
        }
        } catch (SQLException ex) {
            Logger.getLogger(BUS_KhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list ; 
    }
    
    
    public boolean themKM(KhuyenMai km)
    {
        String sql = "INSERT INTO KHUYENMAI(MaKM,TenCTKM,PhanTramKM,NgayBD,NgayKT) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            
            
            
            ps.setString(1,km.getMaKM());
            ps.setString(2,km.getTenKM());
            ps.setInt(3,km.getPhanTramKM());
            ps.setString(4,km.getNgayBD());
            ps.setString(5,km.getNgayKT());
            
            return ps.executeUpdate() > 0 ;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean xoaKM(String MaKM)
    {
        String sql = "DELETE FROM KHUYENMAI WHERE MaKM='"+MaKM+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int PhanTramKM(String MaKM)
    {
        int km = 0;
        String sql = "SELECT PhanTramKM from KHUYENMAI where MaKM = '"+MaKM+"'";
        try {
            PreparedStatement ps = dao.conn().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                km += rs.getInt(1);
            }
            return km;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return km ;
    }
    public boolean KiemTraHopLe(String MaKM){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/M/yyyy");  
        LocalDateTime now = LocalDateTime.now();  
        
        String sql = "SELECT * FROM khuyenmai where STR_TO_DATE('"+dtf.format(now)+"','%d/%m/%Y') BETWEEN STR_TO_DATE(NGAYBD,'%d/%m/%Y') AND STR_TO_DATE(NGAYKT,'%d/%m/%Y') and MAKM = '"+MaKM+"'";
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
