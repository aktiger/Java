import javax.swing.*;

public class simpleFrameTest
{

    public static void main(String[] args)
    {
        simpleFrame frame = new simpleFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
    }

}

class simpleFrame extends JFrame
{
    public simpleFrame()
    {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

}


