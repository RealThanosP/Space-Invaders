package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import mainPacket.MainClass;

public class SpaceFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	static SelectSpaceShipScreen selectSpaceShipScreen = new SelectSpaceShipScreen();
	static GamePlayScreen gameScreen = new GamePlayScreen();
	static CardLayout cardLayout = new CardLayout();
	static EndScreen endScreen = new EndScreen();
	static JPanel mainPanel = new JPanel(cardLayout);
	
	public SpaceFrame() {
		setTitle(MainClass.gameTitle);
		setSize(MainClass.cosmosWidth, MainClass.cosmosHeight);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		
		mainPanel.add(selectSpaceShipScreen);
		mainPanel.add(gameScreen);
		mainPanel.add(endScreen);
		
		cardLayout.show(mainPanel, SelectSpaceShipScreen.class.getSimpleName());
		
		add(mainPanel, BorderLayout.CENTER);
		
		setVisible(true);
	}
	
	
	
}
