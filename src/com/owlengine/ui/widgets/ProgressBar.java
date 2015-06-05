package com.owlengine.ui.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.ui.Frame;
import com.owlengine.ui.Widget;

public class ProgressBar extends Widget {

	private static final int DEFAULT_MIN_VALUE = 0;
	private static final int DEFAULT_MAX_VALUE = 100;
	
	// Data
	private int value;
	private int max;
	
	// Display
	private int drawSizeX;
	
	public ProgressBar(final Frame frame) {
		super(frame);
		this.max = DEFAULT_MAX_VALUE;
	}
	
	public int value(){
		return value;
	}
	
	public int max(){
		return max;
	}
	
	public void reset(){
		setValue(DEFAULT_MIN_VALUE);
	}
	
	public void addValue(int value){
		this.value += value;
		updateDrawSize();
	}
	
	public void subValue(int value){
		this.value -= value;
		updateDrawSize();
	}
	
	public void setValue(int value){
		this.value = value;
		updateDrawSize();
	}
	
	public void setMax(int max){
		this.max = max;
		updateDrawSize();
	}
	
	private void updateDrawSize(){
		if(value <= max){
			//float scaling = (float)value/(float)max;
			this.drawSizeX = (int)(this.sizeX * ((float)value/(float)max));
		}
	}

	@Override
	public void draw(SpriteBatch batch) {
		batch.draw(texNormal, x, y, drawSizeX, sizeY);
		addValue(1);
	}
}
