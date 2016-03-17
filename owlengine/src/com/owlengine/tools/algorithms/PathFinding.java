package com.owlengine.tools.algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public final class PathFinding {

	public static LinkedList<Point> buildPath(final LocationMap map, final int x, final int y, final int toX, final int toY) {
		if(x == toX && y == toY){
			return null;
		}
		else{
			if(map.getMovement(toX, toY)){
				LinkedList<Point> path = search(map, x, y, toX, toY);
				
				if(path != null && path.size() > 0){
					return path;
				}
				else{
					return null;
				}
			}
			else{ 
				return null;
			}
		}
	}

	private static LinkedList<Point> search(final LocationMap map, final int x, final int y, final int toX, final int toY) {
		ArrayList<Cell> openList = new ArrayList<Cell>();
		ArrayList<Cell> closedList = new ArrayList<Cell>();
		
		Cell endCell = new Cell(toX, toY);
		Cell startCell = new Cell(x, y);
		startCell.g = 0;
		startCell.h = startCell.h(endCell);
		startCell.f = startCell.g + startCell.h;
		
		// start algorithm A*
		Cell cell = startCell;
		
		while(!cell.compare(endCell)){
			final int startX = Math.max(0, cell.x - 1);
			final int endX = Math.min(map.getSizeX() - 1, cell.x + 1);
			
			final int startY = Math.max(0, cell.y - 1);
			final int endY = Math.min(map.getSizeY() - 1, cell.y + 1);
			
			for(int i = startX; i <= endX; ++i){
				for(int j = startY; j <= endY; ++j){					
					Cell test = new Cell(i, j);
					
					if(test.compare(endCell)){
						test = endCell;
					}
					
					if(test.compare(cell) || !map.getMovement(test.x, test.y)){
						continue;
					}
					
					int cost = Cell.STAIGHT_COST;
					if(!((cell.x == test.x) || (cell.y == test.y))){
						cost = Cell.DIAGONAL_COST;
					}
					
					int g = cell.g + cost * test.costMultipiller;
					int h = test.h;
					int f = g + h;
					
					if(isOpen(closedList, test) || isClosed(openList, test)){
						if(test.f > f){
							test.setValues(g, h, f);
							test.parent = cell;
						}
					}
					else{
						test.setValues(g, h, f);
						test.parent = cell;
						openList.add(test);
					}
				}
			}
			
			closedList.add(cell);
			
			if(openList.size() == 0){
				return null;
			}
			
			Collections.sort(openList, new Comparator<Cell>() {
				@Override
				public int compare(Cell o1, Cell o2) {
					if(o1.f < o2.f){
				    	return -1;
				    }
				    else{
				    	if(o1.f > o2.f) 
				    		return 1;
				    	else 
				    		return 0;
				    }
				}
			});
			
			cell = openList.get(0);
			openList.remove(0);
		}
		
		return buildPath(startCell, endCell);
	}

	private static LinkedList<Point> buildPath(Cell startCell, Cell endCell) {
		LinkedList<Point> path = new LinkedList<Point>();
		
		Cell node = endCell;
		
		path.add(new Point(endCell.x, endCell.y));
		
		while(node != null && !node.compare(startCell)){
			node = node.parent;
			
			if(!node.compare(startCell)){
				path.add(new Point(node.x, node.y));
			}
		}
		
		return path;
	}

	private static boolean isOpen(ArrayList<Cell> openList, Cell node){
		for(Cell item: openList){
			if(item.compare(node)){
				return true;
			}
		}
		return false;
	}
	
	private static boolean isClosed(ArrayList<Cell> closedList, Cell node){
		for(Cell item: closedList){
			if(item.compare(node)){
				return true;
			}
		}
		return false;
	}
}

final class Cell {
	// constants
	protected static final int STAIGHT_COST = 10;
	protected static final int DIAGONAL_COST = 14; // may be 14 if diagCost != strightCost
	protected int costMultipiller = 1;
	
	// cell data
	protected final int x;
	protected final int y;
	
	protected int f;
	protected int h;
	protected int g;
	
	protected Cell parent;
	
	protected Cell(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	// diagonal heuristic
	protected int h(Cell endNode) {
		int dx = Math.abs(this.x - endNode.x);
		int dy = Math.abs(this.y - endNode.y);
		return DIAGONAL_COST * Math.max(dx, dy);
	}
	
	protected void g(){
		this.g = STAIGHT_COST;
	}
	
	protected void setValues(int g, int h, int f){
		this.g = g;
		this.h = h;
		this.f = f;
	}

	protected boolean compare(Cell node){
		if(this.x == node.x && this.y == node.y){
			return true;
		}
		else{
			return false;
		}
	}
}