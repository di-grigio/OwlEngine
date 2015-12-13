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
	private LuaEngine luaEngine;
	
	public OwlEngine(final LuaLib luaLib) {
		new Tools();
		
		//
		this.assets = new Assets();
		this.sceneMng = new SceneMng();
		
		//
		new LuaEngine(sceneMng, luaLib);
	}
	
	public SceneMng getSceneMng(){
		return sceneMng;
	}
	
	public Assets getAssets(){
		return assets;
	}
	
	public LuaEngine getLuaEngine(){
		return luaEngine;
	}
	
	@Override
	public void dispose() {
		assets.dispose();
		sceneMng.dispose();
	}
}
