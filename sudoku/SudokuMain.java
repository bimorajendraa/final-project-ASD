package sudoku;

import java.awt.*;
import javax.swing.*;

public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L;

    private CardLayout cardLayout;
    private JPanel mainPanel;

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
        });

        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(resetGameItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);

        setJMenuBar(menuBar);
    }

    private void switchToGame() {
        cardLayout.show(mainPanel, "Game");
    }

    private void switchToHome() {
        cardLayout.show(mainPanel, "Home");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SudokuMain::new);
    }
}
