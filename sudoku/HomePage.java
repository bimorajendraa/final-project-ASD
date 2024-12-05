package sudoku;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Home page panel with BoxLayout and added image.
 */
public class HomePage extends JPanel {
    private static final long serialVersionUID = 1L;

    public HomePage(ActionListener startGameListener) {
        // Mengatur layout vertikal menggunakan BoxLayout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Menambahkan margin di sekeliling panel
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Label "Welcome to Sudoku!"
        JLabel lblTitle = new JLabel("Welcome to Sudoku!", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        lblTitle.setAlignmentX(CENTER_ALIGNMENT);
        add(lblTitle);

        // Menambahkan gambar
        ImageIcon sudokuImage = new ImageIcon("sudoku/image/windut.png"); // Ganti dengan path file gambar Anda
        Image scaledImage = sudokuImage.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH); // Mengubah ukuran gambar (200x200)
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        JLabel lblImage = new JLabel(resizedIcon);
        lblImage.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalStrut(20)); // Menambahkan jarak antar komponen
        add(lblImage);

        // Tombol "Start Game"
        JButton btnStartGame = new JButton("Start Game");
        btnStartGame.setFont(new Font("Arial", Font.PLAIN, 24));
        btnStartGame.setAlignmentX(CENTER_ALIGNMENT);
        btnStartGame.setFocusPainted(false); // Hilangkan garis fokus
        btnStartGame.addActionListener(startGameListener);
        add(Box.createVerticalStrut(20)); // Menambahkan jarak antar komponen
        add(btnStartGame);
    }
}
