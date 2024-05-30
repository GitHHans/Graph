package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * This class is used to read from the csv file that the user will input
 */
public class read {

    /**
     * Method only reads from one column of a csv
     * @param col column chosen
     * @param filepath Filepath of the file to be scanned
     * @param delimiter The character that will be used to separate the values
     * @return return an array of strings
     */
    public static String[] readCol(int col,String filepath,String delimiter){
        String[] data;
        String currentLine;
        ArrayList<String> colData = new ArrayList<>();

        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            while ((currentLine = br.readLine()) != null){

                data = currentLine.split((delimiter));

                colData.add(data[col]);
                System.out.println(currentLine);
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return colData.toArray(new String[0]);
    }

    /**
     * This method reads a specified row of a csv file and returns
     * all the values in a string array
     * @param row row number that will be read
     * @param filepath destination of the file that will be read
     * @param delimiter the single character that will separate the values from the csv file
     * @return return an array of strings
     */

    public static String[] readRow(int row,String filepath,String delimiter){
        String[] data;
        String currentLine;
        ArrayList<String> colData = new ArrayList<>();

        try {
            FileReader fr = new FileReader(filepath);
            BufferedReader br = new BufferedReader(fr);
            int count = 0;

            while ((currentLine = br.readLine()) != null && count <= row){
                data = currentLine.split((delimiter));
           //     System.out.println(data[2]);
                if (count == row){
                    for (int i =0; i < data.length;i++){
                        colData.add(data[i]);
                    }
                }
          //      System.out.println(count);
          //      System.out.println(colData);
                count++;
                //     System.out.println(currentLine);
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return colData.toArray(new String[0]);
    }


}
