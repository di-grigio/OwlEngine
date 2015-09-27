package com.owlengine;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.owlengine.config.OwlConfig;

public final class OwlFrame {

	public OwlFrame(final OwlCycle cycle){
		new LwjglApplication(cycle, buildLwjglConfig(new OwlConfig()));
	}
	
	public OwlFrame(final OwlConfig config, final OwlCycle cycle) {
		new LwjglApplication(cycle, buildLwjglConfig(config));
	}

	private LwjglApplicationConfiguration buildLwjglConfig(final OwlConfig config) {
		LwjglApplicationConfiguration frameConfig = new LwjglApplicationConfiguration();
		
		// frame
		frameConfig.title = config.frameTitle();
		frameConfig.width = config.frameWidth();
		frameConfig.height = config.frameHeight();
		frameConfig.fullscreen = config.frameFullscreen();
		frameConfig.resizable = config.frameResizable();
		
		return frameConfig;
	}	
}
