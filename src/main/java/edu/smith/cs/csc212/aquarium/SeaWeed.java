package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;

/**
 * This class is about the fish's food, seaweeds, at the bottom of the ocean，
 * with the methods including draw(), and instance variables including x, y, width, and height.
 */
public class SeaWeed {
	/**
	 * The location of the seaweed.
	 */
	int x;
	int y;
	/**
	 * The width and height of the seaweed.
	 */
	int width;
	int height;
	/**
	 * Create a random number generator.
	 */
	Random rand = new Random();
	
	public SeaWeed() {
		this.width = rand.nextInt(2)+8;
		this.height = rand.nextInt(30)+50;
		this.x = rand.nextInt(500);
		this.y = 500-this.height;
	}
	
	// This is a method that draws the seaweeds.
	public void draw(Graphics2D g) {
		Shape SeaWeed = new Ellipse2D.Double(this.x, this.y, this.width, this.height);
		g.setColor(Color.green.darker());
		g.fill(SeaWeed);
	}

}
