package mainPacket;


import gui.*;


public class MainClass {
	public static final int cosmosWidth = 800;
	public static final int cosmosHeight = 600;
	public static final int spaceShipWidth = 100;
	public static final int spaceShipHeight = 100;
	public static final String gameTitle = "Game Destroyer";
	
	public static void main(String[] args) {
		new SpaceFrame();
		
		
//		LinkedList<SpaceShip> spList = new LinkedList<SpaceShip>();
//		
//		SpaceShip zero = new SpaceShipZERO(spaceShipWidth, spaceShipHeight);
//		SpaceShip alpha = new SpaceShipALPHA(spaceShipWidth, spaceShipHeight);
//		SpaceShip beta = new SpaceShipBETA(spaceShipWidth, spaceShipHeight);
//		SpaceShip gamma = new SpaceShipGAMA(spaceShipWidth, spaceShipHeight);
//		SpaceShip delta = new SpaceShipDELTA(spaceShipWidth, spaceShipHeight);
//		SpaceShip enemy = new SpaceShipENEMY(spaceShipWidth, spaceShipHeight);
//		
//		spList.add(zero);
//		spList.add(alpha);
//		spList.add(beta);
//		spList.add(gamma);
//		spList.add(delta);
//		spList.add(enemy);
//		
//		
//		// With this test 
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Type to test. Valid inputs 'u', 'd', 'l', 'r' 'q'");
//		while (scanner.hasNext()) {
//			String str = scanner.next();
//			
//			if (str.equals("q")) break; // Exit signal
//			
//			for (int i = 0; i < spList.size(); i++) {
//				if (str.equals("u")) spList.get(i).moveUP();
//				if (str.equals("d")) spList.get(i).moveDOWN();
//				if (str.equals("l")) spList.get(i).moveLEFT();
//				if (str.equals("r")) spList.get(i).moveRIGHT();
//				spList.get(i).printCoords();
//			}
//			
//		}
//		
//		scanner.close();
	}

}
