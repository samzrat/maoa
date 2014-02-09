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
		
		int gridXCount = (int) Math.floor(Math.abs((double)fieldWidth/(double)gridDimension.getFirst()));
		int gridYCount = (int) Math.floor(Math.abs((double)fieldHeight/gridDimension.getSecond()));
		
		grids = new Grid[gridXCount][gridYCount];
		
		for(int i=0; i<gridXCount; i++)
			for(int j=0; j<gridYCount; j++)
			grids[i][j] = new Grid(streamWindows);
	}
	
	
	public void processInputEvent(PositionEvent posEvent)
	{
		for(int i=0; i<grids.length; i++)
			for(int j=0; j<grids[i].length; j++)
				grids[i][j].processInputEvent(posEvent);
	}

}
