package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndScreen extends JPanel {
    private static final long serialVersionUID = 1L;

    public EndScreen() {
        // Make the entire screen black by setting background color
        setBackground(Color.BLACK);
        setLayout(new BorderLayout()); // Use BorderLayout for the layout

        // Create the label to display the game result
        JLabel endLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
        endLabel.setForeground(Color.RED); // Set text color to red
        endLabel.setFont(new Font("Arial", Font.BOLD, 48)); // Large font size for visibility

        // Create the menu panel for the back button
        JPanel menuPanel = createMenuPanel();
        menuPanel.setOpaque(false); // Make the menu panel transparent so the black background shows

        // Add components to the main EndScreen
        add(endLabel, BorderLayout.NORTH); // Add the game over label at the top
        add(menuPanel, BorderLayout.CENTER); // Add the menu in the center
    }

    private JPanel createMenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(Color.BLACK); // Set the background to black
        menuPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center-align the button

        // Create the back button
        JButton backButton = new JButton("Back To Main Menu");
        backButton.addActionListener(new EndActionListener());
        backButton.setActionCommand("BACKFROMEND");

        // Style the back button
        backButton.setFocusPainted(false);
        backButton.setBackground(Color.DARK_GRAY);
        backButton.setForeground(Color.WHITE);

        menuPanel.add(backButton); // Add the button to the menu panel
        return menuPanel;
    }
}

class EndActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("BACKFROMEND".equals(command)) {
        	SpaceFrame.gameScreen.resetGamePause();
            SpaceFrame.cardLayout.next(SpaceFrame.mainPanel);
        }
    }
}
