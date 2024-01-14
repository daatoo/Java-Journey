import java.util.Objects;

public class CarPart implements MarketItem{
    private final String name;
    private final int price;

    public CarPart(String name, int price) throws IllegalArgumentException{
        if(name == null || price < 0){
            throw new IllegalArgumentException("name or price is incorrect");
        }
        this.name = name;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
    @Override
    public int hashCode(){
        return Objects.hash(name);
    }

    public boolean equals(CarPart otherCar){
        if(otherCar == null)
            return false;


        if(otherCar.name.equals(name))
            return true;

        return false;
    }


}
