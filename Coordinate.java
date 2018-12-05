
public class Coordinate {

	private int x_coordinate;
	private int y_coordinate;

	public Coordinate() {
		x_coordinate = 0;
		y_coordinate = 0;
	}

	public Coordinate(int x, int y) {
		x_coordinate = x;
		y_coordinate = y;
	}

	public void setXCoordinate(int x) {
		x_coordinate = x;
	}

	public void setYCoordinate(int y) {
		y_coordinate = y;
	}

	public int getXCoordinate() {
		return x_coordinate;
	}

	public int getYCoordinate() {
		return y_coordinate;
	}
	
	public boolean equal(Coordinate other) {
		if(x_coordinate == other.x_coordinate && y_coordinate == other.y_coordinate)
			return true;
		return false;
	}
}