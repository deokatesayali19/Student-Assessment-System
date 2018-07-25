import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;
import java.sql.*;
public class Record implements Serializable{
	
	
	static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://127.0.0.1:3306/sdl3";

	static final String USER="root";
	static final String PASS="sayali";

	ArrayList<Student> s=new ArrayList<Student>();
	ArrayList<Teacher> t=new ArrayList<Teacher>();
	HashMap<Teacher,Queue<Student>> m=new HashMap<Teacher,Queue<Student>>();
	HashSet<queue> hs=new HashSet<queue>();
	
	String sql;
	Connection conn;
	Statement stmt=null;
	ResultSet rs=null;
	PreparedStatement ps=null;
	
	Record()
	{
		try {
			
	
		Class.forName("com.mysql.jdbc.Driver");
		conn=DriverManager.getConnection(DB_URL,USER,PASS);
		System.out.println("Creating System....");
		stmt=conn.createStatement();
		}
		catch(Exception e) {
	}
	}
	void AddTeacher(Teacher a)
	{
		String n=a.name;
		String spl=a.specialization;
		System.out.println(n);
		try {
		ps=conn.prepareStatement("insert into Teacher(name,specialization) values(?,?)");
		ps.setString(1,n);
		ps.setString(2, spl);
		ps.executeUpdate();
		}catch(SQLException e) {
			
		}
	}
	void AddStudent(Student b)
	{
		String n=b.name;
		int r=b.roll_no;
		String s=b.subject;
		try {
			ps=conn.prepareStatement("insert into Student(name,roll_no,subject) values(?,?,?)");
			ps.setString(1,n);
			ps.setInt(2, r);
			ps.setString(3, s);
			ps.executeUpdate();
		}catch(SQLException e) {
			
		}
	}
	ArrayList<Teacher> FacultyInfo()
	{
		sql="select * from Teacher";
		try {
		rs=stmt.executeQuery(sql);
		while(rs.next()) {
			String n=rs.getString("name");
			String spl=rs.getString("specialization");
			t.add(new Teacher(n,spl));
		}
		}catch(SQLException e) {
			
		}
		return t;
		
	}
	ArrayList<Student> StudentsInfo()
	{
		sql="select * from Student";
		try {
		rs=stmt.executeQuery(sql);
		while(rs.next()) {
			String n=rs.getString("name");
			int r=rs.getInt("roll_no");
			String vs=rs.getString("subject");
			s.add(new Student(n,r,vs));
		}
		}catch(SQLException e) {
			
		}
		return s;
	}
	void extract()
	{
		Iterator i=s.iterator();
		while(i.hasNext())
		{
			Object r=i.next();
			Student f=((Student)r);
			submit(f);
		}
		
	}
	void submit(Student p)
	{
		String sub=p.subject;
		String k;
		if(hs.isEmpty())
			
			{
				queue d=new queue();
				d.ssubject=sub;
				d.q.add(p);
				hs.add(d);
			}
		else
		{
			Iterator i=hs.iterator();
			while(i.hasNext())
			{
				Object r=i.next();
				k=((queue)r).ssubject;
				if((k.equalsIgnoreCase(sub)))
				{
					((queue)r).q.add(p);
				}
				else
				{
					queue n=new queue();
					n.ssubject=sub;
					n.q.add(p);
					hs.add(n);
				}
			}

		}
		
		
		
		
	}
	
	HashMap<Teacher,Queue<Student>> assign()
	{
		
		
		
		return m;
		
	}
	
	HashSet<queue> showStatus() 
	{
		
		return hs;
		
	}
	HashMap<Teacher,Queue<Student>> map()
	{
		return m;
	}
	
	void mapping()
	{
		Iterator i=t.iterator();
		
		String s,t;
		Queue<Student> f=new PriorityQueue<Student>();
		while(i.hasNext())
		{
			Object teacher=i.next();
			t=((Teacher)teacher).specialization;
			Iterator j=hs.iterator();
			while(j.hasNext())
			{
				Object student=j.next();
				s=((queue)student).ssubject;
				f=((queue)student).q;
				if(s.equalsIgnoreCase(t))
				{
					
					Teacher x=new Teacher();
					x=((Teacher)teacher);
					m.put(x, f);
				}
				
			}
		}
		
		
	}
	
	

}