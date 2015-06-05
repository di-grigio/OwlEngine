package com.owlengine.lua.lib;

import com.owlengine.scenes.SceneMng;
import com.owlengine.ui.Frame;
import com.owlengine.ui.Widget;

public final class LuaLibUI {

	private static SceneMng scenes;
	
	public LuaLibUI(SceneMng scenes) {
		LuaLibUI.scenes = scenes;
	}
	
	// Lua Methods
	public Frame getFrame(int id){
		return scenes.getFrame(id);
	}
	
	public Frame getFrame(String title){
		return scenes.getFrame(title);
	}
	
	public Widget getWidget(int id){
		return scenes.getWidget(id);
	}
	
	public Widget getWidget(String title){
		return scenes.getWidget(title);
	}
}
