import java.net.*;
public class domainname
{
    public static void main(String args[])
    {
    	try
	{
            InetAddress add1 = InetAddress.getByName("www.baidu.com");
	    System.out.println(add1.toString());
	    InetAddress add2 = InetAddress.getByName("202.112.14.184");
	    System.out.println(add2.toString());
	}
	catch(UnknownHostException e)
	{
	    System.out.println("can not found www.baidu.com");
	}

    }
}
