package spaceships;

import java.awt.Color;

import javax.swing.ImageIcon;
import java.awt.Rectangle;
import mainPacket.MainClass;
import spaceships_laseguns.Laser;
import spaceships_laseguns.Lasergun;

public abstract class SpaceShip implements Navigation{
	public int x, y;
	public Lasergun gun; // Each spaceship has a gun
	public Color laserColor;
	public int health; // The number of hits it can take
	final int shipWidth, shipHeight;
	int upDownPace, leftRightPace; // These are initialized in the children classes
	private int verticalOffset = 50;
	private String name;
	protected ImageIcon SpaceShipImageIcon;
	
	public SpaceShip(int width, int height) {
		shipHeight = height;
		shipWidth = width;
		this.x = 0;
		this.y = MainClass.cosmosHeight - this.shipHeight - verticalOffset;
		this.gun = new Lasergun(laserColor);
	}
	
	// They all return the new coords of the spaceship
	// Movement in all of the spaceships takes in account the window width and height from accessing the static var's in the MainClass
	// If the movement is not valid (x < 0, x > cosmosWidth) or (y < 0 , y > cosmoHeight) then it just moves the top left corner of spaceship to the edge 
	// of the window, accounting for the width of the spaceship that is past in
	// EX: If width = 10 height = 10 and the spaceship is supposed to move to (-5,100) then it moves to (0,100), To left edge of the screen.
	
	public int moveUP() {
		if (this.y - upDownPace < 0) return 0;
		this.y -= upDownPace;
		
		return this.y;
	}
	
	public int moveDOWN() {
		if (this.y + upDownPace + this.shipHeight > MainClass.cosmosHeight) {
			this.y = MainClass.cosmosHeight - this.shipHeight;
			return this.y;
		}
		
		this.y += upDownPace;
		return this.y;
	}
	
	public int moveRIGHT() {
		if (this.x + upDownPace + this.shipWidth > MainClass.cosmosWidth) {
			this.x = MainClass.cosmosWidth - this.shipWidth;
			return this.x;
		}
		this.x += leftRightPace;
		return this.x;
	}
	
	public int moveLEFT() {
		if (this.x - leftRightPace < 0) return 0;
		this.x -= leftRightPace;
		return this.x;
	}
	
	public int getSpeed() {
		return leftRightPace;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void printCoords() {
		System.out.println(this.name + " X: " + this.x + " Y: " + this.y);
	}

	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ImageIcon getIcon() {
		return SpaceShipImageIcon;
	}
	
	public void setMovingOffsets(int xOffset, int yOffset) {
			this.leftRightPace = xOffset;
			this.upDownPace = yOffset;
	}
	
	public void fire() {
		gun.laserGunList.add(new Laser(this.getX(), this.getY()));
	}

	public Rectangle getBounds() { // Gets the bounds of the spaceship
	    return new Rectangle(this.x, this.y, this.shipWidth, this.shipHeight);
	}
	
	public void takeDamage() {
		this.health--;
	}

}
