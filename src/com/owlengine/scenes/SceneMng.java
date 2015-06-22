package com.owlengine.scenes;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.owlengine.interfaces.Event;
import com.owlengine.resources.Resources;

public final class SceneMng implements Disposable, Event {

	private Scene current;
	
	public void loadScene(Scene scene){
		if(current != null){
			this.current.event(Event.SCENE_CLOSE);
			current.dispose();
		}
		
		this.current = scene;
		this.current.event(Event.SCENE_LOAD);
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
		current.uiEvent(code);
		current.event(code);
	}

	@Override
	public void event(int code, int data) {
		current.uiEvent(code, data);
		current.event(code, data);
	}

	@Override
	public void event(int code, char data) {
		current.uiEvent(code, data);
		current.event(code, data);
	}
}
