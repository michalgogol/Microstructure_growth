package First;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class LoadPainter extends JPanel {
    BufferedImage loadImage;
    int x, y;

    public LoadPainter(BufferedImage image, int x, int y)
    {  setPreferredSize(new Dimension(y,x));
        loadImage = image;
        this.x = x;
        this.y = y;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(loadImage,0,0,y,x,this);
        //g.fillRect(1, 1, 20, 20);
    }

}
