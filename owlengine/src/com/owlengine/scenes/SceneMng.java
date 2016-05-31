package com.owlengine.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteCache;
import com.badlogic.gdx.utils.Disposable;
import com.owlengine.interfaces.Event;
import com.owlengine.tools.Log;

public final class SceneMng implements Disposable, Event {

	private Scene current;
	
	public void loadScene(Scene scene){
		if(current != null){
			this.current.event(Event.SCENE_CLOSE);
			current.dispose();
			this.current = null;
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
	
	public void draw(final SpriteBatch batchScene) {
		if(current != null){
			batchScene.begin();
			current.draw(batchScene);
			batchScene.end();
		}
		else{
			Log.err("Engine ERR: Scenes not found (drawScene (1))");
			Gdx.app.exit();
		}
	}
	
	public void draw(final SpriteBatch batchScene, final SpriteBatch batchUi) {
		if(current != null){
			batchScene.begin();
			current.draw(batchScene);
			batchScene.end();
			
			batchUi.begin();
			current.drawUI(batchUi);
			batchUi.end();
		}
		else{
			Log.err("Engine ERR: Scenes not found (drawScene (2))");
			Gdx.app.exit();
		}
	}
	
	public void drawUI(final SpriteBatch batchUi){
		if(current != null){
			batchUi.begin();
			current.drawUI(batchUi);
			batchUi.end();
		}
		else{
			Log.err("SceneMng.drawUI(): Scene not found");
			Gdx.app.exit();
		}
	}
	
	public void drawCache(SpriteCache cache){
		if(current != null){
			cache.begin();
			current.drawCache(cache);
			cache.end();
		}
		else{
			Log.err("Engine ERR: Scenes not found (drawCache)");
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
	
	public void postUpdate(){
		if(current != null){
			current.postUpdate();
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
			
			if(!current.widgetSelected()){
				current.event(code);
			}
		}
	}

	@Override
	public void event(int code, int data) {
		if(current != null){
			current.uiEvent(code, data);
			
			if(!current.widgetSelected()){
				current.event(code, data);
			}
		}
	}

	@Override
	public void event(int code, char data) {
		if(current != null){
			current.uiEvent(code, data);
			
			if(!current.widgetSelected()){
				current.event(code, data);
			}
		}
	}

	@Override
	public void customEvent(int code, Object data) {
		if(current != null){
			current.customEvent(code, data);
		}
	}
}
