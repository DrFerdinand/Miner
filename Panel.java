import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;
import java.util.*;

public class Panel extends JComponent {


        @Override
        public void paint (Graphics g)
        {
            super.paint(g);
            g.setColor(Color.lightGray);
            for (int x = 0; x < 9; x++)
                for (int y = 0; y < 9; y++)
                g.drawRect(x*30, y*30,  30 , 30);
        }


       
}