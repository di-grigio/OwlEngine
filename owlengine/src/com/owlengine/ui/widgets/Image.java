package com.owlengine.ui.widgets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.ui.Frame;
import com.owlengine.ui.Widget;

public class Image extends Widget {

	public Image(final Frame frame) {
		super(frame);
	}
	
	@Override
	public void draw(final SpriteBatch batch) {
		if(selected){
			batch.draw(texSelected, x, y, sizeX, sizeY);
		}
		else{
			batch.draw(texNormal, x, y, sizeX, sizeY);
		}
	}
}
