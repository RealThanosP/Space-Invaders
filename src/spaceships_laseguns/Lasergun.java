package spaceships_laseguns;

import java.awt.Color;
import java.util.LinkedList;

public class Lasergun {
	public Color laserColor;
	public LinkedList<Laser> laserGunList = new LinkedList<Laser>();
	
	public Lasergun(Color color) {
		this.laserColor = color;
	}
	
	public void fire(int x, int y) {
		laserGunList.add(laserGunList.size(), new Laser(x, y)); // add the next laser at the end of the list
	}
}
