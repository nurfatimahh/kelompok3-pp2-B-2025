/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.sistemmanajemenklinik.Models;

public class PasienModel {
    private String id;
    private String nik;
    private String nama;
    private String alamat;
    private String noHp;

    public PasienModel(String id, String nik, String nama, String alamat, String noHp) {
        this.id = id;
        this.nik = nik;
        this.nama = nama;
        this.alamat = alamat;
        this.noHp = noHp;
    }

    // Getters and Setters
    public String getId() { return id; }
    public String getNik() { return nik; }
    public void setNik(String nik) { this.nik = nik; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getNoHp() { return noHp; }
    public void setNoHp(String noHp) { this.noHp = noHp; }
    
    @Override
    public String toString() {
        return nama + " (" + nik + ")";
    }
}
