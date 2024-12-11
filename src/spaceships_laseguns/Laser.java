package spaceships_laseguns;

import mainPacket.MainClass;

public class Laser {
	public int x; // Location of the laser in the plain
	public int y;
	
	public Laser(int x, int y) {
		this.x = MainClass.spaceShipWidth / 2 + x; // To make it so the laser shoots out from the center of the ship
		this.y = y;
	}
	
}
