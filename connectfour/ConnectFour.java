/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group 4
 * 1 - 5026231092 - Muhammad Fawwaz Al-Amien 
 * 2 - 5026231161 - Muhammad Daniel Alfarisi
 * 3 - 5026231210 - Bimo Rajendra Widyadhana
 */
package connectfour;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class ConnectFour extends JPanel {
    private static final long serialVersionUID = 1L;

    // Define constants
    public static final String TITLE = "Connect Four";
    public static final Color COLOR_BG = Color.WHITE;
    public static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
    public static final Color COLOR_CROSS = new Color(239, 105, 80);  // Red #EF6950
    public static final Color COLOR_NOUGHT = new Color(64, 154, 225); // Blue #409AE1
    public static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);

    // Game objects and state
    private Board board;
    private State currentState;
    private Seed currentPlayer;
    private JLabel statusBar;
    private JButton newGameButton;
    private GameMode gameMode;
    private int redWins = 0;
    private int blueWins = 0;
    private JLabel scoreLabel;

    public enum GameMode {
        CASUAL,
        FUN
    }

    public ConnectFour() {
        setupMouseListener();
        setupUI();
        initGame();
        showGameModeDialog();
    }

    private void setupMouseListener() {
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                int col = mouseX / Cell.SIZE;

                if (currentState == State.PLAYING || 
                    (gameMode == GameMode.FUN && (currentState == State.CROSS_WON || currentState == State.NOUGHT_WON))) {
                    if (col >= 0 && col < Board.COLS) {
                        makeMove(col);
                    }
                } else if (currentState == State.DRAW) {
                    newGame();
                }
                repaint();
            }
        });
    }

    private void setupUI() {
        // Status bar setup
        statusBar = new JLabel();
        statusBar.setFont(FONT_STATUS);
        statusBar.setBackground(COLOR_BG_STATUS);
        statusBar.setOpaque(true);
        statusBar.setPreferredSize(new Dimension(300, 30));
        statusBar.setHorizontalAlignment(JLabel.LEFT);
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));

        // Score label setup
        scoreLabel = new JLabel("RED: 0 | BLUE: 0");
        scoreLabel.setFont(FONT_STATUS);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setPreferredSize(new Dimension(150, 30));

        // New Game button setup
        newGameButton = new JButton("New Game");
        newGameButton.setFont(FONT_STATUS);
        newGameButton.addActionListener(e -> {
            showGameModeDialog();
        });

        // Layout setup
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(statusBar, BorderLayout.WEST);
        bottomPanel.add(scoreLabel, BorderLayout.CENTER);
        bottomPanel.add(newGameButton, BorderLayout.EAST);

        setLayout(new BorderLayout());
        add(bottomPanel, BorderLayout.PAGE_END);
        setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 30));
        setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 2, false));
    }

    private void showGameModeDialog() {
        String[] options = {"Casual Mode", "Fun Mode"};
        int choice = JOptionPane.showOptionDialog(
            this,
            "Select Game Mode:\n\n" +
            "Casual Mode: Game ends when a player wins\n" +
            "Fun Mode: Continue playing and count wins",
            "Game Mode Selection",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]
        );

        gameMode = (choice == 0) ? GameMode.CASUAL : GameMode.FUN;
        redWins = 0;
        blueWins = 0;
        updateScoreLabel();
        newGame();
    }

    private void makeMove(int col) {
        soundEffect.EAT_FOOD.play();
        for (int row = Board.ROWS - 1; row >= 0; row--) {
            if (board.cells[row][col].content == Seed.NO_SEED) {
                board.cells[row][col].content = currentPlayer;
                updateGameState(row, col);
                break;
            }
        }
    }

    private void updateGameState(int row, int col) {
        State newState = board.stepGame(currentPlayer, row, col);
        
        if (newState == State.CROSS_WON) {
            redWins++;
            updateScoreLabel();
            if (gameMode == GameMode.FUN) {
                currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
            }
        } else if (newState == State.NOUGHT_WON) {
            blueWins++;
            updateScoreLabel();
            if (gameMode == GameMode.FUN) {
                currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
            }
        } else if (newState == State.PLAYING) {
            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
        }

        if (gameMode == GameMode.CASUAL || newState == State.DRAW) {
            currentState = newState;
        } else {
            currentState = State.PLAYING;
        }
    }

    private void updateScoreLabel() {
        scoreLabel.setText(String.format("BLUE: %d | RED: %d", redWins, blueWins));
    }

    public void initGame() {
        board = new Board();
    }

    public void newGame() {
        for (int row = 0; row < Board.ROWS; ++row) {
            for (int col = 0; col < Board.COLS; ++col) {
                board.cells[row][col].content = Seed.NO_SEED;
            }
        }
        currentPlayer = Seed.CROSS;
        currentState = State.PLAYING;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(COLOR_BG);
        board.paint(g);
        updateStatusBar();
    }

    private void updateStatusBar() {
        if (currentState == State.PLAYING) {
            statusBar.setForeground(Color.BLACK);
            statusBar.setText((currentPlayer == Seed.CROSS) ? "BLUE's Turn" : "RED's Turn");
        } else if (currentState == State.DRAW) {
            statusBar.setForeground(Color.RED);
            soundEffect.DIE.play();
            statusBar.setText("It's a Draw! Click New Game to play again.");
        } else if (currentState == State.CROSS_WON) {
            statusBar.setForeground(Color.RED);
            soundEffect.WIN.play();
            statusBar.setText(gameMode == GameMode.CASUAL ? 
                "BLUE Won! Click New Game to play again." : 
                "BLUE Won! Continue playing...");
        } else if (currentState == State.NOUGHT_WON) {
            statusBar.setForeground(Color.RED);
            soundEffect.WIN.play();
            statusBar.setText(gameMode == GameMode.CASUAL ? 
                "RED Won! Click New Game to play again." : 
                "RED Won! Continue playing...");
        }
    }

    public static void play() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame(TITLE);
            frame.setContentPane(new ConnectFour());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}