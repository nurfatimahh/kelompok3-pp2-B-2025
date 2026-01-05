/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.sistemmanajemenklinik.controllers;

/**
 *
 * @author astri
 */
import id.ac.unpas.sistemmanajemenklinik.KoneksiDB;
import id.ac.unpas.sistemmanajemenklinik.views.DokterView;
import java.sql.*;
import java.util.UUID;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import java.text.MessageFormat;
import java.awt.print.PrinterException;

public class DokterController {
    private DokterView view;

    public DokterController(DokterView view) {
        this.view = view;
        
        this.view.btnSimpan.addActionListener(e -> simpan());
        this.view.btnEdit.addActionListener(e -> update());
        this.view.btnHapus.addActionListener(e -> delete());
        this.view.btnReset.addActionListener(e -> resetForm());
        this.view.btnCari.addActionListener(e -> search());
        this.view.btnExport.addActionListener(e -> exportPdf());

        this.view.tableDokter.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = view.tableDokter.getSelectedRow();
                view.txtNama.setText(view.tableModel.getValueAt(row, 1).toString());
                view.cmbSpesialisasi.setSelectedItem(view.tableModel.getValueAt(row, 2).toString());
                view.cmbJadwal.setSelectedItem(view.tableModel.getValueAt(row, 3).toString());
            }
        });
        loadData();
    }

    private void loadData() {
        view.tableModel.setRowCount(0);
        try {
            Connection conn = KoneksiDB.configDB();
            ResultSet res = conn.createStatement().executeQuery("SELECT * FROM dokter");
            while(res.next()) {
                view.tableModel.addRow(new Object[]{
                    res.getString("id"), res.getString("nama"), 
                    res.getString("spesialisasi"), res.getString("jadwal")
                });
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void simpan() {
        if(view.txtNama.getText().isEmpty() || view.cmbSpesialisasi.getSelectedIndex() == 0 || view.cmbJadwal.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(view, "Nama, Spesialisasi, dan Jadwal wajib dipilih!"); return;
        }
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "INSERT INTO dokter VALUES (?, ?, ?, ?)";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, UUID.randomUUID().toString());
            p.setString(2, view.txtNama.getText());
            p.setString(3, view.cmbSpesialisasi.getSelectedItem().toString());
            p.setString(4, view.cmbJadwal.getSelectedItem().toString());
            p.execute();
            JOptionPane.showMessageDialog(view, "Dokter Berhasil Disimpan!");
            loadData(); resetForm();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void update() {
        int row = view.tableDokter.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(view, "Pilih data dokter terlebih dahulu!");
            return;
        }
        
        if (view.cmbJadwal.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(view, "Pilih jadwal yang valid!"); return;
        }

        String id = view.tableModel.getValueAt(row, 0).toString();
        try {
            Connection conn = KoneksiDB.configDB();
            String sql = "UPDATE dokter SET nama=?, spesialisasi=?, jadwal=? WHERE id=?";
            PreparedStatement p = conn.prepareStatement(sql);
            p.setString(1, view.txtNama.getText());
            p.setString(2, view.cmbSpesialisasi.getSelectedItem().toString());
            p.setString(3, view.cmbJadwal.getSelectedItem().toString());
            p.setString(4, id);
            p.execute();
            JOptionPane.showMessageDialog(view, "Data Dokter Diupdate!");
            loadData(); resetForm();
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void delete() {
        int row = view.tableDokter.getSelectedRow();
        if (row == -1) return;
        int confirm = JOptionPane.showConfirmDialog(view, "Hapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if(confirm == JOptionPane.YES_OPTION) {
            String id = view.tableModel.getValueAt(row, 0).toString();
            try {
                KoneksiDB.configDB().createStatement().execute("DELETE FROM dokter WHERE id='" + id + "'");
                loadData(); resetForm();
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    private void search() {
        String key = "%" + view.txtCari.getText() + "%";
        view.tableModel.setRowCount(0);
        try {
            Connection conn = KoneksiDB.configDB();
            PreparedStatement p = conn.prepareStatement("SELECT * FROM dokter WHERE nama LIKE ?");
            p.setString(1, key);
            ResultSet res = p.executeQuery();
            while(res.next()) {
                view.tableModel.addRow(new Object[]{res.getString("id"), res.getString("nama"), res.getString("spesialisasi"), res.getString("jadwal")});
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void resetForm() {
        view.txtNama.setText(""); 
        view.cmbSpesialisasi.setSelectedIndex(0); 
        view.cmbJadwal.setSelectedIndex(0);
        view.tableDokter.clearSelection();
        loadData();
    }
    
    private void exportPdf() {
        try { view.tableDokter.print(JTable.PrintMode.FIT_WIDTH, new MessageFormat("Data Dokter"), null); } catch (PrinterException ex) {}
    }
}