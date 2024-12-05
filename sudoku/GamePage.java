package sudoku;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GamePage extends JPanel {
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // Private variables
    GameBoardPanel board = new GameBoardPanel();
    JButton btnNewGame = new JButton("New Game");

    // Constructor
    public GamePage(ActionListener startGameListener) {
        setLayout(new BorderLayout());  // Set layout for this JPanel

        add(board, BorderLayout.CENTER); // Tambahkan board ke JPanel
        add(btnNewGame, BorderLayout.SOUTH); // Tambahkan tombol ke bawah

        // Action listener untuk tombol New Game
        btnNewGame.addActionListener(e -> board.newGame());

        // Mulai game baru
        board.newGame();
    }
}
