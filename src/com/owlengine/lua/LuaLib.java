package com.owlengine.lua;

import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.CoerceJavaToLua;
import org.luaj.vm2.lib.jse.JsePlatform;

import com.owlengine.lua.lib.LuaLibConsole;
import com.owlengine.lua.lib.LuaLibFrame;
import com.owlengine.lua.lib.LuaLibUI;
import com.owlengine.scenes.SceneMng;

abstract public class LuaLib {
	
	protected final LuaValue getGlobals(final SceneMng scenes) {
		LuaValue globals = JsePlatform.standardGlobals();
		
		// init engine LuaScript classes
		initEngineLib(scenes, globals);
		
		// init User LuaScript classes
		initConst(globals);
		return globals;
	}
	
	private final void initEngineLib(final SceneMng scenes, final LuaValue globals){
		globals.set("frame", CoerceJavaToLua.coerce(new LuaLibFrame()));
		globals.set("console", CoerceJavaToLua.coerce(new LuaLibConsole()));
		globals.set("ui", CoerceJavaToLua.coerce(new LuaLibUI(scenes)));
	}

	abstract protected void initConst(LuaValue globals);
}
