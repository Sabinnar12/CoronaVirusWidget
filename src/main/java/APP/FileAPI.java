package APP;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileAPI {
    public static void WriteAPI(ArrayList<Integer> cases, ArrayList<Integer> deaths,ArrayList<String> dates, String path) throws IOException {
        FileWriter writer = new FileWriter(path,false);
        int size = cases.size();
        for (int i=0;i<size;i++) {
            String str = cases.get(i).toString()+","+deaths.get(i)+","+dates.get(i);
            writer.write(str);
            if(i < size-1)
            writer.write("\n");
        }
        writer.close();
    }




    public static void ReadAPI(String path) throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader(path));
        ArrayList<Integer> deaths = new ArrayList<>();
        ArrayList<Integer> cases = new ArrayList<>();
        ArrayList<String> dates = new ArrayList<>();
        String line = bufReader.readLine();


            while (line != null) {
                line = bufReader.readLine();
                System.out.println(line);
                if(line!=null) {
                    String[] data = line.split(",");
                    deaths.add(Integer.parseInt(data[1]));
                    cases.add(Integer.parseInt(data[0]));
                    dates.add(data[2]);
                }
                else {
                    System.err.println("No Data in File");
                }
            }
            bufReader.close();
            Varibles.deaths_list = deaths;
            Varibles.cases_list = cases;
            Varibles.dates_list=dates;
        }


    }


