import java.io.Serializable;

public class Student implements Serializable{
	String name;
	int roll_no;
	int marks;
	String remark;
	String subject;
	
	Student()
	{
		marks=0;
		remark="\0";
		
	}
	Student(String name,int roll_no,String subject)
	{
		this.name=name;
		this.roll_no=roll_no;
		this.subject=subject;
	}
	public int compareTo(Student current)
	{
		if(roll_no<current.roll_no)
			return 1;
		else if(roll_no>current.roll_no)
			return -1;
		else
			return 0;
	}
		void displayStudent()
	{
		System.out.println("Name:" +name);
		System.out.println("Roll No:" +roll_no);
		System.out.println("Subject:"+subject);
		System.out.println("\n\n");
		
	}
		void displayResult()
		{
			System.out.println("Name:" +name);
			System.out.println("Roll No:" +roll_no);
			System.out.println("Marks:" +marks);
			System.out.println("Remark:" +remark);
		}

}