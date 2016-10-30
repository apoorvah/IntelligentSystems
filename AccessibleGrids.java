package mars;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class AccessibleGrids {
	
	protected final Map<Integer, Grid> gridsByIndex;
	protected final Planet planet;
	
	public AccessibleGrids() {
		gridsByIndex = new LinkedHashMap<Integer, Grid>();
		planet = new Planet();
	}
	
	public Map<Integer, Grid> getGridsByIndex() {
		return gridsByIndex;
	}

	public void createAccessibleGrids(){
		for(int row=0; row< Constants.WIDTH; row++){
			for(int column =0; column < Constants.WIDTH; column++){
				if(planet.isAccessible(row, column)){
					Grid currentGrid = new Grid(row, column);
					gridsByIndex.put(currentGrid.getIndex(), currentGrid);
				}
			}
		}
	}
	
	public Set<Grid> getAllAccessibleGrids() {
		return new LinkedHashSet<Grid>(gridsByIndex.values());
	}
	
	public void computeShortestDistances() {
		
		for(Grid grid: gridsByIndex.values()){
			//grid.shortestDistanceByGrid.clear();
			for (Movement movement : Movement.values()) {
				int newCurrentGridRow = grid.getRow() + movement.deltaRow;
				int newCurrentGridColumn = grid.getColumn() + movement.deltaColumn;
				if (0 <= newCurrentGridRow && newCurrentGridRow < Constants.WIDTH && 0 <= newCurrentGridColumn && newCurrentGridColumn < Constants.WIDTH &&
						planet.isAccessible(newCurrentGridRow, newCurrentGridColumn))
					grid.shortestDistanceByGrid.put(gridsByIndex.get((newCurrentGridRow*Constants.WIDTH) + newCurrentGridColumn), Constants.STEP_COST);
			}
		}
		
		for(Grid gridK: gridsByIndex.values()){
			for (Grid gridI : gridsByIndex.values()){
				for (Grid gridJ : gridsByIndex.values()) {
					int distanceIJ = gridI.getShortestDistanceTo(gridJ);
					int distanceIK = gridI.getShortestDistanceTo(gridK);
					int distanceKJ = gridK.getShortestDistanceTo(gridJ);
					if (distanceIK != Integer.MAX_VALUE && distanceKJ != Integer.MAX_VALUE)
						distanceIJ = Math.min(distanceIJ, distanceIK + distanceKJ);
					gridI.shortestDistanceByGrid.put(gridJ, distanceIJ);
				}
			}
			
		}
	}

}
