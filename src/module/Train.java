package module;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Train {
    HashMap<Integer, List<RGB>> dataPairs = new HashMap<>();



    //Initialize the training equation with exit data
    public Train() throws IOException, ParseException {
        //read from file
        read();

        //train
        for (Map.Entry<Integer, List<RGB>> keySet: dataPairs.entrySet()) {
            int type = keySet.getKey();
            List<RGB> RGBs = keySet.getValue();
            for(RGB rgb: RGBs){
                trainOneDataPair(rgb, type);
            }
        }

    }

    public void addAndTrainOneDataPair(RGB rgb, int type) throws FileNotFoundException {
        List<RGB> newRGBList = dataPairs.get(type);
        newRGBList.add(rgb);
        dataPairs.put(type, newRGBList);

        //train
        trainOneDataPair(rgb, type);

        //write to file
        write();

    }

    private void trainOneDataPair(RGB rgb, int type){



    }

    private void read() throws IOException, ParseException {

        Object objDataPairs = new JSONParser().parse(new FileReader("dataPairs.json"));

        Map<Integer, List<RGB>> mapDataPairs = (JSONObject)objDataPairs;

            for (Map.Entry<Integer, List<RGB>> keySet: mapDataPairs.entrySet()) {
                int type = keySet.getKey();
                List<RGB> RGBs = keySet.getValue();
                dataPairs.put(type, RGBs);
            }

    }

    private void write() throws FileNotFoundException {

        JSONObject objSemesters = new JSONObject(dataPairs);

        PrintWriter writer = new PrintWriter("Semesters.json");
        writer.write(objSemesters.toJSONString());
        writer.close();

    }
}
