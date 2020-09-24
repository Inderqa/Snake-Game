import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;


public class GamePlay extends JPanel implements KeyListener,ActionListener{
	private ImageIcon titleImage;

	private int []snakexlength=new int[750];
	private int []snakeylength=new int[750];
	private boolean left = false,right=false,up=false,down=false;

	private ImageIcon upmouth,downmouth,rightmouth,leftmouth,snakeimage;
	private Timer timer;
	private int delay=100;
	private int los=3;
	private int moves=0,score=0;
	private int enemyx[]={150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700};
	private int enemyy[]={150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600};
	ImageIcon enemy;
	private Random random=new Random();
	private int xpos,ypos;
	private boolean play=true;
	public GamePlay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay,this);
		timer.start();
	}
	public void paint(Graphics g)
	{
		if(moves==0)
		{
			snakexlength[2]=50;
			snakexlength[1]=75;
			snakexlength[0]=100;

			snakeylength[2]=100;
			snakeylength[1]=100;
			snakeylength[0]=100;
		}

		//Image Border
		g.setColor(Color.GRAY);
		g.drawRect(24, 10, 851, 55);


		//title
		titleImage=new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this,g,25,11);

		//play area
		g.setColor(Color.white);
		g.drawRect(24, 74, 851, 577);

		//background
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);


		//game over
		if(snakexlength[0]>825||snakexlength[0]<50||snakeylength[0]>600||snakeylength[0]<100)
		{
			play=false;
			g.setColor(Color.red);
			g.setFont(new Font("Algerian",2,40));
			g.drawString("Game Over...\nYour Scores : "+score,200, 300);
			moves=0;
		}

		//enemy
		enemy=new ImageIcon("enemy.png");
		enemy.paintIcon(this,g,enemyx[xpos],enemyy[ypos]);

		//score
		g.setColor(Color.blue);
		g.setFont(new Font("Algerian",2,30));
		g.drawString(""+score, 750, 110);


		//snake
		rightmouth=new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this,g,snakexlength[0],snakeylength[0]);
		for(int a=0;a<los;a++)
		{
			if(a==0&&right)
			{
				rightmouth=new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);

			}
			if(a==0&&left)
			{
				leftmouth=new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);

			}
			if(a==0&&up)
			{
				upmouth=new ImageIcon("upmouth.png");
				upmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);

			}
			if(a==0&&down)
			{
				downmouth=new ImageIcon("downmouth.png");
				downmouth.paintIcon(this,g,snakexlength[a],snakeylength[a]);

			}
			if(a!=0)
			{
				snakeimage=new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this,g,snakexlength[a],snakeylength[a]);

			}
		}

		g.dispose();

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		if(play)
		{
			if(right)
			{
				for(int a=los-1;a>=0;a--)
					snakeylength[a+1]=snakeylength[a];

				for(int a=los;a>=0;a--)
				{	if(a==0)
					snakexlength[a]+=25;
				else
					snakexlength[a]=snakexlength[a-1];
				}
				repaint();
			}
			if(left)
			{
				for(int a=los-1;a>=0;a--)
					snakeylength[a+1]=snakeylength[a];

				for(int a=los;a>=0;a--)
				{	if(a==0)
					snakexlength[a]-=25;
				else
					snakexlength[a]=snakexlength[a-1];
				}
				repaint();
			}
			if(up)
			{
				for(int a=los-1;a>=0;a--)
					snakexlength[a+1]=snakexlength[a];

				for(int a=los;a>=0;a--)
				{	if(a==0)
					snakeylength[a]-=25;
				else
					snakeylength[a]=snakeylength[a-1];
			
				}	
				repaint();
			}
			if(down)
			{
				for(int a=los-1;a>=0;a--)
					snakexlength[a+1]=snakexlength[a];

				for(int a=los;a>=0;a--)
				{	if(a==0)
					snakeylength[a]+=25;
				else
					snakeylength[a]=snakeylength[a-1];
				}
				repaint();

			}
		}
			if(snakexlength[0]==enemyx[xpos]&&snakeylength[0]==enemyy[ypos])
			{
				score+=100;
				los++;
				xpos=random.nextInt(23);
				ypos=random.nextInt(19);
				repaint();
			}
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {}
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode()==KeyEvent.VK_RIGHT)
			{
				right=true;
				if(!left)
				{
					right=true;
				}
				else
				{
					right=false;
					left=true;
				}
				up=false;
				down=false;
				moves++;
			}
			if(arg0.getKeyCode()==KeyEvent.VK_LEFT)
			{

				left=true;
				if(!right)
				{
					left=true;
				}
				else
				{
					left=false;
					right=true;
				}
				up=false;
				down=false;
				moves++;
			}
			if(arg0.getKeyCode()==KeyEvent.VK_UP)
			{
				up=true;
				if(!down)
				{
					up=true;
				}
				else
				{
					up=false;
					down=true;
				}
				left=false;
				right=false;
				moves++;
			}
			if(arg0.getKeyCode()==KeyEvent.VK_DOWN)
			{
				down=true;
				if(!up)
				{
					down=true;
				}
				else
				{
					down=false;
					up=true;
				}
				left=false;
				right=false;
				moves++;
			}

			if(arg0.getKeyCode()==KeyEvent.VK_ENTER)

			{
				if(!play)
				{
				play=true;
				moves=0;
				los=3;
				delay=100;
				score=0;
				repaint();
				left = false;
				right=false;
				up=false;
				down=false;
				}
			}
			if(arg0.getKeyCode()==KeyEvent.VK_X)///////////////////////////////////decrease length of snake
			{ if(los>1)
				los--;
			}

			if(arg0.getKeyCode()==KeyEvent.VK_B)///////////////////////////////////increase length of snake
			{ los++;
			}
			if(arg0.getKeyCode()==KeyEvent.VK_S)///////////////////////////////////increase score by 1000000
			{ score+=1000;
			}
			if(arg0.getKeyCode()==KeyEvent.VK_L)///////////////////////////////////decrease score by 1000000
			{ if(score>=1000)
				score-=1000;
			}
		
		repaint();

	}
	@Override
	public void keyTyped(KeyEvent arg0) {}
}
