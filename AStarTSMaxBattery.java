package mars;

import search.AStarFunction;
import search.BestFirstFrontier;
import search.GoalTest;
import search.Node;
import search.TreeSearch;

public class AStarTSMaxBattery {

	public static void main(String[] args) {
		System.out.println("This is a A-star tree search on Mars robot for MAX battery life");
		System.out.println();
		
		AccessibleGrids accessibleGrids = SetUp.getAccessibleGrids();
		Grid startGrid = new Grid(4,4);
		
		RobotState initialConfiguration = new RobotState(startGrid);
		GoalTest goalTest = new MaxBatteryGoalState(accessibleGrids.getAllAccessibleGrids());
		AStarFunction objectiveFunction = new AStarFunction(new MaxBatteryHeuristic(accessibleGrids.gridsByIndex));
		BestFirstFrontier frontier = new BestFirstFrontier(objectiveFunction);
		TreeSearch asts = new TreeSearch(frontier);
		Node solution = asts.search(new Node(null, null, initialConfiguration), goalTest);
		new RobotPathPrinter().printSolution(solution);
		RobotState solutionState = (RobotState)solution.state;
		System.out.println("Max battery life = " + (Constants.MAX_BATTERY-solutionState.remainingBattery));
		System.out.println("Number of nodes generated : "+ asts.getNodesGeneratedCount());
		System.out.println("Max frontier size: "+ frontier.getMaxSizeOfFrontier());

	}
}
