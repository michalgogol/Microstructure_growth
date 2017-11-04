package First;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class NewPanel extends JPanel {

    private int size;
    private ArrayList<Color> usedColors = new ArrayList<>();
    private Map<Integer, Color> idColor = new HashMap<>();
    int counter = 0;
    private int x_dimm, y_dimm;
    private int txtFlag;

    public NewPanel( int y_dimm, int x_dimm, int size, int numbOfInclusion, int sizeOfInclusion, String typeOfIncl, int afterFlag, int probability) {
        setPreferredSize(new Dimension(y_dimm, x_dimm));
        this.size = size;
        this.y_dimm = x_dimm;
        this.x_dimm = y_dimm;
        ca2 = new CA2(x_dimm,y_dimm,size, numbOfInclusion, sizeOfInclusion, typeOfIncl, afterFlag, probability);
        ca2.setGrains();

    }
    public NewPanel(String dir)
    {
        readTxt(dir);
    }
    public NewPanel(){}

    private int[][] results = new int[y_dimm][x_dimm];
    private CA2 ca2;


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D firstGrain = (Graphics2D) g;

        if(txtFlag != 1)
            results = ca2.modifyMatrix();

            for (int i = 0; i < y_dimm; i++) {
                for (int j = 0; j < x_dimm; j++) {
                    if (results[i][j] != 0) {

                        if (results[i][j] == -1)
                             firstGrain.setColor(Color.black);
                        else
                            firstGrain.setColor(Color.decode(Integer.toString(-16777216/results[i][j])));

                        firstGrain.fillRect(j, i, 2, 2);

                    }

                }
                }


        counter++;
    }

    public void saveToBmp(String dir)
    {
        BufferedImage bufferedImage = new BufferedImage(x_dimm, y_dimm, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();


        for (int i = 0; i < y_dimm; i++) {
            for (int k = 0; k < x_dimm; k++) {
                if (results[i][k] != 0) {

                    if (results[i][k] == -1)
                        g2d.setColor(Color.black);
                    else
                      g2d.setColor(Color.decode(Integer.toString(-16777216/results[i][k])));

                 //   g2d.setColor(Color.decode(Integer.toString(-16777216/results[i][k])));
                    g2d.fillRect(k, i, 2, 2);

                }

            }
        }
        g2d.dispose();
        RenderedImage rendImage = bufferedImage;

        File file = new File(dir);
        try {
            ImageIO.write(rendImage, "bmp", file);
        }catch (Exception ex){ex.printStackTrace();}
    }

    public void saveToTxt(String dir)
    {
        try
        {
            FileWriter writer = new FileWriter(dir,true);
            writer.write(y_dimm + " "+x_dimm);
            writer.write("\n");
            for(int i =0; i<y_dimm;i++)
            {
                for(int j=0;j<x_dimm;j++)
                {
                    writer.write(i +" "+j+" "+results[i][j]);
                    writer.write("\n");
                }
            }
            writer.close();
        }catch (IOException ex){ex.printStackTrace();}
    }

    public void readTxt(String dir)
    {
        String line;
        String[] splited;
        int x, y,value;

        txtFlag = 1;
        try {
            BufferedReader br = new BufferedReader(new FileReader(dir));
            line = br.readLine();
            splited = line.split("\\s+");
            x = Integer.parseInt(splited[1]);
            y = Integer.parseInt(splited[0]);
            x_dimm = x;
            y_dimm = y;
            results =  new int[y][x];
            while (line != null)
            {
                line = br.readLine();
                if (line != null) {
                    splited = line.split("\\s+");
                    y = Integer.parseInt(splited[0]);
                    x = Integer.parseInt(splited[1]);
                    value = Integer.parseInt(splited[2]);
                    results[y][x] = value;
                }
            }
        }catch (Exception ex){ex.printStackTrace();}
        setPreferredSize(new Dimension(x_dimm, y_dimm));
    }

    public void readBmp(BufferedImage image, int x, int y)
    {

        y_dimm = y;
        x_dimm = x;
        results = new int[y][x];
        txtFlag = 1;

        for(int i =0; i< y; i++ )
        {
            for(int j =0; j<x; j++)
            {
                results[i][j] =  ( -16777216 / image.getRGB(j,i)  );
            }
        }
        setPreferredSize(new Dimension(x_dimm, y_dimm));
        txtFlag = 1;
    }

    public void addAfterInclusion(int amount, int size, String type)
    {
        if(ca2 == null)
            ca2 = new CA2(y_dimm,x_dimm,results);

        ca2.setAfterFlag();
        ca2.setInclusions(amount,size,type);
    }







    public int getRandom()
    {
        return (int)(Math.random()*300);
    }




}
