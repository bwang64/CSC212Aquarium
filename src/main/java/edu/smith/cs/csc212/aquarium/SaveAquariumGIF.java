package edu.smith.cs.csc212.aquarium;

import java.io.File;

public class SaveAquariumGIF {

	/**
	 * have main methods in different
	 * places and save animation
	 * 
	 * cited from course assignment 1 starting code
	 * at https://github.com/jjfiv/CSC212Aquarium
	 * 
	 * @author jfoley
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Aquarium app = new Aquarium();
		int numSeconds = 3;
		app.playToGIF(new File("aquarium.gif"), 50 * numSeconds);
	}
	

}
