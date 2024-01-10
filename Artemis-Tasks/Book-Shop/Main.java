package fop.w11shop;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        
        BookShop shop = new BookShop();
        shop.addBooksInStore(7500);

        Customer peter = new Customer("Peter", shop);
        Customer paul = new Customer("Pauls", shop);

        Thread peterThread = new Thread(peter);
        Thread paulThread = new Thread(paul);

        peterThread.start();
        paulThread.start();

        peterThread.join();
        paulThread.join();

        shop.printSummary();
        peter.printSummary();
        paul.printSummary();
    }
}
