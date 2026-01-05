/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.ac.unpas.sistemmanajemenklinik.views;
/**
 *
 * @author Narita Risnawati
 */
import id.ac.unpas.sistemmanajemenklinik.models.DokterModel;
import id.ac.unpas.sistemmanajemenklinik.models.PasienModel;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PemeriksaanView extends JPanel {
    public JComboBox<PasienModel> cmbPasien;
    public JComboBox<DokterModel> cmbDokter;
    public JTextArea txtDiagnosa;
    public JTextField txtCari;
    public JButton btnSimpan, btnEdit, btnHapus, btnReset, btnExport, btnCari;
    public JTable tablePemeriksaan;
    public DefaultTableModel tableModel;

    public PemeriksaanView() {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(240, 242, 245));

        // Header
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.setOpaque(false);
        JLabel lblTitle = new JLabel("Data Pemeriksaan");
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

        // Inputs
        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(new JLabel("Pilih Pasien:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; 
        cmbPasien = new JComboBox<>(); cmbPasien.setBackground(Color.WHITE);
        formPanel.add(cmbPasien, gbc);

        gbc.gridx = 2; gbc.gridy = 0; formPanel.add(new JLabel("Pilih Dokter:"), gbc);
        gbc.gridx = 3; gbc.gridy = 0; 
        cmbDokter = new JComboBox<>(); cmbDokter.setBackground(Color.WHITE);
        formPanel.add(cmbDokter, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(new JLabel("Diagnosa / Resep:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; gbc.gridwidth = 3; gbc.ipady = 40; // Tinggi TextArea
        txtDiagnosa = new JTextArea(); 
        txtDiagnosa.setBorder(BorderFactory.createLineBorder(new Color(200,200,200)));
        formPanel.add(new JScrollPane(txtDiagnosa), gbc);
        gbc.ipady = 0; // Reset

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
        searchPanel.add(new JLabel("Cari Data:"));
        txtCari = new JTextField(20); styleField(txtCari); searchPanel.add(txtCari);
        btnCari = styleButton("Cari", new Color(52, 73, 94)); searchPanel.add(btnCari);

        String[] columns = {"ID", "Pasien", "Dokter", "Diagnosa", "Tanggal"};
        tableModel = new DefaultTableModel(columns, 0);
        tablePemeriksaan = new JTable(tableModel);
        tablePemeriksaan.setRowHeight(30);
        tablePemeriksaan.getTableHeader().setBackground(new Color(44, 62, 80));
        tablePemeriksaan.getTableHeader().setForeground(Color.WHITE);
        tablePemeriksaan.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));

        centerPanel.add(searchPanel, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(tablePemeriksaan), BorderLayout.CENTER);

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