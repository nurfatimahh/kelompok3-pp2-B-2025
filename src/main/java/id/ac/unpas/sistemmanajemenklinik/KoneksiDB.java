package id.ac.unpas.sistemmanajemenklinik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class KoneksiDB {

    private static Connection conn;

    public static Connection configDB() {
        try {
            String url = "jdbc:mysql://localhost:3306/klinik_db?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String pass = "";

            conn = DriverManager.getConnection(url, user, pass);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,
                "Koneksi Database Gagal!\n" + e.getMessage());
        }
        return conn;
    }
}
