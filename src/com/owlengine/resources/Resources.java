package com.owlengine.resources;

import java.util.HashMap;

import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.utils.Disposable;
import com.owlengine.tools.Log;

public final class Resources implements Disposable {

	// Font default attributes
	private static final String RUSSIAN_CHARS = "‡·‚„‰ÂÊÁËÈÍÎÏÌÓÔÒÚÛÙıˆ˜¯˘˙˚¸˝˛ˇ¿¡¬√ƒ≈∆«»… ÀÃÕŒœ–—“”‘’÷◊ÿŸ⁄€‹›ﬁﬂ";
	private static final int DEFAULT_FONT_SIZE = 14;
	private static final float DEFAULT_FONT_COLOR_R = 1.0f;
	private static final float DEFAULT_FONT_COLOR_G = 1.0f;
	private static final float DEFAULT_FONT_COLOR_B = 1.0f;
	private static final float DEFAULT_FONT_COLOR_A = 1.0f;
	
	// JSON Loader
	private static final String JSON_OBJECT_PATH = "path";
	private static final String JSON_OBJECT_TITLE = "title";
	
	// JSON Font loader
	private static final String JSON_FONT_SIZE = "size";
	private static final String JSON_FONT_COLOR_R = "r";
	private static final String JSON_FONT_COLOR_G = "g";
	private static final String JSON_FONT_COLOR_B = "b";
	private static final String JSON_FONT_COLOR_A = "a";
	
	// Data
	private static HashMap<String, Texture> textures;
	private static HashMap<String, BitmapFont> fonts;
	
	public Resources() {
		textures = new HashMap<String, Texture>();
		fonts = new HashMap<String, BitmapFont>();
	}
	
	// JSON Loader
	public static void jsonLoadTex(JSONObject json) {
		if(json.containsKey(JSON_OBJECT_PATH)){
			String path = (String)json.get(JSON_OBJECT_PATH);
			Texture tex = loadTex(path);
			
			// register texture
			if(json.containsKey(JSON_OBJECT_TITLE)){
				registerTex((String)json.get(JSON_OBJECT_TITLE), tex);
			}
			else{
				registerTex(path, tex);
			}
		}
		else{
			Log.err("Resources JSON Texture loader - no have path data");
		}
	}
	
	public static void jsonLoadFont(JSONObject json) {
		if(json.containsKey(JSON_OBJECT_PATH)){
			
			// size
			int size = DEFAULT_FONT_SIZE;
			
			if(json.containsKey(JSON_FONT_SIZE)){
				size = ((Number)json.get(JSON_FONT_SIZE)).intValue();
			}
			
			// load font
			String path = (String)json.get(JSON_OBJECT_PATH);
			BitmapFont font = loadFont(path, size);
			
			// color (red, green, blue, alpha)
			float r = DEFAULT_FONT_COLOR_R;
			float g = DEFAULT_FONT_COLOR_G;
			float b = DEFAULT_FONT_COLOR_B;
			float a = DEFAULT_FONT_COLOR_A;
			
			if(json.containsKey(JSON_FONT_COLOR_R)){
				r = ((Number)json.get(JSON_FONT_COLOR_R)).floatValue();
			}
			
			if(json.containsKey(JSON_FONT_COLOR_G)){
				g = ((Number)json.get(JSON_FONT_COLOR_G)).floatValue();
			}
			
			if(json.containsKey(JSON_FONT_COLOR_B)){
				b = ((Number)json.get(JSON_FONT_COLOR_B)).floatValue();
			}
			
			if(json.containsKey(JSON_FONT_COLOR_A)){
				a = ((Number)json.get(JSON_FONT_COLOR_A)).floatValue();
			}
			
			font.setColor(r, g, b, a);
			
			// register font
			if(json.containsKey(JSON_OBJECT_TITLE)){
				registerFont((String)json.get(JSON_OBJECT_TITLE), font);
			}
			else{
				registerFont(path, font);
			}
		}
		else{
			Log.err("Resources JSON Font loader - no have path data");
		}
	}
	
	// Loader
	private static Texture loadTex(String path) {
		return new Texture(path);
	}
	
	private static BitmapFont loadFont(String path, int size) {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(path));
		FreeTypeFontParameter param = new FreeTypeFontParameter();
		
		param.characters = FreeTypeFontGenerator.DEFAULT_CHARS + RUSSIAN_CHARS;
		param.size = size;
		
		BitmapFont font = generator.generateFont(param);
		generator.dispose();
		return font;
	}
	
	// Register
	private static void registerTex(String key, Texture tex){
		textures.put(key, tex);
	}

	private static void registerFont(String key, BitmapFont font){
		fonts.put(key, font);
	}
	
	// External load
	public static Texture externalLoadTex(String path) {
		if(textures.containsKey(path)){
			return textures.get(path);
		}
		else{
			Texture tex = loadTex(path);
			registerTex(path, tex);
			return tex;
		}
	}
	
	public static BitmapFont externalLoadFont(String path){
		if(fonts.containsKey(path)){
			return fonts.get(path);
		}
		else{
			BitmapFont font = loadFont(path, DEFAULT_FONT_SIZE);
			registerFont(path, font);
			return font;
		}
	}
	
	// Get
	public static Texture tex(String path){
		return textures.get(path);
	}
	
	public static BitmapFont font(String key){
		return fonts.get(key);
	}
	
	@Override
	public void dispose() {
		for(Texture tex: textures.values()){
			tex.dispose();
		}
		
		for(BitmapFont font: fonts.values()){
			font.dispose();
		}
	}
}
