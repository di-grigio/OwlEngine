package com.owlengine;

import com.badlogic.gdx.utils.Disposable;
import com.owlengine.lua.LuaEngine;
import com.owlengine.lua.LuaLib;
import com.owlengine.resources.Assets;
import com.owlengine.scenes.SceneMng;
import com.owlengine.tools.Tools;

public final class OwlEngine implements Disposable {

	private Assets assets;
	private SceneMng sceneMng;
	
	public OwlEngine(final LuaLib lib) {
		// 
		assets = new Assets();
		sceneMng = new SceneMng();
		
		// static 
		new Tools();
		new LuaEngine(sceneMng, lib);
	}
	
	public SceneMng getSceneMng(){
		return sceneMng;
	}
	
	public Assets getAssets(){
		return assets;
	}

	@Override
	public void dispose() {
		assets.dispose();
		sceneMng.dispose();
	}
}
