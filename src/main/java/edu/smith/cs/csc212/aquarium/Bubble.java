package edu.smith.cs.csc212.aquarium;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * This a class of bubbles, with methods including draw(), animate(), wiggle(), and wrap(), 
 * and instance variables including x, y, size, wiggle, speed, and counter.
 */

public class Bubble {
	/**
	 * The position of the bubble.
	 */
	int x;
	int y;
	/**
	 * The size of the bubble.
	 */
	int size;
	/**
	 * The distance of wiggle of the bubble.
	 */
	double wiggle;
	double speed;
	/**
	 * Create a random number generator.
	 */
	Random rand = new Random();
	/**
	 * create a conter for wiggle.
	 */
	int counter;
	
	// This is a bubble generator.
	public Bubble(Treasure treasureBox) {
		this.x = rand.nextInt(40)+treasureBox.x; // make bubbles come out from the box.
		this.y = rand.nextInt(20)+480;
		this.speed = rand.nextDouble()+rand.nextInt(4)+1;
		this.size = rand.nextInt(5)+10;
		this.counter = 0;
		
	}
	
	// This is a method that draws the bubbles.
	public void draw(Graphics2D g) {
		Font f = g.getFont();
		g.setFont(f.deriveFont(this.size*0.99f));
		g.setColor(Color.white);
		g.drawString("O",this.x,this.y);
		
		this.animate();
	}
	
	// This is a method that decides the frequency and vertical movements of the bubble.
	public void animate() {
		this.y -= this.speed;
		this.counter+=1;
		this.wrap();
		this.wiggle();
	}
	
	// This is a method that allows the bubbles to reappear from the bottom.
	public void wrap() {
		if (this.y < -20) {
			this.y= 520;
			}
	}
	
	// This is a method that allows each bubble to wiggle horizontally by different frequency and distance.
	public void wiggle() {
		if (this.counter % (2*(Math.round(this.speed))) == 0) {
			this.x -= 2*Math.round(this.speed);
			
			
		}
		else if ((this.counter % (Math.round(this.speed)) == 0) && this.counter % (2*(Math.round(this.speed))) != 0) {
			
			this.x += 2*Math.round(this.speed);
		}
		
	}
	

		
		
	

}
