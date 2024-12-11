package spaceships;

import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import mainPacket.MainClass;

public class SpaceShipGAMA extends SpaceShip{
	private final int verticalOffsets = 50;
	private final int upDownPace = 30, leftRightPace = 30;
	private final String name = "GAMA";
	public static Image img;
	
	static{
			try { 
				img=ImageIO.read(MainClass.class.getResource("../images/GAMA.png"));
			}
			catch (Exception ex) {
				System.out.println(ex); 
			}
		}
	
	public SpaceShipGAMA(int width, int height) {
		super(width, height);
		this.x = 0;
		this.y = MainClass.cosmosHeight - this.shipHeight - verticalOffsets;
		this.setName(name);
		super.SpaceShipImageIcon = new ImageIcon(img);
		this.setMovingOffsets(leftRightPace, upDownPace);
		this.laserColor = new Color(255, 215, 0);
		this.health = 12;
	}
	
}
