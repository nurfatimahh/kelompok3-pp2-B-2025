/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.sistemmanajemenklinik.views;

/**
 *
 * @author astri
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DokterView extends JPanel {
    public JTextField txtNama, txtCari;
    // Ubah txtJadwal jadi cmbJadwal
    public JComboBox<String> cmbSpesialisasi, cmbJadwal;
    public JButton btnSimpan, btnEdit, btnHapus, btnReset, btnExport, btnCari;
    public JTable tableDokter;
    public DefaultTableModel tableModel;

    public DokterView() {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(240, 242, 245));

        // Header
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setOpaque(false);
        JLabel lblTitle = new JLabel("Manajemen Data Dokter");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitle.setForeground(new Color(44, 62, 80));
        headerPanel.add(lblTitle);
        add(headerPanel, BorderLayout.NORTH);

        // Form Panel
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

        // Input Nama
        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Nama Dokter:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; txtNama = new JTextField(15); styleField(txtNama); formPanel.add(txtNama, gbc);

        // Input Spesialisasi
        gbc.gridx = 2; gbc.gridy = 0; formPanel.add(new JLabel("Spesialisasi:"), gbc);
        gbc.gridx = 3; gbc.gridy = 0; 
        String[] spesialis = {"-- Pilih Spesialisasi --", "Dokter Umum", "Spesialis Gigi", "Spesialis Anak", "Spesialis Kandungan", "Spesialis Penyakit Dalam", "Spesialis Bedah", "Spesialis Mata", "Spesialis THT", "Spesialis Kulit & Kelamin"};
        cmbSpesialisasi = new JComboBox<>(spesialis);
        cmbSpesialisasi.setBackground(Color.WHITE);
        formPanel.add(cmbSpesialisasi, gbc);

        // Input Jadwal (Sekarang Dropdown)
        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Jadwal Praktik:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 3; 
        
        String[] jadwal = {
            "-- Pilih Jadwal --",
            "Pagi: Senin - Rabu (08:00 - 12:00)",
            "Siang: Senin - Rabu (13:00 - 16:00)",
            "Sore: Senin - Rabu (16:00 - 20:00)",
            "Pagi: Kamis - Sabtu (08:00 - 12:00)",
            "Siang: Kamis - Sabtu (13:00 - 16:00)",
            "Sore: Kamis - Sabtu (16:00 - 20:00)",
            "Shift Malam: Senin - Jumat (19:00 - 22:00)"
        };
        cmbJadwal = new JComboBox<>(jadwal);
        cmbJadwal.setBackground(Color.WHITE);
        formPanel.add(cmbJadwal, gbc);

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        btnSimpan = styleButton("Simpan", new Color(46, 204, 113));
        btnEdit = styleButton("Update", new Color(52, 152, 219));
        btnHapus = styleButton("Hapus", new Color(231, 76, 60));
        btnReset = styleButton("Reset", new Color(149, 165, 166));
        btnExport = styleButton("Export PDF", new Color(155, 89, 182));

        btnPanel.add(btnSimpan); btnPanel.add(btnEdit); btnPanel.add(btnHapus); 
        btnPanel.add(btnReset); btnPanel.add(btnExport);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 4; 
        formPanel.add(btnPanel, gbc);

        // Table & Search
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setOpaque(false);
        
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setOpaque(false);
        searchPanel.add(new JLabel("Cari Dokter:"));
        txtCari = new JTextField(20); styleField(txtCari); searchPanel.add(txtCari);
        btnCari = styleButton("Cari", new Color(52, 73, 94)); searchPanel.add(btnCari);

        String[] columns = {"ID", "Nama", "Spesialisasi", "Jadwal"};
        tableModel = new DefaultTableModel(columns, 0);
        tableDokter = new JTable(tableModel);
        tableDokter.setRowHeight(30);
        tableDokter.getTableHeader().setBackground(new Color(44, 62, 80));
        tableDokter.getTableHeader().setForeground(Color.WHITE);
        tableDokter.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(tableDokter), BorderLayout.CENTER);

        JPanel mainContent = new JPanel(new BorderLayout(20, 20));
        mainContent.setOpaque(false);
        mainContent.add(formPanel, BorderLayout.NORTH);
        mainContent.add(centerPanel, BorderLayout.CENTER);

        add(mainContent, BorderLayout.CENTER);
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    }
    private JButton styleButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg); btn.setForeground(Color.WHITE); btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        return btn;
    }
}