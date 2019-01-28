import java.awt.Dimension;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame{

	private Dimension size = new Dimension(600,600);
	
	
	public void init(){
		
		setSize(size);
		setTitle("CS-2D Server");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
}
