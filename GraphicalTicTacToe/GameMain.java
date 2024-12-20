/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group 4
 * 1 - 5026231092 - Muhammad Fawwaz Al-Amien
 * 2 - 5026231161 - Muhammad Daniel Alfarisi
 * 3 - 5026231210 - Bimo Rajendra Widyadhana
 */

package GraphicalTicTacToe;

import connectfour.HomePage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
/**
 * Tic-Tac-Toe: Two-player Graphic version with better OO design.
 * The Board and Cell classes are separated in their own classes.
 */

public class GameMain extends JPanel {
    private static final long serialVersionUID = 1L; // to prevent serializable warning

    // Define named constants for the drawing graphics
    public static final String TITLE = "Tic Tac Toe";
    public static final Color COLOR_BG = Color.WHITE;
    public static final Color COLOR_BG_STATUS = new Color(216, 216, 216);
    public static final Color COLOR_CROSS = new Color(239, 105, 80);  // Red #EF6950
    public static final Color COLOR_NOUGHT = new Color(64, 154, 225); // Blue #409AE1
    public static final Font FONT_STATUS = new Font("OCR A Extended", Font.PLAIN, 14);

    // Define game objects
    private Board board;         // the game board
    private State currentState;  // the current state of the game
    private Seed currentPlayer;  // the current player
    private JLabel statusBar;    // for displaying status message
    private AIPlayer aiPlayer; // Objek AIPlayer

    /**
     * Constructor to for the UI and game components
     */
    public GameMain() {

        // This JPanel fires MouseEvent
        super.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();
                // Get the row and column clicked
                int row = mouseY / Cell.SIZE;
                int col = mouseX / Cell.SIZE;

                if (currentState == State.PLAYING) {
                    if (row >= 0 && row < Board.ROWS && col >= 0 && col < Board.COLS
                            && board.cells[row][col].content == Seed.NO_SEED) {
                        // Update cells[][] and return the new game state after the move
                        currentState = board.stepGame(currentPlayer, row, col);
                        if (currentPlayer == Seed.CROSS) {
                            SoundEffect.MEOW.play();  // Play MEOW sound
                        } else if (currentPlayer == Seed.NOUGHT) {
                            SoundEffect.GUG.play();  // Play GUG sound
                        }
                        if (currentState == State.CROSS_WON || currentState == State.NOUGHT_WON) {
                            SoundEffect.WIN.play(); // Play WIN sound
                        } else if (currentState == State.DRAW) {
                            SoundEffect.GUG.play(); // Play DRAW sound
                        }

                        // Only switch player if game is still ongoing
                        if (currentState == State.PLAYING) {
                            currentPlayer = (currentPlayer == Seed.CROSS) ? Seed.NOUGHT : Seed.CROSS;
                        }
                    }
                } else {
                    newGame();
                }
                // AI move only if the game is still playing
                if (currentState == State.PLAYING && currentPlayer == Seed.NOUGHT) {
                    aiMove();
                    // Update game state after AI's move
                    if (currentState == State.CROSS_WON || currentState == State.NOUGHT_WON) {
                        SoundEffect.WIN.play();
                    } else if (currentState == State.DRAW) {
                        SoundEffect.GUG.play();
                    }

                    // Switch player if game is still ongoing
                    if (currentState == State.PLAYING) {
                        currentPlayer = Seed.CROSS;
                    }
                }

                // Refresh the drawing canvas
                repaint();
            }
        });

        // Setup the status bar (JLabel) to display status message
        statusBar = new JLabel();
        statusBar.setFont(FONT_STATUS);
        statusBar.setBackground(COLOR_BG_STATUS);
        statusBar.setOpaque(true);
        statusBar.setPreferredSize(new Dimension(300, 30));
        statusBar.setHorizontalAlignment(JLabel.LEFT);
        statusBar.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 12));

        super.setLayout(new BorderLayout());
        super.add(statusBar, BorderLayout.PAGE_END); // same as SOUTH
        super.setPreferredSize(new Dimension(Board.CANVAS_WIDTH, Board.CANVAS_HEIGHT + 30));
        // account for statusBar in height
        super.setBorder(BorderFactory.createLineBorder(COLOR_BG_STATUS, 2, false));

        // Set up Game
        initGame();
        newGame();
    }

    /**
     * Initialize the game (run once)
     */
    public void initGame() {
        board = new Board();  // allocate the game-board
        boolean useMinimax = true; // Ubah menjadi false untuk menggunakan TableLookup
        aiPlayer = useMinimax ? new AIPlayerMinimax(board) : new AIPlayerTableLookup(board);
        aiPlayer.setSeed(Seed.NOUGHT); // AI selalu bermain sebagai 'O'
    }

    /**
     * Reset the game-board contents and the current-state, ready for new game
     */
    public void newGame() {
        for (int row = 0; row < Board.ROWS; ++row) {
            for (int col = 0; col < Board.COLS; ++col) {
                board.cells[row][col].content = Seed.NO_SEED; // all cells empty
            }
        }
        currentPlayer = Seed.CROSS;    // cross plays first
        currentState = State.PLAYING;  // ready to play
    }

    /**
     * Custom painting codes on this JPanel
     */
    @Override
    public void paintComponent(Graphics g) {  // Callback via repaint()
        super.paintComponent(g);
        setBackground(COLOR_BG); // set background color

        board.paint(g);  //

        // Print status-bar message
        if (currentState == State.PLAYING) {
            statusBar.setForeground(Color.BLACK);
            statusBar.setText((currentPlayer == Seed.CROSS) ? "Cat's Turn" : "Dog's Turn");
        } else if (currentState == State.DRAW) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("It's a Draw! Click to play again.");
        } else if (currentState == State.CROSS_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("'Cat' Won! Click to play again.");
        } else if (currentState == State.NOUGHT_WON) {
            statusBar.setForeground(Color.RED);
            statusBar.setText("'Dog' Won! Click to play again.");
        }
    }

    private void aiMove() {
        if (currentState != State.PLAYING) return; //
        int[] move = aiPlayer.move();
        currentState = board.stepGame(aiPlayer.getSeed(), move[0], move[1]);
        repaint();
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // JFrame utama
            JFrame mainFrame = new JFrame(Homepage.TITLE);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setSize(370, 430); //
            mainFrame.setLocationRelativeTo(null); //

            // Tampilkan Homepage
            mainFrame.setContentPane(new Homepage(mainFrame));
            mainFrame.setVisible(true);
        });
    }
}
