package com.owlengine.ui.widgets;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.owlengine.ui.Frame;
import com.owlengine.ui.Widget;

public class Label extends Widget {
	
	protected String text;
	
	protected TextBounds bounds;
	protected BitmapFont.HAlignment textAlignment;
	
	public Label(final Frame frame) {
		super(frame);
		this.textAlignment = HAlignment.CENTER;
	}
	
	public final String text(){
		return text;
	}
	
	public final void setTextAlignement(final BitmapFont.HAlignment textAlignement){
		this.textAlignment = textAlignement;
	}
	
	public final void setText(final String text){
		this.bounds = font.getBounds(text);
		this.text = text;
	}

	@Override
	public void draw(SpriteBatch batch) {
		if(text != null){
			font.drawWrapped(batch, text, x, y + bounds.height * 2, sizeX, textAlignment);
		}
	}
}
