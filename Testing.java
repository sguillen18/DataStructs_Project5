
public class Testing {

	public static void main(String[] args) {
		Coordinate c = new Coordinate(20, 50);
		c.print();
		System.out.print("\n");
		
		KDTree onlyRoot = new KDTree ();
		onlyRoot.add(c);
		onlyRoot.print();
		System.out.print("\n");
		
		Coordinate d = new Coordinate(21, 48);
		Coordinate e = new Coordinate(10, 70);
		Coordinate f = new Coordinate(40, 23);
		Coordinate g = new Coordinate(30, 17);
		Coordinate h = new Coordinate(38, 17);
		
		KDTree a = new KDTree();
		a.add(d);
		a.add(e);
		a.add(f);
		a.add(g);
		
		a.print();
		System.out.print("\n\n");
		
		
		System.out.println("Height is " + a.getHeight());
		
		System.out.println("\nContains coordinate point (30, 17): " + a.contains(f));
		System.out.println("Contains coordinate point (38, 17): " + a.contains(h));

	}

}
