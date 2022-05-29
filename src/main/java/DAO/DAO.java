/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class DAO {
    
    
    public DAO()
    {
       
    }
    public Connection conn()
    {
         Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/eitea","root","123456");
           conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/eitea","root","");
//            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;
    }
    public String ktraDangNhap(String MaNV ,String MatKhau)    {
        //ArrayList<TaiKhoan> list = new ArrayList<>();
        String sql = "select * from NhanVien where MaNV= '"+MaNV+"' and MatKhau='"+MatKhau+"'";
        try {
            //Connection dmm = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/eitea","root","123456");
            Connection dmm = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/eitea","root","");
            PreparedStatement ps = dmm.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                if(rs.getInt("TrangThai")==1) return rs.getString("MaNV");
                else if(rs.getInt("TrangThai")==0) return "LOCK";
            }
       
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        
    }
    
    public static void main(String[] args) {
       new DAO();
    }

}
