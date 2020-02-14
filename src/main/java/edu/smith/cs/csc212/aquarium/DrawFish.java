package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * 
 * 
 * cited from course assignment 1 starting code
 * at https://github.com/jjfiv/CSC212Aquarium
 * 
 * According to the author, this class is used statically to draw fish in various
 * positions and sizes.
 * 
 * @author jfoley
 *
 */
public class DrawFish {
	/**
	 * This code based on the Python fish in CSC111 labs, e.g.,
	 * https://jcrouser.github.io/CSC111/labs/lab-10-animation.html
	 * 
	 * @author jfoley
	 */
	public static void facingLeft(Graphics2D g, Color color, int x, int y) {
		// Draw the fish.
		g.setColor(color);

		Shape body = new Ellipse2D.Double(x - 40, y - 20, 80, 40);
		Shape tail = new Ellipse2D.Double(x + 30, y - 30, 20, 60);
		Shape eye = new Ellipse2D.Double(x - 15, y - 10, 10, 10);

		g.fill(body);

		// draw body outline.
		g.setColor(Color.black);
		g.draw(body);

		// fill eye color:
		g.fill(eye);

		// fill tail with darker colors:
		Color tailColor = color.darker();
		g.setColor(tailColor);
		g.fill(tail);

		// draw tail outline.
		g.setColor(Color.black);
		g.draw(tail);
	}

	/**
	 * According to the author, this method is implemented by creating a new Graphics "world" located at x,y
	 * that is scaled in a "backwards" X world.
	 * These are big fishes facing right.
	 * 
	 * @param g     the original graphics world.
	 * @param color the color of the fish!
	 * @param x     the x-coordinate of the position to draw the fish.
	 * @param y     the y-coordinate of the position to draw the fish.
	 */
	public static void facingRight(Graphics2D g, Color color, int x, int y) {
		Graphics2D flipped = (Graphics2D) g.create();
		flipped.translate(x, y);
		flipped.scale(-1, 1);
		facingLeft(flipped, color, 0, 0);
		flipped.dispose();
	}

	/**
	 * According to the author, this method creates half the size of a regular one and calls the regular Fish method.
	 * The fishes are facing left.
	 * These are big fishes facing left.
	 * 
	 * @param g     the original graphics world.
	 * @param color the color of the fish!
	 * @param x     the x-coordinate of the position to draw the fish.
	 * @param y     the y-coordinate of the position to draw the fish.
	 */
	public static void smallFacingLeft(Graphics2D g, Color color, int x, int y) {
		Graphics2D halfSize = (Graphics2D) g.create();
		halfSize.translate(x, y);
		halfSize.scale(0.5, 0.5);
		facingLeft(halfSize, color, 0, 0);
		halfSize.dispose();
	}

	/**
	 * According to the author, this method creates half the size of a regular one and calls the regular Fish method.
	 * The fishes are facing right.
	 * 
	 * @param g     the original graphics world.
	 * @param color the color of the fish!
	 * @param x     the x-coordinate of the position to draw the fish.
	 * @param y     the y-coordinate of the position to draw the fish.
	 */
	public static void smallFacingRight(Graphics2D g, Color color, int x, int y) {
		Graphics2D halfSize = (Graphics2D) g.create();
		halfSize.translate(x, y);
		halfSize.scale(-0.5, 0.5);
		facingLeft(halfSize, color, 0, 0);
		halfSize.dispose();
	}
	
	/**
	 * This is a method that creates the huge shark towards left, based on the author's methods.
	 */
	
	public static void hugeFacingLeft(Graphics2D g, Color color, int x, int y) {
		Graphics2D doubleSize = (Graphics2D) g.create();
		doubleSize.translate(x, y);
		doubleSize.scale(2, 2);
		facingLeft(doubleSize, color, 0, 0);
		doubleSize.dispose();
	}
	
	/**
	 * This is a method that creates the huge shark towards right, based on the author's methods.
	 */
	
	public static void hugeFacingRight(Graphics2D g, Color color, int x, int y) {
		Graphics2D doubleSize = (Graphics2D) g.create();
		doubleSize.translate(x, y);
		doubleSize.scale(2, 2);
		facingRight(doubleSize, color, 0, 0);
		doubleSize.dispose();
	}
}
