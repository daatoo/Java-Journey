package fop.w7geo;

public class Circle extends BaseArea {
    private double radius;
    public Circle(double radius){
        this.radius = radius;
    }
    public double getRadius(){
        return radius;
    }
    public void setRadius(double newRadius){
        radius = newRadius;
    }
    @Override
    public double circumference(){
        return 2 * radius * Math.PI;
    }
    @Override
    public double area(){
        return Math.PI * radius * radius;
    }
    @Override
    public boolean isSquare(){
        return false;
    }
    @Override
    public Square toSquare(){
        return null;
    }
}
