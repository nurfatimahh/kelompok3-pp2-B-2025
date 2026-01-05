/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.sistemmanajemenklinik.views;

/**
 *
 * @author Amaliyah
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PasienView extends JPanel {
    public JTextField txtNIK, txtNama, txtAlamat, txtNoHP, txtCari;
    public JButton btnSimpan, btnEdit, btnHapus, btnReset, btnExport, btnCari;
    public JTable tablePasien;
    public DefaultTableModel tableModel;

    public PasienView() {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(240, 242, 245)); // Background abu muda

        // --- 1. HEADER ---
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setOpaque(false);
        JLabel lblTitle = new JLabel("Manajemen Data Pasien");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(44, 62, 80));
        headerPanel.add(lblTitle);
        add(headerPanel, BorderLayout.NORTH);

        // --- 2. FORM INPUT (CARD STYLE) ---
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // Baris 1: NIK & Nama
        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("NIK (16 Digit):"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; txtNIK = new JTextField(15); styleField(txtNIK); formPanel.add(txtNIK, gbc);
        
        gbc.gridx = 2; gbc.gridy = 0; formPanel.add(new JLabel("Nama Lengkap:"), gbc);
        gbc.gridx = 3; gbc.gridy = 0; txtNama = new JTextField(15); styleField(txtNama); formPanel.add(txtNama, gbc);

        // Baris 2: Alamat & No HP
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Alamat:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; txtAlamat = new JTextField(15); styleField(txtAlamat); formPanel.add(txtAlamat, gbc);
        
        gbc.gridx = 2; gbc.gridy = 1; formPanel.add(new JLabel("No HP:"), gbc);
        gbc.gridx = 3; gbc.gridy = 1; txtNoHP = new JTextField(15); styleField(txtNoHP); formPanel.add(txtNoHP, gbc);

        // Baris 3: Tombol Action
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        
        btnSimpan = styleButton("Simpan", new Color(46, 204, 113));
        btnEdit = styleButton("Update", new Color(52, 152, 219));
        btnHapus = styleButton("Hapus", new Color(231, 76, 60));
        btnReset = styleButton("Reset", new Color(149, 165, 166));
        btnExport = styleButton("Export PDF", new Color(155, 89, 182));

        btnPanel.add(btnSimpan);
        btnPanel.add(btnEdit);
        btnPanel.add(btnHapus);
        btnPanel.add(btnReset);
        btnPanel.add(btnExport);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4; 
        formPanel.add(btnPanel, gbc);

        // --- 3. TABEL & SEARCH (CENTER) ---
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setOpaque(false);
        
        // Search Bar
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);
        searchPanel.add(new JLabel("Cari Data:"));
        txtCari = new JTextField(20); styleField(txtCari);
        searchPanel.add(txtCari);
        btnCari = styleButton("Cari", new Color(52, 73, 94));
        searchPanel.add(btnCari);
        
        // Table Style
        String[] columns = {"ID", "NIK", "Nama", "Alamat", "No HP"};
        tableModel = new DefaultTableModel(columns, 0);
        tablePasien = new JTable(tableModel);
        tablePasien.setRowHeight(30);
        tablePasien.getTableHeader().setBackground(new Color(44, 62, 80));
        tablePasien.getTableHeader().setForeground(Color.WHITE);
        tablePasien.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        
        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(tablePasien), BorderLayout.CENTER);

        // Gabung Atas (Form) dan Tengah (Tabel)
        JPanel mainContent = new JPanel(new BorderLayout(20, 20));
        mainContent.setOpaque(false);
        mainContent.add(formPanel, BorderLayout.NORTH);
        mainContent.add(centerPanel, BorderLayout.CENTER);

        add(mainContent, BorderLayout.CENTER);
    }

    // Helpers untuk styling
    private void styleField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 200, 200)), 
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    }

    private JButton styleButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return btn;
    }
}
