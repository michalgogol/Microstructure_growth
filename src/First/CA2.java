package First;

import java.util.*;


import static java.lang.Math.abs;

public class CA2 {
    private int x_dimm;
    private int y_dimm;
    private int[][] grainArray;
    private int[][] grainArrayCopy;
    private int flag = 0;
    private int numbOfGrains = 0;
    private int[][] boundary;
    private int numbOfInclusions;
    private int sizeOfInclusions;
    private String typeOfInclusions;
    private int afterFlag;
    private int isFullfilled, sizeOfField;
    private int probability;

    public CA2 (int x, int y, int numbOfGrains, int numbOfInclusions, int sizeOfInclusions, String typeOfInclusions, int afterFlag, int probability)
    {
        this.numbOfGrains = numbOfGrains;
        x_dimm = y;
        y_dimm = x;
        grainArray =  new int[y_dimm][x_dimm];
        grainArrayCopy = new int[y_dimm][x_dimm];
        this.sizeOfInclusions = sizeOfInclusions;
        this.numbOfInclusions = numbOfInclusions;
        this.typeOfInclusions = typeOfInclusions;
        this.afterFlag = afterFlag;
        this.probability = probability;
        if(afterFlag == 0 )
             setInclusions(numbOfInclusions, sizeOfInclusions, typeOfInclusions);

    }

    public CA2(int x, int y,int[][] importedArray)
    {
        x_dimm = y;
        y_dimm = x;
        grainArray = importedArray;
    }



   public void setGrains()
   {

       boundary = new int[numbOfGrains][2];
       sizeOfField = y_dimm*x_dimm;

       int index = 0;

       while (index < numbOfGrains)
       {
           boundary[index][0] = (int)(Math.random()*y_dimm);
           boundary[index][1] = (int)(Math.random()*x_dimm);

           if (grainArray[boundary[index][0]][boundary[index][1]] != -1)
               index++;

       }



       for(int i =0; i<y_dimm;i++)
       {
           for(int j = 0; j<x_dimm; j++)
           {

               for(int k=0; k<numbOfGrains; k++)
               {
                   if(boundary[k][0]== i && boundary[k][1]== j) {
                       grainArray[i][j] = (int) (Math.random() * 1600 + 1);
                   }

               }

           }
       }

   }


