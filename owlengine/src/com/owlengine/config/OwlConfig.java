package com.owlengine.config;

public class OwlConfig {
	
	private static final String DEFAULT_FRAME_TITLE = "Owl Engine Project";
	
	private static final int DEFAULT_FRAME_WIDTH = 640;
	private static final int DEFAULT_FRAME_HEIGHT = 480;
	
	private static final boolean DEFAULT_FRAME_RESIZABLE = false;
	private static final boolean DEFAULT_FRAME_FULLSCREEN = false;

	public String frameTitle() {
		return DEFAULT_FRAME_TITLE;
	}
	
	public int frameWidth(){
		return DEFAULT_FRAME_WIDTH;
	}
	
	public int frameHeight(){
		return DEFAULT_FRAME_HEIGHT;
	}
	
	public boolean frameResizable(){
		return DEFAULT_FRAME_RESIZABLE;
	}
	
	public boolean frameFullscreen(){
		return DEFAULT_FRAME_FULLSCREEN;
	}
}
