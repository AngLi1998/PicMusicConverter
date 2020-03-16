package module;

public class RGB {
    private int Red;
    private int Green;
    private int Blue;

    public RGB() {}
    public RGB(int Red, int Green, int Blue){
        setRed(Red);
        setBlue(Blue);
        setGreen(Green);
    }

    public int getRed(){
        return Red;
    }

    public void setRed(int Red) {
        if (Red < 0) {
            this.Red = 0;
        } else if (Red > 255)  {
            this.Red = 255;
        } else {
            this.Red = Red;
        }
    }

    public int getBlue(){
        return Blue;
    }

    public void setBlue(int Blue){
        if (Blue < 0) {
            this.Blue = 0;
        } else if (Blue > 255)  {
            this.Blue = 255;
        } else {
            this.Blue = Blue;
        }
    }

    public int getGreen() {
        return Green;
    }

    public void setGreen (int Green) {
        if (Green < 0) {
            this.Green = 0;
        } else if (Green > 255)  {
            this.Green = 255;
        } else {
            this.Green = Green;
        }
    }

    @Override
    public boolean equals(Object obj){
        RGB theRGB = (RGB) obj;
        return (this.getRed() == theRGB.getRed()
                && this.getBlue() ==theRGB.getBlue()
                && this.getGreen() == theRGB.getGreen());

    }

    @Override
    public int hashCode(){
        return this.getBlue() + this.getGreen()*1000 + this.getRed()*1000000;
    }

    @Override
    public String toString() {
        return "module.RGB" + "[" + this.Red + ","+this.Green+","+this.Blue+"]";
    }
}
