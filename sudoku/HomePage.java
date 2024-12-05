package sudoku;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Home page panel.
 */
public class HomePage extends JPanel {
    private static final long serialVersionUID = 1L;

    public HomePage(ActionListener startGameListener) {
        setLayout(new BorderLayout());

        // Label selamat datang
        JLabel lblTitle = new JLabel("Welcome to Sudoku!", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 36));
        add(lblTitle, BorderLayout.CENTER);

        // Tombol untuk memulai permainan
        JButton btnStartGame = new JButton("Start Game");
        btnStartGame.setFont(new Font("Arial", Font.PLAIN, 24));
        btnStartGame.addActionListener(startGameListener);
        add(btnStartGame, BorderLayout.SOUTH);
    }
}
