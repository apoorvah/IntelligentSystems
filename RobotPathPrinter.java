package mars;

import search.Action;
import search.Printing;
import search.State;

public class RobotPathPrinter extends Printing{
	
	public void print(Action action) {
		System.out.print("move ");
		System.out.print(((Movement)action).name());
	}
	
	public void print(State state) {
		RobotState robotState = (RobotState) state;
		System.out.println("Number of grids Visited = "+ robotState.gridsVisited.size());
		for(Grid visited : robotState.gridsVisited){
			System.out.println("Row = "+ visited.getRow() + "   Column = " + visited.getColumn());
		}
	}


}
