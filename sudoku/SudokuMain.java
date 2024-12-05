package sudoku;
import java.awt.*;
import javax.swing.*;

/**
 * The main Sudoku program
 */
public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L;  // to prevent serial warning

    // Private variables
    GameBoardPanel board = new GameBoardPanel();
    JButton btnNewGame = new JButton("New Game");

    // Constructor
    public SudokuMain() {
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(board, BorderLayout.CENTER); // Tambahkan board ke JPanel
        cp.add(btnNewGame, BorderLayout.SOUTH); // Tambahkan tombol ke bawah

        // Action listener untuk tombol New Game
        btnNewGame.addActionListener(e -> board.newGame());

        // Buat menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("Option");
        JMenuItem resetGameItem = new JMenuItem("Reset Game");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Tambahkan listener ke menu item
        resetGameItem.addActionListener(e -> board.newGame());
        exitItem.addActionListener(e -> System.exit(0));

        // Tambahkan menu item ke fileMenu
        fileMenu.add(resetGameItem);
        fileMenu.add(exitItem);

        // Tambahkan fileMenu ke menuBar
        menuBar.add(fileMenu);

        // Pasang menuBar ke JFrame
        setJMenuBar(menuBar);

        // Atur window
        pack();  // Atur ukuran berdasarkan komponen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sudoku");
        setVisible(true);

        // Mulai game baru
        board.newGame();
    }

    /** Entry point */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SudokuMain(); // Create an instance of SudokuMain to display the GUI
            }
        });
    }
}
