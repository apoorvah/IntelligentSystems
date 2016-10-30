package mars;

import search.AStarFunction;
import search.BestFirstFrontier;
import search.GoalTest;
import search.GraphSearch;
import search.Node;

public class AStarGS {

	public static void main(String[] args) {
		System.out.println("This is a A-star graph search on Mars robot");
		System.out.println();
		
		Grid startGrid = new Grid(4,4);
		RobotState initialConfiguration = new RobotState(startGrid);
		GoalTest goalTest = new RobotGoalTest();
		AStarFunction objectiveFunction = new AStarFunction(new UnvisitedGridsEvaluator());
		BestFirstFrontier frontier = new BestFirstFrontier(objectiveFunction);
		GraphSearch asgs = new GraphSearch(frontier);
		Node solution = asgs.search(new Node(null, null, initialConfiguration), goalTest);
		new RobotPathPrinter().printSolution(solution);
		System.out.println("Number of nodes generated : "+ asgs.getNodesGeneratedCount());
		System.out.println("Max frontier size: "+ frontier.getMaxSizeOfFrontier());
	}

}
