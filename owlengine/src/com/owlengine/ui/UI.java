package com.owlengine.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.interfaces.Draw;
import com.owlengine.interfaces.Event;
import com.owlengine.tools.Log;

public final class UI implements Draw, Event {
	
	// JSON type
	private static final String JSON_CONTENT = "content";
	private static final String JSON_TYPE_FRAME = "frame";
	
	// UI Elements
	private HashMap<Integer, Frame>  framesId;
	private HashMap<String, Frame>   framesTitle;
	private HashMap<Integer, Widget> widgetsId;
	private HashMap<String, Widget>  widgetsTitle;
	
	// UI Events
	private UIState state;
	
	public UI() {
		// UI Elements
		framesId = new HashMap<Integer, Frame>();
		framesTitle = new HashMap<String, Frame>();
		widgetsId = new HashMap<Integer, Widget>();
		widgetsTitle = new HashMap<String, Widget>();
		
		// UI Events
		state = new UIState();
	}
	
	public UI(String jsonFile) {
		this(); // main constructor
		readJSON(jsonFile);
	}

	private void readJSON(final String jsonFile) {
		File file = new File(jsonFile);
		
		if(file.exists()){
			try{
				FileInputStream in = new FileInputStream(new File(jsonFile));
				byte [] arr = new byte[(int)file.length()];
				in.read(arr);
				in.close();
				
				JSONArray jsonArray = (JSONArray)JSONValue.parse(new String(arr, "UTF-8"));
					
				if(jsonArray == null){
					Log.err("Error #4: JSON parse error");
				}
				else{
					build(jsonArray);
				}
			}
			catch (FileNotFoundException e) {
				e.printStackTrace();
				Log.err("Error #2: JSON parse error");
			}
			catch (IOException e) {
				e.printStackTrace();
				Log.err("Error #3: JSON parse error");
			}
		}
		else{
			Log.err("Error #1: JSON parse error - file \"" + jsonFile + "\" not found");
		}
	}
	
	private void build(final JSONArray array){
		for(int i = 0; i < array.size(); ++i){
			JSONObject element = (JSONObject)array.get(i);
			
			if(element.containsKey(JSON_CONTENT)){
				String type = (String)element.get(JSON_CONTENT);
				
				if(type.equalsIgnoreCase(JSON_TYPE_FRAME)){
					addFrame(new Frame(this, element));		
				}
			}
		}
	}
	
	private void addFrame(final Frame frame) {
		framesId.put(frame.id(), frame);
		framesTitle.put(frame.title(), frame);
	}
	
	// Widgets management
	protected void registerWidget(Widget widget) {
		widgetsId.put(widget.id(), widget);
		
		if(widget.title() != null){
			widgetsTitle.put(widget.title(), widget);
		}
	}
	
	// Access to elements
	public boolean selected() {
		return state.selected();
	}
	
	public Widget getWidget(String title) {
		return widgetsTitle.get(title);
	}
	
	public Frame getFrame(String title) {
		return framesTitle.get(title);
	}
	
	// Render
	@Override
	public void draw(final SpriteBatch batch) {
		state.draw(batch);
	}

	@Override
	public void event(final int code) {
		switch (code) {
		
			case Event.MOUSE_MOVE:
				state.mouseMove();
				break;
			
			case Event.MOUSE_DRAG:
				state.mouseDrag();
				break;
				
			case Event.MOUSE_KEY_LEFT:
				state.mouseAction();
				break;
				
			case Event.MOUSE_KEY_RIGHT:
				state.mouseActionSecond();
				break;
		}
	}
	
	@Override
	public void event(final int code, final int data) {
		switch (code) {
		
			case Event.MOUSE_SCROLL:
				state.mouseScroll(data);
				break;
				
			case Event.KEY_DOWN:
				state.keyDown(data);
				break;
				
			case Event.KEY_UP:
				state.keyUp(data);
				break;
		}
	}
	
	@Override	
	public void event(final int code, final char data) {
		switch (code) {
		
			case Event.KEY_TYPE:
				state.keyType(data);
				break;
		}
	}
	
	private final class UIState implements Draw {

		private Frame activeFrame;
		private Frame selectedFrame;
		
		private Widget selectedWidget;

		public boolean selected() {
			return selectedWidget != null;
		}
		
		@Override
		public void draw(SpriteBatch batch) {
			if(activeFrame == null){
				for(Frame frame: framesId.values()){	
					frame.draw(batch);
				}
			}
			else{
				for(Frame frame: framesId.values()){
					 if(activeFrame.id() != frame.id()){
						 frame.draw(batch);
					 }
				}
				
				if(activeFrame != null){
					activeFrame.draw(batch);
				}
			}
		}

		private void updateSelecting() {
			// reset selecting
			selectedFrame = null;
			
			if(selectedWidget != null){
				selectedWidget.setSelected(false);
				selectedWidget = null;
			}
			
			// select
			if(activeFrame != null && activeFrame.visible() && activeFrame.inBound()){
				selectedFrame = activeFrame;
				selectedWidget = activeFrame.selectWidget();
			
				if(selectedWidget != null){
					selectedWidget.setSelected(true);
				}
			}
			else{			
				// search new selecting widget
				for(Frame frame: framesId.values()){
					if(frame.visible() && frame.inBound()){
						selectedFrame = frame;
						selectedWidget = frame.selectWidget();
					
						if(selectedWidget != null){
							selectedWidget.setSelected(true);
							selectedFrame = selectedWidget.parent();						
						}
					}
				}
			}
		}
		
		// User Input Events
		private void mouseScroll(int data) { }
		
		private void keyUp(int data) { }

		private void keyDown(int data) { }

		private void keyType(char data) { }
		
		// Events
		private void mouseMove() {
			updateSelecting();
		}

		private void mouseDrag() {
			if(activeFrame != null && activeFrame.movable()){
				activeFrame.drag();
			}
		}
		
		private void mouseAction() {
			activeFrame = selectedFrame;
			
			if(activeFrame != null){
				activeFrame.leftClick();
				
				if(selectedWidget != null){
					selectedWidget.leftClick();
				}
			}
		}
		
		private void mouseActionSecond() {
			if(selectedWidget != null){
				selectedWidget.rightClick();
			}
		}
	}
}