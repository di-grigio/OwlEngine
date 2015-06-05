package com.owlengine.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.owlengine.interfaces.Draw;
import com.owlengine.interfaces.Event;
import com.owlengine.ui.Frame;
import com.owlengine.ui.UI;
import com.owlengine.ui.Widget;

public abstract class Scene implements Draw, Event, Disposable {

	private UI ui;
	
	public Scene() {
		ui = new UI();
	}
	
	public final void setUI(final String jsonFile){
		this.ui = new UI(jsonFile);
	}
	
	public final void drawUI(final SpriteBatch batch){
		ui.draw(batch);
	}
	
	@Override
	public void event(final int code) {
		ui.event(code);
	}
	
	@Override
	public void event(final int code, final int data) {
		ui.event(code, data);
	}

	@Override
	public void event(final int code, final char data) {
		ui.event(code, data);
	}
	
	abstract public void drawHUD(SpriteBatch batch);
	abstract public void update(OrthographicCamera camera);
	
	// UI
	public Widget getWidget(int id) {
		return ui.getWidget(id);
	}

	public Widget getWidget(String title) {
		return ui.getWidgets(title);
	}

	public Frame getFrame(int id) {
		return ui.getFrame(id);
	}

	public Frame getFrame(String title) {
		return ui.getFrame(title);
	}
}