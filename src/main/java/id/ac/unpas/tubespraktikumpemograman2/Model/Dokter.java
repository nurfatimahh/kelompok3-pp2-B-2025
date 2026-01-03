/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.tubespraktikumpemograman2.Model;

/**
 *
 * @author ASUS X441M
 */
public class Dokter {
    private int id;
    private String nama;
    private String spesialis;

    public Dokter(String nama, String spesialis) {
        this.nama = nama;
        this.spesialis = spesialis;
    }

    public String getNama() { 
        return nama; 
    }
    public String getSpesialis() { 
        return spesialis; 
    }
}
