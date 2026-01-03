/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.tubespraktikumpemograman2.Model;

/**
 *
 * @author ASUS X441M
 */
public class Pasien {
    private int id;
    private String nik;
    private String nama;
    private String alamat;

    public Pasien(String nik, String nama, String alamat) {
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
    }

    public String getNik() { 
        return nik; 
    }
    public String getNama() { 
        return nama; 
    }
    public String getAlamat() { 
        return alamat; 
    }
}