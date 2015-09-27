package com.owlengine.ui;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.input.UserInput;
import com.owlengine.interfaces.Draw;
import com.owlengine.tools.Log;

public final class Frame implements Draw {

	// Frame constants
	private static final int FRAME_LAYERS = 5;
	private static final int FRAME_LAYER_BACKGROUND = 0;
	private static final int FRAME_LAYER_ARTWORK = 1;
	private static final int FRAME_LAYER_BORDER = 2;
	private static final int FRAME_LAYER_OVERLAY = 3;
	private static final int FRAME_LAYER_HIGHLIGHT = 4;
	
	// JSON Fields
	private static final String JSON_TITLE = "title";
	private static final String JSON_ALIGNMENT = "alignment";
	private static final String JSON_POS_X = "pos_x";
	private static final String JSON_POS_Y = "pos_y";
	private static final String JSON_SIZE_X = "size_x";
	private static final String JSON_SIZE_Y = "size_y";
	
	// JSON Flags
	private static final String JSON_VISIBLE = "visible";
	private static final String JSON_MOVABLE = "movable";
	
	// JSON Layers
	private static final String JSON_LAYER_BACKGROUND = "background"; // 0
	private static final String JSON_LAYER_ARTWORK = "artwork";       // 1
	private static final String JSON_LAYER_BORDER = "border";         // 2
	private static final String JSON_LAYER_OVERLAY = "overlay";       // 3
	private static final String JSON_LAYER_HIGHLIGHT = "highlight";   // 4
	
	// Data
	// ID
	private static int ID = 0;
	private final int id;
	
	// Header
	private String title;
	
	// Widgets layers
	private FrameLayer [] layers;
	
	// Frame data
	private int alignment;
	private int posX; // relative alignment position
	private int posY; // relative alignment position
	private int sizeX;
	private int sizeY;
	
	// Draw pos
	private int x; // absolute frame position on screen
	private int y; // absolute frame position on screen
	
	// Frame flags
	private boolean visible;
	private boolean movable;
	
	// Drag click position
	private int frameClickedX;
	private int frameClickedY;
	
	//
	public Frame(final UI ui, final JSONObject element) {
		this.id = ++ID;
		this.layers = new FrameLayer[FRAME_LAYERS];
		
		for(int i = 0; i < layers.length; ++i){
			layers[i] = new FrameLayer();
		}
		
		// Read JSON data
		build(ui, element);
		updatePosition();
	}

	public int id(){
		return id;
	}
	
	public String title(){
		return title;
	}

	public int alignment(){
		return alignment;
	}
	
	public int posX() {
		return x;
	}
	
	public int posY() {
		return y;
	}
	
	public int sizeX() {
		return sizeX;
	}
	
	public int sizeY() {
		return sizeY;
	}

	protected void leftClick() {
		// this method save mouse click position in frame coordinates for drag() method
		this.frameClickedX = UserInput.mouseX() - this.x;
		this.frameClickedY = UserInput.mouseY() - this.y;
	}
	
	protected void drag() {
		// translate Frame and all contained elements
		this.x -= this.x + this.frameClickedX - UserInput.dragX(); // this.x -= deltaX;
		this.y -= this.y + this.frameClickedY - (Gdx.graphics.getHeight() - UserInput.dragY()); // this.y -= deltaY;
		
		// update translate
		for(int i = 0; i < layers.length; ++i){
			layers[i].updatePosition();
		}
	}
	
	private void updatePosition() {
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		
		switch (alignment) {
			case Alignment.DOWN_LEFT: // Alignement.ABSOLUTE == Alignment.DOWN_LEFT
				setPos(posX, posY);
				break;
				
			case Alignment.DOWN:
				setPos(posX - sizeX/2 + w/2, posY);
				break;
				
			case Alignment.DOWN_RIGHT:
				setPos(posX - sizeX + w, posY);
				break;
			
			case Alignment.CENTER_LEFT:
				setPos(posX, posY - sizeY/2 + h/2);
				break;
				
			case Alignment.CENTER:
				setPos(posX - sizeX/2 + w/2, posY - sizeY/2 + h/2);
				break;
				
			case Alignment.CENTER_RIGHT:
				setPos(posX - sizeX + w, posY - sizeY/2 + h/2);
				break;
				
			case Alignment.UP_LEFT:
				setPos(posX, posY - sizeY + h);
				break;
				
			case Alignment.UP:
				setPos(posX - sizeX/2 + w/2, posY - sizeY + h);
				break;
				
			case Alignment.UP_RIGHT:
				setPos(posX - sizeX + w, posY - sizeY + h);
				break;
				
			default: // Alignement.ABSOLUTE
				setPos(posX, posY);
				break;
		}
	}
	
