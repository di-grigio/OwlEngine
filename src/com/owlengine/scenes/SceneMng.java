package com.owlengine.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.owlengine.interfaces.Event;
import com.owlengine.resources.Resources;
import com.owlengine.ui.Frame;
import com.owlengine.ui.Widget;

public final class SceneMng implements Disposable, Event {

	private Scene current;
	
	public void loadScene(Scene scene){
		if(current != null){
			current.dispose();
		}
		
		this.current = scene;
	}
	
	public void update(OrthographicCamera camera) {
		current.update(camera);
	}
	
	public void draw(final SpriteBatch sceneBatch, final SpriteBatch uiBatch, Resources resources) {
		sceneBatch.begin();
		current.draw(sceneBatch);
		sceneBatch.end();
		
		uiBatch.begin();
		current.drawUI(uiBatch);
		uiBatch.end();
	}
	
	public void drawHUD(SpriteBatch batch, Resources resources) {
		batch.begin();
		current.drawHUD(batch);
		batch.end();
	}

	@Override
	public void dispose() {
		if(current != null){
			current.dispose();
		}
	}

	@Override
	public void event(int code) {
		current.event(code);
	}

	@Override
	public void event(int code, int data) {
		current.event(code, data);
	}

	@Override
	public void event(int code, char data) {
		current.event(code, data);
	}
	
	// UI
	public Widget getWidget(int id){
		if(current != null){
			return current.getWidget(id);
		}
		else{
			return null;
		}
	}
	
	public Widget getWidget(String title){
		if(current != null){
			return current.getWidget(title);
		}
		else{
			return null;
		}
	}
	
	public Frame getFrame(int id) {
		if(current != null){
			return current.getFrame(id);
		}
		else{
			return null;
		}
	}

	public Frame getFrame(String title) {
		if(current != null){
			return current.getFrame(title);
		}
		else{
			return null;
		}
	}
}
