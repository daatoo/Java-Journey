package fop.w7geo;

public class Prism {
    private BaseArea base;
    private double height;
    public Prism(double height, BaseArea base){
        this.height = height;
        this.base = base;
    }
    public BaseArea getBase() {
        return base;
    }

    public double getHeight() {
        return height;
    }

    public void setBase(BaseArea base) {
        this.base = base;
    }

    public void setHeight(double height) {
        this.height = height;
    }
    public double surface(){
        return 2 * base.area() + base.circumference() * height;
    }
    public double volume(){
        return base.area() * height;
    }
    public boolean isCube(){
        Square square = base.toSquare();
        return square != null && square.getLength() == height;
    }

  
}
