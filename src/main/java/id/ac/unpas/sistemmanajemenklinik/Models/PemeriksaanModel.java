/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.sistemmanajemenklinik.Models;


public class PemeriksaanModel {
    private String id;
    private PasienModel pasien;
    private DokterModel dokter;
    private String diagnosa;
    private String tanggal;

    public PemeriksaanModel (String id, PasienModel pasien, DokterModel dokter, String diagnosa, String tanggal) {
        this.id = id;
        this.pasien = pasien;
        this.dokter = dokter;
        this.diagnosa = diagnosa;
        this.tanggal = tanggal;
    }

    public String getId() { return id; }
    public PasienModel getPasien() { return pasien; }
    public DokterModel getDokter() { return dokter; }
    public String getDiagnosa() { return diagnosa; }
    public void setDiagnosa(String diagnosa) { this.diagnosa = diagnosa; }
    public String getTanggal() { return tanggal; }
}
