package com.owlengine.tools.algorithms;

import com.badlogic.gdx.utils.StringBuilder;

public class Point {

	public final int x;
	public final int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("[").append(x).append(";").append(y).append("]").toString();
	}
}