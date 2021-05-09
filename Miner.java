import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

class  Miner
{   
    

    int countBomb = 10;
    int SIZE_CELL = 30;
    JFrame frame = new JFrame("Miner"); 
    Panel panel = new Panel();
    Random random = new Random();
    Cells[][] map = new Cells[9][9];

    public static void main (String[]  args)
    {
        new Miner();
    }

    private  Miner() 
    {   
        
        panelInit();
        
    }

    private void panelInit()
    {   

        panel.setPreferredSize(new Dimension(270,270));
        panel.setBackground(Color.lightGray);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable (false);
        frame.add(BorderLayout.CENTER, panel);
        frame.setVisible (true);
        frame.pack();
        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                super.mouseReleased(e);
                int x = e.getX()/30;
                int y = e.getY()/30;
                //System.out.println(x);
                map[x][y].isClick();
                panel.repaint();
            }

        });
        createCell();
    }

    private void createCell()
    { int x,y;
        for (x = 0; x < 9 ; x++)
            for (y = 0; y < 9; y++)
                {
                    map[x][y] = new Cells();
                    map[x][y].setCoord(x,y);
     
                }
        for (int planted = 0; planted < countBomb; planted++)
        {  
            do 
            {
              x = random.nextInt(9);
              y = random.nextInt(9);
            } while (map[x][y].isBombed());
            map[x][y].setBomb();
        }
    }
        

    class Panel extends JPanel
    {   
       @Override
        public void paint(Graphics g) 
        {
            super.paint(g);
            for (int x = 0; x < 9 ; x++)
                for (int y = 0; y < 9; y++) 
                    map[y][x].paintComponent(g);

        }
    }


   
   /*private class MouseHandler extends MouseAdapter
   {
    @Override
    public void mouseClicked(MouseEvent e)
    
   }*/

}

