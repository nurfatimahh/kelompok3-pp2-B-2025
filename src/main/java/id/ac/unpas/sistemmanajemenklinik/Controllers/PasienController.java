/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.sistemmanajemenklinik.controllers;

/**
 *
 * @author Amaliyah
 */

import id.ac.unpas.sistemmanajemenklinik.KoneksiDB;
import id.ac.unpas.sistemmanajemenklinik.models.PasienModel;
import id.ac.unpas.sistemmanajemenklinik.views.PasienView;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.UUID;
import javax.swing.table.DefaultTableModel;
import java.text.MessageFormat;
import java.awt.print.PrinterException;

public class PasienController {
    private PasienView view;

    public PasienController(PasienView view) {
        this.view = view;

        this.view.btnSimpan.addActionListener(e -> simpan());
        this.view.btnEdit.addActionListener(e -> update());
        this.view.btnHapus.addActionListener(e -> delete());
        this.view.btnReset.addActionListener(e -> resetForm());
        this.view.btnCari.addActionListener(e -> search());
        this.view.btnExport.addActionListener(e -> exportPdf());
        
        this.view.tablePasien.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.tablePasien.getSelectedRow();
                view.txtNIK.setText(view.tableModel.getValueAt(row, 1).toString());
                view.txtNama.setText(view.tableModel.getValueAt(row, 2).toString());
                view.txtAlamat.setText(view.tableModel.getValueAt(row, 3).toString());
                view.txtNoHP.setText(view.tableModel.getValueAt(row, 4).toString());
            }
        });
        
        loadData(); // Load data dari Database saat awal jalan
    }

    private void loadData() {
        view.tableModel.setRowCount(0);
        try {
            Connection conn = KoneksiDB.configDB();
            Statement stm = conn.createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM pasien");
            while(res.next()) {
                view.tableModel.addRow(new Object[]{
                    res.getString("id"), 
                    res.getString("nik"), 
                    res.getString("nama"), 
                    res.getString("alamat"), 
                    res.getString("no_hp")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void simpan() {
        String nik = view.txtNIK.getText();
        String nama = view.txtNama.getText();
        String alamat = view.txtAlamat.getText();
        String noHp = view.txtNoHP.getText();

        if (nama.trim().isEmpty() || !nik.matches("\\d{16}") || noHp.length() < 12) {
            JOptionPane.showMessageDialog(view, "Cek Inputan: Nama wajib, NIK 16 digit, HP min 12 digit!");
            return;
        }

        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "INSERT INTO pasien (id, nik, nama, alamat, no_hp) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, UUID.randomUUID().toString());
            p.setString(2, nik);
            p.setString(3, nama);
            p.setString(4, alamat);
            p.setString(5, noHp);
            p.execute();
            
            JOptionPane.showMessageDialog(view, "Data Pasien Berhasil Disimpan ke Database!");
            loadData();
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal Simpan: " + e.getMessage());
        }
    }

    private void update() {
        int row = view.tablePasien.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Pilih data dulu!"); return;
        }
        
        String id = view.tableModel.getValueAt(row, 0).toString();
        
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "UPDATE pasien SET nik=?, nama=?, alamat=?, no_hp=? WHERE id=?";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, view.txtNIK.getText());
            p.setString(2, view.txtNama.getText());
            p.setString(3, view.txtAlamat.getText());
            p.setString(4, view.txtNoHP.getText());
            p.setString(5, id);
            p.execute();
            
            JOptionPane.showMessageDialog(view, "Data Berhasil Diupdate!");
            loadData();
            resetForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Gagal Update: " + e.getMessage());
        }
    }

    private void delete() {
        int row = view.tablePasien.getSelectedRow();
        if (row == -1) return;
        
        int confirm = JOptionPane.showConfirmDialog(view, "Yakin hapus?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String id = view.tableModel.getValueAt(row, 0).toString();
            try {
                Connection conn = KoneksiDB.configDB();
                PreparedStatement p = conn.prepareStatement("DELETE FROM pasien WHERE id=?");
                p.setString(1, id);
                p.execute();
                loadData();
                resetForm();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void search() {
        String key = "%" + view.txtCari.getText() + "%";
        view.tableModel.setRowCount(0);
        try {
            Connection conn = KoneksiDB.configDB();
            PreparedStatement p = conn.prepareStatement("SELECT * FROM pasien WHERE nama LIKE ? OR nik LIKE ?");
            p.setString(1, key);
            p.setString(2, key);
            ResultSet res = p.executeQuery();
            while(res.next()) {
                view.tableModel.addRow(new Object[]{
                    res.getString("id"), res.getString("nik"), res.getString("nama"), 
                    res.getString("alamat"), res.getString("no_hp")
                });
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void resetForm() {
        view.txtNIK.setText(""); view.txtNama.setText(""); view.txtAlamat.setText(""); view.txtNoHP.setText("");
        loadData();
    }
    
    private void exportPdf() {
        try { view.tablePasien.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat("Data Pasien"), null); } 
        catch (PrinterException ex) { ex.printStackTrace(); }
    }
}