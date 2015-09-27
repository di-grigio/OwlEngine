package com.owlengine.tools;

import java.util.Random;

import com.badlogic.gdx.graphics.Pixmap;

public final class Tools {

	private static Random rand;
	
	public Tools(){
		rand = new Random(System.currentTimeMillis());
	}
	
	public static int rand(int min, int max){
		return rand.nextInt((max - min) + 1) + min;
	}
	
	public static int rand(Random rand, int min, int max){
		return rand.nextInt((max - min) + 1) + min;
	}

	public static float rand(float min, float max){
		return rand.nextFloat() * (max - min) + min;
	}
	
	public static float rand(Random rand, float min, float max){
		return rand.nextFloat() * (max - min) + min;
	}
	
	public static float getRange(int x1, int y1, int x2, int y2) {
		return (float)Math.sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
	}
	
	public static Pixmap pixmapFlipX(Pixmap pix) {
	    final int width = pix.getWidth();
	    final int height = pix.getHeight();
	    Pixmap flipped = new Pixmap(width, height, pix.getFormat());

	    for (int i = 0; i < width; ++i) {
	        for (int j = 0; j < height; ++j) {
	            flipped.drawPixel(i, j, pix.getPixel(width - i - 1, j));
	        }
	    }
	    
	    return flipped;
	}
	
	public static Pixmap pixmapFlipY(Pixmap pix) {
	    final int width = pix.getWidth();
	    final int height = pix.getHeight();
	    Pixmap flipped = new Pixmap(width, height, pix.getFormat());

	    for (int i = 0; i < width; ++i) {
	        for (int j = 0; j < height; ++j) {
	        	flipped.drawPixel(i, j, pix.getPixel(i, height - j - 1));
	        }
	    }
	    
	    return flipped;
	}
}
