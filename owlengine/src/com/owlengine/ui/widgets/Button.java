package com.owlengine.ui.widgets;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.owlengine.ui.Frame;
import com.owlengine.ui.Widget;

public class Button extends Widget {

	protected String text;
	
	protected TextBounds bounds;
	protected BitmapFont.HAlignment textAlignment;
	
	private boolean highlighted;
	
	public Button(final Frame frame) {
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
	
	public final void setHighlighted(final boolean value){
		this.highlighted = value;
	}
	
	@Override
	public void draw(final SpriteBatch batch) {
		if(highlighted){
			if(selected){
				batch.draw(texNormal, x, y, sizeX, sizeY);
				batch.draw(texSelected, x, y, sizeX, sizeY);
			}
			else{
				batch.draw(texNormal, x, y, sizeX, sizeY);
			}
		}
		else{
			if(selected){
				batch.draw(texSelected, x, y, sizeX, sizeY);
			}
			else{
				batch.draw(texNormal, x, y, sizeX, sizeY);
			}
		}
		
		if(text != null){
			font.drawWrapped(batch, text, x, y + bounds.height * 2, sizeX, textAlignment);
		}
	}
}
