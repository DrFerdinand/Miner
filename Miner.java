import java.awt.*;
import javax.swing.*;
import java.awt.event.*; // Адаптер мыши
import java.util.*; //Рандом
 
class  Miner
{   
    private int x;
    private int y;
    private int countBomb = 10;
    private int sizeOfFeild = 8;
    private boolean end = false;
 
    Cells[][] map; 
 
    Choice choise = new Choice();
    JFrame setDifficult = new JFrame("Miner");
 
    JFrame frame = new JFrame("Miner"); 
 
    Panel panel;
    Random random = new Random();
 
 
    JButton restartButton = new JButton("Restart");
 
 
    public static void main (String[]  args)
    {
        new Miner();
    }
 
    private  Miner() 
    {   
        diffInit();  
    }
    private void diffInit()
    {   
        JPanel firstPanel = new JPanel();
 
        JLabel label = new JLabel("Please, choise difficult:");
 
        JButton buttonOk = new JButton("OK");
 
        choise.add("Easy");
        choise.add("Medium");
        choise.add("Hard");
 
        choise.addItemListener(choiseListener);
        buttonOk.addActionListener(listenerOk);
 
        setDifficult.setLocationRelativeTo(null);
        setDifficult.setVisible (true);
        setDifficult.setResizable (false);
 
        setDifficult.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
 
        setDifficult.setIconImage(getImage("icon"));
 
        setDifficult.add(BorderLayout.CENTER, firstPanel);
 
        firstPanel.add(label);
        firstPanel.add(choise);
        firstPanel.add(buttonOk);
 
        setDifficult.pack();
 
    }
 
    private void frameInit()
    {   
 
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable (false);
 
        frame.add(BorderLayout.SOUTH, restartButton);
        frame.add(BorderLayout.CENTER, panel);
 
 
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
 
        frame.setIconImage(getImage("icon"));
 
        frame.pack();
    }
 
    private void panelInit()
    {   
 
        panel = new Panel();
 
        panel.setPreferredSize(new Dimension(30 * sizeOfFeild, 30 * sizeOfFeild));
 
        panel.setBackground(Color.WHITE);
 
        panel.addMouseListener(mouse); 
 
        restartButton.addActionListener(buttonListener);
 
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
        map = new Cells[sizeOfFeild][sizeOfFeild];
        int i,j;
        for (i = 0; i < sizeOfFeild ; i++)
        {
            for (j = 0; j < sizeOfFeild; j++)
                {
                    map[i][j] = new Cells();
                    map[i][j].setCoord(i,j);
                }
        }
 
        for (int planted = 0; planted < countBomb; planted++)
        {  
            do 
            {
              i = random.nextInt(sizeOfFeild);
              j = random.nextInt(sizeOfFeild);
            } while (map[i][j].isBombed());
            map[i][j].setBomb();
        }
 
 
        for (i = 0; i < sizeOfFeild; i++)
        {
            for (j = 0; j < sizeOfFeild; j++)
            {
               if (!map[i][j].isBombed())
               {
                   int count = 0;
                   for (int x1 = -1; x1 < 2; x1++)
                   {
                        for (int y1 = -1; y1 < 2; y1++)
                        {
                            int dx = i + x1;
                            int dy = j + y1;
                            if (dx < 0 || dy < 0 || dx > sizeOfFeild - 1 || dy > sizeOfFeild - 1) 
                            {
                              dx = i;
                              dy = j;    
                            }
                            if (map[dx][dy].isBombed())
                                {
                                count++;
                                } 
                        }       
                        map[i][j].setCountNearBomb(count);
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
 
    public class Panel extends JPanel
    {   
       @Override
        public void paint(Graphics g) 
        {
            super.paint(g);
            for (int i = 0; i < sizeOfFeild ; i++)
                for (int j = 0; j < sizeOfFeild; j++) 
                    map[i][j].paintComponent(g);
        }
    }
 
    MouseListener mouse = new MouseAdapter() 
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
                if (e.getButton() == MouseEvent.BUTTON3) { map[x][y].isFlag();}
                if (end == true)
                { 
                    map[x][y].End(end);
                    panel.removeMouseListener(mouse);
                }
            panel.repaint();
            }
        };
 
        ItemListener choiseListener = new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent ie)
            {
                if (ie.getItem() == "Easy")
                {
                sizeOfFeild = 8;
                countBomb = 10;
                };
                if (ie.getItem() == "Medium")
                {
                sizeOfFeild = 16;
                countBomb = 52; 
                };
            if (ie.getItem() == "Hard")
                {
                sizeOfFeild = 20;
                countBomb = 80; 
                };
            }
        };
 
        ActionListener buttonListener = new ActionListener() 
        {   
            @Override
            public void actionPerformed(ActionEvent e)
            {   
                x = 0;
                y = 0;
                if (end == true) 
                {
                    end = false;
                    panel.addMouseListener(mouse);
                }
                map[x][y].refresh();
                createCell();
                panel.repaint();
 
            }
        };
 
        ActionListener listenerOk = new ActionListener() 
        {   
            @Override
            public void actionPerformed(ActionEvent e)
            {   
                setDifficult.dispose(); 
                panelInit();
                frameInit();
                panel.repaint();
 
            }
        };
 
}




