/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
/**
 *
 * @author Devandi
 */
public class KoneksiDatabase {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;

    public Connection getConn() {
        if (conn==null){
            try {
                Class.forName("com.mysql.jdbc.Driver");
                try {
                    String url = "jdbc:mysql://localhost:3306/proyekbp";
                    conn = DriverManager.getConnection(url, "root", "");
                    System.out.println("========== perpuslite ==========");
                    System.out.println("== Program Build Version: 1.0 ==");
                    System.out.println("Pesan: Aplikasi berhasil dijalankan tanpa kendala.");
                    System.out.println("================================");
                } catch (SQLException se) {
                    System.out.println("========== perpuslite ==========");
                    System.out.println("== Program Build Version: 1.0 ==");
                    System.out.println("Pesan: Koneksi gagal dengan alasan " + se);
                    System.out.println("================================");
                    System.exit(0);
                }
            } catch (ClassNotFoundException cnfe) {
                System.out.println("========== perpuslite ==========");
                System.out.println("== Program Build Version: 1.0 ==");
                System.out.println("Pesan: Class " + cnfe + " tidak ditemukan. Dimohon untuk membuat/ menambahkan class " + cnfe + " terlebih dahulu.");
                System.out.println("================================");
                System.exit(0);
            }
        }
        return conn;
    }

    public ResultSet getRs() {
        return rs;
    }

    public boolean eksekusiQuery(String query, boolean baris){
        try {
            ps = conn.prepareStatement(query);
            if(baris){
                rs = ps.executeQuery(); 
            }else{
                ps.executeUpdate(); 
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        new KoneksiDatabase().getConn();
    }
}
