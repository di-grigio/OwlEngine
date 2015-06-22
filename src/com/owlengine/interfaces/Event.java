package com.owlengine.interfaces;

public interface Event {

	public static final int NULL = 0;
	
	// User Input Events
	// mouse
	public static final int MOUSE_MOVE = 1;
	public static final int MOUSE_DRAG = 2;
	public static final int MOUSE_KEY_LEFT = 3;
	public static final int MOUSE_KEY_RIGHT = 4;
	public static final int MOUSE_SCROLL = 5;
	// keys
	public static final int KEY_DOWN = 6;
	public static final int KEY_UP = 7;
	public static final int KEY_TYPE = 8;

	// Scene events
	public static final int SCENE_LOAD = 9;
	public static final int SCENE_CLOSE = 10;
	
	//
	public void event(int code);
	public void event(int code, int data);
	public void event(int code, char data);
}
