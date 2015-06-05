package com.owlengine;

import com.badlogic.gdx.utils.Disposable;
import com.owlengine.lua.LuaEngine;
import com.owlengine.lua.LuaLib;
import com.owlengine.resources.Resources;
import com.owlengine.scenes.SceneMng;
import com.owlengine.tools.Tools;

public final class OwlEngine implements Disposable {

	private static Resources resources;
	private static SceneMng sceneMng;
	
	public OwlEngine(final LuaLib lib) {
		// 
		resources = new Resources();
		sceneMng = new SceneMng();
		
		// static 
		new Tools();
		new LuaEngine(sceneMng, lib);
	}
	
	public static SceneMng getSceneMng(){
		return sceneMng;
	}

	public static Resources getResources() {
		return resources;
	}

	@Override
	public void dispose() {
		resources.dispose();
		sceneMng.dispose();
	}
}
