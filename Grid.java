package mars;

import java.util.LinkedHashMap;
import java.util.Map;

public class Grid {
	
	protected final int row;
	protected final int column;
	protected final int index;
	protected final Map<Grid,Integer> shortestDistanceByGrid;
	
	public Grid(int row, int column){
		this.row = row;
		this.column = column;
		this.index = (row*Constants.WIDTH) + column;
		this.shortestDistanceByGrid = new LinkedHashMap<Grid, Integer>();
		this.shortestDistanceByGrid.put(this, 0);
	}

	public int getShortestDistanceTo(Grid targetGrid) {
		Integer distance = shortestDistanceByGrid.get(targetGrid);
		if (distance == null)
			return Integer.MAX_VALUE;
		else
			return distance.intValue();
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public int getIndex() {
		return index;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
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
		Grid other = (Grid) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

}
