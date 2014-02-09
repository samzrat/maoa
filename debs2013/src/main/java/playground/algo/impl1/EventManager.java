package playground.algo.impl1;


import java.awt.Point;
import java.util.*;


public class EventManager {
		
	private final HashMap<String, ArrayList<Field>> playerMap;
	
	public EventManager(String[] players, Point[] fieldDimensions, ArrayList<Pair1<Integer, Integer>> gridDimensionsList, int[] streamWindows )
	{
		playerMap = new HashMap<String, ArrayList<Field>>(players.length);
		for(int i=0; i<players.length; i++)
		{
			ArrayList<Field> fields = new ArrayList<Field>(gridDimensionsList.size());
			for(int i1=0; i1<gridDimensionsList.size(); i1++)
				fields.add(new Field(fieldDimensions, gridDimensionsList.get(i1), streamWindows));
			
			playerMap.put(players[i], fields);		
		}	
	}
	
	
	public static void main( String[] args )
    {
        System.out.println( "Booting Event Manager" );
        
        ArrayList<Pair1<Integer, Integer>> gridDimensions = createGridDimensions();
        int[] streamWindows = new int[]{1/*, 5, 10, 40*/};
        String[] players = new String[]{"Sameer", "Ratul"};
        Point[] fieldDimensions = new Point[]{new Point(0, 33965), new Point(52477, 33941), new Point(-50, -33960), new Point(52489, -33939)};
        
        EventManager eventManager = new EventManager(players, fieldDimensions, gridDimensions, streamWindows );
        
        PositionEvent positionEvent = new PositionEvent("Ratul", 457, 150, 15, 0, 0, 0, 0);
        eventManager.processEvent(positionEvent);
    }
	
	private void processEvent(PositionEvent positionEvent)
	{
		ArrayList<Field> fields = playerMap.get(positionEvent.sid);
		
		if(fields != null)
			for(Field field: fields)
				field.processInputEvent(positionEvent);
		
	}
	
	private static ArrayList<Pair1<Integer, Integer>> createGridDimensions()
	{
		ArrayList<Pair1<Integer, Integer>> gridDimensions = new ArrayList<Pair1<Integer, Integer>>(4);
        /*gridDimensions.add(new Pair1<Integer, Integer>(8, 13));
        gridDimensions.add(new Pair1<Integer, Integer>(16, 25));
        gridDimensions.add(new Pair1<Integer, Integer>(32, 50));
        gridDimensions.add(new Pair1<Integer, Integer>(64, 100));*/
		gridDimensions.add(new Pair1<Integer, Integer>(10000, 10000));
        
        return gridDimensions;
	}

}
