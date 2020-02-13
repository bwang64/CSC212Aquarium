package edu.smith.cs.csc212.aquarium;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;


/**
 * This is a class of the treasure box at the left bottom.
 */
public class Treasure {
	
	//This is a method that draws the treasure box in three parts : top, bottom, and the locker.
	public void draw(Graphics2D g) {
		// Draw the fish.
		g.setColor(Color.yellow);

		Shape top = new Rectangle.Double(20, 480, 40, 20);
		Shape bottom = new Rectangle.Double(20, 490, 40, 20);
		Shape locker = new Rectangle.Double(36, 485, 8, 10);

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
