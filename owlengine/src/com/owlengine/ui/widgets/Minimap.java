package com.owlengine.ui.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.resources.Assets;
import com.owlengine.ui.Frame;

public class Minimap extends Image {

	// x*y map nodes are show at minimap screen
	private int mapPartResolutionX;
	private int mapPartResolutionY;
	
	// x*y camera box draw
	private int mapCameraResolutionNodesX;
	private int mapCameraResolutionNodesY;

	// (x,y) current position, must be updated
	private int cameraNodePosX;
	private int cameraNodePosY;

	//
	private Texture texCameraFrame;
	
	//
	private boolean centring;
	
	public Minimap(Frame frame) {
		super(frame);
	}
	
	public final void setMinimapPart(int sizeX, int sizeY){
		this.mapPartResolutionX = sizeX;
		this.mapPartResolutionY = sizeY;
	}
	
	public final void setCameraResolution(int sizeX, int sizeY){
		this.mapCameraResolutionNodesX = sizeX;
		this.mapCameraResolutionNodesY = sizeY;
	}
	
	public final void updateCameraPosition(int x, int y){
		this.cameraNodePosX = x;
		this.cameraNodePosY = y;
	}
	
	public void setMinimapTexture(Texture texture) {
		this.setTexNormal(texture);
	}

	public final void setCentring(final boolean centring) {
		this.centring = centring;
	}
	
	public final void setTexCameraFrame(final String path){
		texCameraFrame = Assets.getTex(path);
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		// draw part of map
		if(centring){
			batch.draw(texNormal, 
					x, y, 
					cameraNodePosX - mapPartResolutionX/2, 
					texNormal.getHeight() - cameraNodePosY - mapPartResolutionY/2,
					mapPartResolutionX,
					mapPartResolutionY
			);
		}
		else{
			batch.draw(texNormal, x, y, cameraNodePosX, texNormal.getHeight() - cameraNodePosY, mapPartResolutionX, mapPartResolutionY);
		}
		
		if(texCameraFrame != null){
			batch.draw(texCameraFrame, 
					x + mapPartResolutionX/2 - mapCameraResolutionNodesX/2, 
					y + mapPartResolutionY/2 - mapCameraResolutionNodesY/2, 
					mapCameraResolutionNodesX,
					mapCameraResolutionNodesY
			);
		}
	}
}
