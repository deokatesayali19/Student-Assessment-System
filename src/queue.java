import java.io.Serializable;
import java.util.PriorityQueue;
import java.util.Queue;
public class queue implements Serializable{
	Queue<Student> q=new PriorityQueue<Student>();
	String ssubject;
	queue()
	{
		
	}
	queue(PriorityQueue<Student> a,String b)
	{
		q=a;
		ssubject=b;
	}

}