package com.owlengine.ui;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.interfaces.Draw;
import com.owlengine.interfaces.Script;
import com.owlengine.tools.Log;

public final class FrameLayer implements Draw {

	// data
	private Widget [] widgets;
	
	public void build(final Frame frame, final UI ui, final JSONArray data, final Script script) {
		try {
			widgets = new Widget[data.size()];
			
			// build process
			for(int i = 0; i < data.size(); ++i){
				widgets[i] = WidgetBuilder.build(frame, (JSONObject)data.get(i), script);
				ui.registerWidget(widgets[i]);
			}
		}
		catch(ClassCastException e){
			Log.err("Error #6: JSON parse error");
		}
	}

	protected Widget selectWidget() {
		if(widgets == null){
			return null;
		}
		else{
			for(Widget widget: widgets){
				if(widget != null && widget.visible() && widget.inBound()){
					return widget;
				}
			}
			
			return null;
		}
	}

	public void updatePosition() {
		if(widgets != null){
			for(int i = 0; i < widgets.length; ++i){
				widgets[i].updatePosition();
			}
		}
	}

	@Override
	public void draw(final SpriteBatch batch) {
		if(widgets != null){
			for(int i = 0; i < widgets.length; ++i){
				if(widgets[i].visible()){
					widgets[i].draw(batch);
				}
			}
		}
	}
}
