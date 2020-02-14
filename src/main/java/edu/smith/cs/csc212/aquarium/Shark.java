package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

/**
 * This a class of the shark, with methods including hunt(), leave(), and draw(), 
 * and instance variables including x, y, size, color, speed, isLeft, fishEaten, isHunting, destX, and destY.
 * The shark swims towards the fishes one by one and eat them, once all fishes are eaten, the shark would leave and that's the end.
 * To call the shark, you need to turn on the shark mode in Aquarium.java line 95.
 */
public class Shark {
	
	/**
	 * The position of the shark.
	 */
	int x;
	int y;
	/**
	 * whether the fish is eaten.
	 */
	boolean fishEaten;
	/**
	 * whether the shark is hunting.
	 */
	boolean isHunting;
	/**
	 * The color of the shark.
	 */
	Color color;
	/**
	 * The speed of the shark.
	 */
	int speed;
	/**
	 * The direction of the shark.
	 */
	boolean isLeft;
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
	this.speed = 3;
	this.fishEaten = false;
	this.isHunting = true;
	this.isLeft = true;
}
	// This is a method that draws the shark.
	public boolean draw(Graphics2D g, Fish fish) {
		
		if (isLeft) {
		DrawFish.hugeFacingLeft(g, Color.cyan, this.x, this.y);
		}
		
		else {
			DrawFish.hugeFacingRight(g, Color.cyan, this.x, this.y);
		}
		if (this.isHunting) {
		this.hunt(fish);
		}
		return this.fishEaten;
	}
	
	// Shark leaves after eating all the fish
	public void leave() {
		this.isLeft = true;
		this.x -= 2;
		if (this.x < -100) {
			this.x = -100;
		}
	}
	
	// This is a method that allows the shark to hunt according to each fish's location.
	public void hunt(Fish fish) {
		
			this.destX=fish.x;
			this.destY=fish.y;
			
			// make the shark activate depending on the prey.
			if (Math.sqrt((this.destX - this.x)*(this.destX-this.x)+(this.destY-this.y)*(this.destY-this.y))<=10) {
				this.fishEaten = true;
			}
						
			if (this.destX < this.x - this.speed) {
				this.isLeft = true;	
				this.x -= this.speed;
			}
			else if (this.destX > this.x + this.speed) {
				this.isLeft = false;	
				this.x += this.speed;
			}
			
			if (this.destY < this.y - this.speed) {
				this.y -= this.speed;
			}
			else if (this.destY > this.y + this.speed) {	
				this.y += this.speed;
			}
		}	
	
}

