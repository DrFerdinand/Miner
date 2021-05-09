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
        
      
        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {
                super.mouseReleased(e);
                int x = e.getX()/30;
                int y = e.getY()/30;
                //System.out.println(x);
                if (e.getButton() == MouseEvent.BUTTON1)
                    {
                      recursiveOpener(x,y);
                    }
                if (e.getButton() == MouseEvent.BUTTON3) map[x][y].isFlag();
                panel.repaint();
                }
        });

        frame.add(BorderLayout.CENTER, panel);
        frame.setVisible (true);
        frame.pack();
        createCell();
    }

    private void recursiveOpener (int q, int v)
    {   if ((q < 0 || q > 8 || v < 0 || v > 8)) return;
        if (map[q][v].isOpen() == false)
             {
                map[q][v].openCell();
             } else return;                 
                for (int x1 = -1; x1 < 2; x1++)
                    for (int y1 = -1; y1 < 2; y1++)
                            recursiveOpener(q + x1, v + y1);
    }

    private void createCell()
    { 
        int x,y;
        for (x = 0; x < 9 ; x++)
        {
            for (y = 0; y < 9; y++)
                {
                    map[x][y] = new Cells();
                    map[x][y].setCoord(x,y);
                }
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


        for (x = 0; x < 9; x++)
        {
            for (y = 0; y < 9; y++)
            {
               if (!map[x][y].isBombed())
               {
                   int count = 0;
                   for (int x1 = -1; x1 < 2; x1++)
                   {
                        for (int y1 = -1; y1 < 2; y1++)
                        {
                            int dx = x + x1;
                            int dy = y + y1;
                            if (dx < 0 || dy < 0 || dx > 9 - 1 || dy > 9 - 1) 
                            {
                              dx = x;
                              dy = y;    
                            }
                            if (map[dx][dy].isBombed())
                                {
                                count++;
                                } 
                        }       
                        map[x][y].setCountNearBomb(count);
                    }    
                }    
            }
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
}   



