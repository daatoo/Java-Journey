package fop.w8inter;

public class Penguin extends Animal implements Comparable, Copyable{
    private String name;
    private double height;
    public Penguin(int age, String name, double height){
        super(age);
        this.name = name;
        this.height = height;
    }
    public String getName(){
        return name;
    }
    public double getHeight(){
        return height;
    }
    public void setHeight(double height){
        this.height = height;
    }
    public void setName(String name){
        this.name = name;
    }
    @Override
    public int compareTo(Penguin other){
        if(Integer.compare(this.age, other.age) > 0){
            return 1;
        }else if(Integer.compare(this.age, other.age) < 0){
            return -1;
        }else{
            if(Double.compare(this.height, other.getHeight()) > 0){
                return 1;
            }else if(Double.compare(this.height, other.getHeight()) < 0){
                return -1;
            }else{
                if(this.name.compareTo(other.getName()) > 0){
                    return 1;
                }else if(this.name.compareTo(other.getName()) < 0){
                    return -1;
                }else{
                    return 0;
                }
            }
        }
    }
    @Override
    public Penguin copy(){
        return new Penguin(age, name, height);
    }
}
