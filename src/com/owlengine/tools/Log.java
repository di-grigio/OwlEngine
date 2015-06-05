package com.owlengine.tools;

public final class Log {

	public static void log(String data){
		System.out.println(data);
	}

	public static void err(String data) {
		System.err.println(data);
	}

	public static void lua(String data) {
		System.out.println("LUA: " + data);
	}
}
