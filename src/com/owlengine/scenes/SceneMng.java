package com.owlengine.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.owlengine.interfaces.Event;
import com.owlengine.tools.Log;

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
		if(current != null){
			current.update(camera);
		}
		else{
			Log.err("Engine ERR: Scenes not found (update)");
			Gdx.app.exit();
		}
	}
	
	public void draw(final SpriteBatch sceneBatch, final SpriteBatch uiBatch) {
		if(current != null){
			sceneBatch.begin();
			current.draw(sceneBatch);
			sceneBatch.end();
			
			uiBatch.begin();
			current.drawUI(uiBatch);
			uiBatch.end();
		}
		else{
			Log.err("Engine ERR: Scenes not found (drawScene)");
			Gdx.app.exit();
		}
	}
	
	public void drawHUD(SpriteBatch batch) {
		if(current != null){
			batch.begin();
			current.drawHUD(batch);
			batch.end();
		}
		else{
			Log.err("Engine ERR: Scenes not found (drawHUD)");
			Gdx.app.exit();
		}
	}

	@Override
	public void dispose() {
		if(current != null){
			current.dispose();
		}
	}

	@Override
	public void event(int code) {
		if(current != null){
			current.uiEvent(code);
			current.event(code);
		}
	}

	@Override
	public void event(int code, int data) {
		if(current != null){
			current.uiEvent(code, data);
			current.event(code, data);
		}
	}

	@Override
	public void event(int code, char data) {
		if(current != null){
			current.uiEvent(code, data);
			current.event(code, data);
		}
	}
}
