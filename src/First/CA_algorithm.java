package First;

public class CA_algorithm {

    private int[][] grainArray = new int[300][300];
    private int flag = 0;

/*
    public CA_algorithm (int x1, int y1, int x2, int y2)
    {
            for(int i =0; i<300;i++)
            {
                for(int j = 0; j<300; j++)
                {
                    if (i == y1 && j ==x1)
                        grainArray[i][j] = 1;
                    else if (i == y2 && j ==x2)
                        grainArray[i][j] = 2;
                    else
                        grainArray[i][j] = 0;
                }
            }
    }

    public CA_algorithm (int x1, int y1, int x2, int y2, int x3, int y3 )
    {
        for(int i =0; i<300;i++)
        {
            for(int j = 0; j<300; j++)
            {
                if (i == y1 && j ==x1)
                    grainArray[i][j] = 1;
                else if (i == y2 && j ==x2)
                    grainArray[i][j] = 2;
                else if (i == y3 && j ==x3)
                    grainArray[i][j] = 3;
                else
                    grainArray[i][j] = 0;
            }
        }
    }

    public CA_algorithm (int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4)
    {
        for(int i =0; i<300;i++)
        {
            for(int j = 0; j<300; j++)
            {
                if (i == y1 && j ==x1)
                    grainArray[i][j] = 1;
                else if (i == y2 && j ==x2)
                    grainArray[i][j] = 2;
                else if (i == y3 && j ==x3)
                    grainArray[i][j] = 3;
                else if (i == y4 && j ==x4)
                    grainArray[i][j] = 4;
                else
                    grainArray[i][j] = 0;
            }
        }
    }

    public int[][] modifyMatrix()
    {
        if ( flag ==0 ) {
            flag = 1;
            return grainArray;
        }
        System.out.println();
        for(int i =0; i<300;i++)
        {
            for(int j = 0; j<300; j++)
            {
              //  System.out.print(grainArray[i][j]);
              if(grainArray[i][j] != 0 && grainArray[i][j] != 10 && grainArray[i][j] != 20 && grainArray[i][j] != 30  && grainArray[i][j] != 40)
              {
                  if (i>0) {
                      if(grainArray[i - 1][j] == 0)
                          grainArray[i - 1][j] = grainArray[i][j];
                  }
                  if (i<299) {
                      if(grainArray[i + 1][j] == 0)
                          grainArray[i + 1][j] = grainArray[i][j]*10;
                  }

                  if (j>0) {
                      if(grainArray[i][j - 1] == 0)
                        grainArray[i][j - 1] = grainArray[i][j];
                  }

                  if (j<299) {
                      if(grainArray[i][j + 1] == 0) {
                          grainArray[i][j + 1] = grainArray[i][j];
                          j++;
                      }
                  }


              }
            }
        }
      //  show();
        clearArray();

        return grainArray;
    }

    public void clearArray()
    {
        for(int i =0; i<300;i++)
        {
            for(int j = 0; j<300; j++)
            {
                if(grainArray[i][j] == 10)
                    grainArray[i][j] = 1;
                else if(grainArray[i][j] == 20)
                    grainArray[i][j] = 2;
                else if(grainArray[i][j] == 30)
                    grainArray[i][j] = 3;
                else if(grainArray[i][j] == 40)
                    grainArray[i][j] = 4;
            }

        }
    }
    public void show()
    {
        for(int i =0; i<300;i++)
        {
            for(int j = 0; j<300; j++)
            {
               System.out.print(grainArray[i][j]);
            }
            System.out.println();
        }
    }

*/

}
