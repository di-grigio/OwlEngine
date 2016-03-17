package com.owlengine.resources;

import org.json.simple.JSONObject;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.owlengine.tools.Log;

public final class Assets implements Disposable {
	
	// JSON Loader
	private static final String JSON_OBJECT_PATH = "path";
	
	// Font builder
	public static final String RUSSIAN_CHARS = "абвгдежзийклмнопрстуфхцчшщъыьэюяАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ№";
	public static final int DEFAULT_FONT_SIZE = 14;
	public static final float DEFAULT_FONT_COLOR_R = 1.0f;
	public static final float DEFAULT_FONT_COLOR_G = 1.0f;
	public static final float DEFAULT_FONT_COLOR_B = 1.0f;
	public static final float DEFAULT_FONT_COLOR_A = 1.0f;
	
	// Data
	private static AssetManager manager;
	
	public Assets() {
		manager = new AssetManager();
		
		// setup BitmapFont loader
		FileHandleResolver resolver = new InternalFileHandleResolver();
		manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
		manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
	}
	
	// Loading
	public static void loadTex(String path){
		if(!manager.isLoaded(path)){
			manager.load(path, Texture.class);
		
			try{
				while(!manager.update()){} // wait
				Log.debug("Texture: " + path + " loaded");
			}
			catch(GdxRuntimeException e){
				Log.err("Texture: " + path + " cant be loaded");
			}
		}
	}
	
	public static void loadTex(String path, TextureParameter param){    
        if(!manager.isLoaded(path)){
            manager.load(path, Texture.class, param);
        
            try{
                while(!manager.update()){} // wait
                Log.debug("Texture: " + path + " loaded");
            }
            catch(GdxRuntimeException e){
                Log.err("Texture: " + path + " cant be loaded");
            }
        }
    }
	
	public static void loadFont(String path){
		if(!manager.isLoaded(path)){
			manager.load(path, FreeTypeFontGenerator.class);
		
			while(!manager.update()){} // wait
			Log.debug("Font: " + path + " loaded");
		}
	}
	
	public static void loadMusic(String path){
		if(!manager.isLoaded(path)){
			manager.load(path, Music.class);
		
			while(!manager.update()){} // wait
			Log.debug("Music: " + path + " loaded");
		}
	}
	
	public static void loadSound(String path) {
		if(!manager.isLoaded(path)){
			manager.load(path, Sound.class);
		
			while(!manager.update()){} // wait
			Log.debug("Music: " + path + " loaded");
		}
	}
	
	// JSON Loading
	public static void jsonLoadTex(JSONObject json) {
		if(json.containsKey(JSON_OBJECT_PATH)){
			String path = (String)json.get(JSON_OBJECT_PATH);
			
			// Load texture
			loadTex(path);
		}
		else{
			Log.err("Resources JSON Texture loader - no have path data");
		}
	}
	
	public static void jsonLoadFont(JSONObject json) {
		if(json.containsKey(JSON_OBJECT_PATH)){
			String path = (String)json.get(JSON_OBJECT_PATH);
			
			// Load font
			loadFont(path);
		}
		else{
			Log.err("Resources JSON Font loader - no have path data");
		}
	}
	
	// Get objects
	public static Texture getTex(String path){
		return manager.get(path, Texture.class);
	}
	
	public static BitmapFont getFont(String path, int size){
		FreeTypeFontGenerator generator = manager.get(path, FreeTypeFontGenerator.class);
		
		if(generator == null){
			Log.err("(Warning) Assets.getFont(): try to get unexisted font [1]");
			return new BitmapFont();
		}
		else{
			FreeTypeFontParameter param = new FreeTypeFontParameter();
			param.characters = FreeTypeFontGenerator.DEFAULT_CHARS + RUSSIAN_CHARS;
			param.size = size;
			BitmapFont font = generator.generateFont(param);
			return font;
		}
	}
	
	public static BitmapFont getFont(String path, FreeTypeFontParameter param){
		FreeTypeFontGenerator generator = manager.get(path, FreeTypeFontGenerator.class);
		
		if(generator == null){
			Log.err("(Warning) Assets.getFont(): try to get unexisted font [2]");
			return new BitmapFont();
		}
		else{
			param.characters = FreeTypeFontGenerator.DEFAULT_CHARS + RUSSIAN_CHARS;
			BitmapFont font = generator.generateFont(param);
			return font;
		}
	}

	public static BitmapFont getFont(final String path) {
		FreeTypeFontGenerator generator = manager.get(path, FreeTypeFontGenerator.class);
		
		if(generator == null){
			Log.err("(Warning) Assets.getFont(): try to get unexisted font [3]");
			return new BitmapFont();
		}
		else{
			FreeTypeFontParameter param = new FreeTypeFontParameter();
			param.characters = FreeTypeFontGenerator.DEFAULT_CHARS + RUSSIAN_CHARS;
			param.size = DEFAULT_FONT_SIZE;
			BitmapFont font = generator.generateFont(param);
			font.setColor(1, 1, 1, 1);
			return font;
		}
	}
	
	public static Music getMusic(String path) {
		return manager.get(path, Music.class);
	}
	
	public static Sound getSound(String path){
		return manager.get(path, Sound.class);
	}
	
	// Check status
	public static boolean isLoaded(){
	    if(manager.getProgress() >= 1){
	        return true;
	    }
	    else{
	    	return false;
	    }
	}
	
	// Dispose
	public static void unload(String path){
		manager.unload(path);
	}
	
	public static void clear(){
		manager.clear();
	}

	@Override
	public void dispose() {
		manager.dispose();
	}
}
