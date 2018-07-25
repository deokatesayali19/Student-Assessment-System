
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.io.*;
public class Client {
	static void putMarks(Iterator i)
	{
		Scanner scan=new Scanner(System.in);
		while(i.hasNext())
		{
			int mks;
			String rmk;
			Object r=i.next();
			Student d=((Student)r);
			int s=((Student)r).roll_no;
			System.out.println("Enter Marks and remark of Roll no "+s);
			mks=scan.nextInt();
			rmk=scan.next();
			d.marks=mks;
			d.remark=rmk;
			
			
			
		}
	}
	

	public static void main(String[] args)throws Exception {
		Socket s=new Socket("localhost",9477);
		
		OutputStream os=s.getOutputStream();
		InputStream is= s.getInputStream();
		PrintWriter out=new PrintWriter(os,true);
		
		BufferedReader br=new BufferedReader(new InputStreamReader(is));
		
		BufferedReader cin=new BufferedReader(new InputStreamReader(System.in));
		
		Scanner sc=new Scanner(System.in);
		
		ObjectInputStream ooi;

		HashSet<queue> hs=new HashSet<queue>();
		

		
		char continuing='y';
		
		
		do
		{
			continuing='y';
			char menu[]=new char[500];
	        br.read(menu);
		System.out.println(menu);
		int choice=sc.nextInt();
		out.println(choice);
		
		switch(choice)
		{
		case 1:
			        String a,b;
				a=br.readLine();
				System.out.println(a);
				a=cin.readLine();
				out.println(a);
				b=br.readLine();
				System.out.println(b);
				b=cin.readLine();
				out.println(b);
				break;

		case 2:
				String c,d;
				int n;
				c=br.readLine();
				System.out.println(c);
				c=cin.readLine();
				out.println(c);
				c=br.readLine();
				System.out.println(c);
				n=sc.nextInt();
				out.println(n);
				d=br.readLine();
				System.out.println(d);
				d=cin.readLine();
				out.println(d);
				break;
				
		case 3: 
				ooi=new ObjectInputStream(is);
				
				ArrayList<Teacher> t=new ArrayList<Teacher>();
				t=(ArrayList<Teacher>)ooi.readObject();
				Iterator i=t.iterator();
				while(i.hasNext())
				{
					Object r=i.next();
					Teacher x=(Teacher)r;
					x.displayTeacher();
				}
				break;
				
		case 4:ooi=new ObjectInputStream(is);
		
				ArrayList<Student> st=new ArrayList<Student>();
				st=(ArrayList<Student>)ooi.readObject();
				Iterator j=st.iterator();
				while(j.hasNext())
					{
					Object r=j.next();
					Student x=(Student)r;
					x.displayStudent();
					}
				break;
		case 5: String v=br.readLine();
				System.out.println(v);
				break;
		case 6: ooi=new ObjectInputStream(is);
				
				hs=(HashSet<queue>)ooi.readObject();
				Iterator k=hs.iterator();
				String yo;
				while(k.hasNext())
				{
					Object r=k.next();
					yo=((queue)r).ssubject;
					System.out.println(yo +":");
					
					for(Student ll:((queue)r).q)
					{
						System.out.println(ll.roll_no);
					}
				}
				break;
				
		case 7: ooi=new ObjectInputStream(is);
				HashMap<Teacher, Queue<Student>> m=new HashMap<Teacher,Queue<Student>>();
				m=(HashMap<Teacher, Queue<Student>>)ooi.readObject();
				Scanner scan=new Scanner(System.in);
				int ch=1;
				do
				{
				System.out.println("Enter subject to give remarks: ");
				String cchoice;
				cchoice=scan.next();
				Iterator ic=hs.iterator();
				while(ic.hasNext())
				{
					Object r=ic.next();
					String sub=((queue)r).ssubject;
					if(sub.equalsIgnoreCase(cchoice))
					{
						Iterator l=((queue)r).q.iterator();
						putMarks(l);
					}
				}
				System.out.println("Want to check assignments of other subject(1/0):");
				ch=scan.nextInt();
				}while(ch==1);
				
				break;
		case 8: ooi=new ObjectInputStream(is);
				HashMap<Teacher, Queue<Student>> hm=new HashMap<Teacher,Queue<Student>>();
				hm=(HashMap<Teacher, Queue<Student>>)ooi.readObject();
				Iterator ii=hm.keySet().iterator();
				Teacher proff=new Teacher();
				Queue<Student> q=new PriorityQueue<Student>();
				
				while(ii.hasNext())
				{
					Object key=ii.next();
					Object value=hm.get((Teacher)key);
					proff=((Teacher)(key));
					q=((PriorityQueue<Student>)value);
					System.out.println("Teacher :"+proff.name+ " of subject "+proff.specialization);
					System.out.println("Has checked the assignments of following students and the result is:");
					for(Student f:q)
					{
						f.displayResult();
						System.out.println("\n");
					}
					System.out.println("\n\n");
					
				}
				break;
		case 9: continuing='n';
				out.println(continuing);
				break;
				
		default:break;
		
		}
		
		
		}while(continuing=='y');
		
		
		
		
		
		
		
		
		
		

	}

}