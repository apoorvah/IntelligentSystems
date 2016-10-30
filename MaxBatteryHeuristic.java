package mars;

import java.util.Map;
import java.util.Map.Entry;

import search.Node;
import search.NodeFunction;

public class MaxBatteryHeuristic implements NodeFunction {
	protected final Map<Integer, Grid> gridsByIndex;
	
	public MaxBatteryHeuristic(Map<Integer, Grid> gridsByIndex) {
		this.gridsByIndex = gridsByIndex;
	}

	@Override
	public int computeNodeValue(Node node) {
		RobotState state = (RobotState) node.state;
		int distance = 0;
		
		for(Entry<Grid, Integer> gridDistance : gridsByIndex.get(state.currentGrid.index).shortestDistanceByGrid.entrySet()){
			if((!state.gridsVisited.contains(gridDistance.getKey())) && 
					distance < gridsByIndex.get(state.currentGrid.index).getShortestDistanceTo(gridDistance.getKey()))
			{
				distance = gridsByIndex.get(state.currentGrid.index).getShortestDistanceTo(gridDistance.getKey());
			}
		}
		return distance;
	}

}
