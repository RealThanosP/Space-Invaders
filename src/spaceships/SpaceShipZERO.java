package spaceships;

import java.awt.Color;
import java.awt.Image;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import mainPacket.MainClass;

public class SpaceShipZERO extends SpaceShip{
	private final int upDownPace = 5, leftRightPace = 5;
	private final String name = "ZERO";
	public static Image img;
	
	static{
			try { 
				SpaceShipZERO.img=ImageIO.read(MainClass.class.getResource("../images/ZERO.png"));
			}
			catch (Exception ex) {
				System.out.println(ex); 
			}
		}
	
	public SpaceShipZERO(int width, int height) {
		super(width, height);
		this.setName(name);
		super.SpaceShipImageIcon = new ImageIcon(img);
		this.setMovingOffsets(leftRightPace, upDownPace);
		this.laserColor = new Color(255, 255, 0);
		this.health = 3;
	}
	

}
