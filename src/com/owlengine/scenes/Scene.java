package com.owlengine.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.owlengine.interfaces.Draw;
import com.owlengine.interfaces.Event;
import com.owlengine.ui.UI;

public abstract class Scene implements Draw, Event, Disposable {

	private UI ui;
	
	public Scene() {
		ui = new UI();
	}
	
	public final void setUI(final String jsonFile){
		this.ui = new UI(jsonFile);
	}
	
	public UI getUI() {
		return ui;
	}
	
	public final void drawUI(final SpriteBatch batch){
		if(ui != null){
			ui.draw(batch);
		}
	}
	
	// Receiving events
	public final void uiEvent(final int code){
		ui.event(code);
	}
	
	public final void uiEvent(final int code, final int data){
		ui.event(code, data);
	}
	
	public final void uiEvent(final int code, final char data){
		ui.event(code, data);
	}
	
	@Override
	public void event(final int code) {}
	
	@Override
	public void event(final int code, final int data) {}
	
	@Override
	public void event(final int code, final char data) {}
	
	abstract public void drawHUD(SpriteBatch batch);
	abstract public void update(OrthographicCamera camera);
}