package standard;
import java.util.*;

/*
 * 2012-11-10 15:57:05
 * The java file's name can not be TreeSet, cause the library have
 * used it!!
 * */
public class Tree_Set 
{
	public static void main(String args[])
	{
		TreeSet<String> x = new TreeSet<String>();
		
		x.add("girl");
		x.add("zoo");
		x.add("apple");
		x.add("boy");

		Iterator<String> it = x.iterator();
		while(it.hasNext())
		{
			System.out.println(it.next()+" ");
		}
	}

}
