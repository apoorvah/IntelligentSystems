package mars;

import search.AStarFunction;
import search.BestFirstFrontier;
import search.GoalTest;
import search.GraphSearch;
import search.Node;

public class AStarGSMaxBattery {
	
	public static void main(String[] args) {
		System.out.println("This is a A-star graph search on Mars robot for MAX battery life");
		System.out.println();
		
		AccessibleGrids accessibleGrids = SetUp.getAccessibleGrids();
		Grid startGrid = new Grid(4,4);
		
		RobotState initialConfiguration = new RobotState(startGrid);
		GoalTest goalTest = new MaxBatteryGoalState(accessibleGrids.getAllAccessibleGrids());
		AStarFunction objectiveFunction = new AStarFunction(new MaxBatteryHeuristic(accessibleGrids.gridsByIndex));
		BestFirstFrontier frontier = new BestFirstFrontier(objectiveFunction);
		GraphSearch asgs = new GraphSearch(frontier);
		Node solution = asgs.search(new Node(null, null, initialConfiguration), goalTest);
		new RobotPathPrinter().printSolution(solution);
		RobotState solutionState = (RobotState)solution.state;
		System.out.println("Max battery life = " + (Constants.MAX_BATTERY-solutionState.remainingBattery));
		System.out.println("Number of nodes generated : "+ asgs.getNodesGeneratedCount());
		System.out.println("Max frontier size: "+ frontier.getMaxSizeOfFrontier());

	}

}
