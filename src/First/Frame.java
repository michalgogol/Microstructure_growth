package First;



import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Frame extends JFrame {

    NewPanel panel ;
   private JButton startButton = new JButton("Start");
   private JButton resetButton = new JButton("Restart");
   private JButton saveButton  = new JButton("Export");
   private JButton importButton = new JButton("Import");
   private JButton afterButton = new JButton("After Grain");
   private JCheckBox addInclusionBefore = new JCheckBox("Before Grain");
   private JCheckBox addInclusionAfter = new JCheckBox("After Grain");
   private GridBagConstraints c = new GridBagConstraints();
   private JTextField horizontal;
   private JTextField vertical;
   private JTextField grains;
   private JTextField numberOfInclusions;
   private JTextField sizeOfInclusions;
   private JTextField probability;
   private JPanel jPanel = new JPanel();
   private JPanel outboundPanel;
   private JRadioButton asTXT = new JRadioButton("TXT");
   private JRadioButton asBMP = new JRadioButton("BMP");
   private ButtonGroup buttonGroup = new ButtonGroup();
   private JComboBox typeOfInclusion;
   private String[] typeOfIncl = {"Square", "Circle"};
   private int numberOfIncl;
   private int sizeOfIncl;



    public Frame()
    {

        setLocation(280, 60);

        Thread repainter = new Thread(() -> {
            while (true) {
                repaint();

                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                }
            }
        });
        repainter.setName("Panel repaint");
        repainter.setPriority(Thread.MIN_PRIORITY);


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        jPanel.setLayout(new GridBagLayout());

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(addInclusionBefore.isSelected())
                {
                    numberOfIncl = Integer.parseInt(numberOfInclusions.getText());
                    sizeOfIncl = Integer.parseInt(sizeOfInclusions.getText());
                }

               setPanel(Integer.parseInt(vertical.getText()),Integer.parseInt(horizontal.getText()),Integer.parseInt(grains.getText()));
                getContentPane().repaint();
                revalidate();
                repainter.start();


            }
        });

        c.gridx = 0;
        c.gridy = 0;
        jPanel.add(startButton,c);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(addInclusionBefore.isSelected())
                {
                    numberOfIncl = Integer.parseInt(numberOfInclusions.getText());
                    sizeOfIncl = Integer.parseInt(sizeOfInclusions.getText());
                }

                getContentPane().invalidate();
                getContentPane().validate();
                remove(outboundPanel);
                setPanel(Integer.parseInt(vertical.getText()),Integer.parseInt(horizontal.getText()),Integer.parseInt(grains.getText()));
                getContentPane().repaint();
                revalidate();

            }
        });
        c.gridx = 1;
        c.gridy = 0;
        jPanel.add(resetButton,c);


        c.gridx = 2;
        c.gridy = 1;
        jPanel.add(new JLabel("Width"),c);

        horizontal = new JTextField(10);
        c.gridx = 2;
        c.gridy = 0;
        jPanel.add(horizontal,c);

        c.gridx = 3;
        c.gridy = 1;
        jPanel.add(new JLabel("Height"),c);

        vertical = new JTextField(10);
        c.gridx = 3;
        c.gridy = 0;
        jPanel.add(vertical,c);


        c.gridx = 4;
        c.gridy = 1;
        jPanel.add(new JLabel("Nucleons"),c);

        grains = new JTextField(10);
        c.gridx = 4;
        c.gridy = 0;
        jPanel.add(grains,c);

        probability = new JTextField(10);
        c.gridx = 5;
        c.gridy = 0;
        jPanel.add(probability,c);

        c.gridx = 5;
        c.gridy = 1;
        jPanel.add(new JLabel("Probability"),c);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(asTXT.isSelected()) {
                    String directory = JOptionPane.showInputDialog("Directory", "C://Users/Gogelix/Desktop/grain.txt");
                    panel.saveToTxt(directory);
                }
                if(asBMP.isSelected())
                {
                    String directory = JOptionPane.showInputDialog("Directory", "C://Users/Gogelix/Desktop/grain.bmp");
                    panel.saveToBmp(directory);
                }

            }
        });
        c.gridx = 6;
        c.gridy = 0;
        jPanel.add(saveButton,c);


        importButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(asBMP.isSelected()) {
                    String directory = JOptionPane.showInputDialog("Directory", "C://Users/Gogelix/Desktop/grain.bmp");
                  //  loadBmp(directory);

                    if (outboundPanel != null) {
                        getContentPane().invalidate();
                        getContentPane().validate();
                        remove(outboundPanel);
                    }

                    panel = new NewPanel();
                    loadBmp(directory);
                    outboundPanel = new JPanel(new FlowLayout());
                    outboundPanel.add(panel);
                    add(outboundPanel);
                    getContentPane().repaint();
                    revalidate();
                    repainter.start();


                }
                if(asTXT.isSelected()) {
                    String directory = JOptionPane.showInputDialog("Directory", "C://Users/Gogelix/Desktop/grain.txt");

                    if (outboundPanel != null) {
                        getContentPane().invalidate();
                        getContentPane().validate();
                        remove(outboundPanel);
                    }

                    panel = new NewPanel(directory);
                    outboundPanel = new JPanel(new FlowLayout());
                    outboundPanel.add(panel);
                    add(outboundPanel);
                    getContentPane().repaint();
                    revalidate();
                    repainter.start();

                }

            }
        });
        c.gridx = 7;
        c.gridy = 0;
        jPanel.add(importButton,c);


        buttonGroup.add(asBMP);
        buttonGroup.add(asTXT);

        c.gridx = 7;
        c.gridy = 1;
        jPanel.add(asBMP,c);

        c.gridx = 6;
        c.gridy = 1;
        jPanel.add(asTXT,c);



        numberOfInclusions = new JTextField(10);
        c.gridx = 2;
        c.gridy = 2;
        jPanel.add(numberOfInclusions,c);


        c.gridx = 2;
        c.gridy = 3;
        jPanel.add(new JLabel("Inclusion amount"),c);

        sizeOfInclusions = new JTextField(10);
        c.gridx = 3;
        c.gridy = 2;
        jPanel.add(sizeOfInclusions,c);

        c.gridx = 3;
        c.gridy = 3;
        jPanel.add(new JLabel("Size of inclusion"),c);

        c.gridx = 4;
        c.gridy = 3;
        jPanel.add(new JLabel("Type of inclusion"),c);

        typeOfInclusion = new JComboBox(typeOfIncl);

        c.gridx = 4;
        c.gridy = 2;
        jPanel.add(typeOfInclusion,c);

        c.gridx = 5;
        c.gridy = 2;
        jPanel.add(addInclusionBefore,c);

        afterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(panel != null)
                {
                    panel.addAfterInclusion(Integer.parseInt(numberOfInclusions.getText()),Integer.parseInt(sizeOfInclusions.getText()),(String)typeOfInclusion.getSelectedItem());
                }


            }
        });

        c.gridx = 6;
        c.gridy = 2;
        jPanel.add(afterButton,c);


        add(jPanel, BorderLayout.SOUTH);



        pack();
        setVisible(true);
        setResizable(false);
        setMinimumSize(new Dimension(700, 600));


    }

    public void setPanel(int x, int y, int z)
    {
        int afterFlag = 0;
        if(addInclusionAfter.isSelected()) {
            afterFlag = 1;
            numberOfIncl = Integer.parseInt(numberOfInclusions.getText());
            sizeOfIncl = Integer.parseInt(sizeOfInclusions.getText());
        }

        outboundPanel = new JPanel(new FlowLayout());

        panel = new NewPanel(y,x, z, numberOfIncl, sizeOfIncl, (String)typeOfInclusion.getSelectedItem(), afterFlag,Integer.parseInt(probability.getText()));
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        panel.setLayout(new FlowLayout());

        outboundPanel.add(panel);

        add(outboundPanel);
    }

    public void loadBmp(String dir)
    {
        BufferedImage img = null;
        JPanel loadedImg;
        LoadPainter loadPainter;
        try {
            img = ImageIO.read(new File(dir));
        } catch (IOException e) { }

        panel.readBmp(img,img.getWidth(), img.getHeight());
        /*
        if (outboundPanel != null) {
            getContentPane().invalidate();
            getContentPane().validate();
            remove(outboundPanel);
        }

            outboundPanel = new JPanel(new FlowLayout());
            loadPainter = new LoadPainter(img,img.getHeight(),img.getWidth());
            loadPainter.setLayout(new FlowLayout());
            outboundPanel.add(loadPainter);
            add(outboundPanel);

            getContentPane().repaint();
            revalidate();
*/

    }






}
