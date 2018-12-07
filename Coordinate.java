
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
	
	public int compare(Coordinate coor, int level) {
		int ans = 400;
		if(coor == null)
			ans = 400;
		if(level%2 == 1) {
			if(x_coordinate < coor.getXCoordinate())
				ans = 1;
			if(x_coordinate > coor.getXCoordinate()) 
				ans = -1;
			else
				ans = 0;
		}
		else {
			if(y_coordinate < coor.getYCoordinate())
				ans = 1;
			if(y_coordinate > coor.getYCoordinate()) 
				ans = -1;
			else
				ans = 0;
		}
		return ans;
	}
	
	public boolean equal(Coordinate other) {
		if(x_coordinate == other.x_coordinate && y_coordinate == other.y_coordinate)
			return true;
		return false;
	}
	
	public void print() {
		if(x_coordinate == 0 && y_coordinate == 0) 
			System.out.print("(*, *)");
		
		else
			System.out.print("(" + x_coordinate + ", " + y_coordinate + ")");
	}
}