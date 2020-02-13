package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * This class is about the snail who likes to eat the algae.
 * 
 * most parts cited from course assignment 1 starting code 
 * at https://github.com/jjfiv/CSC212Aquarium
 * 
 * except that move() and setSize() are completely created by myself.
 * 
 * For the cited part, I revised some of the comments but pointed out the contents said by the author.
 * I also changed some of the instance variables : I added this. eyeColor and this. pupilColor.
 */

public class Snail {
	/**
	 * The height of the snail.
	 */
	public static int HEIGHT = 50;
	/**
	 * The positioning of the snail. 
	 */
	private String direction;
	/**
	 * The position of the Snail; x-coordinate.
	 */
	public int x;
	/**
	 * The position of the Snail; y-coordinate.
	 */
	public int y;
	/**
	 * The status of snail.
	 */
	public boolean wake;
	/**
	 * The color of snail eye and pupil.
	 */
	public Color eyeColor;
	public Color pupilColor;

	/**
	 * Create a snail at (sx, sy) with position s.
	 * 
	 * @param sx - x coordinate
	 * @param sy - y coordinate
	 * @param s  - the "positioning" of the Snail
	 */
	public Snail(int sx, int sy, String s) {
		this.x = sx;
		this.y = sy;
		this.wake = false;
		this.setSide("top");
		this.eyeColor = Color.red;
		this.pupilColor = Color.red;
		
	}
	
	// Set the direction of the snail according to its current position.
	public void setDirect() {
		if (this.y <= 52) {
			this.setSide("top");
		}
		else if (this.y >= 450) {
			this.setSide("bottom");
		}
		if (this.x < 52 && this.y > 52) {
			this.setSide("left");
		}
		else if (this.x > 448 && this.y < 450) {
			this.setSide("right");
			
		}
	}

	/**
	 * According to the author, this method changes the side that the the snail on.
	 * 
	 * @param s - one of "top", "bottom", "left" or "right".
	 */
	public void setSide(String s) {
		this.direction = s.toLowerCase();
	}

	/**
	 * TODO: move the snail according to its current direction.
	 */
	public void move() {
		
		// Move if the snail wakes up and opens its eyes.
		if(this.wake) {
		
			this.eyeColor = Color.white;
			this.pupilColor = Color.black;
			
			if ("top".equals(this.direction)) {
				this.x += 5;
			    }
			else if ("bottom".equals(this.direction)) {
				this.x -= 5;
				}
			else if ("left".equals(this.direction)) {
				this.y -= 5;
				}
			else if ("right".equals(this.direction)) {
				this.y += 5;
				}
		}
		
		// Sleep if the snail is full and closes eyes.
		else{ 
			this.eyeColor = Color.red;
			this.pupilColor = Color.red;
		}

	}

	/**
	 * Draw the snail at the current setup.
	 * 
	 * @param g - the window to draw to.
	 */
	public void draw(Graphics2D g) {
		// Move the snail by calling draw().
		this.move();

		// Rotate the snail according to its current direction.
		Graphics2D position = (Graphics2D) g.create();
		position.translate(x, y);

		// compare strings with ".equals"
		if ("bottom".equals(this.direction)) {
			drawSnail(position, Color.red, Color.red, this.eyeColor, this.pupilColor);
		} else if ("top".equals(this.direction)) {
			position.scale(-1, -1);
			drawSnail(position, Color.red, Color.red, this.eyeColor, this.pupilColor);
		} else if ("left".equals(this.direction)) {
			position.rotate(Math.PI / 2);
			drawSnail(position, Color.red, Color.red,  this.eyeColor, this.pupilColor);
		} else { 			
			position.rotate(-Math.PI / 2);
			drawSnail(position, Color.red, Color.red, this.eyeColor, this.pupilColor);
		}

		
		position.dispose();
	}

	/**
	 * Kudos to Group 7, (Fall 2018).
	 * 
	 * @param g          The graphics object to draw with.
	 * @param bodyColor  The color of the snail body.
	 * @param shellColor The color of the snail shell.
	 * @param eyeColor   The color of the snail eye.
	 */
	public static void drawSnail(Graphics2D g, Color bodyColor, Color shellColor, Color eyeWhiteColor, Color eyeColor) {
		Shape body = new Rectangle2D.Double(0, 0, 40, 50);
		Shape tentacleL = new Rectangle2D.Double(0, -20, 5, 20);
		Shape eyeWhiteL = new Ellipse2D.Double(-4, -28, 12, 12);
		Shape eyePupilL = new Ellipse2D.Double(-2, -26, 4, 4);

		g.setColor(bodyColor);
		g.fill(body);
		g.fill(tentacleL);
		g.setColor(eyeWhiteColor);
		g.fill(eyeWhiteL);
		g.setColor(eyeColor);
		g.fill(eyePupilL);

		Shape tentacleR = new Rectangle2D.Double(35, -20, 5, 20);
		Shape eyeWhiteR = new Ellipse2D.Double(35 - 4, -28, 12, 12);
		Shape eyePupilR = new Ellipse2D.Double(35 + 2, -26 + 4, 4, 4);

		g.setColor(bodyColor);
		g.fill(tentacleR);
		g.setColor(eyeWhiteColor);
		g.fill(eyeWhiteR);
		g.setColor(eyeColor);
		g.fill(eyePupilR);

		Shape shell3 = new Ellipse2D.Double(45, 20, 10, 10);
		Shape shell2 = new Ellipse2D.Double(35, 10, 30, 30);
		Shape shell1 = new Ellipse2D.Double(25, 0, 50, 50);

		g.setColor(shellColor);
		g.fill(shell1);
		g.setColor(Color.black);
		g.draw(shell1);
		g.setColor(shellColor);
		g.fill(shell2);
		g.setColor(Color.black);
		g.draw(shell2);
		g.setColor(shellColor);
		g.fill(shell3);
		g.setColor(Color.black);
		g.draw(shell3);
	}
}
