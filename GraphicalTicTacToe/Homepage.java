package GraphicalTicTacToe;

import javax.swing.*;
import java.awt.*;

public class Homepage extends JPanel {

    private static final long serialVersionUID = 1L;
    public static final String TITLE = "Homepage - Tic Tac Toe Game";

    private JFrame mainFrame; // Referensi ke JFrame utama

    // Constructor dengan referensi JFrame
    public Homepage(JFrame frame) {
        this.mainFrame = frame; // Simpan referensi ke JFrame utama

        // Set layout for the homepage
        setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("Welcome to Tic Tac Toe Game!", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(64, 154, 225));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        // Center image or text area
        JLabel centerLabel = new JLabel();
        centerLabel.setHorizontalAlignment(JLabel.CENTER);
        centerLabel.setVerticalAlignment(JLabel.CENTER);
        centerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Replace "path/to/your/image.jpg" with your actual image path
        centerLabel.setIcon(new ImageIcon("GraphicalTicTacToe/image/tttxo.jpg"));

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton playButton = new JButton("Play Game");
        JButton exitButton = new JButton("Exit");

        playButton.setFont(new Font("Arial", Font.BOLD, 16));
        exitButton.setFont(new Font("Arial", Font.BOLD, 16));

        buttonPanel.add(playButton);
        buttonPanel.add(exitButton);

        // Add action listeners for buttons
        playButton.addActionListener(e -> {
            // Ganti konten JFrame dengan GameMain
            mainFrame.setContentPane(new GameMain());
            mainFrame.revalidate(); // Segarkan frame
            mainFrame.repaint(); // Lukis ulang frame
        });

        exitButton.addActionListener(e -> System.exit(0));

        // Add components to the main panel
        add(titleLabel, BorderLayout.NORTH);
        add(centerLabel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame homepageFrame = new JFrame(TITLE);
            homepageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            homepageFrame.setSize(370, 430);
            homepageFrame.setLocationRelativeTo(null);

            // Tampilkan homepage di JFrame utama
            homepageFrame.setContentPane(new Homepage(homepageFrame));
            homepageFrame.setVisible(true);
        });
    }
}
