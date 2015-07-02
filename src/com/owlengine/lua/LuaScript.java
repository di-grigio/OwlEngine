package com.owlengine.lua;

import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaValue;

import com.owlengine.interfaces.Script;
import com.owlengine.tools.Log;

public final class LuaScript implements Script {
	
	private LuaValue globals;

	public LuaScript(final LuaValue globals) {
		this.globals = globals;
	}
	
	@Override
	public void execute() {
		Log.err("(Warning) Calling void Lua script");
	}
	
	public void execute(final String method){
	    try {
	    	if(!globals.get(method).isnil()){
	    		globals.get(method).call();
	    	}
	    }
	    catch (LuaError e){  
	    	e.printStackTrace();
	    }
	}
}
