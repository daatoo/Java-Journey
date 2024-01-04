package pgdp.collections;

public class PenguinCustomer {
    private String name;
    private int money;
    private Stack<FishyProduct> products;

    public PenguinCustomer(String name, int money){
        if(name == null) ExceptionUtil.illegalArgument("name must not be null");
        this.name = name;
        if(money < 0) ExceptionUtil.illegalArgument("money must be positive");
        this.money = money;
        this.products = new LinkedStack<>();
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public Stack<FishyProduct> getProducts() {
        return products;
    }

    public void addProductToBasket(FishyProduct product){
        products.push(product);
    }

    public void placeAllProductsOnBand(Queue<FishyProduct> band){
        DataStructureLink<FishyProduct> dls = new DataStructureLink<>(new StackConnector<>(products),
                new QueueConnector<>(band));
        dls.moveAllFromAToB();
    }

    public void takeAllProductsFromBand(Queue<FishyProduct> band){
        DataStructureLink<FishyProduct> dls = new DataStructureLink<>(new QueueConnector<>(band),
                new StackConnector<>(products));
        dls.moveAllFromAToB();
    }

    public void pay(int amount){
        if(amount <= 0) ExceptionUtil.illegalArgument("amount must be positive");
        if(money < amount) ExceptionUtil.illegalArgument(name + " cannot pay " + amount);
        money -= amount;
    }

    public void goToCheckout(PenguinSupermarket supermarket){
        supermarket.getCheckoutWithSmallestQueue().getQueue().enqueue(this);
    }

    @Override
    public String toString(){
        return "Customer " + name + ", money: " + money + ", products: " + products;
    }
}
