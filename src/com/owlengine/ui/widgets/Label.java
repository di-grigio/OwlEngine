package com.owlengine.ui.widgets;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.ui.Frame;
import com.owlengine.ui.Widget;

public class Label extends Widget {
	
	protected String text;
	protected GlyphLayout layout;
	
	public Label(final Frame frame) {
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
	public void draw(SpriteBatch batch) {
		if(text != null){
			font.draw(batch, text, x + sizeX/2 - layout.width/2, y + sizeY - layout.height/2);
		}
	}
}
