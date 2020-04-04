package module;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.abs;
import static java.lang.Math.sin;

public class Train {
    private HashMap<Integer, List<RGB>> dataPairs = new HashMap<>();


    private double threshold = 0.5; //decide when output true or false
    private double alpha = 0.001; //learning parameter where 0<α≤1
    private double error; //hold actual error of the training sample
    private double error_sum; //hold the error sum of the training set

    //6 algorithms:
    //algorithm1: first level: either 1-4 or 5-7
    //algorithm2: second level if in 1-4: either 1-2 or 3-4
    //algorithm3: second level if in 5-7: either 5-6 or 7
    //algorithm4: third level if in 1-2: either 1 or 2
    //algorithm5: third level if in 3-4: either 3 or 4
    //algorithm6: third level if in 5-6: either 5 or 6
    private HashMap<Integer, List<Double>> algorithms = new HashMap<>();



    //Initialize the training equation with exit data
    public Train() throws IOException, ParseException {
        //initialize algorithm
        for(int i=0; i<6; i++){
            List<Double> algorithm = new ArrayList<>();
            algorithm.add(1.0); //hold actual weight of input 0
            algorithm.add(1.0); //hold actual weight of input 1
            algorithm.add(1.0); //hold actual weight of input 2
            algorithm.add(1.0); //hold actual weight of input 3
            algorithm.add(1.0); //value of input 0; it's the bias
            algorithms.put(i+1, algorithm);
        }

        //read from file
        try{
            read();
        } catch (Exception e) {
            dataPairs.put(1, new ArrayList<>());
            dataPairs.put(2, new ArrayList<>());
            dataPairs.put(3, new ArrayList<>());
            dataPairs.put(4, new ArrayList<>());
            dataPairs.put(5, new ArrayList<>());
            dataPairs.put(6, new ArrayList<>());
            dataPairs.put(7, new ArrayList<>());

            e.printStackTrace();
        }

        //train
        for(int i = 0; i<10000; i++){
            error_sum = 0;
            for (Map.Entry<Integer, List<RGB>> keySet : dataPairs.entrySet()) {
                int type = keySet.getKey();
                List<RGB> RGBs = keySet.getValue();
                for (RGB rgb : RGBs) {
                    trainOneDataPair(rgb, type);
                }
            }
            System.out.println(error_sum);
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

    public int type(RGB rgb){
        int red = rgb.getRed();
        int green = rgb.getGreen();
        int blue = rgb.getBlue();
        if(getOneAlgorithm(1, red, green, blue)){
            if(getOneAlgorithm(2, red, green, blue)){
                if(getOneAlgorithm(4, red, green, blue)){
                    return 1;
                }else{
                    return 2;
                }
            }else{
                if(getOneAlgorithm(5, red, green, blue)){
                    return 3;
                }else{
                    return 4;
                }
            }
        }else{
            if(getOneAlgorithm(3, red, green, blue)){
                if(getOneAlgorithm(6, red, green, blue)){
                    return 5;
                }else{
                    return 6;
                }
            }else{
                return 7;
            }
        }
    }

    private void trainOneDataPair(RGB rgb, int type){

        int red = rgb.getRed();
        int green = rgb.getGreen();
        int blue = rgb.getBlue();

        if (type <= 4) {
            trainOneAlgorithm(1, red, green, blue, 1);
            if(type <= 2){
                trainOneAlgorithm(2, red, green, blue, 1);
                if(type == 1){
                    trainOneAlgorithm(4, red, green, blue, 1);
                } else {
                    trainOneAlgorithm(4, red, green, blue, 0);
                }
            } else {
                trainOneAlgorithm(2, red, green, blue, 0);
                if(type == 3){
                    trainOneAlgorithm(5, red, green, blue, 1);
                } else {
                    trainOneAlgorithm(5, red, green, blue, 0);
                }
            }
        } else {
            trainOneAlgorithm(1, red, green, blue, 0);
            if(type <= 6){
                trainOneAlgorithm(3, red, green, blue, 1);
                if(type == 5){
                    trainOneAlgorithm(6, red, green, blue, 1);
                } else {
                    trainOneAlgorithm(6, red, green, blue, 0);
                }
            } else {
                trainOneAlgorithm(3, red, green, blue, 0);
            }
        }

    }

    private void read() throws IOException, ParseException {



            try{
                Object objDataPairs = new JSONParser().parse(new FileReader("dataPairs.json"));

                Map<String, Object> mapDataPairs = (JSONObject)objDataPairs;
                for (Map.Entry<String, Object> keySet: mapDataPairs.entrySet()) {
                    int type = Integer.parseInt(keySet.getKey());

                    JSONArray objRGBs = (JSONArray) keySet.getValue();
                    List<RGB> RGBs = new ArrayList<>();
                    for (int i = 0, size = objRGBs.size(); i < size; i++) {
                        Map<String, Long> RGB = (JSONObject) objRGBs.get(i);

                        int r = RGB.get("red").intValue();
                        int g = RGB.get("green").intValue();
                        int b = RGB.get("blue").intValue();

                        RGBs.add(new RGB(r,g,b));

                    }
                    dataPairs.put(type, RGBs);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }




        

    }

    private void write() throws FileNotFoundException {
        Map<Integer, Object> mapDataPairs = new HashMap<>();

        for (int i = 0, size = dataPairs.size(); i < size; i++) {

            JSONArray RGBs = new JSONArray();
            for (RGB rgb: dataPairs.get(i+1)){
                Map<String, Integer> RGB = new HashMap<>();

                RGB.put("red", rgb.getRed());
                RGB.put("green", rgb.getGreen());
                RGB.put("blue", rgb.getBlue());

                JSONObject objRGB = new JSONObject(RGB);

                RGBs.add(objRGB);
            }
            mapDataPairs.put(i+1,RGBs);

        }

        JSONObject objDataPairs = new JSONObject(mapDataPairs);

        PrintWriter writer = new PrintWriter("dataPairs.json");
        writer.write(objDataPairs.toJSONString());
        writer.close();

    }

    private void trainOneAlgorithm(int algorithmNum, int red, int green, int blue, int expectOutput){

        //Define y here.
        double y = algorithms.get(algorithmNum).get(4) * algorithms.get(algorithmNum).get(0) + red * algorithms.get(algorithmNum).get(1) + green * algorithms.get(algorithmNum).get(2) + blue * algorithms.get(algorithmNum).get(3);

        //Create an if-statement for y.
        int f_x;
        if (y > threshold)
        {
            f_x = 1;
        }
        else
        {
            f_x = 0;
        }

        //Define the error.
        error = expectOutput - f_x;
        System.out.println(error);

        // Define the error_sum.
        error_sum = error_sum + abs(error);

        //Change the weights of the hidden layer
        double w_0 = algorithms.get(algorithmNum).get(0) + alpha * algorithms.get(algorithmNum).get(4) * error;
        double w_1 = algorithms.get(algorithmNum).get(1) + alpha * red * error;
        double w_2 = algorithms.get(algorithmNum).get(2) + alpha * green * error;
        double w_3 = algorithms.get(algorithmNum).get(3) + alpha * blue * error;

        algorithms.get(algorithmNum).set(0, w_0);
        algorithms.get(algorithmNum).set(1, w_1);
        algorithms.get(algorithmNum).set(2, w_2);
        algorithms.get(algorithmNum).set(3, w_3);


    }

    private boolean getOneAlgorithm(int algorithmNum, int red, int green, int blue) {
        double y = algorithms.get(algorithmNum).get(4) * algorithms.get(algorithmNum).get(1) + sin(red) * algorithms.get(algorithmNum).get(2) + sin(green) * algorithms.get(algorithmNum).get(3) + sin(blue) * algorithms.get(algorithmNum).get(3);
        return y > threshold;
    }
}
