package module;

public class Main {
    public static void main(String[] args) throws Exception {
        String path = "图片地址";
        RGB rgb = GetTheMainRGB.getMainRGB(path);
        System.out.println(rgb);
    }
}
