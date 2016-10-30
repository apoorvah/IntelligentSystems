package mars;

public class SetUp {
	
	public static AccessibleGrids getAccessibleGrids() {
		AccessibleGrids grids = new AccessibleGrids();
		grids.createAccessibleGrids();
		grids.computeShortestDistances();
		return grids;
	}

}
