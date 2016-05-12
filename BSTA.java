//Final Project BSTA
//12/14/2013
//This is a binary search tree that keeps StudentNodes and is ordered by SSN. It is used any time lookup by SSN is needed,
//such as for looking up a record, modifying or deleting by the admin or entering a new idea as a student. It can
//inser, delete, search, traverse, and print the tree.



public class BSTA
{
	private StudentNode root;
	
	public void BSTA()
	{
		root=null;
	}
	
	public boolean isEmptyTree()
	{
		return root==null;
	}
	
	public void insert(StudentNode x)
	{
		if (root==null)
			root=x;
		else
			insert2(root, x);
	}
	
	private void insert2(StudentNode y, StudentNode x)
	{
		if (y!=null)
		{
			if (x.getSSN()<y.getSSN())
			{
				if (y.getLeftA()==null)
				{
					y.setLeftA(x);
					x.setPreviousA(y);
				}
				else
				{
					StudentNode temp=y.getLeftA();
					insert2(temp,x);
				}
			}					
			else
			{
				if (y.getRightA()==null)
				{
					y.setRightA(x);
					x.setPreviousA(y);
				}
				else
				{
					StudentNode temp=y.getRightA();
					insert2(temp,x);
				}
			}
		}
	}
	
	public StudentNode search(int key)
	{
		if (root==null)
			return null;
		else
			return search2(root, key);
	}
	
	private StudentNode search2(StudentNode y, int key)
	{
		if (y!=null)
		{
			if (y.getSSN()==key)
				return y;
			else if (key<y.getSSN())
			{	
				StudentNode temp=y.getLeftA();
				return search2(temp,key);
			}
			else
			{
				StudentNode temp=y.getRightA();
				return search2(temp,key);
			}
		}
		return null;
	}
	
	public void traverse()
	{
		if (root==null)
			System.out.println("null");
		else
			traverse2(root);
		System.out.println("");
	}
	
	private void traverse2(StudentNode y)
	{
		if (y!=null)
		{
			traverse2(y.getLeftA());
			System.out.print(y.getName()+"   ");
			traverse2(y.getRightA());
		}
	}
		
	public void delete(StudentNode x)
	{
		if (root==null)
			System.out.println("null");
		else
			delete2(root, x);
	}
	
	private void delete2(StudentNode y, StudentNode x)
	{
		if (y!=null)
		{
			if (x.getRightA()==null && x.getLeftA()==null)					//delete a leaf
			{
				if (x==root)
					root=null;
				else
				{
					if (x.getSSN()<x.getPreviousA().getSSN())
						x.getPreviousA().setLeftA(null);
					else
						x.getPreviousA().setRightA(null);
				}
			}
			else if (x.getRightA()==null && x.getLeftA()!=null)						//delete a single parent with a left child
			{
				if (x!=root)
				{
					if (x.getSSN()<x.getPreviousA().getSSN())
					{
						x.getPreviousA().setLeftA(x.getRightA());
						x.getLeftA().setPreviousA(x.getPreviousA());
					}
					else
					{
						x.getPreviousA().setRightA(x.getLeftA());
						x.getLeftA().setPreviousA(x.getPreviousA());
					}
				}
				else
				{
					root=x.getLeftA();
					root.setPreviousA(null);
					x.setLeftA(null);
				}
			}
			else if (x.getRightA()!=null && x.getLeftA()==null)                 //delete a single parent with a right child
			{
				if (x!=root)
				{
					if (x.getSSN()<x.getPreviousA().getSSN())
					{
						x.getPreviousA().setLeftA(x.getRightA());
						x.getRightA().setPreviousA(x.getPreviousA());
					}
					else
					{
						x.getPreviousA().setRightA(x.getRightA());
						x.getRightA().setPreviousA(x.getPreviousA());
					}
				}
				else
				{
					root=x.getRightA();
					root.setPreviousA(null);
					x.setRightA(null);
				}
			}
			else
			{
				StudentNode temp=x;							//delete a parent of 2
				temp=temp.getRightA();
				while (temp.getLeftA()!=null)
					temp=temp.getLeftA();
				if (x==root) 									//if deleting the root
				{
					if (temp!=x.getRightA())						//if successor is not deleted node's right child
					{
						temp.getPreviousA().setLeftA(temp.getRightA());
						if (temp.getRightA()!=null)
							temp.getRightA().setPreviousA(temp.getPreviousA());
						temp.setLeftA(root.getLeftA());
						temp.setRightA(root.getRightA());
						temp.setPreviousA(null);
						root.setLeftA(null);
						root.setRightA(null);
						temp.getLeftA().setPreviousA(temp);
						temp.getRightA().setPreviousA(temp);
						root=temp;
					}
					else								//if successor is deleted node's right child
					{
						temp.setPreviousA(null);
						temp.setLeftA(x.getLeftA());
						x.getLeftA().setPreviousA(temp);
						x.setLeftA(null);
						x.setRightA(null);
						root=temp;
					}
				}
				else										//delete double parent but not the root
				{
					if (temp!=x.getRightA())					//if succesor isn't deleted node's right child
					{	
						temp.getPreviousA().setLeftA(temp.getRightA());
						temp.getRightA().setPreviousA(temp.getPreviousA());
						temp.setLeftA(x.getLeftA());
						temp.setRightA(x.getRightA());
						temp.setPreviousA(x.getPreviousA());
						x.setLeftA(null);
						x.setRightA(null);
						x.setPreviousA(null);
						temp.getLeftA().setPreviousA(temp);
						temp.getRightA().setPreviousA(temp);
					}
					else									//if successor is right child
					{
						temp.setPreviousA(x.getPreviousA());
						temp.getPreviousA().setRightA(temp);
						temp.setLeftA(x.getLeftA());
						temp.getLeftA().setPreviousA(temp);
						x.setLeftA(null);
						x.setRightA(null);
						x.setPreviousA(null);
					}
				}
			}
		}
	}
	
	
	public void printTree() 
	{
		System.out.println();
		printTree2(root);
		System.out.println();
        }

	private void printTree2(StudentNode tree) 
	{
		if (tree != null) 
		{
			System.out.print(tree.getSSN() + " ");
			if (tree.getLeftA() != null)
				System.out.print("Left: " + tree.getLeftA().getSSN() + " ");
			else
				System.out.print("Left: null ");
			if (tree.getRightA() != null)
				System.out.println("Right: " + tree.getRightA().getSSN() + " ");
			else
				System.out.println("Right: null ");
			printTree2(tree.getLeftA());
			printTree2(tree.getRightA());
	        }
	}		
}
		
		
		
		
		
		
		
		
		
		
		
	