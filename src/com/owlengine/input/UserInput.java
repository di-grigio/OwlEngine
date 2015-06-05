package com.owlengine.input;

import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.owlengine.interfaces.Event;

public final class UserInput implements InputProcessor {

	// for events
	private static Event listener;
	
	// data
	private static int mouseX;
	private static int mouseY;
	private static int dragX;
	private static int dragY;
	private static boolean [] keys;
		
	public UserInput(Event listener) {
		UserInput.listener = listener;
		UserInput.keys = new boolean[256];
	}
	
	public static boolean key(int code){
		return keys[code];
	}
	
	public static int mouseX(){
		return mouseX;
	}
	
	public static int mouseY(){
		return mouseY;
	}
	
	public static int dragX(){
		return dragX;
	}
	
	public static int dragY(){
		return dragY;
	}
	
	@Override
	public boolean keyDown(int keycode) {
		keys[keycode] = true;
		listener.event(Event.KEY_DOWN, keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		keys[keycode] = false;
		listener.event(Event.KEY_UP, keycode);
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		listener.event(Event.KEY_TYPE, character);
		return false;
	}

	@Override
	public boolean mouseMoved(int mouseX, int mouseY) {
		UserInput.mouseX = mouseX;
		UserInput.mouseY = Gdx.graphics.getHeight() - mouseY;
		UserInput.listener.event(Event.MOUSE_MOVE);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		UserInput.listener.event(Event.MOUSE_SCROLL, amount);
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if (button == Buttons.LEFT) {
			UserInput.listener.event(Event.MOUSE_KEY_LEFT);
			return true;     
		}
		else if(button == Buttons.RIGHT){
			UserInput.listener.event(Event.MOUSE_KEY_RIGHT);
			return true;
		}
		else{
			return false;
		}
	}

	@Override
	public boolean touchDragged(int mouseX, int mouseY, int pointer) {
		UserInput.dragX = mouseX;
		UserInput.dragY = mouseY;
		UserInput.listener.event(Event.MOUSE_DRAG);
		return false;
	}
	
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}
}