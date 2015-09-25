package com.owlengine;

import com.badlogic.gdx.ApplicationListener;
import com.owlengine.lua.LuaLib;

abstract public class OwlCycle implements ApplicationListener {

	protected OwlEngine engine;
	private LuaLib luaLib;
	
	public OwlCycle() { }
	
	public OwlCycle(final LuaLib luaLib) {
		this.luaLib = luaLib;
	}
	
	@Override
	public final void create() {
		engine = new OwlEngine(luaLib);
		setup();
	}

	public void setup() {}

	@Override
	public void render() {}
	
	@Override
	public void dispose() {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}
	
	@Override
	public void resize(int width, int height) {}
}
