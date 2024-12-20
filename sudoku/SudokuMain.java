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
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L;

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private Clip audioClip;
    private String[] songs = {"sudoku/soundboard/chill-guy.wav", "sudoku/soundboard/aur-auran.wav"};
    private int currentSongIndex = 0;


    public SudokuMain() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        HomePage homePage = new HomePage(e -> switchToGame());
        GamePage gamePage = new GamePage(e -> switchToHome());

        mainPanel.add(homePage, "Home");
        mainPanel.add(gamePage, "Game");

        getContentPane().add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku");
        pack();
        setLocationRelativeTo(null);

        // Create menu bar here
        createMenuBar();
        initializeAudio(songs[currentSongIndex]);
        playAudio();


        setVisible(true);
        cardLayout.show(mainPanel, "Home");
    }

    private void createMenuBar() {
        // Create menu bar and add it to JFrame
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Option");

        JMenuItem resetGameItem = new JMenuItem("Reset Game");
        JMenuItem exitItem = new JMenuItem("Exit");

        resetGameItem.addActionListener(e -> {
            // Reset game logic
            GamePage gamePage = (GamePage) mainPanel.getComponent(1);
            gamePage.board.newGame();
            gamePage.resetTimer(); // Reset timer ke 5 menit
            gamePage.timer.start();
        });

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(resetGameItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        JMenu soundMenu = new JMenu("Sound");
        JMenuItem muteItem = new JMenuItem("Mute");
        JMenuItem unmuteItem = new JMenuItem("Unmute");
        JMenu changeSongMenu = new JMenu("Change Song");

        muteItem.addActionListener(e -> stopAudio());
        unmuteItem.addActionListener(e -> playAudio());

        // Add song selection options
        for (int i = 0; i < songs.length; i++) {
            String songPath = songs[i];
            String songName = new File(songPath).getName();
            JMenuItem songItem = new JMenuItem(songName);
            final int songIndex = i;
            songItem.addActionListener(e -> {
                currentSongIndex = songIndex;
                initializeAudio(songs[currentSongIndex]);
                playAudio();
            });
            changeSongMenu.add(songItem);
        }

        soundMenu.add(muteItem);
        soundMenu.add(unmuteItem);
        soundMenu.add(changeSongMenu);
        menuBar.add(fileMenu);
        menuBar.add(soundMenu);

        setJMenuBar(menuBar);
    }

    private void initializeAudio(String audioFilePath) {
        if (audioClip != null && audioClip.isOpen()) {
            audioClip.close();
        }

        try {
            File audioFile = new File(audioFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            audioClip = AudioSystem.getClip();
            audioClip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            JOptionPane.showMessageDialog(this, "Error loading audio file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void playAudio() {
        if (audioClip != null) {
            audioClip.setFramePosition(0);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    private void stopAudio() {
        if (audioClip != null && audioClip.isRunning()) {
            audioClip.stop();
        }
    }


    private void switchToGame() {
        cardLayout.show(mainPanel, "Game");
        GamePage.timer.start();
    }

    private void switchToHome() {
        cardLayout.show(mainPanel, "Home");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuMain::new);
    }
}
