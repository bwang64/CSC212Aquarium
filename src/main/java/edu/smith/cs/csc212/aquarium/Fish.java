package edu.smith.cs.csc212.aquarium;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Random;


/**
 * This is a class of the fishes in the aquarium, with the methods draw(), hungyDraw(), eat(), seekFood(), and swim().
 * And the instance variables including size, x, y, destX, destY, speed, color, size, direction, hungry meter.
 */

public class Fish {
	/**
	 * Theposition of the fish.
	 */
	int x;
	int y;
	/**
	 * The destination of the fish.
	 */
	int destX;
	int destY;
	/**
	 * The color of the fish.
	 */
	Color color;
	/**
	 * The size of the fish.
	 */
	boolean isLittle;
	/**
	 * The direction of the fish.
	 */
	boolean isLeft;
	/**
	 * The speed of the fish.
	 */
	double speed;
	/**
	 * A random number generator.
	 */
	Random rand = new Random();
	/**
	 * The current hungry meter of the fish.
	 */
	int hungry;
	/**
	 * The total hungry meter of the fish.
	 */
	int total;
	/**
	 * The status of hunger of the fish.
	 */
	boolean isHungry;
	/**
	 * The original color as stored variable.
	 */
	Color colorOriginal;
	
	/**
	 * A fish class generator.
	 */
	public Fish(int x, int y) {
		this.color=Color.getHSBColor(
                rand.nextFloat(), 0.8f, 0.8f);
		this.colorOriginal=this.color;
		this.x=x;
		this.y=y;
		this.isLittle=rand.nextBoolean();
		this.isLeft=rand.nextBoolean();
		this.speed=rand.nextDouble()*2+1;
		this.destX = rand.nextInt(500);
		this.destY = rand.nextInt(500);
		if (this.isLittle) {
			this.hungry = 500;
			this.total = 500;
		}
		else {
			this.hungry = 1000;
			this.total = 1000;
		}
		this.isHungry = false;
		
				
	}
	
	// The method that draws the fish depending on the size and direction of the fish.
	public void draw(Graphics2D g) {
		if (this.isLeft && this.isLittle) {
			DrawFish.smallFacingLeft(g, this.color, this.x, this.y);
		}
		else if (!this.isLeft && this.isLittle) {
			DrawFish.smallFacingRight(g, this.color, this.x, this.y);
		}
		else if (!this.isLeft && !this.isLittle) {
			DrawFish.facingRight(g, this.color, this.x, this.y);
		}
		else if (this.isLeft && !this.isLittle) {
			DrawFish.facingLeft(g, this.color, this.x, this.y);
		}
		this.swim();
		this.hungryDraw(g);
		this.eat();
		
	}
	
	// The method that draws the hungry meter of each fish.
	public void hungryDraw(Graphics2D g) {
		Shape totalHungry = new Rectangle.Double(this.x - 20, this.y - 30, this.total/20, 5);
		Shape currentHungry = new Rectangle.Double(this.x - 20, this.y - 30, this.hungry/20, 5);
		g.setColor(Color.white);
		g.draw(totalHungry);
		g.draw(currentHungry);
		g.setColor(Color.red);
		g.fill(currentHungry);
	}
	
	// The method that controls the increment and decrement of the hungry meter.
	public void eat() {
		
		// Judge according to the position.
		if (this.y < 410) {
			this.hungry -= 1;
		}
		else {
			this.hungry += 10;
		}
		
		// Set the fish's status and color.
		if (this.hungry <= 0) {
			this.hungry = 0;
			this.isHungry = true;
			this.color = Color.black;
		}
		else if (this.hungry > this.total) {
			this.hungry = this.total;
			this.isHungry = false;
			this.color = this.colorOriginal;
		}
		// Invoke the action of seeking for food.
		this.seekFood();
	}
	
	// The method that makes the hungry fish to seekk for foods.
	public void seekFood() {
		if (this.isHungry) {
			this.destY = rand.nextInt(90) + 410;
		}
	}
	
	// The methods that controls the fish swimming to a refreshing destination.
	public void swim() {
		
		// pick up a new destination if the last one is achieved.
		if (Math.abs(this.destX - this.x) <= this.speed || Math.abs(this.destY - this.y) <= this.speed) {
			this.destX = rand.nextInt(500);
			this.destY = rand.nextInt(500);
		}
		
		if (this.destX < this.x ) {
			this.isLeft = true;	
			this.x -= this.speed;
		}
		else if (this.destX > this.x ) {
			this.isLeft = false;	
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
