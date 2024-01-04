package pgdp.collections;

public class Checkout {
    private final Queue<PenguinCustomer> queue;
    private final Queue<FishyProduct> bandBeforeCashier;
    private final Queue<FishyProduct> bandAfterCashier;

    public Checkout(){
        queue = new LinkedQueue<>();
        bandAfterCashier = new LinkedQueue<>();
        bandBeforeCashier = new LinkedQueue<>();
    }

    public Queue<FishyProduct> getBandAfterCashier() {
        return bandAfterCashier;
    }

    public Queue<FishyProduct> getBandBeforeCashier() {
        return bandBeforeCashier;
    }

    public Queue<PenguinCustomer> getQueue() {
        return queue;
    }

    public int queueLength(){
        return queue.size();
    }

    public void serveNextCustomer(){
        PenguinCustomer current = queue.dequeue();
        current.placeAllProductsOnBand(bandBeforeCashier);
        int sum = scanAllProducts();
        current.takeAllProductsFromBand(bandAfterCashier);
        current.pay(sum);
    }
    public int scanAllProducts(){
        int sum = 0;
        while(!bandBeforeCashier.isEmpty()){
            FishyProduct temp = bandBeforeCashier.dequeue();
            bandAfterCashier.enqueue(temp);
            sum += temp.getPrice();
        }
        return sum;
    }

    public void close(PenguinSupermarket supermarket){
        Stack<PenguinCustomer> ps = new LinkedStack<>();
        DataStructureLink<PenguinCustomer> link = new DataStructureLink<>(new QueueConnector<>(queue),
                new StackConnector<>(ps));
        link.moveAllFromAToB();
        while(!ps.isEmpty()){
            ps.pop().goToCheckout(supermarket);
        }
    }
}
