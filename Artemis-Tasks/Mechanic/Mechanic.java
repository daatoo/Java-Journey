public class Mechanic extends Customer{

    private final int reduction;

    public Mechanic(String name, int money, int reduction) throws IllegalArgumentException{
        super(name, money);
        if(reduction < 0)
            throw new IllegalArgumentException("Illegal argument");
        this.reduction = reduction;
    }

    @Override
    public boolean buyItemsFromCart(){
        if(getConsumerCart().calculatePrice() <= getMoney()){
            int currentMoney = getMoney();
            double newMoney = (double) currentMoney - (currentMoney - getConsumerCart().checkout(getMoney())) * (1 - (double) reduction / 100);
            setMoney((int) newMoney);
            return true;
        }else{
            return false;
        }
    }
}
