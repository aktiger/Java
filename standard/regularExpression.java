package standard;

public class regularExpression 
{
	public static void main(String args[])
	{
		String regex = "\\w{1,}@\\w{1,}\56\\w{1,}";
		String justin="uestczhangchao@gmail.com";
		if(justin.matches(regex))
		{
			System.out.println("this email address is correct!");
		}
		else
		{
			System.out.println("invalid email address!");
		}
	}

}
