package mars;

import java.util.Set;

import search.GoalTest;
import search.State;

public class MaxBatteryGoalState implements GoalTest{
	
	protected final Set<Grid> gridsToVisit;
	
	public MaxBatteryGoalState(Set<Grid> gridsToVisit) {
		this.gridsToVisit = gridsToVisit;
	}
	
	public boolean isGoal(State state) {
		RobotState robotState = (RobotState)state;
		return gridsToVisit.equals(robotState.gridsVisited);
	}

}
