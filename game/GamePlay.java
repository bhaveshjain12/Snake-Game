package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.font.*;
import java.util.Random;
import java.awt.Graphics;   //for graphics
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.*;

                                    // keylistener and actionListner are 2 intefaces and these conatin
                                    // abstract method  that we need to implement inside the class which
                                    // implements these interfaces

public class GamePlay  extends JPanel implements KeyListener ,ActionListener{
	private ImageIcon titleImage;
	
	//to define the position of the snake in the panel so we use 2 arrays for x position and y position
	private int[] snake_Xlength=new int[750];
	private int[] snake_Ylength=new int[750];
			
	//for the detecting the direction of snake we need 4 boolean variable
	private boolean left=false;
	private boolean right=false;
	private boolean up=false;
	private boolean down=false;
	
	//need 4 varaiables for thr particular facce of snake
	private ImageIcon rightmouth;
	private ImageIcon leftmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	
	private int lengthsnake=3;
	//defualt length of snake
	
	
	//class which manage the speed of snake inside the panel
	private Timer timer;
	//speed of timer
	private int delay=100;
	//image of snake body
	private ImageIcon snakeImage;
	 
	//default position for the pickup
	private int[] enemyXpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,
	                         350,375,400,425,475,500,525,550,575,600,625,650,
	                         675,700,725,750,775,800,825,850};
	private int[] enemyYpos= {25,50,75,100,125,150,175,200,225,250,275,300,325,
            350,375,400,425,475,500,525,550,575,600,625,650,
            675,700,725,750,775,800,825,850};
	
	private ImageIcon enemyimage;
	
	private Random random=new Random();
	private int xpos=random.nextInt(34);//34 is the total number of horizontal position
	private int ypos=random.nextInt(23);
	
	private int score=0;
	private int moves =0;
	
	public GamePlay()  //constructor
	{
		addKeyListener(this)   ;  //this keyword is used for gameplay class ,,this is obj of class which implents the keylistener
     	setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		//instantiate our timer class
		timer = new Timer(delay,this);
		timer.start();
	}
	
	public void paint(Graphics g)
	{
		if(moves==0)  // it will detect if the game has just started then set the default position of snake  to this
		{	                              //if game is alreday started and we are in the play mode then dont check this bcz we 
		  snake_Xlength[2]=50;	          //increment this
		  snake_Xlength[1]=75;	 
		  snake_Xlength[0]=100;	
		  
		  snake_Ylength[2]=100;	
		  snake_Ylength[1]=100;	
		  snake_Ylength[0]=100;	
		  
		
	    }
		// draw title image border
		g.setColor(Color.WHITE );
		g.drawRect(24, 10, 851, 55);
		
		//draw the title image
		titleImage= new ImageIcon("snaketitle.jpg");
		titleImage.paintIcon(this, g,25,11);
		
		//draw border for plain area gameplay
       g.setColor(Color.WHITE);
       g.drawRect(24, 74, 851, 577);
       
       //draw background for the gameplay
       g.setColor(Color.BLACK);
       g.fillRect(25, 75, 850, 575);  //multiple of 25  so that snake can switch in this gameplay area
       
       //draw score
       g.setColor(Color.WHITE);
       g.setFont(new Font("arial",Font.PLAIN,14));
       g.drawString("SCORES : "+score, 780,30);
       
       //draw length of the snake
       g.setColor(Color.WHITE);
       g.setFont(new Font("arial",Font.PLAIN,14));
       g.drawString("LENGTH : "+lengthsnake, 780,50);
       
       //draw the snake at the default position at the top left corner of our blackish area of our panel
       
       rightmouth= new ImageIcon("rightmouth.png");
       rightmouth.paintIcon( this, g , snake_Xlength[0], snake_Ylength[0]);
       
       //detect the direction of snake
       for(int a=0;a<lengthsnake;a++)
       {
    	   if(a==0 && right)
    	   {
    		   rightmouth= new ImageIcon("rightmouth.png");
    	       rightmouth.paintIcon( this, g , snake_Xlength[a], snake_Ylength[a]);
    	   }
    	   if(a==0 && left)
    	   {
    		   leftmouth= new ImageIcon("leftmouth.png");
    	       leftmouth.paintIcon( this, g , snake_Xlength[a], snake_Ylength[a]);
    	   }
    	   if(a==0 && up)
    	   {
    		   upmouth= new ImageIcon("upmouth.png");
    	       upmouth.paintIcon( this, g , snake_Xlength[a], snake_Ylength[a]);
    	   }
    	   if(a==0 && down)
    	   {
    		   downmouth= new ImageIcon("downmouth.png");
    	       downmouth.paintIcon( this, g , snake_Xlength[a], snake_Ylength[a]);
    	   }
    	   if(a!=0)  // tmeans this not the face bcz indentifies the first index of the snake 
    	   {                //this condition means snake head has beem drwan now we need to draw the body of snake
    		  snakeImage= new ImageIcon("snakeimage.png");
    	       snakeImage.paintIcon( this, g , snake_Xlength[a], snake_Ylength[a]);
    	   }
       }
       
       
       enemyimage=new ImageIcon("enemy.png");
       if((enemyXpos[xpos]==snake_Xlength[0] )&& (enemyYpos[ypos]==snake_Ylength[0]))
       {
    	   score++;
    	   lengthsnake++;
    	   xpos=random.nextInt(34);
    	   ypos=random.nextInt(23);
       }
       
       enemyimage.paintIcon(this,g,enemyXpos[xpos],enemyYpos[ypos]);
       
       //to check collision
       for(int b=1;b<lengthsnake;b++)
       {
    	   if(snake_Xlength[b]==snake_Xlength[0] && snake_Ylength[b]==snake_Ylength[0])
    	   {
    		   right=false;
    		   left=false;
    		   up=false;
    		   down=false;
    		   
    		   g.setColor(Color.WHITE);
    		   g.setFont(new Font("arial",Font.BOLD,50));
    		   g.drawString("GAME OVER",300, 300);
    		   
    		   g.setColor(Color.WHITE);
    		   g.setFont(new Font("arial",Font.BOLD,30));
    		   g.drawString("Space to restart",350, 340);
    	   }
       }
       g.dispose(); 
       
	}
 
	// added different methods required this is the methods that comes from the Actionlistener 
	
	@Override                    
	public void actionPerformed(ActionEvent arg0) { //this method detect when timer.start() is triggered
		timer.start()  ;// restart the timer

		if(right)
		{
			for(int r=lengthsnake-1;r>=0;r--)
			{
				snake_Ylength[r+1]=snake_Ylength[r];
			}
			
			//shiftd the position of snake to x length
			for(int r=lengthsnake;r>=0;r--)
			{
			    if(r==0)
			    {
			    	snake_Xlength[r]=snake_Xlength[r]+25;
			    }
			    else
			    {
			    	snake_Xlength[r]=snake_Xlength[r-1];
			    }
			    if(snake_Xlength[r]>850)
			    {
			    	snake_Xlength[r]=25;
			    }
			    
			}
			repaint();   //call paint mathod automatically
		}
		if(left)
		{
			for(int r=lengthsnake-1;r>=0;r--)
			{
				snake_Ylength[r+1]=snake_Ylength[r];
			}
			
			//shiftd the position of snake to x length
			for(int r=lengthsnake;r>=0;r--)
			{
			    if(r==0)
			    {
			    	snake_Xlength[r]=snake_Xlength[r]-25;
			    }
			    else
			    {
			    	snake_Xlength[r]=snake_Xlength[r-1];
			    }
			    if(snake_Xlength[r]<25)
			    {
			    	snake_Xlength[r]=850;
			    }
			    
			}
			repaint();   //call paint mathod automatically
		}
		if(up)
		{
			for(int r=lengthsnake-1;r>=0;r--)
			{
				snake_Xlength[r+1]=snake_Xlength[r];
			}
			
			//shiftd the position of snake to x length
			for(int r=lengthsnake;r>=0;r--)
			{
			    if(r==0)
			    {
			    	snake_Ylength[r]=snake_Ylength[r]-25;
			    }
			    else
			    {
			    	snake_Ylength[r]=snake_Ylength[r-1];
			    }
			    if(snake_Ylength[r]<75)
			    {
			    	snake_Ylength[r]=625;
			    }
			    
			}
			repaint();   //call paint mathod automatically
		}
		if(down)
		{
			for(int r=lengthsnake-1;r>=0;r--)
			{
				snake_Xlength[r+1]=snake_Xlength[r];
			}
			
			//shiftd the position of snake to x length
			for(int r=lengthsnake;r>=0;r--)
			{
			    if(r==0)
			    {
			    	snake_Ylength[r]=snake_Ylength[r]+25;
			    }
			    else
			    {
			    	snake_Ylength[r]=snake_Ylength[r-1];
			    }
			    if(snake_Ylength[r]>625)
			    {
			    	snake_Ylength[r]=275;
			    }
			    
			}
			repaint();   //call paint mathod automatically
		}
		
	}
    
	//these 3 methods comes from KeyListener
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			moves=0;
			score=0;
			lengthsnake=3;
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			moves++;
			right = true;
			if(!left)   //for dont collide
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
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			moves++;
			left = true;
			if(!right)   //for dont collide
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
		}
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{
			moves++;
			up = true;
			if(!down)   //for dont collide
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
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{
			moves++;
			down = true;
			if(!up)   //for dont collide
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
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
