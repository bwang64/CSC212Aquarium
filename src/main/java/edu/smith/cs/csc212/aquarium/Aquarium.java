package edu.smith.cs.csc212.aquarium;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.List;

import me.jjfoley.gfx.GFX;

/**
 * Aquarium is a system that fishes swimming inside. Each fish has its own destination, size,
 * location,direction, color, and speed. They will also each have a hungry meter to show when and whether they are hungry.
 * 
 * Bubbles keep floating upwards and each has their own size, speed, and location. When they disappear from the top of the aquarium,
 * they will reappear from the bottom. Each bubble also wiggles left and right differently.
 * 
 * Note there are treasure boxes at the bottom as the sources of bubbles. 
 * 
 * Their foods are the seaweeds at the bottom of the aquarium. When fishes are hungry, they swim there to eat food.
 * When they leave there, they will get more and more hungry.
 * 
 * There is a snail eats algae. When algae is getting more in the ocean, the ocean gets greener and the snail would wake up.
 * It will move around the bound of the aquarium upside-down. When it finished eating, the ocean gets blue again and the snail 
 * stays sleeping.
 * 
 * Note the shark mode is off at the beginning. To make the shark appear, you need to go to the Aquarium.java line 95
 * and turns the mode on.
 * The shark would swim from the right side and pick each fish as destination one by one. Once it's close to the fish, it will eat them.
 * 
 * When all the fishes are eaten, it's the end!!
 * 
 * 
 * 
 * The DrawFish.java, SaveAquariumGIF.java, and Snail.java except for Move() and setDirect(), are cited from course assignment 1 starting code
 * at https://github.com/jjfiv/CSC212Aquarium
 * 
 * I've revised some of the comments and tiny detials, and fully understand the meaning of those classes.
 * 
 * @author jfoley
 *
 */
public class Aquarium extends GFX {
	/**
	 * This is a static variable that tells us how wide the aquarium is.
	 */
	public static int WIDTH = 500;
	/**
	 * This is a static variable that tells us how tall the aquarium is.
	 */
	public static int HEIGHT = 500;

	/**
	 * Put a snail on the top of the tank.
	 */
	Snail algorithm = new Snail(177, Snail.HEIGHT + 1, "top");

	/**
	 * Create an array of fishes.
	 */
	Fish[] fishes = new Fish[12];
	
	/**
	 * Create an array of bubbles.
	 */
	Bubble[] bubbles = new Bubble[20];
	
	/**
	 * Create an array of seaweeds.
	 */
	SeaWeed[] foods = new SeaWeed[15];
	
	/**
	 * Create an earray of treasure boxes.
	 */
	Treasure[] treasureBox = new Treasure[5];
	
	/**
	 * Whether it's the end.
	 */
	boolean theEnd = false;
	
	/**
	 * Set the color prameter of the ocean.
	 */
	int greenO = 0;
	int blueO = 255;
	
	/**
	 * Set the current prey fish index.
	 */
	int fishIndex = 0;
	
	/**
	 * Set the shark mode.
	 */
	boolean sharkMode = true;
	
	/**
	 * Whether the current fish is eaten.
	 */
	boolean fishEaten = false;
	
	/**
	 * Create a shark.
	 */	
	Shark jaws = new Shark();
	
	/**
	 * This is a constructor that runs when we make a new Aquarium.
	 */   
	public Aquarium() {
		// Here we ask GFX to make our window of size WIDTH and HEIGHT.
		
		super(WIDTH, HEIGHT);
	
		// Create the fishes.
	  for (int i = 0; i < this.fishes.length; i++) {
	    int x = 50 + (i*90) % (WIDTH-100);
	    int y = 50 + (i*40) % (HEIGHT-100);
	    this.fishes[i] = new Fish(x,y);
	  }
	  
	  
	  // Create the seaweeds.
	  for (int i = 0; i < this.foods.length; i++) {
		  this.foods[i] = new SeaWeed();
	  } 	  
	  
	  // Create the treasure boxes.
	  for (int i = 0; i < this.treasureBox.length; i++) {
		  this.treasureBox[i] = new Treasure();
	}
		// Create the bubbles.
			  for (int i = 0; i < this.bubbles.length; i++) {
				  this.bubbles[i] = new Bubble(treasureBox[i/4]);
			  }
	}
	
	/**
	 * This method draws all the objects in the Aquarium.
	 */

	@Override
	public void draw(Graphics2D g) {
		// Draw the "ocean" background.
		Color oceanColor = new Color(0,greenO,blueO,255);
		g.setColor(oceanColor);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		//Decide whether to activate the snail depending on the algae status in the ocean.
		
		//the process that the ocean gets greener
		if (!algorithm.wake) {
					
			this.greenO += 1;
				
			if (this.greenO >= 255) {
				this.greenO = 255;
				algorithm.wake = true;
			}
				
			this.blueO -= 1;
			if (this.blueO <= 0) {
				this.blueO = 0;
			}
		}
		//the process that the ocean gets bluer
		else {
			algorithm.setDirect();
			
			this.greenO -= 1;
			
			if (this.greenO <= 0) {
				this.greenO = 0;
				algorithm.wake = false;
			}
		
			this.blueO += 1;
			if (this.blueO >= 255) {
				this.blueO = 255;
			}
		}
		
		// Draw the fish!
		for (int i = 0; i < this.fishes.length; i++) {
			this.fishes[i].draw(g);
		}
		// Draw the bubbles!
		for (int i = 0; i < this.bubbles.length; i++) {
			this.bubbles[i].draw(g);
		}
		// Draw the treasure boxes!
		for (int i = 0; i < this.treasureBox.length; i++) {
			this.treasureBox[i].draw(g);
		}
		
		// Draw the seaweeds!
		for (int i = 0; i < this.foods.length; i++) {
			this.foods[i].draw(g);
		}
		
		// Draw our snail!
		algorithm.draw(g);
		
		//Draw our shark if the shark mode is on.
		if (sharkMode) {
		fishEaten = jaws.draw(g, this.fishes[fishIndex]);
		}
		
		// Update the fish as parameter to the shark class.
		if (fishEaten) {
			this.fishes[fishIndex].isEaten = true;
			
			fishIndex += 1;
			
			jaws.fishEaten = false;
		}
		
		// All fishes sre eaten.
		if (fishIndex > 11) {
			fishIndex = 11;
			theEnd = true;
		}
		
		// If all fishes are eaten, the shark would leave and that's the end!
		if (theEnd) {
			
			jaws.leave();
			jaws.isHunting = false;
			Font f = g.getFont();
			g.setFont(f.deriveFont(100.0f));
			g.setColor(Color.white);
			g.drawString("THE END",30,300);
		}
	}
	

	public static void main(String[] args) {
		
		// store an Aquarium in a variable of type GFX to make the animation effect
		GFX app = new Aquarium();
		app.start();
	}

}
