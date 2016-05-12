//Final Project IdeaNode
//12/14/2012
//This is the IdeaNode, a familiar node class variation. it holds the idea number, submitter's ssn, the idea rating, the
//actual idea text, as well as 2 pointers next and next2. These allow each idea to be placed in 2 linked lists, as discussed 
//in the comments for the main program. It also has a parameter 'active' which is automatically assigned a 1 initially. If 
//the idea is sold during the main program, 'active' is changed to a 0. That way, the next time the program is run, that
//idea is not added to the database but only the submitter's last 10 queue. The IdeaNode has methods to get and set all
//of the information held in it. 

public class IdeaNode
{
	private int ideaNum;
	private int ssn;
	private int rating;
	private String ideaText;
	private IdeaNode next;
	private IdeaNode next2;
	private int active;
	
	public IdeaNode(int ideaNum0, int ssn0, String ideaText0,int rating0,int active0)
	{
		ideaNum=ideaNum0;
		ssn=ssn0;
		ideaText=ideaText0;
		rating=rating0;
		active=active0;
	}
	
	public int getSSN()
	{
		return ssn;
	}
	
	public int getIdeaNum()
	{
		return ideaNum;
	}
	
	public int getRating()
	{
		return rating;
	}
	
	public String getIdea()
	{
		return ideaText;
	}
	
	public void setSSN(int ssn1)
	{
		ssn=ssn1;
	}
	
	public void setIdeaNum(int ideaNum1)
	{
		ideaNum=ideaNum1;
	}
	
	public void setRating(int rating1)
	{
		rating=rating1;
	}
	
	public void setIdea(String ideaText1)
	{
		ideaText=ideaText1;
	}
	
	public IdeaNode getNext()
	{
		return next;
	}
	
	public void setNext(IdeaNode next1)
	{
		next=next1;
	}
	
	public IdeaNode getNext2()
	{
		return next2;
	}
	
	public void setNext2(IdeaNode next3)
	{
		next2=next3;
	}
	
	public int isActive()
	{
		return active;
	}
	
	public void deactivate()
	{
		active=0;
	}
}
	