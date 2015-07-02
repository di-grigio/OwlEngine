package com.owlengine.ui;

import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.owlengine.interfaces.Script;
import com.owlengine.resources.Assets;
import com.owlengine.tools.Log;
import com.owlengine.ui.widgets.Button;
import com.owlengine.ui.widgets.Image;
import com.owlengine.ui.widgets.Label;
import com.owlengine.ui.widgets.Minimap;
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
	private static final String TEXT_ALIGNMENT = "text_alignment";
	private static final String FONT = "font";
	
	// JSON Events fields
	private static final String EVENT_ONLOAD = "on_load";
	private static final String EVENT_ACTION = "on_action";
	private static final String EVENT_ACTION_SECOND = "on_action_second";

	// JSON Widget types
	private static final String WIDGET_TYPE_LABEL = "label";
	private static final String WIDGET_TYPE_BUTTON = "button";
	private static final String WIDGET_TYPE_IMAGE = "image";
	private static final String WIDGET_TYPE_PROGRESS_BAR = "progress_bar";
	private static final String WIDGET_TYPE_MINIMAP = "minimap";
	
	// JSON Button settings
	private static final String BUTTON_HIGHLIGHTED = "highlighted";
	
	// JSON Progress Bar fieds
	private static final String PROGRESS_BAR_VALUE = "value";
	private static final String PROGRESS_BAR_MAX_VALUE = "max";
	
	// JSON Minimap
	private static final String MINIMAP_CENTRING = "centering";
	private static final String MINIMAP_CAMERA_FRAME = "texture_camera_frame";
	
	// JSON Font 
	private static final String FONT_COLOR_R = "font_color_r";
	private static final String FONT_COLOR_G = "font_color_g";
	private static final String FONT_COLOR_B = "font_color_b";
	private static final String FONT_COLOR_A = "font_color_a";
	private static final String FONT_COLOR_SIZE = "font_size";
	
	protected static Widget build(final Frame frame, final JSONObject json, final Script script) {
		Widget widget = null;
		
		if(json.containsKey(CONTENT)){
			try {
				final String type = (String)json.get(CONTENT);
				
				// Components
				if(type.equals(WIDGET_TYPE_LABEL)){
					widget = buildLabel(frame, json);
				}
				else if(type.equals(WIDGET_TYPE_BUTTON)){
					widget = buildButton(frame, json);
				}
				else if(type.equals(WIDGET_TYPE_IMAGE)){
					widget = buildImage(frame, json);
				}
				else if(type.equals(WIDGET_TYPE_PROGRESS_BAR)){
					widget = buildProgressBar(frame, json);
				}
				else if(type.equals(WIDGET_TYPE_MINIMAP)){
					widget = buildMinimap(frame, json);
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

	private static Widget buildWidget(final Widget widget, final Frame frame, final JSONObject json, final Script script) {
		// External data loading
		if(json.containsKey(TEXTURE_NORMAL)){
			String path = (String)json.get(TEXTURE_NORMAL);
			Assets.loadTex(path);
			widget.setTexNormal(path);
		}
		
		if(json.containsKey(TEXTURE_SELECT)){
			String path = (String)json.get(TEXTURE_SELECT);
			Assets.loadTex(path);
			widget.setTexSelected(path);
		}
		
		if(json.containsKey(TEXTURE_DISABLED)){
			String path = (String)json.get(TEXTURE_DISABLED);
			Assets.loadTex(path);
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
	
	private static Label buildLabel(final Frame frame, final JSONObject json) {
		Label label = new Label(frame);
		
		FreeTypeFontParameter fontParam = new FreeTypeFontParameter();
		
		if(json.containsKey(FONT_COLOR_R)){
			fontParam.color.r = ((Number)json.get(FONT_COLOR_R)).intValue();
		}
		else{
			fontParam.color.r = Assets.DEFAULT_FONT_COLOR_R;
		}
		
		if(json.containsKey(FONT_COLOR_G)){
			fontParam.color.g = ((Number)json.get(FONT_COLOR_G)).intValue();
		}
		else{
			fontParam.color.g = Assets.DEFAULT_FONT_COLOR_G;
		}
		
		if(json.containsKey(FONT_COLOR_B)){
			fontParam.color.b = ((Number)json.get(FONT_COLOR_B)).intValue();
		}		
		else{
			fontParam.color.b = Assets.DEFAULT_FONT_COLOR_B;
		}
		
		if(json.containsKey(FONT_COLOR_A)){
			fontParam.color.a = ((Number)json.get(FONT_COLOR_A)).intValue();
		}
		else{
			fontParam.color.a = Assets.DEFAULT_FONT_COLOR_A;
		}
		
		if(json.containsKey(FONT_COLOR_SIZE)){
			fontParam.size = ((Number)json.get(FONT_COLOR_SIZE)).intValue();
		}
		else{
			fontParam.size = Assets.DEFAULT_FONT_SIZE;
		}
		
		if(json.containsKey(FONT)){
			String path = (String)json.get(FONT);
			Assets.loadFont(path);
			label.setFont(path, fontParam);
		}
		
		if(json.containsKey(TEXT)){
			label.setText((String)json.get(TEXT));
		}

		return label;
	}
	
	private static Button buildButton(final Frame frame, final JSONObject json) {
		Button button = new Button(frame);
		
		if(json.containsKey(FONT)){
			String path = (String)json.get(FONT);
			Assets.loadFont(path);
			button.setFont(path);
		}
		
		if(json.containsKey(TEXT_ALIGNMENT)){
			String ali = (String)json.get(TEXT_ALIGNMENT);
			
			if(ali.equals("center")){
				button.setTextAlignement(HAlignment.CENTER);
			}
			else if(ali.equals("left")){
				button.setTextAlignement(HAlignment.LEFT);
			}
			else if(ali.equals("right")){
				button.setTextAlignement(HAlignment.RIGHT);
			}
		}
		
		if(json.containsKey(TEXT)){
			button.setText((String)json.get(TEXT));
		}
		
		if(json.containsKey(BUTTON_HIGHLIGHTED)){
			button.setHighlighted((Boolean)json.get(BUTTON_HIGHLIGHTED));
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
	
	private static Widget buildMinimap(Frame frame, JSONObject json) {
		Minimap map = new Minimap(frame);
		
		if(json.containsKey(MINIMAP_CENTRING)){
			map.setCentring((Boolean)json.get(MINIMAP_CENTRING));
		}
		
		if(json.containsKey(MINIMAP_CAMERA_FRAME)){
			String path = (String)json.get(MINIMAP_CAMERA_FRAME);
			Assets.loadTex(path);
			map.setTexCameraFrame(path);
		}
		
		return map;
	}
}