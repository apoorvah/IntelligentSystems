package mars;

import search.AStarFunction;
import search.BestFirstFrontier;
import search.GoalTest;
import search.Node;
import search.TreeSearch;

public class AStarTS {
	
	public static void main(String[] args) {
		System.out.println("This is a A-star tree search on Mars robot");
		System.out.println();
		
		Grid startGrid = new Grid(4,4);
		RobotState initialConfiguration = new RobotState(startGrid);
		GoalTest goalTest = new RobotGoalTest();
		AStarFunction objectiveFunction = new AStarFunction(new UnvisitedGridsEvaluator());
		BestFirstFrontier frontier = new BestFirstFrontier(objectiveFunction);
		TreeSearch asts = new TreeSearch(frontier);
		Node solution = asts.search(new Node(null, null, initialConfiguration), goalTest);
		new RobotPathPrinter().printSolution(solution);
		System.out.println("Number of nodes generated : "+ asts.getNodesGeneratedCount());
		System.out.println("Max frontier size: "+ frontier.getMaxSizeOfFrontier());
	}

}