	public void setPos(final int x, final int y){
		this.x = x;
		this.y = y;
		
		for(int i = 0; i < layers.length; ++i){
			layers[i].updatePosition();
		}
	}

	public boolean visible(){
		return visible;
	}
	
	public void setVisible(boolean visible){
		this.visible = visible;
	}
	
	public boolean movable(){
		return movable;
	}
	
	public void setMovable(boolean movable){
		this.movable = movable;
	}
	
	protected boolean inBound() {
		return (x <= UserInput.mouseX() && UserInput.mouseX() <= x + sizeX &&
				y <= UserInput.mouseY() && UserInput.mouseY() <= y + sizeY);
	}
	
	protected Widget selectWidget() {
		for(int i = layers.length - 1; i >= 0; --i){ // reversed check
			Widget widget = layers[i].selectWidget();
			
			if(widget != null){
				return widget;
			}
		}
		
		return null;
	}
	
	private void build(final UI ui, final JSONObject frameData){
		try {
			// Load frame fields
			if(frameData.containsKey(JSON_TITLE)){
				this.title = (String)frameData.get(JSON_TITLE);
			}
			
			if(frameData.containsKey(JSON_ALIGNMENT)){
				String value = (String)frameData.get(JSON_ALIGNMENT);
				this.alignment = Alignment.toInt(value);
			}
			
			if(frameData.containsKey(JSON_POS_X)){
				this.posX = ((Number)frameData.get(JSON_POS_X)).intValue();
			}
			
			if(frameData.containsKey(JSON_POS_Y)){
				this.posY = ((Number)frameData.get(JSON_POS_Y)).intValue();
			}
			
			if(frameData.containsKey(JSON_SIZE_X)){
				this.sizeX = ((Number)frameData.get(JSON_SIZE_X)).intValue();
			}
			
			if(frameData.containsKey(JSON_SIZE_Y)){
				this.sizeY = ((Number)frameData.get(JSON_SIZE_Y)).intValue();
			}
			
			if(frameData.containsKey(JSON_VISIBLE)){
				this.visible = (Boolean)frameData.get(JSON_VISIBLE);
			}
			
			if(frameData.containsKey(JSON_MOVABLE)){
				this.movable = (Boolean)frameData.get(JSON_MOVABLE);
			}
			
			// Load layers
			if(frameData.containsKey(JSON_LAYER_BACKGROUND)){
				JSONArray data = (JSONArray)frameData.get(JSON_LAYER_BACKGROUND);
				if(data != null){
					layers[FRAME_LAYER_BACKGROUND].build(this, ui, data);
				}
			}
			
			if(frameData.containsKey(JSON_LAYER_ARTWORK)){
				JSONArray data = (JSONArray)frameData.get(JSON_LAYER_ARTWORK);
				if(data != null){
					layers[FRAME_LAYER_ARTWORK].build(this, ui, data);
				}
			}
			
			if(frameData.containsKey(JSON_LAYER_BORDER)){
				JSONArray data = (JSONArray)frameData.get(JSON_LAYER_BORDER);
				
				if(data != null){
					layers[FRAME_LAYER_BORDER].build(this, ui, data);
				}
			}
			
			if(frameData.containsKey(JSON_LAYER_OVERLAY)){
				JSONArray data = (JSONArray)frameData.get(JSON_LAYER_OVERLAY);
				if(data != null){
					layers[FRAME_LAYER_OVERLAY].build(this, ui, data);
				}
			}
			
			if(frameData.containsKey(JSON_LAYER_HIGHLIGHT)){
				JSONArray data = (JSONArray)frameData.get(JSON_LAYER_HIGHLIGHT);
				if(data != null){
					layers[FRAME_LAYER_HIGHLIGHT].build(this, ui, data);
				}
			}
		}
		catch(ClassCastException e){
			Log.err("Error #5: JSON parse error");
		}
	}
	
	@Override
	public void draw(final SpriteBatch batch) {
		if(visible){
			for(int i = 0; i < layers.length; ++i){
				layers[i].draw(batch);
			}
		}
	}
}
