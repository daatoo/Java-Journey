public class Bistro {
    private int seats;
    private Thread order, meal, waiter;

    public Bistro(int n) {
        this.seats = n;

        this.waiter = new Thread(() -> {
            try {
                while(!waiter.isInterrupted()){
                    serve();
                }
            } catch (InterruptedException e) {
                System.out.println("Enjoy");
            }
        });
        this.waiter.start();
    }

    public synchronized void dine(int price) throws InterruptedException {
        while(seats == 0) {
            wait();
        }
        seats--;
        while(order != null){
            wait();
        }
        order = Thread.currentThread();
        System.out.println("Guest " + order.getName() + " orders for " + price + " lari");
        notifyAll();

        while(this.meal != Thread.currentThread()){
            wait();
        }
        meal = null;
        System.out.println("Guest " + Thread.currentThread().getName() + " eats ...");

        seats++;
        notifyAll();


    }

    private synchronized void serve() throws InterruptedException {
        while(order == null){
            wait();
        }
        this.meal = this.order;
        this.order = null;

        while(meal != null){
            wait();
        }
        System.out.println("Enjoy");
        notifyAll();


    }

    public void shutdown() {
        waiter.interrupt();
        System.out.println("Shut down!");
    }

    public static void main(String[] arr) {
        Bistro b = new Bistro(2);
        Thread g1 = new Thread(() -> {
            try {
                b.dine(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread g2 = new Thread(() -> {
            try {
                b.dine(20);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread g3 = new Thread(() -> {
            try {
                b.dine(30);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread g4 = new Thread(() -> {
            try {
                b.dine(40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        g1.start();
        g2.start();
        g3.start();
        g4.start();

        try {
            g1.join();
            g2.join();
            g3.join();
            g4.join();
        } catch (InterruptedException e) {}

        b.shutdown();
    }
}