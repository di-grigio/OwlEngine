package com.owlengine.tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import javax.imageio.ImageIO;

public final class MapGenerator {
	
	public static int [][] voronoyMap(long seed, int sizeX, int sizeY, int noize, int colors){
		// Voronoy magic
		Random rand = new Random(seed);
		
		int areas = (sizeX*sizeY) / noize;
		int [][] result = new int[sizeX][sizeY];
		int [][] layers = new int[areas][3];
		int freeDots = 0;
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){
				result[i][j] = -1;
				++freeDots;
			}
		}
		
		for(int i = 0; i < areas; ++i){
			layers[i][0] = rand.nextInt(sizeX);
			layers[i][1] = rand.nextInt(sizeY);
			layers[i][2] = Tools.rand(rand, 0, colors - 1);
			
			if(result[layers[i][0]][layers[i][1]] == -1){
				result[layers[i][0]][layers[i][1]] = layers[i][2];
				--freeDots;
			}
		}
		
		ArrayList<HashSet<int[]>> sets = new ArrayList<HashSet<int[]>>();
		
		for(int i = 0; i < areas; ++i){
			HashSet<int[]> lines = new HashSet<int[]>();
			lines.add(layers[i]);
			sets.add(lines);
		}
		
		while(freeDots != 0){
			ArrayList<HashSet<int[]>> newSets = new ArrayList<HashSet<int[]>>();
			
			for(HashSet<int[]> set: sets){
				HashSet<int[]> newSet = new HashSet<int[]>();
				
				for(int [] dots: set){
					int x1, x2 , x = dots[0];
					int y = dots[1];
					int tmp = dots[2];
					
					if(x == 0){ 
						x1 = sizeX - 1;
					}
					else{
						x1 = x - 1;
					}
					
					if(x == sizeX - 1){
						x2 = 0;
					}
					else{ 
						x2 = x + 1;
					}
					
					if(result[x1][y] == -1){
						newSet.add(new int[]{x1, y, tmp});
						--freeDots;
						result[x1][y] = tmp;
					}
					
					if(result[x2][y]==-1){
						newSet.add(new int[]{x2, y, tmp});
						--freeDots;
						result[x2][y] = tmp;
					}
					
					
					if(y != 0){
						if(result[x][y-1] == -1){
							newSet.add(new int[]{x, y - 1, tmp});
							--freeDots;
							result[x][y-1] = tmp;
						}
					}
					
					if(y != sizeY - 1){
						if(result[x][y+1] == -1){
							newSet.add(new int[]{x, y + 1, tmp});
							--freeDots;
							result[x][y+1] = tmp;
						}
					}
				}
				
				newSets.add(newSet);
			}
			
			sets = newSets;
		}
		
		return result;
	}

	public static final void logMap(int [][] map, int sizeX, int sizeY, int colors){		
		BufferedImage img = new BufferedImage(sizeX, sizeY, BufferedImage.TYPE_INT_RGB);
		int rgb = 0;
		
		int colorCoeficent =  256 / colors;
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){
				rgb = (map[i][j]*colorCoeficent << 16)  + (map[i][j]*colorCoeficent << 8) + map[i][j]*colorCoeficent;
				img.setRGB(i, j, rgb);		
			}
		}
		
		File outputfile = new File("genMap.png");
		
		try {
			ImageIO.write(img, "png", outputfile);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