    public int[][] modifyMatrix()
    {
        ArrayList<Integer> maxGrain = new ArrayList<>();
        ArrayList<Integer> mooreArray = new ArrayList<>();
        ArrayList<Integer> furtherMooreArray = new ArrayList<>();
        ArrayList<Integer> nearestMooreArray = new ArrayList<>();
        ArrayList<Integer> randomArray = new ArrayList<>();

        int counter = 0;
        int tempResult = 0;

        if ( flag ==0 ) {
            flag = 1;
            return grainArray;
        }

        if(isFullfilled == 1 && afterFlag == 1)
            setInclusions(numbOfInclusions, sizeOfInclusions, typeOfInclusions);


        grainArrayCopy = grainArray;
        grainArray = new int[y_dimm][x_dimm];
        for(int i =0; i<y_dimm;i++)
        {
            for(int j = 0; j<x_dimm; j++)
            {
                grainArray[i][j] = grainArrayCopy[i][j];
                if(grainArrayCopy[i][j] == 0) {
                    maxGrain.clear();
                    mooreArray.clear();
                    furtherMooreArray.clear();
                    nearestMooreArray.clear();
                    randomArray.clear();
                    tempResult = 0;

                    /*
                    if (i > 0) {
                        if (grainArrayCopy[i - 1][j] != 0 && grainArrayCopy[i - 1][j] != -1)
                            maxGrain.add(grainArrayCopy[i - 1][j]);
                    }
                    if (i < y_dimm - 1) {
                        if (grainArrayCopy[i + 1][j] != 0 && grainArrayCopy[i + 1][j] != -1)
                            maxGrain.add(grainArrayCopy[i + 1][j]);
                    }

                    if (j > 0) {
                        if (grainArrayCopy[i][j - 1] != 0 && grainArrayCopy[i][j - 1] != -1)
                            maxGrain.add(grainArrayCopy[i][j - 1]);
                    }

                    if (j < x_dimm - 1) {
                        if (grainArrayCopy[i][j + 1] != 0 && grainArrayCopy[i][j + 1] != -1) {
                            maxGrain.add(grainArrayCopy[i][j + 1]);
                        }
                    }

*/

                    try
                    {
                        if (grainArrayCopy[i - 1][j] != 0 && grainArrayCopy[i - 1][j] != -1) {
                            mooreArray.add( grainArrayCopy[i - 1][j]);
                            nearestMooreArray.add( grainArrayCopy[i - 1][j]);
                            randomArray.add( grainArrayCopy[i - 1][j]);
                        }

                        if (grainArrayCopy[i + 1][j] != 0 && grainArrayCopy[i + 1][j] != -1)
                        {
                            mooreArray.add(grainArrayCopy[i + 1][j]);
                            nearestMooreArray.add(grainArrayCopy[i + 1][j]);
                            randomArray.add(grainArrayCopy[i + 1][j]);
                        }

                        if (grainArrayCopy[i][j - 1] != 0 && grainArrayCopy[i][j - 1] != -1)
                        {
                            mooreArray.add(grainArrayCopy[i][j-1]);
                            nearestMooreArray.add(grainArrayCopy[i][j-1]);
                            randomArray.add(grainArrayCopy[i][j-1]);
                        }

                        if (grainArrayCopy[i][j + 1] != 0 && grainArrayCopy[i][j + 1] != -1)
                        {
                            mooreArray.add(grainArrayCopy[i][j+1]);
                            nearestMooreArray.add(grainArrayCopy[i][j+1]);
                            randomArray.add(grainArrayCopy[i][j+1]);
                        }

                        if (grainArrayCopy[i + 1][j - 1] != 0 && grainArrayCopy[i + 1][j - 1] != -1)
                        {
                            mooreArray.add(grainArrayCopy[i + 1][j - 1]);
                            furtherMooreArray.add(grainArrayCopy[i + 1][j - 1]);
                            randomArray.add(grainArrayCopy[i+1][j-1]);
                        }
                        if (grainArrayCopy[i + 1][j + 1] != 0 && grainArrayCopy[i + 1][j + 1] != -1)
                        {
                            mooreArray.add(grainArrayCopy[i + 1][j + 1]);
                            furtherMooreArray.add(grainArrayCopy[i + 1][j + 1]);
                            randomArray.add(grainArrayCopy[i+1][j+1]);
                        }
                        if (grainArrayCopy[i - 1][j - 1] != 0 && grainArrayCopy[i - 1][j - 1] != -1)
                        {
                            mooreArray.add(grainArrayCopy[i - 1][j - 1]);
                            furtherMooreArray.add(grainArrayCopy[i - 1][j - 1]);
                            randomArray.add(grainArrayCopy[i-1][j-1]);
                        }
                        if (grainArrayCopy[i - 1][j + 1] != 0 && grainArrayCopy[i - 1][j + 1] != -1)
                        {
                            mooreArray.add(grainArrayCopy[i - 1][j + 1]);
                            furtherMooreArray.add(grainArrayCopy[i - 1][j + 1]);
                            randomArray.add(grainArrayCopy[i-1][j+1]);
                        }


                    }catch (IndexOutOfBoundsException ex){}
/*
                    if (maxGrain.size() > 0) {
                        grainArray[i][j] = checkNeigh(maxGrain);
                    }
*/

                    tempResult = mooreGrowth(mooreArray);
                    if(tempResult != 0) {
                        grainArray[i][j] = tempResult;
                        continue;
                    }

                    tempResult = nearestMoore(nearestMooreArray);
                    if(tempResult != 0) {
                        grainArray[i][j] = tempResult;
                        continue;
                    }

                    tempResult = furtherMooreGrowth(furtherMooreArray);
                    if(tempResult != 0) {
                        grainArray[i][j] = tempResult;
                        continue;
                    }

                    tempResult = randomGrowth(randomArray);
                    if(tempResult != 0) {
                        grainArray[i][j] = tempResult;
                        continue;
                    }

                }

                if(grainArray[i][j] != 0)
                    counter++;

                if (counter == sizeOfField)
                    isFullfilled = 1;
            }
        }

          return grainArray;
    }

