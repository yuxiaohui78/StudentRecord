//Final Project IdeaQueue
//12/14/2012
//This is the data structure that holds each student's last 10 ideas. It is implemented as an array. It has methods to return the top value
//in the queue, enqueue a new idea, or dequeue the oldest idea. The dequeue function is a little different from the one we did in class
//since we didn't want this method to return anything. IdeaQueue is part of each StudentNode. 

public class IdeaQueue
{
	private IdeaNode[] q;
	private int front;
	private int tail;
	
	public IdeaQueue()
	{
		q=new IdeaNode[10];
		front=0;
		tail=0;
	}
	
	public boolean isEmpty()
	{
		return front==tail;
	}
	
	public IdeaNode front()
	{
		if (front==tail)
			return null;
		else
			return q[front];
	}
	
	public void dequeue()
	{
		if (front==tail)
			System.out.println("Queue Empty");
		else
			front=(front+1)%10; 
	}
	
	public void enqueue(IdeaNode x)
	{
		q[tail]=x;
		tail=(tail+1)%10;
	}

	public void printQueue()
	{
		if (front <= tail)
			for(int i = front; i <tail; i++) 
				System.out.println(q[i].getIdea());
		else 
		{
			for(int i = front; i <10; i++) 
				System.out.println(q[i].getIdea());          
			for(int i = 0; i <tail; i++) 
				System.out.println(q[i].getIdea());
		}          
        }	
}