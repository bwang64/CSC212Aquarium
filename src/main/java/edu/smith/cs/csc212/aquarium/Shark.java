package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * This a class of the shark, with methods including hunt() and draw(), 
 * and instance variables including x, y, size, color, speed, destX, and destY..
 */
public class Shark {
	
	/**
	 * The position of the shark.
	 */
	int x;
	int y;
	/**
	 * The color of the shark.
	 */
	Color color;
	/**
	 * The speed of the shark.
	 */
	int speed;
	/**
	 * Create a random number generator.
	 */
	Random rand = new Random();
	/**
	 * The fish arraylist from the aquarium.
	 */
	Fish[] fishes;
	/**
	 * The destination of the shark.
	 */
	int destX;
	int destY;
	
	
	public Shark(){
	this.x = rand.nextInt (80)+500;
	this.y = rand.nextInt (500);
	this.speed = 1;
}
	// This is a method that draws the shark.
	public void draw(Graphics2D g, Fish[] fishes) {
		DrawFish.hugeFacingLeft(g, Color.cyan, this.x, this.y);
		this.hunt(fishes);
	}
	
	// This is a method that allows the shark to hunt according to each fish's location.
	public void hunt(Fish[] fishes) {
		for (int i = 0; i < 12; i++) {
			this.destX=fishes[i].x;
			this.destY=fishes[i].y;
			
			if (Math.abs(this.destX - this.x) <= this.speed || Math.abs(this.destY - this.y) <= this.speed) {
				this.destX = rand.nextInt(500);
				this.destY = rand.nextInt(500);
			}
			
			if (this.destX < this.x ) {
				//this.isLeft = true;	
				this.x -= this.speed;
			}
			else if (this.destX > this.x ) {
				//this.isLeft = false;	
				this.x += this.speed;
			}
			
			if (this.destY < this.y ) {
				this.y -= this.speed;
			}
			else if (this.destY > this.y ) {	
				this.y += this.speed;
			}
		}	
	}
}

