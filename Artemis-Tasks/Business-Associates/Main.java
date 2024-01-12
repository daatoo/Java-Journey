package fop.w11part;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BusinessPenguin peter = new BusinessPenguin("Peter");
        BusinessPenguin paul = new BusinessPenguin("Paul");

        peter.setPartner(paul);
        paul.setPartner(peter);

        Customer petersCustomer = new Customer(peter);
        Customer paulsCustomer = new Customer(paul);

        Thread first = new Thread(petersCustomer);
        Thread second = new Thread(paulsCustomer);

        first.start();
        second.start();

        first.join();
        second.join();

        System.out.println(peter.balance);
        System.out.println(paul.balance);
    }
}
