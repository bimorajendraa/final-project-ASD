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

public class GamePage extends JPanel {
    private static final long serialVersionUID = 1L;  // to prevent serial warning
    private static final int GAME_TIME = 180; // menambah waktu bermain selama 180 detik (3 menit)

    // Initialize variables
    GameBoardPanel board = new GameBoardPanel();
    JButton btnNewGame = new JButton("New Game");
    JButton btnPause = new JButton("Pause");
    JButton btnResume = new JButton("Resume");
    JLabel timerLabel = new JLabel("Time Left: 03:00", SwingConstants.CENTER);
    static Timer timer;
    int remainingTime;

    // Constructor
    public GamePage(ActionListener startGameListener) {
        setLayout(new BorderLayout());  // Set layout for this JPanel
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(timerLabel);
        topPanel.add(btnPause);
        topPanel.add(btnResume);

        add(topPanel, BorderLayout.NORTH);
        add(board, BorderLayout.CENTER); // Tambahkan board ke JPanel
        add(btnNewGame, BorderLayout.SOUTH); // Tambahkan tombol ke bawah

        // Timer initialization
        remainingTime = GAME_TIME;
        timer = new Timer(1000, e -> updateTimer());

        // Action listener untuk tombol New Game
        btnNewGame.addActionListener(e -> board.newGame());
        btnPause.addActionListener(e -> pauseTimer());
        btnResume.addActionListener(e -> resumeTimer());
        btnNewGame.addActionListener(e -> startNewGame());

        // Mulai game baru
        board.newGame();
    }

    private void startNewGame() {
        board.newGame(); // Mengatur ulang papan permainan
        resetTimer();    // Reset waktu ke 3 menit
        timer.start();   // Mulai timer
    }


    private void pauseTimer() {
        timer.stop();
    }

    private void resumeTimer() {
        timer.start();
    }

    public void resetTimer() {
        remainingTime = GAME_TIME;
        updateTimerLabel();
        timer.stop(); // Pastikan timer dihentikan dulu sebelum reset
    }

    private void updateTimer() {
        if (board.isSolved()) { // Jika permainan selesai
            timer.stop();         // Hentikan timer
            return;
        }
        if (remainingTime > 0) {
            remainingTime--;
            updateTimerLabel();
        } else {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Time's up! Game Over.", "Game Over", JOptionPane.WARNING_MESSAGE);
            startNewGame();
        }
    }


    private void updateTimerLabel() {
        int minutes = remainingTime / 60;
        int seconds = remainingTime % 60;
        timerLabel.setText(String.format("Time Left: %02d:%02d", minutes, seconds));
    }

}
