package edu.smith.cs.csc212.aquarium;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.Random;

/**
 * This is a class of the treasure boxes at the bottom as sources of bubbles.
 */
public class Treasure {
	/**
	 * The x position of the box.
	 */
	int x;
	/**
	 * The random number generator.
	 */
	Random rand = new Random();
	
	public Treasure() {
		this.x = rand.nextInt(400) + 50;
	}
	
	//This is a method that draws the treasure box in three parts : top, bottom, and the locker.
	public void draw(Graphics2D g) {
		// Draw the fish.
		g.setColor(Color.yellow);

		Shape top = new Rectangle.Double(this.x, 480, 40, 20);
		Shape bottom = new Rectangle.Double(this.x, 490, 40, 20);
		Shape locker = new Rectangle.Double(this.x+16, 485, 8, 10);

		g.fill(top);

		// draw body outline.
		g.setColor(Color.black);
		g.draw(top);
		g.draw(bottom);
		g.draw(locker);

		// draw tail:
		Color bottomColor = Color.yellow.darker();
		g.setColor(bottomColor);
		g.fill(bottom);
		
		//draw locker.
		g.setColor(Color.black);
		g.fill(locker);

	}
	

}
