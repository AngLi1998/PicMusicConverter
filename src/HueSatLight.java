public class HueSatLight {
    private float h;     //Hue  色相，基本的色彩
    private float s;     //Saturation 画面饱和度
    private float l;     //light  亮度

    public HueSatLight(){

    }

    public HueSatLight(float h, float s, float l){
        setHue(h);
        setSat(s);
        setLig(l);
    }

    public float getHue(){
        return h;
    }

    public void setHue(float h) {
        if (h<0) {
            this.h = 0;

        } else if (h > 360) {
            this.h = 360;
        } else {
            this.h = h;
        }
    }

    public float getSat() {
        return s;
    }

    public void setSat(float s) {
        if (s < 0) {
            this.s = 0;
        } else if (s > 255) {
            this.s = 255;
        } else {
            this.s = s;
        }
    }

    public float getLig() {
        return l;
    }

    public void setLig(float l) {
        if (l < 0) {
            this.l = 0;
        } else if (l > 255) {
            this.l = 255;
        } else {
            this.l = l;
        }
    }

    @Override
    public boolean equals(Object obj) {
        HueSatLight theHSL = (HueSatLight) obj;
        return this.getHue() == theHSL.getHue() && this.getSat() == theHSL.getSat() && this.getLig() == theHSL.getLig();
    }

    @Override
    public int hashCode() {
        return Float.valueOf(this.getHue() * 1000000 + this.getSat() * 1000 + this.getLig()).intValue();
    }

    public String toString() {
        return "HSL {" + h + ", " + s + ", " + l + "}";
    }



}
