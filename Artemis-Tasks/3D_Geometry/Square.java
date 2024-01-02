package fop.w7geo;

public class Square extends BaseArea {
    private double length;
    public Square(double length){
        this.length = length;
    }
    public double getLength(){
        return length;
    }
    public void setLength(double newLength){
        length = newLength;
    }
    @Override
    public double circumference(){
        return 4 * length;
    }
    @Override
    public double area(){
        return length * length;
    }
    @Override
    public boolean isSquare(){
        return true;
    }
    @Override
    public Square toSquare(){
        return this;
    }
}
