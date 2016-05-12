
//main

import java.util.Scanner;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

public class FinalProject
{
	public static void main(String[] args)
	{
		BSTA a =new BSTA();                                                    //initializing data structures
		BSTB b=new BSTB();
		ordList database=new ordList();
		studList studentList=new studList();
		ordList2 ideaNumList = new ordList2();
		int n=1;                                                                        //to keep track number of ideas
		
		try
		{
			FileReader studentIn=new FileReader("students.txt");                                 //read in from students.txt
			Scanner studIn=new Scanner(studentIn);
			while (studIn.hasNextLine()==true)
			{
				String nameIn=studIn.next();
				String emailIn=studIn.next();
				int snIn=studIn.nextInt();
				int ssnIn=studIn.nextInt();
				StudentNode student=new StudentNode(nameIn, emailIn, snIn, ssnIn);                    //create the StudentNodes
				a.insert(student);
				b.insert(student);
				studentList.insert(student);											//insert into the 3 student data structures
			}
		}
		
		catch(IOException e)
		{
			System.out.println("Input/output error " + e);
		}
		catch(NoSuchElementException e)
		{
			System.out.println("");
		}
		
		try
		{
			FileReader ideaIn=new FileReader("ideas.txt");                                                 //read in from ideas.txt
			Scanner ideasIn=new Scanner(ideaIn);
			while (ideasIn.hasNextLine()==true)
			{
				int ideaNumIn=ideasIn.nextInt();
				int ideaSSNIn=ideasIn.nextInt();
				int ideaRatingIn=ideasIn.nextInt();
				int ideaActiveIn=ideasIn.nextInt();
				String ideaTextIn=ideasIn.nextLine();
				IdeaNode idea=new IdeaNode(ideaNumIn,ideaSSNIn,ideaTextIn,ideaRatingIn,ideaActiveIn);		//create IdeaNodes
				n++;
				ideaNumList.insert(idea);								//insert into list ordered by idea number
				if (a.search(idea.getSSN())!=null)						
						a.search(idea.getSSN()).addIdea(idea);			//add to submitter's idea queue
				if (idea.isActive()==1)
					database.insert(idea);							//if active, add to main database
			}
		}
		
		catch(IOException e)
		{
			System.out.println("Input/output error " + e);
		}
		catch(NoSuchElementException e)
		{
			System.out.println("");
		}
		
		while (true)
		{
			Scanner user0 = new Scanner(System.in);
			System.out.print("Are you a student or an admin? (Or type 'exit' to quit) ");			//main menu
			String user1=user0.next();
			if (user1.equals("student") || user1.equals("Student"))                                               
			{
				
				Scanner ssn0 = new Scanner(System.in);								//student menu
				System.out.print("Please enter last 4 digits of your SSN  ");
				int ssn1=ssn0.nextInt();
				if (a.search(ssn1)!=null)
				{
					System.out.println("Hello, "+ a.search(ssn1).getName());
					while (true)
					{
						System.out.println("As a student you may enter a new idea from this menu");
						System.out.print("type 'idea' to enter a new idea or 'exit' to return to the main menu:   ");
						Scanner studSelect=new Scanner(System.in);
						String studSelect1=studSelect.next();
						if (studSelect1.equals("idea") || studSelect1.equals("Idea"))
						{
							Scanner idea0=new Scanner(System.in);
							Scanner rating0=new Scanner(System.in);
							System.out.println("Enter your idea please.");
							String idea1=idea0.nextLine();
							System.out.print("Enter the idea rating given to you by the admin  ");
							int rating1=rating0.nextInt();
							IdeaNode newIdea= new IdeaNode(n,ssn1,idea1,rating1,1);
							a.search(ssn1).addIdea(newIdea);
							database.insert(newIdea);
							ideaNumList.insert(newIdea);									//add the new idea
							n++;
						}
						else if (studSelect1.equals("exit") || studSelect1.equals("Exit"))
							break;
						else
							System.out.println("invalid input, please try again");
					}
				}
				else 
					System.out.println("user not found");
			}	
			else if (user1.equals("admin") || user1.equals("Admin"))							//admin menu
			{
				while (true)
				{
					System.out.println("-------------------------------------------------------------------------");
					System.out.println("Hello, from this menu you can make several changes and view many items");
					System.out.println("type 'a' to view a student record");
					System.out.println("type 'b' to add a student record or an idea");
					System.out.println("type 'c' to modify a student record");
					System.out.println("type 'd' to delete a student record");
					System.out.println("type 'e' to look up an email address by Student Number");
					System.out.println("type 'f' to view the best idea in the database and delete it");
					System.out.println("type 'g' to view a list of all students");
					System.out.println("type 'h' to view the best idea in the database");
					System.out.println("type 'exit' to quit");
					System.out.println("-------------------------------------------------------------------------");
					Scanner adminSelect=new Scanner(System.in);
					String adminSelect1=adminSelect.next();
					
					if (adminSelect1.equals("a") || adminSelect1.equals("A"))					//choice a: lookup a record by SSN
					{
						System.out.println("");
						System.out.print("please enter the SSN of a student to display their record:  ");
						Scanner ssnSearch=new Scanner(System.in);
						int ssnSearch1=ssnSearch.nextInt();
						if (a.search(ssnSearch1)!=null)
						{
							System.out.println("");
							System.out.println("Name: "+a.search(ssnSearch1).getName());
							System.out.println("Email: "+a.search(ssnSearch1).getEmail());
							System.out.println("SSN: "+a.search(ssnSearch1).getSSN());
							System.out.println("Avg Score: "+a.search(ssnSearch1).getAVG());
							System.out.println("");
							a.search(ssnSearch1).printIdeas();
						}
						else
							System.out.println("Student not found. Please try again");
						System.out.println("");
						
					}
					
					else if (adminSelect1.equals("b") || adminSelect1.equals("B"))				//choice b: add student
					{
						System.out.println("");
						System.out.print("Type 'student' to create a new student or 'idea' to create a new idea: ");
						Scanner studOrIdea=new Scanner(System.in);
						String studOrIdea1=studOrIdea.next();
						if (studOrIdea1.equals("student") || studOrIdea1.equals("Student"))
						{
							System.out.println("You have chosen to create a new student record");
							Scanner newName=new Scanner(System.in);
							Scanner newEmail=new Scanner(System.in);
							Scanner newSN=new Scanner(System.in);
							Scanner newSSN=new Scanner(System.in);
							System.out.print("Enter name: ");
							String newName1=newName.next();
							System.out.print("Enter email: ");
							String newEmail1=newEmail.next();
							System.out.print("Enter Student Number: ");
							int newSN1=newSN.nextInt();
							System.out.print("Enter SSN: ");
							int newSSN1=newSSN.nextInt();
							StudentNode newStudent=new StudentNode(newName1,newEmail1,newSN1,newSSN1);
							a.insert(newStudent);
							b.insert(newStudent);
							studentList.insert(newStudent);
							System.out.println("Student record added.");
						}
						else if (studOrIdea1.equals("idea") || studOrIdea1.equals("Idea"))
						{
							System.out.println("You have chosen to create a new idea");
							Scanner idea0=new Scanner(System.in);
							Scanner rating0=new Scanner(System.in);
							System.out.println("Enter your idea please.");
							String idea1=idea0.nextLine();
							System.out.print("Enter the idea rating  ");
							int rating1=rating0.nextInt();
							IdeaNode newIdea= new IdeaNode(n,0,idea1,rating1,1);
							database.insert(newIdea);
							ideaNumList.insert(newIdea);
							n++;
							System.out.println("Idea has been added to the database.");
						}
						else
							System.out.println("invalid input, please try again");
						System.out.println("");
						
					}
					
					 else if (adminSelect1.equals("c") || adminSelect1.equals("C"))				//choice c: modify
					{
						System.out.println("");
						System.out.print("Enter the SSN of the student you want to modify: ");
						Scanner ssnSearch=new Scanner(System.in);
						int ssnSearch1=ssnSearch.nextInt();
						if (a.search(ssnSearch1)!=null)
						{
							System.out.print("Type 'a' to modify name: ");
							System.out.print("Type 'b' to modify email: ");
							System.out.print("Type 'exit to return to previous menu: ");
							Scanner modify=new Scanner(System.in);
							String modify1=modify.next();
							if (modify1.equals("a") || modify1.equals("A"))
							{
								System.out.println("Current Name: "+a.search(ssnSearch1).getName());
								System.out.print("Type a new name to overwrite: ");
								Scanner newName=new Scanner(System.in);
								String newName1=newName.next();
								a.search(ssnSearch1).setName(newName1);
								System.out.println("Name adjusted. New name: "+a.search(ssnSearch1).getName());
							}
							else if (modify1.equals("b") || modify1.equals("B"))
							{
								System.out.println("Current Email: "+a.search(ssnSearch1).getEmail());
								System.out.print("Type a new Email to overwrite: ");
								Scanner newEmail=new Scanner(System.in);
								String newEmail1=newEmail.next();
								a.search(ssnSearch1).setEmail(newEmail1);
								System.out.println("Email adjusted. New email: "+a.search(ssnSearch1).getEmail());
							}
							else if (modify1.equals("exit") || modify1.equals("Exit"))
								break;
							else
								System.out.println("invalid input, please try again");
							System.out.println("");
						}
						else
							System.out.println("Student not found, please try again");
					}
					
					else if (adminSelect1.equals("d") || adminSelect1.equals("D"))				//choice d: delete record
					{
						System.out.println("");
						System.out.print("Enter the SSN of the student you want to delete: ");
						Scanner ssnSearch=new Scanner(System.in);
						int ssnSearch1=ssnSearch.nextInt();
						if (a.search(ssnSearch1)!=null)
						{
							String tempName=a.search(ssnSearch1).getName();
							int tempSN=a.search(ssnSearch1).getSN();
							a.delete(a.search(ssnSearch1));
							b.delete(b.search(tempSN));
							studentList.searchRemove(ssnSearch1);
							System.out.println("Student record: "+tempName+" deleted");
						}
						else
							System.out.println("Student not found, no need to delete what isn't there!");
						System.out.println("");
						
					}
					
					else if (adminSelect1.equals("e") || adminSelect1.equals("E"))				//choice e: lookup email by SN
					{
						System.out.println("");
						System.out.print("Enter the Student Number to lookup his or her email address: ");
						Scanner snSearch=new Scanner(System.in);
						int snSearch1=snSearch.nextInt();
						if (b.search(snSearch1)!=null)
							System.out.println("Found email address: "+b.search(snSearch1).getEmail());
						else
							System.out.println("Student not found, please try again.");
						System.out.println("");
						
					}
					
					else if (adminSelect1.equals("f") || adminSelect1.equals("F"))			//choice f: view and delete best idea from database
					{
						System.out.println("");
						System.out.println("This is the best idea in the database. It will now be sold and thus deleted.");
						System.out.println("");
						System.out.println("Best Idea: "+database.viewHead().getIdea());
						System.out.println("Idea Rating: "+database.viewHead().getRating());
						if (a.search(database.viewHead().getSSN())!=null && database.viewHead().getSSN()!=0)
							System.out.println("Idea Submitter: "+a.search(database.viewHead().getSSN()).getName());
						else if (database.viewHead().getSSN()==0)
							System.out.println("Idea Submitter: Administrator");
						else
							System.out.println("Idea Submitter: No longer in database");
						System.out.println("");
						database.viewHead().deactivate();
						database.deleteMax();
					}
					
					else if (adminSelect1.equals("g") || adminSelect1.equals("G"))		//choice g: view list of all students, info
					{
						System.out.println("");
						b.traverse();
						System.out.println("");
					}

					else if (adminSelect1.equals("h") || adminSelect1.equals("H"))		//choice h: view best idea in database without deleting
					{
						System.out.println("");
						System.out.println("This is the best idea in the database.");
						System.out.println("");
						System.out.println("Best Idea: "+database.viewHead().getIdea());
						System.out.println("Idea Rating: "+database.viewHead().getRating());
						if (a.search(database.viewHead().getSSN())!=null && database.viewHead().getSSN()!=0)
							System.out.println("Idea Submitter: "+a.search(database.viewHead().getSSN()).getName());
						else if (database.viewHead().getSSN()==0)
							System.out.println("Idea Submitter: Administrator");
						else
							System.out.println("Idea Submitter: No longer in database");
						System.out.println("");
					}
					else if (adminSelect1.equals("exit") || adminSelect1.equals("Exit"))
					{
						break;
					}
					else
					{
						System.out.println("invalid input, please try again");
					}
				}
			}
			else if (user1.equals("exit") || user1.equals("Exit"))
				break;
			else
			{
				System.out.println("invalid input, please try again");
			}
		}
	
		try
		{
			PrintWriter studentsOut = new PrintWriter("students.txt");				//write StudentNodes to students.txt
			PrintWriter ideasOut = new PrintWriter("ideas.txt");
			
			StudentNode temp=studentList.viewHead();
			for (int i=0;i<studentList.length();i++)
			{
				studentsOut.print(temp.getName()+" ");
				studentsOut.print(temp.getEmail()+" ");
				studentsOut.print(temp.getSN()+" ");
				studentsOut.println(temp.getSSN()+" ");
				temp=temp.getNext();
			}
			studentsOut.close();
			
			IdeaNode temp1=ideaNumList.viewHead();						//write IdeaNodes to ideas.txt
			for(int z=0;z<ideaNumList.length();z++)
			{
				ideasOut.print(temp1.getIdeaNum()+" ");
				ideasOut.print(temp1.getSSN()+" ");
				ideasOut.print(temp1.getRating()+" ");
				ideasOut.print(temp1.isActive()+" ");
				ideasOut.println(temp1.getIdea()+" ");
				temp1=temp1.getNext2();
			}
			ideasOut.close();
		}
		
		catch(IOException e)
		{
			System.out.println("Input/output error " + e);
		}
	}	
}
