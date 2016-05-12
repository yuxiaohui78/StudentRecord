//Final Project ordList2
//12/14/2012
//This is an ordered linked list. It is ordered by idea number. The lowest value is the head of the list. This data structure is used to 
//make sure that when the program starts up, it adds nodes to each person's IdeaQueue in chronological order. The
//IdeaNodes are written to the file via this list. It has methods to return the length, insert a new IdeaNode, view the maximum value
//and delete the maximum value,as well as a method to print the list. 
public class ordList2
{
	private IdeaNode head;
	private int n;
	
	public ordList2()
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
			if(temp1.getIdeaNum()>=ideaNode.getIdeaNum())
				break;
			temp2=temp1;
			temp1=temp1.getNext2();
		}
		
		if(temp2==null)
		{
			ideaNode.setNext2(head);
			head=ideaNode;
		}
		else
		{
			temp2.setNext2(ideaNode);
			ideaNode.setNext2(temp1);
		}
		n++;
		
	}
	
	public void printList()
	{
		IdeaNode temp = head;
		while(temp != null)
		{
			System.out.println(temp.getIdeaNum());
			temp = temp.getNext2();
		}
	}
	
	public IdeaNode viewHead()
	{
		return head;
	}
	
	public void deleteMax()
	{
		head=head.getNext2();
		n--;
	}
}

