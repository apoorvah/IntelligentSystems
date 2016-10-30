package mars;

import search.Node;
import search.NodeFunction;

public class UnvisitedGridsEvaluator implements NodeFunction{
	
	@Override
	public int computeNodeValue(Node node) {
		RobotState state = (RobotState) node.state;
		return Constants.MAX_BATTERY - state.gridsVisited.size();
	}

}
