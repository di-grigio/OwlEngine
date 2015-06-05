package com.owlengine.tools;

import java.util.Random;

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
}
