import java.awt.*;
import javax.swing.*;
import java.util.*;
 
public  class Cells extends JComponent
{  
   private boolean open = false; 
   private boolean bomb = false;
   private boolean flag = false;
   private boolean bang = false;
   private boolean won = false;
 
   private static boolean end = false;
   private static int rows;
   private static int bombs;
   private static int sizeOfSqrt = 30;
   private static  int countOpenCells = 0;
 
   private  int countBombNear;  
   private int x;
   private int y;
 
   private static Color[] color = { Color.BLUE, Color.GREEN, Color.RED, Color.MAGENTA, Color.PINK};
 
   public void setFieldSize(int rows1, int bombs1)
   {
      bombs = bombs1;
      rows = rows1;
   }
 
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
 
   public void refresh()
   {
      end = false;
      countOpenCells = 0;  
   }
 
   public void openCell()
   {
      open = true;
      bang =  bomb;
      countOpenCells++;
      if(countOpenCells >= rows*rows-bombs){ won = true;}
 
   }
 
   private void paintflag (Graphics g, String str)
   {
      g.setColor(Color.red);
      g.setFont(new Font("", Font.BOLD, sizeOfSqrt));
      g.drawString(str, x*sizeOfSqrt + 8, y*sizeOfSqrt + 26);
   }
 
   public void setCoord(int x1,  int y2)
   {
      x = x1;
      y = y2;
   }
 
   private void paintBomb(Graphics g) 
   {  
      g.setColor(Color.BLACK);
      g.fillOval(x*sizeOfSqrt + 7, y*sizeOfSqrt + 7 , 15, 15);
      g.fillRect(x*sizeOfSqrt + 5, y*sizeOfSqrt + 13, 20, 4);
      g.fillRect(x*sizeOfSqrt+ 13, y*sizeOfSqrt + 5, 4, 20);
      g.setColor(Color.WHITE);
      g.fillRect(x*sizeOfSqrt + 11, y*sizeOfSqrt + 10, 4, 4); 
   }
 
   private void paintBang(Graphics g)
   {  
      g.setColor(Color.RED);
      g.fillOval(x*sizeOfSqrt + 7, y*sizeOfSqrt + 7 , 15, 15);
      g.fillRect(x*sizeOfSqrt + 5, y*sizeOfSqrt + 13, 20, 4);
      g.fillRect(x*sizeOfSqrt+ 13, y*sizeOfSqrt + 5, 4, 20);
      g.setColor(Color.WHITE);
      g.fillRect(x*sizeOfSqrt + 11, y*sizeOfSqrt + 10, 4, 4);  
   }
 
   private void paintString (Graphics g, int num)
   { 
      g.setColor(color [countBombNear - 1]);
      g.setFont(new Font("", Font.BOLD, sizeOfSqrt));
      g.drawString(Integer.toString(num), x*sizeOfSqrt + 8, y*sizeOfSqrt + 26);
   }
 
   protected void paintComponent(Graphics g)
    {    
        g.setColor(Color.lightGray);
        g.drawRect(x*sizeOfSqrt, y*sizeOfSqrt, sizeOfSqrt, sizeOfSqrt);
 
        if (!open)
        {
          if (isBombed() && end == true) paintBomb(g);
            else
               {
                  g.setColor(Color.lightGray);
                  g.fill3DRect(x*sizeOfSqrt, y*sizeOfSqrt, sizeOfSqrt, sizeOfSqrt, true);
                  if (flag) paintflag(g, "f");
               }  
         }
         else 
               if (isBombed() && getFail()) paintBang(g);
               else
                        if (countBombNear > 0)
                        paintString(g, countBombNear);
      } 
 
}   

