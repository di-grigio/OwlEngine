package com.owlengine.ui;

import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.owlengine.interfaces.Script;
import com.owlengine.resources.Resources;
import com.owlengine.tools.Log;
import com.owlengine.ui.widgets.Button;
import com.owlengine.ui.widgets.Image;
import com.owlengine.ui.widgets.ProgressBar;

final class WidgetBuilder {

	// JSON Fields
	private static final String CONTENT = "content";
	private static final String TITLE = "title";
	
	// JSON Position
	private static final String ALIGNMENT = "alignment";
	private static final String POS_X = "pos_x";
	private static final String POS_Y = "pos_y";
	private static final String SIZE_X = "size_x";
	private static final String SIZE_Y = "size_y";
	
	// JSON Flags
	private static final String VISIBLE = "visible";
	private static final String DISABLED = "disabled";
	
	// JSON Graphics
	private static final String TEXTURE_NORMAL = "texture_normal";
	private static final String TEXTURE_SELECT = "texture_select";
	private static final String TEXTURE_DISABLED = "texture_disabled";
	
	// JSON Text
	private static final String TEXT = "text";
	private static final String FONT = "font";
	
	// JSON Events fields
	private static final String EVENT_ONLOAD = "on_load";
	private static final String EVENT_ACTION = "on_action";
	private static final String EVENT_ACTION_SECOND = "on_action_second";

	// JSON Widget types
	private static final String WIDGET_TYPE_BUTTON = "button";
	private static final String WIDGET_TYPE_IMAGE = "image";
	private static final String WIDGET_TYPE_PROGRESS_BAR = "progress_bar";
	
	// JSON Progress Bar fieds
	private static final String PROGRESS_BAR_VALUE = "value";
	private static final String PROGRESS_BAR_MAX_VALUE = "max";
	
	protected static Widget build(Frame frame, JSONObject json, Script script) {
		Widget widget = null;
		
		if(json.containsKey(CONTENT)){
			try {
				final String type = (String)json.get(CONTENT);
				
				// Components
				if(type.equals(WIDGET_TYPE_BUTTON)){
					widget = buildButton(frame, json);
				}
				else if(type.equals(WIDGET_TYPE_IMAGE)){
					widget = buildImage(frame, json);
				}
				else if(type.equals(WIDGET_TYPE_PROGRESS_BAR)){
					widget = buildProgressBar(frame, json);
				}
				
				// Common widget fields
				if(widget != null){
					return buildWidget(widget, frame, json, script);
				}
				else{
					Log.err("Error #7: JSON parse error - Unknown widget 'type'");
					Gdx.app.exit();
					return null;
				}
			}
			catch(NumberFormatException e){
				Log.err("Error #8: JSON parse error - Invalid 'numeric' param");
				Gdx.app.exit();
				return null;
			}
			catch(ClassCastException e){
				Log.err("Error #9: JSON parse error - Invalid param type");
				Gdx.app.exit();
				return null;
			}
		}
		else{
			Log.err("Error #10: JSON parse error - No widget 'type'");
			Gdx.app.exit();
			return null;
		}
	}
	
	private static Widget buildWidget(final Widget widget, final Frame frame, final JSONObject json, Script script) {
		// External data loading
		if(json.containsKey(TEXTURE_NORMAL)){
			String path = (String)json.get(TEXTURE_NORMAL);
			Resources.externalLoadTex(path);
			widget.setTexNormal(path);
		}
		
		if(json.containsKey(TEXTURE_SELECT)){
			String path = (String)json.get(TEXTURE_SELECT);
			Resources.externalLoadTex(path);
			widget.setTexSelected(path);
		}
		
		if(json.containsKey(TEXTURE_DISABLED)){
			String path = (String)json.get(TEXTURE_DISABLED);
			Resources.externalLoadTex(path);
			widget.setTexDisabled(path);
		}
		
		// Events
		if(script != null){
			widget.setScript(script);
		}
		
		if(json.containsKey(EVENT_ONLOAD)){
			widget.setEventOnLoad((String)json.get(EVENT_ONLOAD));
		}
		
		if(json.containsKey(EVENT_ACTION)){
			widget.setEventAction((String)json.get(EVENT_ACTION));
		}
		
		if(json.containsKey(EVENT_ACTION_SECOND)){
			widget.setEventActionSecond((String)json.get(EVENT_ACTION_SECOND));
		}	
		
		// Another widget data
		if(json.containsKey(TITLE)){
			widget.setTitle((String)json.get(TITLE));
		}
		
		if(json.containsKey(ALIGNMENT)){
			String value = (String)json.get(ALIGNMENT);
			widget.setAlignment(Alignment.toInt(value));
		}
		else{
			widget.setAlignment(Alignment.ABSOLUTE);
		}
		
		if(json.containsKey(POS_X)){
			widget.setPosX(((Number)json.get(POS_X)).intValue());
		}
		
		if(json.containsKey(POS_Y)){
			widget.setPosY(((Number)json.get(POS_Y)).intValue());
		}
		
		if(json.containsKey(SIZE_X)){
			widget.setSizeX(((Number)json.get(SIZE_X)).intValue());
		}
		
		if(json.containsKey(SIZE_Y)){
			widget.setSizeY(((Number)json.get(SIZE_Y)).intValue());
		}
		
		if(json.containsKey(VISIBLE)){
			widget.setVisibible((Boolean)json.get(VISIBLE));
		}
		
		if(json.containsKey(DISABLED)){
			widget.setDisabled((Boolean)json.get(DISABLED));
		}
		
		//
		widget.onload();
		return widget;
	}

	private static Button buildButton(final Frame frame, final JSONObject json) {
		Button button = new Button(frame);
		
		if(json.containsKey(FONT)){
			String path = (String)json.get(FONT);
			Resources.externalLoadFont(path);
			button.setFont(path);
		}
		
		if(json.containsKey(TEXT)){
			button.setText((String)json.get(TEXT));
		}

		return button;
	}
	
	private static Image buildImage(final Frame frame, final JSONObject json) {
		Image image = new Image(frame);
		return image;
	}
	
	private static Widget buildProgressBar(final Frame frame, final JSONObject json) {
		ProgressBar bar = new ProgressBar(frame);
		
		if(json.containsKey(PROGRESS_BAR_VALUE)){
			bar.setValue(((Number)json.get(PROGRESS_BAR_VALUE)).intValue());
		}
		
		if(json.containsKey(PROGRESS_BAR_MAX_VALUE)){
			bar.setValue(((Number)json.get(PROGRESS_BAR_MAX_VALUE)).intValue());
		}
		
		return bar;
	}
}
