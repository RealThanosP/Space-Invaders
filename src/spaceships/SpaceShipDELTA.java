package spaceships;

import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import mainPacket.MainClass;

public class SpaceShipDELTA extends SpaceShip{
	private final int verticalOffsets = 50;
	final int upDownPace = 40 , leftRightPace = 40;
	private final String name = "DELTA";
	public static Image img;
	
	static{
			try { 
					img=ImageIO.read(MainClass.class.getResource("../images/DELTA.png"));
			}
			catch (Exception ex) {
				System.out.println(ex); 
			}
		}
	
	public SpaceShipDELTA(int width, int height) {
		super(width, height);
		this.x = 0;
		this.y = MainClass.cosmosHeight - this.shipHeight - verticalOffsets;
		this.setName(name);
		super.SpaceShipImageIcon = new ImageIcon(img);
		this.setMovingOffsets(leftRightPace, upDownPace);
		this.laserColor = new Color(0, 191, 255);
		this.health = 10;
	}

}
