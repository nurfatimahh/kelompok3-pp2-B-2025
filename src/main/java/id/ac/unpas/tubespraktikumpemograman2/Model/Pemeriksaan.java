/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.tubespraktikumpemograman2.Model;

/**
 *
 * @author ASUS X441M
 */
public class Pemeriksaan {
    private int id;
    private int idPasien;
    private int idDokter;
    private String diagnosa;

    public Pemeriksaan(int idPasien, int idDokter, String diagnosa) {
        this.idPasien = idPasien;
        this.idDokter = idDokter;
        this.diagnosa = diagnosa;
    }

    public String getDiagnosa() { 
        return diagnosa; 
    }
}
