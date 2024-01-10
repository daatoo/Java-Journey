package fop.w7geo;

public class Rectangle extends BaseArea {
    private double width;
    private double height;

    public Rectangle(double width, double height){
        this.width = width;
        this.height = height;
    }
    public double getWidth(){
        return width;
    }
    public double getHeight(){
        return height;
    }
    public void setWidth(double newWidth){
        width = newWidth;
    }
    public void setHeight(double newHeight){
        height = newHeight;
    }
    @Override
    public double circumference(){
        return 2 * (width + height);
    }
    @Override
    public double area(){
        return width * height;
    }
    @Override
    public boolean isSquare(){
        if(width == height){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public Square toSquare(){
        if(isSquare()){
            return new Square(width);
        }else{
            return null;
        }
    }
}
