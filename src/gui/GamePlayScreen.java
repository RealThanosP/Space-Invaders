package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;
import mainPacket.MainClass;
import spaceships.SpaceShip;
import spaceships.SpaceShipENEMY;
import spaceships_laseguns.Laser;

public class GamePlayScreen extends JPanel implements KeyListener {
    private static final long serialVersionUID = 1L;
    private SpaceShip userSpaceShip;
    private SpaceShipENEMY enemySpaceShip;
    private boolean gamePaused = false, gameWon;
    private JPanel menuPanel;
    static JPanel controlsPanel = new JPanel();
    private GameAudioPlayer audioPlayer;
    Timer timer;
    
    public GamePlayScreen() {
    	// Game loop timer
        timer = new Timer(100, e -> paintComponent(getGraphics())); // the action listener pluged in is anonymous. awt.Timer class used and seemed to work
        
    	setBackground(Color.BLACK);
        setLayout(new GridBagLayout()); // Use GridBagLayout to center components in GamePlayScreen
        
        // Create the menu panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridBagLayout()); // Center contents inside the menu panel
        menuPanel.setBackground(Color.BLACK);
        
        // Configure constraints for centering the menuPanel in GamePlayScreen
        GridBagConstraints panelConstraints = new GridBagConstraints();
        panelConstraints.gridx = 0;
        panelConstraints.gridy = 0;
        panelConstraints.anchor = GridBagConstraints.CENTER;
        panelConstraints.fill = GridBagConstraints.NONE;
        
        
        // Configure constraints for the backButton
        GridBagConstraints backButtonConstraints = new GridBagConstraints();
        backButtonConstraints.gridx = 0; // Column 0
        backButtonConstraints.gridy = 1; // Row 0
        backButtonConstraints.anchor = GridBagConstraints.CENTER; // Center alignment
        backButtonConstraints.insets = new Insets(10, 0, 10, 0); // Add some spacing around the button

        // BACK button
        JButton backButton = new JButton("Back To Main Menu");
        backButton.addActionListener(new GameActionListener());
        backButton.setActionCommand("BACK");
        menuPanel.add(backButton, backButtonConstraints); // Add the back button to the menuPanel        
        
        // Configure constraints for the controlsButton
        GridBagConstraints controlsButtonConstraints = new GridBagConstraints();
        controlsButtonConstraints.gridx = 0; // Same column as backButton
        controlsButtonConstraints.gridy = 2; // Row 1, directly below backButton
        controlsButtonConstraints.anchor = GridBagConstraints.CENTER; // Center alignment
        controlsButtonConstraints.insets = new Insets(10, 0, 10, 0); // Add spacing around the button
        
        // CONTROLS button
        JButton controlsButton = new JButton("Controls");
        controlsButton.addActionListener(new GameActionListener());
        controlsButton.setActionCommand("CONTROLS");
        menuPanel.add(controlsButton, controlsButtonConstraints); // Add the controls button to the menuPanel
        
        
        // Configure the constraints for the resumeButton
        GridBagConstraints resumeButtonConstraints = new GridBagConstraints();
        controlsButtonConstraints.gridx = 0; // Same column as backButton
        controlsButtonConstraints.gridy = 0; // Row 1, directly below backButton
        controlsButtonConstraints.anchor = GridBagConstraints.CENTER; // Center alignment
        controlsButtonConstraints.insets = new Insets(10, 0, 10, 0); // Add spacing around the button
        
        // Resume Game button
        JButton resumeButton = new JButton("Resume");
        resumeButton.addActionListener(new GameActionListener());
        resumeButton.setActionCommand("RESUME");
        menuPanel.add(resumeButton, resumeButtonConstraints);
        
        
        add(menuPanel, panelConstraints); // Add menuPanel to the center of GamePlayScreen
        menuPanel.setVisible(false); // The menu shows only when the user hits esc
        
        // Initialize and start background music
        audioPlayer = new GameAudioPlayer();
        audioPlayer.load(MainClass.class.getResource("../audio/space-ambience.wav"));
        
        // Set focusable and add KeyListener
        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(this);
    }
    
    void resetGamePause() {
    	gamePaused = false;
    }
    
