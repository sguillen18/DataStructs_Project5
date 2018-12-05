import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


public class KDTree {

	private BinaryNode root;

	public KDTree () {
		initializeTree (null, null, null);
	}
	public KDTree (Coordinate rootData) {
		initializeTree (rootData, null, null);
	}
	/*public KDTree (Coordinate rootData, KDTree leftTree,
			KDTree rightTree) {
		initializeTree (rootData, leftTree, rightTree);					 
	}*/

	public boolean isEmpty() {
		return root == null;
	}

	public void setTree (Coordinate rootData) {
		root = new BinaryNode(rootData);
	}

	/*public void setTree (Coordinate rootData, 
			BinaryTreeInterface  leftTree,
			BinaryTreeInterface  rightTree) {
		initializeTree (rootData, (KDTree) leftTree, 
				(KDTree) rightTree);
	}*/

	private void initializeTree (Coordinate rootData, 
			KDTree leftTree, KDTree  rightTree) {
		root = new BinaryNode (rootData);
		if (leftTree != null)	
			root.setLeftChild (leftTree.getRootData());
		if (rightTree != null)	
			root.setRightChild (rightTree.getRootData());	   
	}


	public void setRoot(Coordinate rootData) {
		root = new BinaryNode (rootData);
	}

	public BinaryNode  getRootData(){
		return root;
	}

	/*public void setLeftTree(Coordinate left) {
		BinaryNode  leftTree = new BinaryNode (left);
		root.setLeftChild(leftTree);
	}*/

	/*public void setRightTree(Coordinate right) {
		BinaryNode  rightTree = new BinaryNode (right);
		root.setLeftChild(rightTree);
	} */
	
	public int getHeight() {
		return root.getHeight();
	}

	public int getNumberOfNodes() {
		return root.getNumberOfNodes();
	}

	public void getLeaves() {
		root.getLeaves();
	}
	
	public void add(Coordinate newCoor) {
		root.add(newCoor, 1);
	}
	
	public boolean contains (Coordinate coor) {
		return root.contains(coor);
	}
	
	public void print() {
		root.print(null, 1, 'q');
	}


	class BinaryNode {
		private Coordinate data = new Coordinate();
		private BinaryNode leftChild;
		private BinaryNode rightChild;

		public BinaryNode () {
			this (null);
		}

		public BinaryNode (Coordinate rootData) {
			this (rootData, null, null);
		}

		public BinaryNode (Coordinate rootData, BinaryNode  newLeftChild,
				BinaryNode  newRightChild) {
			data = rootData;
			leftChild = newLeftChild;
			rightChild = newRightChild;
		}

		public Coordinate getData() {
			return data;
		}

		public void setData (Coordinate newData) {
			data = newData;
		}

		public BinaryNode getLeftChild () {
			return leftChild;
		}

		public BinaryNode getRightChild () {
			return rightChild;
		}	   
		public void setLeftChild (BinaryNode newLeftChild) {
			leftChild = newLeftChild;
		}

		public boolean hasLeftChild () {
			return (leftChild != null);
		}

		public void setRightChild (BinaryNode newRightChild) {
			rightChild = newRightChild;
		}

		public boolean hasRightChild () {
			return (rightChild != null);
		}

		public boolean isLeaf () {
			return (leftChild == null && (rightChild == null));
		}

		public int getHeight() {
			int leftHeight = hasLeftChild() ? leftChild.getHeight() : 0;
			int rightHeight = hasRightChild() ? rightChild.getHeight() : 0;
			return 1 + Math.max (leftHeight, rightHeight);
		}

		public int getNumberOfNodes() {
			int n = 1;
			if(rightChild!=null) {
				n += rightChild.getNumberOfNodes();
			}
			if(leftChild!=null) {
				n += leftChild.getNumberOfNodes();
			}
			return n;
		}

		public void getLeaves() {
			if(root.isLeaf()) {
				System.out.print(getData() + " ");
			}
			else {
				if(root.hasLeftChild()) {
					BinaryNode currLeft = leftChild;
					currLeft.getLeaves();
				}
				if(root.hasRightChild()) {
					BinaryNode currRight = rightChild;
					currRight.getLeaves();
				}
			}
		}
		
		public void add(Coordinate newCoor, int level) {
			if(level%2 == 1) {
				if(data == null) {
					data = newCoor;
				}
				else {
					if(data.getXCoordinate() > newCoor.getXCoordinate()) {
						if(!(hasLeftChild())) {
							BinaryNode  n = new BinaryNode (newCoor);
							setLeftChild(n);
						}
						else {
							leftChild.add(newCoor, level+1);
						}
					}

					if(data.getXCoordinate() <= newCoor.getXCoordinate()){
						if(!(hasRightChild())){
							BinaryNode  n = new BinaryNode (newCoor);
							setRightChild(n);
						}
						else {
							rightChild.add(newCoor, level+1);
						}
					}
				}
			}
			
			if(level%2 == 0) {
				if(data == null) {
					data = newCoor;
				}
				else {
					if(data.getYCoordinate() > newCoor.getYCoordinate()) {
						if(!(hasLeftChild())) {
							BinaryNode  n = new BinaryNode (newCoor);
							setLeftChild(n);
						}
						else {
							leftChild.add(newCoor, level+1);
						}
					}

					if(data.getYCoordinate() <= newCoor.getYCoordinate()) {
						if(!(hasRightChild())) {
							BinaryNode  n = new BinaryNode (newCoor);
							setRightChild(n);
						}
						else {
							rightChild.add(newCoor, level+1);
						}
					}
				}
			}
		}
		
