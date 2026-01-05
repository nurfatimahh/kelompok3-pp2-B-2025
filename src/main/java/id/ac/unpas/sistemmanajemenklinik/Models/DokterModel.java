/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.sistemmanajemenklinik.Models;

public class DokterModel {
    private String id;
    private String nama;
    private String spesialisasi;
    private String jadwal;

    public DokterModel(String id, String nama, String spesialisasi, String jadwal) {
        this.id = id;
        this.nama = nama;
        this.spesialisasi = spesialisasi;
        this.jadwal = jadwal;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getSpesialisasi() { return spesialisasi; }
    public void setSpesialisasi(String spesialisasi) { this.spesialisasi = spesialisasi; }
    public String getJadwal() { return jadwal; }
    public void setJadwal(String jadwal) { this.jadwal = jadwal; }

    @Override
    public String toString() {
        return nama + " - " + spesialisasi;
    }
}
