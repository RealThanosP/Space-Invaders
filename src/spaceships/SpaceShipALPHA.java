package spaceships;

import java.awt.Color;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import mainPacket.MainClass;

public class SpaceShipALPHA extends SpaceShip{
	private final int upDownPace = 10, leftRightPace = 10;
	private final int verticalOffsets = 50;
	public static Image img;
	private final String name = "ALPHA";
	
	static{
		try { 
			img=ImageIO.read(MainClass.class.getResource("../images/ALPHA.png"));
		}
		catch (Exception ex) {
			System.out.println(ex); 
		}
	}
	
	
	public SpaceShipALPHA(int width, int height) {
		super(width, height);
		this.x = 0;
		this.y = MainClass.cosmosHeight - this.shipHeight - verticalOffsets;
		this.setName(name);
		super.SpaceShipImageIcon = new ImageIcon(img);
		this.setMovingOffsets(leftRightPace, upDownPace);
		this.laserColor = Color.WHITE;
		this.health = 5; // 5 shots
	}
	
}
