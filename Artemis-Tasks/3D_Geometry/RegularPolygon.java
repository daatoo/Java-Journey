package fop.w7geo;

public class RegularPolygon extends BaseArea {
    private int n;
    private double length;
    public RegularPolygon(int n, double length){
        this.n = n;
        this.length = length;
    }
    public int getN(){
        return n;
    }
    public double getLength(){
        return length;
    }
    public void setLength(double newLength){
        length = newLength;
    }
    public void setN(int newN){
        n = newN;
    }
    @Override
    public double circumference(){
        return n * length;
    }
    @Override
    public double area(){
        return (n * length * length) / (4 * Math.tan(Math.PI/n));
    }
    @Override
    public boolean isSquare(){
        if(n == 4){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public Square toSquare(){
        if(isSquare()){
            return new Square(length);
        }else{
            return null;
        }
    }
}
