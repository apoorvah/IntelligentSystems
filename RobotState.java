package mars;

import java.util.LinkedHashSet;
import java.util.Set;

import search.Action;
import search.State;

public class RobotState implements State {
	
	protected final Set<Grid> gridsVisited;
	protected final Grid currentGrid;
	protected final int remainingBattery ;
	protected final Planet planet = new Planet();
	
	
	public RobotState(Grid startGrid) {
		this.gridsVisited = new LinkedHashSet<Grid>();
		gridsVisited.add(startGrid);
		this.currentGrid = startGrid;
		this.remainingBattery = Constants.MAX_BATTERY;
	}
	
	public RobotState(Set<Grid> gridsVisited, Grid currentGrid, int remainingBattery) {
		this.gridsVisited = gridsVisited;
		this.currentGrid = currentGrid;
		this.remainingBattery = remainingBattery;
	}

	@Override
	public Set<Action> getApplicableActions() {
		Set<Action> actions = new LinkedHashSet<Action>();
		for (Movement movement : Movement.values()) {
			int newCurrentGridRow = currentGrid.getRow() + movement.deltaRow;
			int newCurrentGridColum = currentGrid.getColumn() + movement.deltaColumn;
			int updatedRemainingBattery = remainingBattery - 1;
			if (0 <= newCurrentGridRow && newCurrentGridRow < Constants.WIDTH && 0 <= newCurrentGridColum && newCurrentGridColum < Constants.WIDTH &&
					planet.isAccessible(newCurrentGridRow, newCurrentGridColum) && updatedRemainingBattery >=0)
				actions.add(movement);
		}
		return actions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currentGrid == null) ? 0 : currentGrid.hashCode());
		result = prime * result + ((gridsVisited == null) ? 0 : gridsVisited.hashCode());
		result = prime * result + remainingBattery;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RobotState other = (RobotState) obj;
		if (currentGrid == null) {
			if (other.currentGrid != null)
				return false;
		} else if (!currentGrid.equals(other.currentGrid))
			return false;
		if (gridsVisited == null) {
			if (other.gridsVisited != null)
				return false;
		} else if (!gridsVisited.equals(other.gridsVisited))
			return false;
		if (remainingBattery != other.remainingBattery)
			return false;
		return true;
	}

	@Override
	public State getActionResult(Action action) {
		Movement movement=(Movement)action;
		int newCurrentGridRow = currentGrid.getRow() + movement.deltaRow;
		int newCurrentGridColumn = currentGrid.getColumn() + movement.deltaColumn;
		Grid newCurrentGrid = new Grid(newCurrentGridRow, newCurrentGridColumn);
		Set<Grid> newGridsVisited = new LinkedHashSet<Grid>(gridsVisited);
		newGridsVisited.add(newCurrentGrid);
		int updatedRemainingBattery = remainingBattery - 1;
		return new RobotState(newGridsVisited, newCurrentGrid, updatedRemainingBattery);
	}

}
