package com.owlengine.lua;

import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaValue;

import com.owlengine.interfaces.Script;

public final class LuaScript implements Script {
	
	private LuaValue globals;
	private String method;

	public LuaScript(final LuaValue globals, final String method) {
		this.globals = globals;
		this.method = method;
	}
	
	@Override
	public void execute() {
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
