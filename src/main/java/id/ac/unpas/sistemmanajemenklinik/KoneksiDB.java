/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.sistemmanajemenklinik;

/**
 *
 * @author Narita Risnawati
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KoneksiDB {
    
    private static Connection mysqlconfig;
    
    public static Connection configDB() throws SQLException {
        try {
            // 1. URL Database
            // Ganti 'klinik_db' sesuai nama database yang kamu buat di MySQL
            String url = "jdbc:mysql://localhost:3306/klinik_db"; 
            String user = "root"; // User default XAMPP
            String pass = "";     // Password default XAMPP (biasanya kosong)
            
            // 2. Registrasi Driver MySQL
            // Pastikan library mysql-connector-j sudah ditambahkan ke Libraries proyek
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            
            // 3. Buat Koneksi
            mysqlconfig = DriverManager.getConnection(url, user, pass);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Koneksi Database Gagal: " + e.getMessage());
        }
        
        return mysqlconfig;
    }
}
