package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import mainPacket.MainClass;
import spaceships.*;

public class SelectSpaceShipScreen extends JPanel{
	private static final long serialVersionUID = 1L;
	private Image[] spaceshipImages = {
            SpaceShipZERO.img,
            SpaceShipALPHA.img,
            SpaceShipBETA.img,
            SpaceShipGAMA.img,
            SpaceShipDELTA.img
    };
	private SpaceShip[] spaceships = {
            new SpaceShipZERO(MainClass.spaceShipWidth, MainClass.spaceShipHeight),
            new SpaceShipALPHA(MainClass.spaceShipWidth, MainClass.spaceShipHeight),
            new SpaceShipBETA(MainClass.spaceShipWidth, MainClass.spaceShipHeight),
            new SpaceShipGAMA(MainClass.spaceShipWidth, MainClass.spaceShipHeight),
            new SpaceShipDELTA(MainClass.spaceShipWidth, MainClass.spaceShipHeight)
    };
	
	public GameAudioPlayer audioPlayer;
	
	public SelectSpaceShipScreen() {
		setLayout(new BorderLayout(100,100));
		setBackground(Color.BLACK);
		
		audioPlayer = new GameAudioPlayer();
        audioPlayer.load(MainClass.class.getResource("../audio/spaceShipSelection.wav"));
		
		add(createNorthPanel(), BorderLayout.NORTH);
		add(createCenterPanel(), BorderLayout.CENTER);
	}
	
	private JPanel createNorthPanel() {
		JPanel northPanel = new JPanel();
		
		northPanel.setBackground(Color.BLACK);
		
		JLabel topLabel = new JLabel("Please Select you Spaceship");
		topLabel.setFont(new Font("Arcade Classic", Font.PLAIN, 25));
		topLabel.setForeground(Color.WHITE);
		
		northPanel.add(topLabel);
		
		return northPanel;
	}
	
	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.setBackground(Color.BLACK);
		
		// Configute the center Panel constraints
		GridBagConstraints centerPanelConstraints = new GridBagConstraints();
		centerPanelConstraints.gridx = 0;
		centerPanelConstraints.gridy = 0;		
		centerPanelConstraints.anchor = GridBagConstraints.NORTH;
		
		for (int i = 0;  i < spaceships.length; i++) {
			
			// Selection Button
			JButton selectButton = new JButton();
			selectButton.setOpaque(false);
			selectButton.setBorderPainted(false);
			selectButton.setContentAreaFilled(false);
			selectButton.setIcon(new ImageIcon(spaceshipImages[i]));
			selectButton.addActionListener(new SelectionActionHandler(spaceships[i].getName()));
			selectButton.setActionCommand(spaceships[i].getName()); 
			
			// Selection Button Constraints
			GridBagConstraints selectButtonConstraints = new GridBagConstraints();
			selectButtonConstraints.gridx = i; // Lines up all the buttons next to each other
			selectButtonConstraints.gridy = 0; // Keeps the buttons on the same vertical level
			selectButtonConstraints.insets = new Insets(0,10,0,10); // Adds horizontal margins
			
			// Selection label
			JLabel selectLabel = new JLabel(spaceships[i].getName()); // Names of the spaceships
			selectLabel.setForeground(Color.white);
			// Selection label Constraints
			// Same constraints as the buttons just one level down
			GridBagConstraints selectLabelConstraints = new GridBagConstraints();
			selectLabelConstraints.gridx = i;
			selectLabelConstraints.gridy = 1;
			selectLabelConstraints.insets = new Insets(20, 10, 0, 10);
			
			centerPanel.add(selectLabel, selectLabelConstraints);
			centerPanel.add(selectButton,selectButtonConstraints);
			
		}
		
		return centerPanel;
	}
	
}

class SelectionActionHandler implements ActionListener{
	String choice;
	private static final int shipWidth = MainClass.spaceShipWidth;
	private static final int shipHeight = MainClass.spaceShipHeight;

	public SelectionActionHandler(String buttonName) {
		choice = buttonName;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		SpaceFrame.selectSpaceShipScreen.audioPlayer.play(false);
		
		if (choice.equals("ZERO")) SpaceFrame.gameScreen.setUserSpaceShip(new SpaceShipZERO(shipWidth, shipHeight));
		if (choice.equals("ALPHA")) SpaceFrame.gameScreen.setUserSpaceShip(new SpaceShipALPHA(shipWidth, shipHeight));
		if (choice.equals("BETA")) SpaceFrame.gameScreen.setUserSpaceShip(new SpaceShipBETA(shipWidth, shipHeight));
		if (choice.equals("GAMA")) SpaceFrame.gameScreen.setUserSpaceShip(new SpaceShipGAMA(shipWidth, shipHeight));
		if (choice.equals("DELTA")) SpaceFrame.gameScreen.setUserSpaceShip(new SpaceShipDELTA(shipWidth, shipHeight));
		
		SpaceFrame.cardLayout.next(SpaceFrame.mainPanel);
		SpaceFrame.gameScreen.requestFocus();
		SpaceFrame.gameScreen.setFocusable(true);
	}
	
}