    public int nearestMoore(List list)
    {
        for(int i =0; i < list.size(); i++)
        {
            if (Collections.frequency(list,list.get(i)) >= 3)
                return (int)list.get(i);
        }
        return 0;
    }

    public int mooreGrowth(List list)
    {
        for(int i =0; i < list.size(); i++)
        {
            if (Collections.frequency(list,list.get(i)) >= 5)
                return (int)list.get(i);
        }
        return 0;
    }

    public int furtherMooreGrowth(List list)
    {
        for(int i =0; i < list.size(); i++)
        {
            if (Collections.frequency(list,list.get(i)) >= 3)
                return (int)list.get(i);
        }
        return 0;
    }

    public int randomGrowth(List list)
    {
        for(int i =0; i < list.size(); i++)
        {
            if((int)(Math.random()*100) <= probability)
                return (int)list.get(i);
        }
        return 0;
    }




    public int checkNeigh(List list)
    {
        ArrayList<Integer> sorted = new ArrayList<>();
        HashMap<Integer, Integer> max = new HashMap<>();
        int counter = 0;

        for(int i =0; i < list.size(); i++)
        {
            if (Collections.frequency(list,list.get(i)) == list.size())
                return (int)list.get(i);

            max.put(i,Collections.frequency(list,list.get(i)));
        }

        max.put(5,0);
        for(int i =0; i<list.size();i++)
        {
            if(max.get(i) > max.get(5))
                max.put(5,max.get(i));

            if(max.get(i) == 1)
                counter++;
        }


        if(counter == list.size()) {

            return (int) list.get(max.get((int) (Math.random() * list.size())));

        }
        else
            return (int)list.get(max.get(5));

    }



    public void setInclusions(int amount, int size, String type)
    {
        int settedInclusions = 0;

        typeOfInclusions = type;

        while (settedInclusions < amount) {

            int posY = (int) (Math.random() * y_dimm);
            int posX = (int) (Math.random() * x_dimm);

            if (fillInclusion(posX, posY, size, typeOfInclusions,settedInclusions, amount))
                 settedInclusions++;

            if(settedInclusions == (amount ))
                afterFlag = 0;
        }

    }


    public boolean fillInclusion(int x, int y, int size, String type, int set, int amount)
    {

        if(afterFlag == 1)
        {
            try {
                if (grainArray[y][x - 1] == grainArray[y][x] && grainArray[y][x + 1] == grainArray[y][x]
                        && grainArray[y - 1][x] == grainArray[y][x] && grainArray[y + 1][x] == grainArray[y][x]
                       && grainArray[y- 1][x - 1] == grainArray[y][x] && grainArray[y+1][x + 1] == grainArray[y][x]
                        && grainArray[y-1][x + 1] == grainArray[y][x] && grainArray[y+1][x - 1] == grainArray[y][x])
                    return false;
            }catch (ArrayIndexOutOfBoundsException ex){ return false;}
        }

        if(type == "Square")
        for(int i =0; i < y; i++)
        {
            for(int j = 0; j< x; j++)
            {
                if(j>(x - size) && j<(x + size) && i < (y+size) && i > (y-size)) {

                    grainArray[i][j] = -1;
                }
            }
        }

        else {
            for (int i = 0; i < y_dimm; i++) {
                for (int j = 0; j < x_dimm; j++) {
                    if (calculateRadius(x, y, j, i, size))
                        grainArray[i][j] = -1;
                }
            }
        }

        if(afterFlag == 1 && set == amount +1)
            afterFlag = 0;
        return true;
    }

    public boolean calculateRadius(int x, int y, int x1, int y1, int size)
    {
        int x_relative = abs(x1 - x);
        int y_relative = abs(y1 - y);

        int radius =(int) Math.sqrt( Math.pow(x_relative, 2) + Math.pow(y_relative, 2) );


        if (radius < size/2)
            return true;
        else
            return false;
    }

    public void setAfterFlag()
    {
        afterFlag = 1;
    }









}
