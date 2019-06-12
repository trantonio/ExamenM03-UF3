package Example.AguirreAntonio.v4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Lectura implements Comparable<Lectura>{

    File file1 = new File ("data/in/2015_ACCIDENTS_CAUSES_GU_BCN_2015 v2.csv");
    String splitBy= ",";
    String line ="";
    int numColum;

    boolean oneLine= false;

    public Lectura() {

        try(BufferedReader br= new BufferedReader(new FileReader(file1))){

            while((line = br.readLine())!=null){
                int i =0;
                if(oneLine) {
                    if (!line.isEmpty()) {
                        String[] lineArray = line.split(splitBy);
                        if(lineArray.length==numColum){
                            new Accidentes(lineArray);
                        }else{
                            System.err.println("Le Falta el ultimo argumento");
                        }
                    }
                }else {
                    i++;
                    if(i>=4) {
                        String[] la = line.split(splitBy);
                        numColum = la.length;
                        oneLine = true;
                    }
                }
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public int compareTo(Lectura o) {
        return 0;
    }
}



