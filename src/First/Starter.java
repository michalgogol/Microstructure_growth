package First;

import jdk.internal.cmm.SystemResourcePressureImpl;

import javax.swing.*;
import java.awt.*;

public class Starter {

   // public int[][] temp = ca_algorithm.modifyMatrix();

    public static void main (String[] args)
    {
        /* CA_algorithm ca_algorithm = new CA_algorithm();
        ca_algorithm.modifyMatrix();
        ca_algorithm.temp2();
        */


        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                new Frame();
            }
        });

    }




}
