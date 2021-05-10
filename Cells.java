import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

public class Cells extends JComponent
{  
   private boolean open = false; 
   private boolean bomb = false;
   private boolean flag = false;
   private boolean bang = false;
   private boolean won = false;
   private static boolean end = false;

   private  int countBombNear = 0;
   static private int countOpenCells = 0;  
   private int x;
   private int y;

   private static Color[] color = { Color.BLUE, Color.GREEN, Color.RED, Color.MAGENTA, Color.PINK};

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

   public void isFlag()
   {
      flag = !flag;
   }

   public void setCountNearBomb (int count)
   {
      countBombNear = count;
   }
   
   public int getCountNearBomb ()
   {
      return countBombNear;
   }

   public boolean getBang ()
   {
      return bang;
   }

   public boolean getWon ()
   {
      return won;
   }

   public boolean getFail ()
   {  
      return bang;
   }

   public boolean End (boolean _end)
   {
      return end = _end;
   }

   public void openCell()
   {
      open = true;
      bang =  bomb;
      countOpenCells++;
      if(countOpenCells >= 71){ won = true;}
      
   }

   private void paintBomb(Graphics g) 
   {  
      g.setColor(Color.BLACK);
      g.fillOval(x*30 + 7, y*30 + 7 , 15, 15);
      g.fillRect(x*30 + 5, y*30 + 13, 20, 4);
      g.fillRect(x*30 + 13, y*30 + 5, 4, 20);
      g.setColor(Color.WHITE);
      g.fillRect(x*30 + 11, y*30 + 10, 4, 4); 
   }

   private void paintBang(Graphics g)
   {  
      g.setColor(Color.RED);
      g.fillOval(x*30 + 7, y*30 + 7 , 15, 15);
      g.fillRect(x*30 + 5, y*30 + 13, 20, 4);
      g.fillRect(x*30 + 13, y*30 + 5, 4, 20);
      g.setColor(Color.ORANGE);
      g.fillRect(x*30 + 11, y*30 + 10, 4, 4); 
   }

   private void paintString (Graphics g, int num)
   { 
      g.setColor(color [countBombNear - 1]);
      g.setFont(new Font("", Font.BOLD, 30));
      g.drawString(Integer.toString(num), x*30 + 8, y*30 + 26);
   }

   private void paintflag (Graphics g, String str)
   {
      g.setColor(Color.red);
      g.setFont(new Font("", Font.BOLD, 30));
      g.drawString(str, x*30 + 8, y*30 + 26);
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

        if (!open)
        {
          if (isBombed() && end == true) paintBomb(g);
            else
               {
                  g.setColor(Color.lightGray);
                  g.fill3DRect(x*30,y*30, 30, 30, true);
                  if (flag) paintflag(g, "f");
               }  
         }
         else 
               if (isBombed() && bang ) paintBang(g);
               else
                        if (countBombNear > 0)
                        paintString(g, countBombNear);
      } 
    
}   
