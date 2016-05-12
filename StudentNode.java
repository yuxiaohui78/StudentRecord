//Final Project StudentNode
//12/14/2012
//The StudentNode class keeps track of all information for each student. It contains each students name, email, SSN, Student Number. 
//It also keeps within it an IdeaQueue to keep the students last 10 ideas. It has 2 Left, 2 Right, and 2 Previous pointers so it can
//be placed into both BSTA and BSTB. There is an additional pointer 'next' that is used when inserting a StudentNode into the studList.
//It also has 3 floats: iQcount keeps track of how many ideas are in the IdeaQueue (maximum 10), iQsum keeps track of the sum of the scores
//of the ideas in the queue, and iQavg is the quotient of iQsum and iQcount. StudentNode has methods to get and set all fields, plus a
//method to add a new idea to the queue, a method that returns the iQavg, and a method that prints all the ideas.

public class StudentNode
{
	private String name;
	private int ssn;
	private int studNum;
	private String email;
	private StudentNode LeftA;
	private StudentNode RightA;
	private StudentNode PreviousA;
	private StudentNode LeftB;
	private StudentNode RightB;
	private StudentNode PreviousB;
	private StudentNode next;
	private IdeaQueue iQ;
	private float iQcount;
	private float iQsum;
	private float iQavg;
	
	public StudentNode(String name0,String email0,int studNum0,int ssn0)
	{
		name=name0;
		ssn=ssn0;
		studNum=studNum0;
		email=email0;
		iQ=new IdeaQueue();
		iQcount=0;
		iQsum=0;
		iQavg=0;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public int getSSN()
	{
		return ssn;
	}
	
	public int getSN()
	{
		return studNum;
	}
	
	public StudentNode getLeftA()
	{
		return LeftA;
	}
	
	public StudentNode getRightA()
	{
		return RightA;
	}
	
	public StudentNode getPreviousA()
	{
		return PreviousA;
	}
	
	public StudentNode getLeftB()
	{
		return LeftB;
	}
	
	public StudentNode getRightB()
	{
		return RightB;
	}
	
	public StudentNode getPreviousB()
	{
		return PreviousB;
	}
	
	public void setName(String name1)
	{
		name=name1;
	}
	
	public void setEmail(String email1)
	{
		email=email1;
	}
	
	public void setSSN(int ssn1)
	{
		ssn=ssn1;
	}
	
	public void setSN(int studNum1)
	{
		studNum=studNum1;
	}
	
	public void setLeftA(StudentNode left1)
	{
		LeftA=left1;
	}
	
	public void setRightA(StudentNode right1)
	{
		RightA=right1;
	}
	
	public void setPreviousA(StudentNode previous1)
	{
		PreviousA=previous1;
	}
	
	public void setLeftB(StudentNode left1)
	{
		LeftB=left1;
	}
	
	public void setRightB(StudentNode right1)
	{
		RightB=right1;
	}
	
	public void setPreviousB(StudentNode previous1)
	{
		PreviousB=previous1;
	}
	
	public StudentNode getNext()
	{
		return next;
	}
	
	public void setNext(StudentNode next1)
	{
		next=next1;
	}
	
	public void addIdea(IdeaNode x)
	{
		if (x!=null)
		{
			if (iQcount<9)
			{
				iQ.enqueue(x);
				iQcount++;
				iQsum=iQsum+x.getRating();
				if (iQcount!=0)
					iQavg=iQsum/iQcount;
				else
					iQavg=0;
			}
			else
			{
				iQsum=iQsum-iQ.front().getRating();
				iQ.dequeue();
				iQ.enqueue(x);
				iQsum=iQsum+x.getRating();
				iQavg=iQsum/iQcount;
				
			}
		}
	}
	
	public void printIdeas()
	{
		iQ.printQueue();
	}
	
	public float getAVG()
	{
		return iQavg;
	}
}
