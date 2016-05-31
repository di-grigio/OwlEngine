package com.owlengine.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.owlengine.input.UserInput;
import com.owlengine.interfaces.Draw;
import com.owlengine.interfaces.Script;
import com.owlengine.resources.Assets;

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
	private Script scriptOnLoad;
	private Script scriptOnAction;
	private Script scriptOnActionSecond;
	
	public Widget(Frame frame) {
		this.id = ++ID;
		this.parent = frame;
		
		this.alignment = Alignment.ABSOLUTE;
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

	public final void setVisibible(final boolean visible) {
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
	public final void setScriptOnLoad(final Script script){
		this.scriptOnLoad = script;
	}
	
	public final void setScriptOnAction(final Script script){
		this.scriptOnAction = script;
	}
	
	public final void setScriptOnActionSecond(final Script script){
		this.scriptOnActionSecond = script;
	}

	protected final Script eventOnLoad(){
		return scriptOnLoad;
	}
	
	protected final void setEventOnLoad(final Script script) {
		this.scriptOnLoad = script;
	}

	protected final Script eventOnAction(){
		return scriptOnAction;
	}
	
	protected final void setEventAction(final Script script) {
		this.scriptOnAction = script;
	}
	
	protected final Script eventOnActionSecond(){
		return scriptOnActionSecond;
	}
	
	protected final void setEventActionSecond(final Script script){
		this.scriptOnActionSecond = script;
	}

	// Script executing
	protected final void onload(){
		if(scriptOnLoad != null){
			scriptOnLoad.execute();
		}
	}
	
	protected final void leftClick() {
		if(scriptOnAction != null){
			scriptOnAction.execute();
		}
	}

	protected final void rightClick() {
		if(scriptOnActionSecond != null){
			scriptOnActionSecond.execute();
		}
	}
	
	// Graphics
	public final void setTexNormal(final String key) {
		this.texNormal = Assets.getTex(key);
	}
	
	protected final void setTexNormal(final Texture tex){
		this.texNormal = tex;
	}
	
	public final void setTexSelected(final String key){
		this.texSelected = Assets.getTex(key);
	}

	protected final void setTexDisabled(final String key) {
		this.texDisabled = Assets.getTex(key);
	}
	
	protected final void setFont(final String key){
		this.font = Assets.getFont(key);
	}

	public void setFont(String path, FreeTypeFontParameter fontParam) {
		this.font = Assets.getFont(path, fontParam);
	}
}