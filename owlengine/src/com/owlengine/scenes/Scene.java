package com.owlengine.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.owlengine.interfaces.Event;
import com.owlengine.ui.UI;

public abstract class Scene implements Event, Disposable {

	private UI ui;
	
	public Scene() {
		ui = new UI();
	}
	
	public final void setUI(final String jsonFile){
		this.ui = new UI(jsonFile);
	}
	
	protected UI getUI() {
		return ui;
	}
	
	protected final void drawUI(final SpriteBatch batch){
		if(ui != null){
			ui.draw(batch);
		}
	}
	
	public boolean widgetSelected() {
		return ui.selected();
	}
	
	protected void update(OrthographicCamera camera) {}
	
	protected void draw(SpriteBatch batch) {}
	
	protected void drawHUD(SpriteBatch batch) {}
	
	protected void postUpdate() {};
	
	// Receiving events
	@Override
	public void event(final int code) {}
	
	@Override
	public void event(final int code, final int data) {}
	
	@Override
	public void event(final int code, final char data) {}
	
	@Override
	public void dispose() {}
	
	// UI events
	protected final void uiEvent(final int code){
		ui.event(code);
	}
	
	protected final void uiEvent(final int code, final int data){
		ui.event(code, data);
	}
	
	protected final void uiEvent(final int code, final char data){
		ui.event(code, data);
	}
}