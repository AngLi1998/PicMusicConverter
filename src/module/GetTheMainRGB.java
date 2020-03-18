package module;

//import javafx.css.converter.ColorConverter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class GetTheMainRGB {

    public static RGB getMainRGB (String path) throws Exception {
        Map<Float, Integer> hueCountMap = new HashMap<>();
        Map<HueSatLight, Integer> hslCountMap = new HashMap<>();
        BufferedImage image = ImageIO.read(new File(path));
        int width = image.getWidth();
        int height = image.getHeight();
        int minx = image.getMinX();
        int miny = image.getMinY();

        for (int i = minx; i < width; i++) {
            for (int j = miny; j < height; j++) {
                int pixel = image.getRGB(i, j);
                Color color = new Color(pixel);
                RGB rgb = new RGB(color.getRed(), color.getGreen(), color.getBlue());
                HueSatLight hsl = RGBToHSLConverter.RGBToHSL(rgb);
                float h = computeHue(hsl.getHue());
                float s = computeSAndL(hsl.getSat());
                float l = computeSAndL(hsl.getLig());
                HueSatLight newHSL = new HueSatLight(h, s, l);

                Integer count = hueCountMap.get(h);
                if (count == null) {
                    hueCountMap.put(h, 1);
                } else {
                    hueCountMap.put(h, count + 1);
                }

                count = hslCountMap.get(newHSL);                //HSL的数量
                if (count == null) {
                    hslCountMap.put(newHSL, 1);
                } else {
                    hslCountMap.put(newHSL, count + 1);
                }
            }
        }
        //找出最多的Hue的值是哪个
        float maxHue = 0;
        int maxCount = 0;
        for(Map.Entry<Float, Integer> entry : hueCountMap.entrySet()){
            float hue = entry.getKey();
            int count = entry.getValue();
            if(count > maxCount){
                maxCount = count;
                maxHue = hue;
            }
        }


        HueSatLight maxHSL = null;
        maxCount = 0;
        for(Map.Entry<HueSatLight, Integer> entry : hslCountMap.entrySet()){
            HueSatLight hsl = entry.getKey();
            int count = entry.getValue();
            if(hsl.getHue() == maxHue && count > maxCount){
                maxCount = count;
                maxHSL = hsl;
            }
        }


        RGB resultRGB = RGBToHSLConverter.HSLToRGB(maxHSL);
        return resultRGB;
    }

    public static float computeHue (float h){
        if(h <= 15){
            return 0;
        }
        if(15 < h && h <= 45){
            return 30;
        }
        if(45 < h && h <= 75){
            return 60;
        }
        if(75 < h && h <= 105){
            return 90;
        }
        if(105 < h && h <= 135){
            return 120;
        }
        if(135 < h && h <= 165){
            return 150;
        }
        if(165 < h && h <= 195){
            return 180;
        }
        if(195 < h && h <= 225){
            return 210;
        }
        if(225 < h && h <= 255){
            return 240;
        }
        if(255 < h && h <= 285){
            return 270;
        }
        if(285 < h && h <= 315){
            return 300;
        }
        if(315 < h && h <= 345){
            return 330;
        }
        if(345 < h){
            return 360;
        }
        return 360;
    }

    public static float computeSAndL (float s){
        if(s <= 32){
            return 0;
        }
        if(32 < s && s <= 96){
            return 64;
        }
        if(96 < s && s <= 160){
            return 128;
        }
        if(160 < s && s <= 224){
            return 192;
        }
        if(s > 224){
            return 255;
        }
        return 255;
    }

}
