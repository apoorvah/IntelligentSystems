package mars;

import search.GoalTest;
import search.State;

public class RobotGoalTest implements GoalTest {
	@Override
	public boolean isGoal(State state) {
		RobotState robotState = (RobotState)state;
		return robotState.remainingBattery==0;
	}

}
