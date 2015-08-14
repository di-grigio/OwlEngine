package com.owlengine.ui.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.interfaces.Script;
import com.owlengine.resources.Assets;
import com.owlengine.ui.Frame;

public class Checkbox extends Button {

	private boolean value;
	
	private Texture texValueCheckNormal;
	private Texture texValueCheckSelected;
	
	public Checkbox(Frame frame) {
		super(frame);
		
		// native script
		setScriptOnAction(new Script() {
			
			@Override
			public void execute(String key) { }
			
			@Override
			public void execute() { switchValue(); }
		});
	}
	
	public final boolean value(){
		return value;
	}
	
	public final void switchValue(){
		this.value = !value;
	}
	
	public final void setValue(final boolean value){
		this.value = value;
	}
	
	public final void setTexValueNormal(final String key){
		this.texValueCheckNormal = Assets.getTex(key);
	}
	
	public final void setTexValue(final Texture tex){
		this.texValueCheckNormal = tex;
	}

	public final void setTexValueSelected(final String path) {
		this.texValueCheckSelected = Assets.getTex(path);
	}
	
	public final void setTexValueSelected(final Texture tex) {
		this.texValueCheckSelected = tex;
	}
	
	@Override
	public void draw(final SpriteBatch batch) {
		if(selected){
			if(value){
				batch.draw(texValueCheckSelected, x, y, sizeX, sizeY);
			}
			else{
				batch.draw(texSelected, x, y, sizeX, sizeY);
			}
		}
		else{
			if(value){
				batch.draw(texValueCheckNormal, x, y, sizeX, sizeY);
			}
			else{
				batch.draw(texNormal, x, y, sizeX, sizeY);
			}
		}
	}
}