		public boolean contains (Coordinate coor) {
			if(data.equals(coor))
				return true;
			return leftChild.contains(coor) || rightChild.contains(coor);
		}

		public BinaryNode  copy() {
			BinaryNode copied = new BinaryNode (data);

			if(hasLeftChild())
				copied.setLeftChild((BinaryNode) leftChild.copy());
			if(hasRightChild())
				copied.setRightChild((BinaryNode) rightChild.copy());
			return copied;
		}
		
		public void print(Coordinate root, int level, char left) {
			System.out.println("\nLevel " + level);
			if(root == null) {
				System.out.print("  Root");
			}
			if(left == 'l' && root != null) {
				System.out.print("  Left: Parent is ");
				root.print();
				System.out.print("\n");
			}
			if(left == 'r' && root != null) {
				System.out.print("  Right: Parent is ");
				root.print();
				System.out.print("\n");
			}
			System.out.print("  ");
			data.print();
			if(hasLeftChild())
				leftChild.print(data, level+1, 'l');
			if(hasRightChild()) 
				rightChild.print(data, level+1, 'r');
		}

	}

	public Iterator<Coordinate> getPreorderIterator(){
		return new PreorderIterator();
	}

	public void printPreorder() {
		Iterator<Coordinate> p = getPreorderIterator();
		while(p.hasNext()) {
			System.out.print(p.next()+ " ");
		}
	}

	private class PreorderIterator implements Iterator <Coordinate>{	
		private Stack <BinaryNode> nodeStack;

		public PreorderIterator() {
			nodeStack = new Stack<>();

			addToStack(root);

		}
		private void addToStack(BinaryNode addedNode) {
			if (addedNode.hasRightChild()) {
				addToStack( (BinaryNode ) addedNode.getRightChild());
			}
			if (addedNode.hasLeftChild()) {
				addToStack((BinaryNode ) addedNode.getLeftChild());
			}
			nodeStack.push(addedNode);
		}

		public Coordinate next() {
			return(nodeStack.pop().getData());
		}

		public boolean hasNext() {
			return(!nodeStack.isEmpty());
		}
	}

	public Iterator<Coordinate> getInorderIterator(){
		return new InorderIterator();
	}

	public void printInorder() {
		Iterator<Coordinate> p = getInorderIterator();
		while(p.hasNext()) {
			System.out.print(p.next()+ " ");
		}
	}

	private class InorderIterator implements Iterator<Coordinate>{
		private Stack <BinaryNode> nodeStack;

		public InorderIterator() {
			nodeStack = new Stack <> ();
			addToStack (root);
		}

		private void addToStack (BinaryNode  aNode) {
			if (aNode == null)
				return;
			BinaryNode  right = 
					(BinaryNode )aNode.getRightChild();
			BinaryNode  left = 
					(BinaryNode )aNode.getLeftChild();	
			addToStack (right);
			nodeStack.push(aNode);
			addToStack (left);
		}

		public boolean hasNext() {
			return (!nodeStack.isEmpty());
		}

		public Coordinate next() {
			return nodeStack.pop().getData();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public Iterator<Coordinate> getPostorderIterator(){
		return new PostorderIterator();
	}

	public void printPostorder() {
		Iterator<Coordinate> p = getPostorderIterator();
		while(p.hasNext()) {
			System.out.print(p.next()+ " ");
		}
	}

	private class PostorderIterator implements Iterator<Coordinate>  {
		private Stack <BinaryNode> nodeStack;

		public PostorderIterator() {
			nodeStack = new Stack <> ();
			addToStack (root);
		}

		private void addToStack(BinaryNode addedNode) {
			nodeStack.push(addedNode);
			if (addedNode.hasRightChild()) {
				addToStack( (BinaryNode ) addedNode.getRightChild());
			}
			if (addedNode.hasLeftChild()) {
				addToStack((BinaryNode ) addedNode.getLeftChild());
			}
		}

		public boolean hasNext() {
			return (!nodeStack.isEmpty());
		}

		public Coordinate next() {
			return nodeStack.pop().getData();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public Iterator<Coordinate> getLevelorderIterator(){
		return new LevelorderIterator();
	}

	public void printLevelorder() {
		Iterator<Coordinate> p = getLevelorderIterator();
		while(p.hasNext()) {
			System.out.print(p.next() + " ");
		}
	}

	private class LevelorderIterator implements Iterator<Coordinate>  {
		private Queue <BinaryNode> nodeQueue;

		public LevelorderIterator() {
			nodeQueue = new  LinkedList <>();
			int h = root.getHeight();
			for(int i = 1; i <= h; i++)
				addToQueue (root, i);
		}

		private void addToQueue(BinaryNode addedNode, int level) {
			if(addedNode == null)
				return;
			if(level == 1)
				nodeQueue.add(addedNode);
			else if(level > 1) {
				addToQueue(addedNode.leftChild, level-1);
				addToQueue(addedNode.rightChild, level-1);
			}
		}

		public boolean hasNext() {
			return (!nodeQueue.isEmpty());
		}

		public Coordinate next() {
			return nodeQueue.remove().getData();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}
}
