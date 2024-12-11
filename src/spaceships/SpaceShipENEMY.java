package spaceships;

import java.awt.Color;
import java.awt.Image;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import mainPacket.MainClass;

public class SpaceShipENEMY extends SpaceShip{
	private final int upDownOffset = 15, leftRightOffset = 15;
	private final String name = "ENEMY";
	public static Image img;
	
	static{
			try { 
				img=ImageIO.read(MainClass.class.getResource("../images/ENEMY.png"));
			}
			catch (Exception ex) {
				System.out.println(ex); 
			}
		}
	
	public SpaceShipENEMY(int width , int height) {
		super(width, height);
		this.x = Math.round(MainClass.cosmosWidth / 2);
		this.y = 0;
		this.setName(name);
		super.SpaceShipImageIcon = new ImageIcon(img);
		this.setMovingOffsets(leftRightOffset, upDownOffset);
		this.laserColor = new Color(255, 0, 0);
		this.health = 5;
	}
	
	@Override
	// Gets the correctly resized icon of the enemy ship
    public ImageIcon getIcon() {
        String path = "/images/ENEMY.png"; 
        
        
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null){
            ImageIcon EnemyImage = new ImageIcon(imgURL);
            int imageW=100;
            int imageH=100;
            
            Image scaledEnemyImage= EnemyImage.getImage().getScaledInstance(imageW, imageH, Image.SCALE_SMOOTH);            
            return new ImageIcon(scaledEnemyImage);
        } else{
            
            System.err.println("Image not found: " + path);
            return null;
        }
    }
	
	public void followPlayer(SpaceShip userShip) {
		// Basic idea of this algo:
		// 1) Follow in the x-axis
		// 2) Always be in the neibourhood of the user within a random variance depending on difficulty
		
		
	    // Set the enemy speed to be slightly slower than the userShip
		Random random = new Random();
	    int userSpeed = userShip.getSpeed();
	    this.setMovingOffsets(userSpeed / 2, userSpeed / 2);
	    int weight = 1000; // weight to determin the difficulty of the game. The lower the harderd
	    // Calculate direction vector
	    int dx = userShip.getX() - x;
	    
	    // Add a larger random factor to movement
	    int randomX = random.nextInt(weight);

	    // Mix randomness with the direction vector
	    dx += randomX;

	    // Occasionally skip movement (e.g., 10% chance)
	    if (Math.random() < 0.1) {
	        return; // Skip this frame's movement
	    }

	    // Move the enemy based on the final direction vector
	    if (dx < weight/2) {
	        this.moveLEFT();
	    } else if (dx > weight/2) {
	        this.moveRIGHT();
	    }
	}

	public void randomFire() {
		Random random = new Random();
		
		int weight = 100;
		
		int randomTime = random.nextInt(weight); 
				
		if (randomTime < weight / 100) this.fire(); // Wanting ~1% firing chance
		
		// If we consider 1% firing in 100ms gameloop clock, then we have about 0 lasers/s fireRate
	}
}
