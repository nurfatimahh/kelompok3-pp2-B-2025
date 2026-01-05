/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.sistemmanajemenklinik;

import id.ac.unpas.sistemmanajemenklinik.views.PasienView;
import id.ac.unpas.sistemmanajemenklinik.views.DokterView;
import id.ac.unpas.sistemmanajemenklinik.views.PemeriksaanView;

import id.ac.unpas.sistemmanajemenklinik.controllers.PasienController;
import id.ac.unpas.sistemmanajemenklinik.controllers.DokterController;
import id.ac.unpas.sistemmanajemenklinik.controllers.PemeriksaanController;

import javax.swing.*;

public class Main extends JFrame {
    
    public Main() {
        setTitle("Klinik Sehat Dashboard");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Init MVC (Tidak perlu oper List lagi, Controller urus DB sendiri)
        PasienView vPasien = new PasienView();
        new PasienController(vPasien);

        DokterView vDokter = new DokterView();
        new DokterController(vDokter);

        PemeriksaanView vPeriksa = new PemeriksaanView();
        PemeriksaanController cPeriksa = new PemeriksaanController(vPeriksa);

        // Tabs
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Data Pasien", vPasien);
        tabs.addTab("Data Dokter", vDokter);
        tabs.addTab("Pemeriksaan", vPeriksa);

        // Refresh combobox saat tab Pemeriksaan dibuka agar data pasien/dokter terbaru muncul
        tabs.addChangeListener(e -> {
            if (tabs.getSelectedIndex() == 2) cPeriksa.loadComboData();
        });

        add(tabs);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().setVisible(true));
    }
}