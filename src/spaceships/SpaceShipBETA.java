package spaceships;

import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import mainPacket.MainClass;

public class SpaceShipBETA extends SpaceShip{
	private final int verticalOffsets = 50;
	private final int upDownPace = 20;
	private final int leftRightPace = 20;
	public static Image img;
	private final String name = "BETA";
		
	static{
			try { 
				img=ImageIO.read(MainClass.class.getResource("../images/BETA.png"));
			}
			catch (Exception ex) {
				System.out.println(ex); 
			}
		}
	

	public SpaceShipBETA(int width, int height) {
		super(width, height);
		this.x = 0;
		this.y = MainClass.cosmosHeight - this.shipHeight - verticalOffsets;
		this.setName(name);
		super.SpaceShipImageIcon = new ImageIcon(img);
		this.setMovingOffsets(leftRightPace, upDownPace);
		this.laserColor = new Color(0, 255, 0);
		this.health = 8;
	}

}
