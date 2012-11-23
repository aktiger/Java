//package standard;
/*
 2012-11-10 16:07:21
 Only one public class in a java file is allowed! 
 In java you can use class as variable name, which is not recommend,
 cause it will confuse the reader that a static method is in a class,
 but in fact it's not!!
 */

public class multithread 
{
	public static void main(String args[])
	{
		lefthand lefthand = new lefthand();
		lefthand.start();//people may think that stat() is a static method!!
		righthand righthand = new righthand();
		righthand.start();
		
		for (int i =0; i<10; i++)
		{
			System.out.println("I'm parent process");
		}
	}
	
}

class lefthand extends Thread
{
	public void run()
	{
		for (int i =0; i<10; i++)
		System.out.println("I'm lefthand");
	}
}

class righthand extends Thread
{
	public void run()
	{
		for (int i=0; i<10; i++)
		{
			System.out.println("I'm righthand");
		}
	}
}


