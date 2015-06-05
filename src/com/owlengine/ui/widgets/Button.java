package com.owlengine.ui.widgets;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.ui.Frame;
import com.owlengine.ui.Widget;

public class Button extends Widget {

	protected String text;
	protected GlyphLayout layout;
	
	public Button(final Frame frame) {
		super(frame);
		this.layout = new GlyphLayout();
	}
	
	public final String text(){
		return text;
	}
	
	public final void setText(final String text){
		this.layout.setText(font, text);
		this.text = text;
	}
	
	@Override
	public void draw(final SpriteBatch batch) {
		if(selected){
			batch.draw(texSelected, x, y, sizeX, sizeY);
		}
		else{
			batch.draw(texNormal, x, y, sizeX, sizeY);
		}
		
		if(text != null){
			font.draw(batch, text, x + sizeX/2 - layout.width/2, y + sizeY - layout.height/2);
		}
	}
}
