import java.awt.Color;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;


public class snake extends JFrame{
	public static void main(String ar[])
	{
		JFrame f=new JFrame("Snake Game");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GamePlay g=new GamePlay();
		f.add(g);
		f.setVisible(true);
		f.setBounds(10, 10, 905, 700);
		f.setResizable(false);
		
		f.setSize(900, 700);
		f.setBackground(Color.DARK_GRAY);
	
		}
}