import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

public class Cells extends JComponent
{  
   private boolean click = false;   
   private int x;
   private int y;
   boolean open() 
   {
    return false;
   }
    
    boolean mined()
   {
    return false;
   }

   public void isClick ()
   {
      if (click)  {click = false;}
      else if (!click) {click = true;}
   }


   public void click( Graphics g)
   {
      if (click == true)
      g.setColor(Color.RED);
      else
      g.setColor(Color.lightGray); 
   }
   public void setCoord(int x1,  int y2)
   {
      x = x1;
      y = y2;
   }


   protected void paintComponent(Graphics g)
    {
        click(g);
        g.drawRect(x*30 , y*30, 30, 30);
    }



}   
