
//Final Project studList
//12/14/2012
//studList is an unordered linked list. Whenever a student is created, it is inserted into this list along with BSTA and BSTB. It
//is used because in order to write all StudentNodes to a file, we want them to be done so in an order that is independent of
//either SSN or SN. If we used SSN or SN, when we reinserted every StudentNode next time the program was run, it would
//render one of BSTA or BSTB useless as an ordered linked list, making O(logn) not accomplished. It has methods for returning
//the length, inserting, deleting, viewing the front of the list, and printing the entire list.


public class studList
{
	private StudentNode head;
	private int n;
	
	public studList()
	{
		head=null;
		n=0;
	}
	
	public int length()
	{
		return n;
	}
	
	public boolean isEmptyList()
	{
		return n==0;
	}

	public void insert(StudentNode x)
	{
		if (head==null)
			head=x;
		else
		{
			StudentNode temp=head;
			while (temp.getNext()!=null)
				temp=temp.getNext();
			temp.setNext(x);
		}
		n++;
	}
	
	public StudentNode searchRemove(int key)
	{
		if (head!=null)
		{
			StudentNode temp;
			temp=head;
			
			if (head.getSSN()==key)
			{
				head=head.getNext();
				n=n-1;
				return temp;
			}
			
			else
			{
				for (int c=0; c<n; c++)
				{
					if (temp.getNext()!=null)
					{
						if (temp.getNext().getSSN()==key)
						{
							StudentNode found;
							found=temp.getNext();
							temp.setNext(found.getNext());
							found.setNext(null);
							n=n-1;
							return found;
						}
					}
					else 
						return null;
					temp=temp.getNext();
				}
			}
		} 
		return null;	
	}	
	
	public StudentNode viewHead()
	{
		return head;
	}
	
	public void printList()
	{
		if (head!=null)
		{
			StudentNode temp;
			temp=head;
			for (int a=0; a<n; a++)
			{	
				System.out.println(temp.getName()+" "+temp.getEmail()+" "+temp.getSN()+" "+temp.getSSN());
				temp=temp.getNext();
			}
		}
	}
}
		
		