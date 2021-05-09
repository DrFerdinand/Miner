import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

public class Cells extends JComponent
{  
   private boolean open = false; 
   private boolean bomb = false;  
   private int x;
   private int y;

   
   

   public boolean isOpen() 
   {
    return open;
   }
   public boolean isBombed()
   {
      return bomb;
   }

   public void setBomb()
   {
      bomb = true;
   } 

   public void isClick ()
   {
      open = true;
   }

   private void paintBomb(Graphics g) 
   {  
      g.setColor(Color.BLACK);
      g.fillOval(x*30 + 7, y*30 + 7 , 15, 15);
      g.fillRect(x*30 + 5, y*30 + 13, 20, 4);
      g.fillRect(x*30 + 13, y*30 + 5, 4, 20);
      g.setColor(Color.WHITE );
      g.fillRect(x*30 + 11, y*30 + 10, 4, 4); 
   }
   public void openCell(Graphics g)
   {
      if (open == true)
      {
      g.setColor(Color.WHITE);
      g.fillRect(x*30 , y*30, 30, 30);
      g.setColor(Color.BLACK);
      }
   }
   public void setCoord(int x1,  int y2)
   {
      x = x1;
      y = y2;
   }

   protected void paintComponent(Graphics g)
    {    
        g.setColor(Color.lightGray);
        g.drawRect(x*30 , y*30, 30, 30);
        if(isBombed()) paintBomb(g);
        else
        {
           g.setColor(Color.lightGray);
           g.fill3DRect(x*30,y*30, 30, 30, true);
        }
    }

}   
