package com.owlengine.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.owlengine.input.UserInput;
import com.owlengine.interfaces.Draw;
import com.owlengine.interfaces.Script;
import com.owlengine.resources.Resources;

abstract public class Widget implements Draw {

	// header data
	private static int ID;
	private int id;
	private String title;
	
	// parent Frame
	private Frame parent;

	// position
	protected int alignment;
	protected int posX;
	protected int posY;
	protected int sizeX;
	protected int sizeY;
	
	// draw position
	protected int x;
	protected int y;
	
	// graphics
	protected Texture texNormal;
	protected Texture texSelected;
	protected Texture texDisabled;
	protected BitmapFont font;
	
	// flags
	protected boolean visible;
	protected boolean disabled;
	protected boolean selected;
	
	// scripts methods
	private Script script;
	private String eventOnLoad;
	private String eventOnAction;
	private String eventOnActionSecond;
	
	public Widget(Frame frame) {
		this.id = ++ID;
		this.parent = frame;
		
		this.alignment = Alignment.ABSOLUTE;
		
		this.texNormal = Resources.tex(null);
		this.texSelected = Resources.tex(null);
		this.texDisabled = Resources.tex(null);
		
		this.font = Resources.font(null);
	}
	
	//
	public final int id(){
		return id;
	}
	
	public final Frame parent(){
		return parent;
	}
	
	public final String title(){
		return title;
	}
	
	public final void setTitle(final String title) {
		this.title = title;
	}

	//
	protected final int alignment(){
		return alignment;
	}
	
	public final void setAlignment(final int alignment) {
		this.alignment = alignment;
		updatePosition();
	}
	
	protected final void setPosX(final int posX){
		this.posX = posX;
		updatePosition();
	}
	
	protected final void setPosY(final int posY){
		this.posY = posY;
		updatePosition();
	}
	
	protected final void setSizeX(final int sizeX) {
		this.sizeX = sizeX;
		updatePosition();
	}

	protected final void setSizeY(final int sizeY) {
		this.sizeY = sizeY;
		updatePosition();
	}
	
	protected final void updatePosition(){
		int w = parent.sizeX();
		int h = parent.sizeY();
		
		switch (alignment) {
		
			case Alignment.DOWN_LEFT:
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
				
			default:
				setPos(posX, posY);
				break;
		}
	}
	
	protected void setPos(final int x, final int y) {
		this.x = x + parent.posX();
		this.y = y + parent.posY();
	}

	protected final boolean inBound() {
		return (x <= UserInput.mouseX() && UserInput.mouseX() <= x + sizeX &&
				y <= UserInput.mouseY() && UserInput.mouseY() <= y + sizeY);
	}
	
	//
	protected final boolean visible() {
		return visible;
	}

	protected final void setVisibible(final boolean visible) {
		this.visible = visible;
	}
	
	//
	protected final boolean disabled() {
		return disabled;
	}
	
	protected final void setDisabled(final boolean disabled){
		this.disabled = disabled;
	}
	
	//
	protected final boolean selected(){
		return selected;
	}
	
	protected final void setSelected(final boolean selected){
		this.selected = selected;
	}
	
	// Events
	protected final void setScript(final Script script){
		this.script = script;
	}
	
	protected final String eventOnLoad(){
		return eventOnLoad;
	}
	
	protected final void setEventOnLoad(final String string) {
		this.eventOnLoad = string;
	}

	protected final String eventOnAction(){
		return eventOnAction;
	}
	
	protected final void setEventAction(final String string) {
		this.eventOnAction = string;
	}
	
	protected final String eventOnActionSecond(){
		return eventOnActionSecond;
	}
	
	protected final void setEventActionSecond(final String string){
		this.eventOnActionSecond = string;
	}

	// Script executing
	protected final void onload(){
		if(script != null && eventOnLoad != null){
			script.execute(eventOnLoad);
		}
	}
	
	protected final void leftClick() {
		if(script != null && eventOnAction != null){
			script.execute(eventOnAction);
		}
	}

	protected final void rightClick() {
		if(script != null && eventOnActionSecond != null){
			script.execute(eventOnActionSecond);
		}
	}
	
	// Graphics
	protected final void setTexNormal(final String key) {
		this.texNormal = Resources.tex(key);
	}
	
	protected final void setTexSelected(final String key){
		this.texSelected = Resources.tex(key);
	}

	protected final void setTexDisabled(final String key) {
		this.texDisabled = Resources.tex(key);
	}
	
	protected final void setFont(final String key){
		this.font = Resources.font(key);
	}
}