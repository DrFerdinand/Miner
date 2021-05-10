import java.awt.*;
import javax.swing.*;
import java.awt.event.*; // Адаптер мыши
import java.util.*; //Рандом

class  Miner
{   
    int x = 0;
    int y = 0;
    int countBomb = 10;
    int sizeOfFeild = 8;
    boolean end = false;
    Cells[][] map = new Cells[sizeOfFeild][sizeOfFeild];
    JFrame frame = new JFrame("Miner"); 
    Panel panel = new Panel();
    Random random = new Random();
    

    JButton restartButton = new JButton("Restart");

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

        panel.setPreferredSize(new Dimension(30*sizeOfFeild, 30*sizeOfFeild));

        panel.setBackground(Color.WHITE);

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable (false);
        
      
        panel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent e)
            {   
                super.mouseReleased(e);
                
                x = e.getX()/30;
                y = e.getY()/30;

                if (e.getButton() == MouseEvent.BUTTON1)
                    {
                      recursiveOpener(x,y);
                      if (map[x][y].getWon() || map[x][y].getFail()){end = true;}
                    }
                if (e.getButton() == MouseEvent.BUTTON3) map[x][y].isFlag();
                if (end == true) map[x][y].End(end);

                 panel.repaint();
                if (end == true) panel.removeMouseListener(this);
                }
        });

        restartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {   
                
                panelInit();
                end = false;
                map[x][y].refresh();
                
            }
        });

        frame.add(BorderLayout.SOUTH, restartButton);
        frame.add(BorderLayout.CENTER, panel);
        frame.setVisible (true);
        frame.setIconImage(getImage("icon"));
        frame.pack();

        createCell();
        map[0][0].setFieldSize(sizeOfFeild,countBomb);
    }
   

    private void recursiveOpener (int q, int v)
    {   if ((q < 0 || q > sizeOfFeild -1 || v < 0 || v > sizeOfFeild - 1)) return;
        if (map[q][v].isOpen() == false)
             {
                map[q][v].openCell();
             } else return;

        if (map[q][v].getCountNearBomb() > 0) return;

        for (int x1 = -1; x1 < 2; x1++)
            for (int y1 = -1; y1 < 2; y1++)
                recursiveOpener(q + x1, v + y1);
    }

    private void createCell()
    { 
        int x,y;
        for (x = 0; x < sizeOfFeild ; x++)
        {
            for (y = 0; y < sizeOfFeild; y++)
                {
                    map[x][y] = new Cells();
                    map[x][y].setCoord(x,y);
                }
        }

        for (int planted = 0; planted < countBomb; planted++)
        {  
            do 
            {
              x = random.nextInt(sizeOfFeild);
              y = random.nextInt(sizeOfFeild);
            } while (map[x][y].isBombed());
            map[x][y].setBomb();
        }


        for (x = 0; x < sizeOfFeild; x++)
        {
            for (y = 0; y < sizeOfFeild; y++)
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
                            if (dx < 0 || dy < 0 || dx > sizeOfFeild - 1 || dy > sizeOfFeild - 1) 
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


    private Image getImage (String name)
    {
        String filename = "res/" + name.toLowerCase() + ".png"; 
        ImageIcon icon = new ImageIcon (getClass().getResource(filename));
        return icon.getImage();
    }

    class Panel extends JPanel
    {   
       @Override
        public void paint(Graphics g) 
        {
            super.paint(g);
            for (int x = 0; x < sizeOfFeild ; x++)
                for (int y = 0; y < sizeOfFeild; y++) 
                    map[y][x].paintComponent(g);
        }
    }
}   



