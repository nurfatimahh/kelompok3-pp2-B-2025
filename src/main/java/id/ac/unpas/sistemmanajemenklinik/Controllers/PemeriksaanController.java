/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.sistemmanajemenklinik.controllers;

import id.ac.unpas.sistemmanajemenklinik.models.PasienModel;
import id.ac.unpas.sistemmanajemenklinik.models.DokterModel;
import id.ac.unpas.sistemmanajemenklinik.KoneksiDB;
import id.ac.unpas.sistemmanajemenklinik.views.PemeriksaanView;
import java.sql.*;
import java.util.UUID;
import java.util.Date;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import java.text.MessageFormat;
import java.awt.print.PrinterException;

public class PemeriksaanController {
    private PemeriksaanView view;

    public PemeriksaanController(PemeriksaanView view) {
        this.view = view;
        
        this.view.btnSimpan.addActionListener(e -> simpan());
        this.view.btnEdit.addActionListener(e -> update());
        this.view.btnHapus.addActionListener(e -> delete());
        this.view.btnReset.addActionListener(e -> resetForm());
        this.view.btnCari.addActionListener(e -> search());
        this.view.btnExport.addActionListener(e -> exportPdf());
        
        this.view.tablePemeriksaan.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.tablePemeriksaan.getSelectedRow();
                view.txtDiagnosa.setText(view.tableModel.getValueAt(row, 3).toString());
            }
        });

        loadComboData();
        loadTableData();
    }

    // Load data Pasien & Dokter dari DB ke ComboBox
    public void loadComboData() {
        view.cmbPasien.removeAllItems();
        view.cmbDokter.removeAllItems();
        try {
            Connection conn = KoneksiDB.configDB();
            
            // Isi Combo Pasien
            ResultSet resP = conn.createStatement().executeQuery("SELECT * FROM pasien");
            while(resP.next()) {
                // Trik: Simpan Object PasienModel ke ComboBox
                view.cmbPasien.addItem(new PasienModel(
                    resP.getString("id"), resP.getString("nik"), resP.getString("nama"), "", ""
                ));
            }

            // Isi Combo Dokter
            ResultSet resD = conn.createStatement().executeQuery("SELECT * FROM dokter");
            while(resD.next()) {
                view.cmbDokter.addItem(new DokterModel(
                    resD.getString("id"), resD.getString("nama"), "", ""
                ));
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void loadTableData() {
        view.tableModel.setRowCount(0);
        try {
            Connection conn = KoneksiDB.configDB();
            // JOIN 3 Tabel untuk menampilkan Nama Pasien & Dokter, bukan ID-nya
            String sql = "SELECT p.id, ps.nama as nama_pasien, d.nama as nama_dokter, p.diagnosa, p.tanggal " +
                         "FROM pemeriksaan p " +
                         "JOIN pasien ps ON p.pasien_id = ps.id " +
                         "JOIN dokter d ON p.dokter_id = d.id";
            ResultSet res = conn.createStatement().executeQuery(sql);
            while(res.next()) {
                view.tableModel.addRow(new Object[]{
                    res.getString("id"),
                    res.getString("nama_pasien"),
                    res.getString("nama_dokter"),
                    res.getString("diagnosa"),
                    res.getString("tanggal")
                });
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void simpan() {
        PasienModel p = (PasienModel) view.cmbPasien.getSelectedItem();
        DokterModel d = (DokterModel) view.cmbDokter.getSelectedItem();
        
        if (p == null || d == null) {
            JOptionPane.showMessageDialog(view, "Pilih Pasien & Dokter!"); return;
        }

        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "INSERT INTO pemeriksaan (id, pasien_id, dokter_id, diagnosa, tanggal) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, UUID.randomUUID().toString());
            pst.setString(2, p.getId()); // Ambil ID asli dari Database
            pst.setString(3, d.getId()); // Ambil ID asli dari Database
            pst.setString(4, view.txtDiagnosa.getText());
            pst.setString(5, new Date().toString());
            pst.execute();
            
            JOptionPane.showMessageDialog(view, "Data Pemeriksaan Berhasil Disimpan!");
            loadTableData(); resetForm();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    private void update() {
        int row = view.tablePemeriksaan.getSelectedRow();
        if(row == -1) return;
        String id = view.tableModel.getValueAt(row, 0).toString();
        
        try {
             Connection conn = KoneksiDB.configDB();
             PreparedStatement pst = conn.prepareStatement("UPDATE pemeriksaan SET diagnosa=? WHERE id=?");
             pst.setString(1, view.txtDiagnosa.getText());
             pst.setString(2, id);
             pst.execute();
             JOptionPane.showMessageDialog(view, "Data Pemeriksaan Diupdate!");
             loadTableData(); resetForm();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void delete() {
        int row = view.tablePemeriksaan.getSelectedRow();
        if(row == -1) return;
        String id = view.tableModel.getValueAt(row, 0).toString();
        try {
            KoneksiDB.configDB().createStatement().execute("DELETE FROM pemeriksaan WHERE id='" + id + "'");
            loadTableData(); resetForm();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void search() {
         String key = "%" + view.txtCari.getText() + "%";
         view.tableModel.setRowCount(0);
         try {
            Connection conn = KoneksiDB.configDB();
            String sql = "SELECT p.id, ps.nama as nama_pasien, d.nama as nama_dokter, p.diagnosa, p.tanggal " +
                         "FROM pemeriksaan p " +
                         "JOIN pasien ps ON p.pasien_id = ps.id " +
                         "JOIN dokter d ON p.dokter_id = d.id " + 
                         "WHERE ps.nama LIKE ? OR d.nama LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, key);
            pst.setString(2, key);
            ResultSet res = pst.executeQuery();
            while(res.next()) {
                view.tableModel.addRow(new Object[]{
                    res.getString("id"), res.getString("nama_pasien"), res.getString("nama_dokter"),
                    res.getString("diagnosa"), res.getString("tanggal")
                });
            }
         } catch (Exception e) { e.printStackTrace(); }
    }

    private void resetForm() {
        view.txtDiagnosa.setText(""); view.cmbPasien.setSelectedIndex(0); view.cmbDokter.setSelectedIndex(0);
        loadTableData();
    }
    
    private void exportPdf() {
        try { view.tablePemeriksaan.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat("Laporan Pemeriksaan"), null); } catch (PrinterException ex) {}
    }
}
