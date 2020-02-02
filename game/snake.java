package game;
import java.awt.Color;

import javax.swing.JFrame;

public class snake {
  public static void main(String[] args)
  {
	  //outer frame boder
	  JFrame obj= new JFrame();
	  GamePlay gameplay = new GamePlay();   //make obj of gameplay class
	  
	  obj.setBounds(10,10,905,700);
	  obj.setBackground(Color.DARK_GRAY);
	  obj.setResizable(false);
	  obj.setVisible(true);       // create new window where we run the game
	  obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  obj.add(gameplay);      //adding the obj of gameplay into the onj of JFrame
  }
  
}