    void showControlsWindow() {
        // Create a child window (JDialog)
        JDialog controlsDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), "Game Controls", true);
        controlsDialog.setSize(300, 200);
        controlsDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        controlsDialog.setLocationRelativeTo(this);

        // Add controls information
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.DARK_GRAY);

        JLabel controlsLabel = new JLabel("<html><center>Game Controls:<br>"
                + "Move: WASD<br>"
                + "Shoot: Space Bar<br>"
                + "Pause: Esc<br></html>");
        controlsLabel.setForeground(Color.WHITE);
        controlsLabel.setHorizontalAlignment(SwingConstants.CENTER);

        contentPanel.add(controlsLabel, BorderLayout.CENTER);

        // Close button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> controlsDialog.dispose());
        contentPanel.add(closeButton, BorderLayout.SOUTH);

        controlsDialog.add(contentPanel);
        controlsDialog.setVisible(true);
    }
    
    void showMenuPanel() {
    	menuPanel.setVisible(true);
    }
    
    void setUserSpaceShip(SpaceShip userChoice) {
        userSpaceShip = userChoice;
        enemySpaceShip = new SpaceShipENEMY(MainClass.spaceShipWidth, MainClass.spaceShipHeight);
    }

    @Override
    protected synchronized void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	if (!gamePaused) {
    		enemySpaceShip.followPlayer(userSpaceShip);
    		enemySpaceShip.randomFire();
    		audioPlayer.play(!gamePaused);
    	}else audioPlayer.stop();
        // Ensure userSpaceShip is not null before drawing
        if (userSpaceShip != null && userSpaceShip.getIcon() != null) {
            userSpaceShip.getIcon().paintIcon(this, g, userSpaceShip.getX(), userSpaceShip.getY());
            enemySpaceShip.getIcon().paintIcon(this, g, enemySpaceShip.getX(), enemySpaceShip.getY());
        }
        
        checkForCollisions(userSpaceShip, enemySpaceShip);
        
        
        showLaserShots(g);
        repaint();
    }
    
    private void checkForCollisions(SpaceShip userSpaceShip, SpaceShip enemSpaceShip){
    	// User lasers -> Enemy collisions
        Iterator<Laser> userLaserIterator = userSpaceShip.gun.laserGunList.iterator();
        while (userLaserIterator.hasNext()) {
            Laser laser = userLaserIterator.next();
            if (isCollision(enemySpaceShip, laser)) {
                userLaserIterator.remove();
                enemySpaceShip.health--;
                if (enemySpaceShip.health <= 0) {
                    System.out.println("ENEMY DESTROYED");
                    // Update the state of the game
                    gameWon = true;
                    endGame();
                    break;
                }
            }
        }

        // Enemy lasers -> User collisions
        Iterator<Laser> enemyLaserIterator = enemySpaceShip.gun.laserGunList.iterator();
        while (enemyLaserIterator.hasNext()) {
            Laser laser = enemyLaserIterator.next();
            if (isCollision(userSpaceShip, laser)) {
                enemyLaserIterator.remove();
                userSpaceShip.health--;
                System.out.println("Player hit! Health: " + userSpaceShip.health);
                if (userSpaceShip.health <= 0) {
                    System.out.println("GAME OVER");
                    gameWon = false;
                    endGame();
                    break;
                }
            }
        }
    }
    
    private void endGame() {
        gamePaused = true;
        timer.stop();
        audioPlayer.stop();

        // Set game result and show end screen
        SpaceFrame.cardLayout.next(SpaceFrame.mainPanel);
    }
    
    private boolean isCollision(SpaceShip ship, Laser laser) {
        return ship.getBounds().contains(new Point(laser.x, laser.y));
    }
    
    
    private void showLaserShots(Graphics g) {
    	
    	if (userSpaceShip.gun.laserGunList == null || enemySpaceShip.gun.laserGunList == null) return;
    	
    	// For each laser in the list set its color and show it in the screen
    	userSpaceShip.gun.laserGunList.forEach((laser) -> {
    		g.setColor(userSpaceShip.laserColor);
    		g.drawLine(laser.x, laser.y, laser.x, laser.y - 10);
    		laser.y = laser.y - 10; // Update the position of the laser that is moving up
    	});
    	
    	// Same for the enemy lasergun
    	enemySpaceShip.gun.laserGunList.forEach( (laser) -> {
    		g.setColor(enemySpaceShip.laserColor);
    		g.drawLine(laser.x, laser.y, laser.x, laser.y + 10);
    		laser.y = laser.y + 10; // Update the position of the laser that is now moving down
    	});
    	
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Move spaceship based on arrow keys
        if ((e.getKeyCode() == KeyEvent.VK_W) && (!gamePaused)) userSpaceShip.moveUP(); 
        else if ((e.getKeyCode() == KeyEvent.VK_A) && (!gamePaused)) userSpaceShip.moveLEFT();
        else if ((e.getKeyCode() == KeyEvent.VK_S) && (!gamePaused)) userSpaceShip.moveDOWN();
        else if ((e.getKeyCode() == KeyEvent.VK_D) && (!gamePaused)) userSpaceShip.moveRIGHT();
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE && (!gamePaused)) {
        	gamePaused = !gamePaused; // Invert the pausing
        	showMenuPanel();
        }
        else if ((e.getKeyCode() == KeyEvent.VK_SPACE) && (!gamePaused)) userSpaceShip.fire();
        repaint(); // Repaint the panel to reflect the new position
    }
    
    public boolean getGameResult() {
    	return gameWon;
    }
    
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
    
    public void hideMenuPanel() {
    	menuPanel.setVisible(false);
    }
}

class GameActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if ("BACK".equals(command)) {
            SpaceFrame.gameScreen.setUserSpaceShip(null);
            SpaceFrame.cardLayout.previous(SpaceFrame.mainPanel);
        }
        if ("CONTROLS".equals(command)) {
        	SpaceFrame.gameScreen.showControlsWindow();
        }
        if ("RESUME".equals(command)) {
        	SpaceFrame.gameScreen.resetGamePause();
        	SpaceFrame.gameScreen.hideMenuPanel();
        }
    }
}
