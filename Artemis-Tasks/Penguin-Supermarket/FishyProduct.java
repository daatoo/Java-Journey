package pgdp.collections;

public class FishyProduct {
    private final String name;
    private final int price;

    public FishyProduct(String name, int price){
        if(name == null) ExceptionUtil.illegalArgument("name must not be null");
        this.name = name;
        if(price <= 0) ExceptionUtil.illegalArgument("price must be greater than 0");
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
    @Override
    public String toString(){
        return "FishProduct " + name + " price(" + price + ")";
    }
}
