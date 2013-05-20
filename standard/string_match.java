import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class string_match{
    public static void main(String[] agrs)
    {
	string_match	test = new string_match();
	System.out.println("hello mystring match");
	System.out.println(test.my_find("breakfastf ood", "f*d"));
    }
public String my_find(String input, String rule)
{
    System.out.println(input.length());
    StringBuffer	sb  = new StringBuffer();
    StringBuffer	sb1 = new StringBuffer();
    for(int i = 0; i < rule.length(); i++)
    {
	char	c = rule.charAt(i);
	if(c == '*')
	{
	    sb.append("[\\w\\W]*");
	}
	else if (c == '?')
	    sb.append("[\\w\\W");
	else
	    sb.append(c);
    }
    Pattern	p	= Pattern.compile(sb.toString());
    Matcher	matcher = p.matcher(input);
    while(matcher.find())
    {
	sb1.append(matcher.group() + " ");
    }
    if(sb1.length() > 0)
    {
	sb.deleteCharAt(sb1.length() - 1);
    }
    return sb1.toString();
}

}
