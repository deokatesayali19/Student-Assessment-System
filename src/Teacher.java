import java.io.Serializable;

public class Teacher implements Serializable{
	String name;
	String specialization;
	Teacher()
	{
		
	}
	Teacher(String name,String specialization)
	{
		this.name=name;
		this.specialization=specialization;
	}
	void displayTeacher()
	{
		System.out.println("Name:"+name);
		System.out.println("Co-ordinating : "+specialization);
	}
	String retsubject()
	{
		return specialization;
	}

}