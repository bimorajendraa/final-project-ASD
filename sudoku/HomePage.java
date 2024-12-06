/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group 4
 * 1 - 5026231092 - Muhammad Fawwaz Al-Amien 
 * 2 - 5026231161 - Muhammad Daniel Alfarisi
 * 3 - 5026231210 - Bimo Rajendra Widyadhana
 */
package sudoku;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Home page panel with BoxLayout and centered components.
 */
public class HomePage extends JPanel {
    private static final long serialVersionUID = 1L;

    public HomePage(ActionListener startGameListener) {
        // Mengatur layout vertikal menggunakan BoxLayout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Menambahkan margin di sekeliling panel
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        add(Box.createVerticalGlue());        

        // Menambahkan gambar
        ImageIcon sudokuImage = new ImageIcon("sudoku/image/sudoku.jpg"); // Ganti dengan path file gambar Anda
        Image scaledImage = sudokuImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Mengubah ukuran gambar (200x200)
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JLabel lblImage = new JLabel(resizedIcon);
        lblImage.setAlignmentX(CENTER_ALIGNMENT); // Tengah horizontal
        add(lblImage);

        JLabel lblTitle = new JLabel("Welcome to Sudoku!", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20)); // Menambahkan jarak antar komponen
        add(lblTitle);

        // Tombol "Start Game"
        JButton btnStartGame = new JButton("Start Game");
        btnStartGame.setFont(new Font("Arial", Font.PLAIN, 24));
        btnStartGame.setAlignmentX(CENTER_ALIGNMENT); // Tengah horizontal
        btnStartGame.setFocusPainted(false); // Hilangkan garis fokus
        btnStartGame.addActionListener(startGameListener);
        add(Box.createVerticalStrut(20)); // Menambahkan jarak antar komponen
        add(btnStartGame);

        // Glue di bawah untuk centering vertikal
        add(Box.createVerticalGlue());
    }
}
