package playground.algo.impl1;

import java.awt.Point;


public class Field {
	
	Pair1<Integer, Integer> gridDimension;
	int[] streamWindows;
	
	Grid[][] grids;
	
	public Field(Point[] fieldDimensions, Pair1<Integer, Integer> gridDimension, int[] streamWindows)
	{
		int fieldWidth = fieldDimensions[1].x - fieldDimensions[0].x;
		int fieldHeight = fieldDimensions[2].y - fieldDimensions[0].y;
		
		double gridWidth = (double)fieldWidth/(double)gridDimension.getFirst();
		
		int gridCount = (int) Math.abs(Math.ceil((double)fieldWidth/gridDimension.getFirst() * (double)fieldHeight/gridDimension.getSecond()));  
		
		grids = new Grid[gridDimension.getFirst()][gridDimension.getSecond()];
		
		for(int i=0; i<gridDimension.getFirst(); i++)
			for(int j=0; j<gridDimension.getFirst(); j++)
			grids[i][j] = new Grid(streamWindows);
	}
	
	
	public void processInputEvent(PositionEvent posEvent)
	{
		for(int i=0; i<gridDimension.getFirst(); i++)
			for(int j=0; j<gridDimension.getFirst(); j++)
				grids[i][j].processInputEvent(posEvent);
	}

}
