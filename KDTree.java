
public class KDTree {

	private Coordinates root;
	
	public KDTree() {
		root = null;
	}
	
	public KDTree(int x, int y) {
		root.setXCoordinate(x);
		root.setYCoordinate(y);
	}
	
	public boolean isEmpty() {
		return root == null;
	}
	
}
