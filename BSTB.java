\\Ordered by student number

public class BSTB
{
	private StudentNode root;
	
	public void BST()
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
			if (x.getSN()<y.getSN())
			{
				if (y.getLeftB()==null)
				{
					y.setLeftB(x);
					x.setPreviousB(y);
				}
				else
				{
					StudentNode temp=y.getLeftB();
					insert2(temp,x);
				}
			}					
			else
			{
				if (y.getRightB()==null)
				{
					y.setRightB(x);
					x.setPreviousB(y);
				}
				else
				{
					StudentNode temp=y.getRightB();
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
			if (y.getSN()==key)
				return y;
			else if (key<y.getSN())
			{	
				StudentNode temp=y.getLeftB();
				return search2(temp,key);
			}
			else
			{
				StudentNode temp=y.getRightB();
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
		if (y!=null)                				//traverse prints SN, name, avg idea score, SSN, email
		{
			traverse2(y.getLeftB());
			System.out.println(y.getSN()+"   "+y.getName()+"    "+"AVG Score: "+y.getAVG()+"    "+"SSN: "+y.getSSN()+"    "+y.getEmail());
			traverse2(y.getRightB());
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
			if (x.getRightB()==null && x.getLeftB()==null)
			{
				if (x==root)
					root=null;
				else
				{
					if (x.getSN()<x.getPreviousB().getSN())
						x.getPreviousB().setLeftB(null);
					else
						x.getPreviousB().setRightB(null);
				}
			}
			else if (x.getRightB()==null && x.getLeftB()!=null)
			{
				
				if (x!=root)
				{
					if (x.getSN()<x.getPreviousB().getSN())
					{
						x.getPreviousB().setLeftB(x.getLeftB());
						x.getLeftB().setPreviousB(x.getPreviousB());
					}
					else
					{
						x.getPreviousB().setRightB(x.getLeftB());
						x.getLeftB().setPreviousB(x.getPreviousB());
					}
				}
				else
				{
					root=x.getLeftB();
					root.setPreviousB(null);
					x.setLeftB(null);
				}
			}
			else if (x.getRightB()!=null && x.getLeftB()==null)
			{
				if (x!=root)
				{
					if (x.getSN()<x.getPreviousB().getSN())
					{
						x.getPreviousB().setLeftB(x.getRightB());
						x.getRightB().setPreviousB(x.getPreviousB());
					}
					else
					{
						x.getPreviousB().setRightB(x.getRightB());
						x.getRightB().setPreviousB(x.getPreviousB());
					}
				}
				else
				{
					root=x.getRightB();
					root.setPreviousB(null);
					x.setRightB(null);
				}
			}
			else
			{
				StudentNode temp=x;
				temp=temp.getRightB();
				while (temp.getLeftB()!=null)
					temp=temp.getLeftB();
				if (x==root)
				{
					if (temp!=x.getRightB())
					{
						temp.getPreviousB().setLeftB(temp.getRightB());
						if (temp.getRightB()!=null)
							temp.getRightB().setPreviousB(temp.getPreviousB());
						temp.setLeftB(root.getLeftB());
						temp.setRightB(root.getRightB());
						temp.setPreviousB(null);
						root.setLeftB(null);
						root.setRightB(null);
						temp.getLeftB().setPreviousB(temp);
						temp.getRightB().setPreviousB(temp);
						root=temp;
					}
					else
					{
						temp.setPreviousB(null);
						temp.setLeftB(x.getLeftB());
						x.getLeftB().setPreviousB(temp);
						x.setLeftB(null);
						x.setRightB(null);
						root=temp;
					}
				}
				else
				{
					if (temp!=x.getRightB())
					{	
						temp.getPreviousB().setLeftB(temp.getRightB());
						temp.getRightB().setPreviousB(temp.getPreviousB());
						temp.setLeftB(x.getLeftB());
						temp.setRightB(x.getRightB());
						temp.setPreviousB(x.getPreviousB());
						x.setLeftB(null);
						x.setRightB(null);
						x.setPreviousB(null);
						temp.getLeftB().setPreviousB(temp);
						temp.getRightB().setPreviousB(temp);
					}
					else
					{
						temp.setPreviousB(x.getPreviousB());
						temp.getPreviousB().setRightB(temp);
						temp.setLeftB(x.getLeftB());
						temp.getLeftB().setPreviousB(temp);
						x.setLeftB(null);
						x.setRightB(null);
						x.setPreviousB(null);
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
			System.out.print(tree.getSN() + " ");
			if (tree.getLeftB() != null)
			System.out.print("Left: " + tree.getLeftB().getSN() + " ");
			else
				System.out.print("Left: null ");
			if (tree.getRightB() != null)
				System.out.println("Right: " + tree.getRightB().getSN() + " ");
			else
				System.out.println("Right: null ");
			printTree2(tree.getLeftB());
			printTree2(tree.getRightB());
	        }
	}		
}
		
		
		
		
		
		
		
		
		
		
		
	
