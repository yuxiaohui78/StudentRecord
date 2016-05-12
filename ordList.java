//Final Project ordList
//12/14/2012
//This is an ordered linked list. It is ordered by idea rating. The highest value is the head of the list. This is the data structure used to 
//create the idea database. It has methods to return the length, insert a new IdeaNode, view the maximum value, and delete the maximum value,
//as wel as a method to print the list. 

public class ordList
{
	private IdeaNode head;
	private int n;
	
	public ordList()
	{
		head=null;
		n=0;
	}
	
	public int length()
	{
		return n;
	}
	
	public void insert(IdeaNode ideaNode)
	{
		IdeaNode temp1=head;
		IdeaNode temp2=null;
		while(temp1!=null)
		{
			if(temp1.getRating()<=ideaNode.getRating())
				break;
			temp2=temp1;
			temp1=temp1.getNext();
		}
		
		if(temp2==null)
		{
			ideaNode.setNext(head);
			head=ideaNode;
		}
		else
		{
			temp2.setNext(ideaNode);
			ideaNode.setNext(temp1);
		}
		n++;
	}
	
	public void printList()
	{
		IdeaNode temp = head;
		while(temp != null)
		{
			System.out.println(temp.getRating());
			temp = temp.getNext();
		}
	}
	
	public IdeaNode viewHead()
	{
		return head;
	}
	
	public void deleteMax()
	{
		head=head.getNext();
		n--;
	}
}
	