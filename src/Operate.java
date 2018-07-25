import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;
import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Operate {
	
	public static void main(String[] args)throws Exception
	{
		System.out.println("Server is in waiting state");
		
		ServerSocket ss=new ServerSocket(9477);
		
		Socket s=ss.accept();
		
		System.out.println("Connection established");
		
		OutputStream os=s.getOutputStream();
		PrintWriter out=new PrintWriter(os,true);
		
		
		BufferedReader br=new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		BufferedReader cin=new BufferedReader(new InputStreamReader(System.in));
		Scanner take=new Scanner(System.in);
		Record division=new Record();
		char continuing='y';
		 
		
		HashSet<queue> hs=new HashSet<queue>();
		
		
		
		do
		{
			
			out.println("\nEnter operation to perform\n\n1.Add Teacher\n2.Add Student\n3.Display Faculty information\n4.Display Students information\n5.Make queues of students for checking\n6.Display status of submission\n7.Allot teachers and give marks\n8.Display Marks\n9.Exit");
			
			String ch=br.readLine();
			int choice=Integer.parseInt(ch);
			
			switch(choice)
			{
			case 1:  
					 String a,b;
					 out.println("Enter name of the teacher:");
					 a=br.readLine();
					 
					 out.println("Enter the subject of teacher:");
					 b=br.readLine();
					 
					 Teacher p=new Teacher(a,b);
					 division.AddTeacher(p);
					 break;
					 
		    case 2:  
					 String c,d;
					 out.println("Enter name of the student:");
					 c=br.readLine();
					 int n;
					 out.println("Enter roll no:");
					 ch=br.readLine();
					 n=Integer.parseInt(ch);
					 //System.out.println(n);
					 out.println("Enter the subject:");
					 d=br.readLine();
					
					 Student v=new Student(c,n,d);
					 division.AddStudent(v);
					 break;
					 
			case 3:
					ArrayList<Teacher> t=new ArrayList<Teacher>();
					 t=(ArrayList<Teacher>)division.FacultyInfo();
					 ObjectOutputStream oos= new ObjectOutputStream(s.getOutputStream());
					 
					 oos.writeObject(t); 
					break;
					
			case 4:ArrayList<Student> st=new ArrayList<Student>();
					st=(ArrayList<Student>)division.StudentsInfo();
					ObjectOutputStream oos1= new ObjectOutputStream(s.getOutputStream());
					oos1.writeObject(st); 
					
					break;
					
			case 5: division.extract();
					out.println("Done!");
					break;
					 
			case 6: 
					hs=division.showStatus();
					ObjectOutputStream oos2=new ObjectOutputStream(s.getOutputStream());
					oos2.writeObject(hs);
					break;
					
			case 7: division.mapping();
					HashMap<Teacher,Queue<Student>> m=new HashMap<Teacher,Queue<Student>>();
					
					m=(HashMap<Teacher,Queue<Student>>)division.assign();
					ObjectOutputStream oos3=new ObjectOutputStream(s.getOutputStream());
					oos3.writeObject(m);
					
					break;
					
			case 8: HashMap<Teacher,Queue<Student>> hm=new HashMap<Teacher,Queue<Student>>();
			
					hm=(HashMap<Teacher,Queue<Student>>)division.map();
					ObjectOutputStream oos4=new ObjectOutputStream(s.getOutputStream());
					oos4.writeObject(hm);
					break;
					
			case 9: continuing=br.readLine().charAt(0);
					
					break;
					
					
			default: break;
					 
		   
			}
		
		}while(continuing=='y');
	}

}