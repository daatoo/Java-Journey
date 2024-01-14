public class Customer {
    private final String name;
    private int money;
    private final Cart consumerCart = new Cart();
    public  Customer(String name, int money) throws  IllegalArgumentException{
        if(name == null || money < 0)
            throw new IllegalArgumentException("illegal arguments");
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public Cart getConsumerCart() {
        return consumerCart;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void addItemToCart(MarketItem item, int n){
        consumerCart.addItem(item, n);
    }
    public boolean removeItem(MarketItem item, int n){
        return consumerCart.removeItem(item, n);
    }
    public boolean buyItemsFromCart(){
        if(consumerCart.calculatePrice() <= money){
            money = consumerCart.checkout(money);
            return true;
        }else{
            return false;
        }
    }
}
