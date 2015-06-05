package com.owlengine.ui;

final class Alignment {
	
	public static final int ABSOLUTE = 0;
	public static final int DOWN_LEFT = 0;
	public static final int DOWN = 1;
	public static final int DOWN_RIGHT = 2;
	
	public static final int CENTER_LEFT = 3;
	public static final int CENTER = 4;
	public static final int CENTER_RIGHT = 5;
	
	public static final int UP_LEFT = 6;
	public static final int UP = 7;
	public static final int UP_RIGHT = 8;
	
	public static int toInt(final String value) {
		if(value.equalsIgnoreCase("absolute")){
			return ABSOLUTE;
		}
		else if(value.equalsIgnoreCase("down_left")){
			return DOWN_LEFT;
		}
		else if(value.equalsIgnoreCase("down")){
			return DOWN;
		}
		else if(value.equalsIgnoreCase("down_right")){
			return DOWN_RIGHT;
		}
		else if(value.equalsIgnoreCase("center_left")){
			return CENTER_LEFT;
		}
		else if(value.equalsIgnoreCase("center")){
			return CENTER;
		}
		else if(value.equalsIgnoreCase("center_right")){
			return CENTER_RIGHT;
		}
		else if(value.equalsIgnoreCase("up_left")){
			return UP_LEFT;
		}
		else if(value.equalsIgnoreCase("up")){
			return UP;
		}
		else if(value.equalsIgnoreCase("up_right")){
			return UP_RIGHT;
		}
		else{
			return ABSOLUTE;
		}
	}
}
