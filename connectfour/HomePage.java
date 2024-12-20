package connectfour;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
    private JFrame frame;

    public HomePage() {
        // Create the main frame
        frame = new JFrame("Connect-4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 150);
        frame.setLocationRelativeTo(null);

        // Create a panel for the home page
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.GREEN);

        // Add title label
        JLabel titleLabel = new JLabel("Connect-4", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 34));
        titleLabel.setForeground(Color.BLACK);
        panel.add(titleLabel, BorderLayout.NORTH);

        // Create a panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.green);

        // Add "Play" button
        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Arial", Font.PLAIN, 18));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the home page
                startGame(); // Launch the game
            }
        });
        buttonPanel.add(playButton);

        // Add "Exit" button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 18));
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);

        panel.add(buttonPanel, BorderLayout.CENTER);

        // Add the panel to the frame
        frame.add(panel);
    }

    public void show() {
        frame.setVisible(true);
    }

    private void startGame() {
        ConnectFour game = new ConnectFour();
        game.play();
    }
}
