public class Election {
    private PenguinVoters pv;
    private Machine[] machines;

    public Election(int n){
        pv = new PenguinVoters();
        machines = new Machine[n];

        for(int i = 0; i < n; i++){
            machines[i] = new Machine(pv);
            machines[i].setDaemon(true);
        }
        for(int i = 0; i < n; i++){
            machines[i].start();
        }
    }

    public void arrive(Penguin p){
        pv.register(p);
    }
    public void shutdown() throws InterruptedException{
        for(int i = 0; i < machines.length; i++){
            machines[i].interrupt();
            //Thread.currentThread().join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Election e = new Election(4);
        e.arrive(() -> System.out.println("peng"));
        e.arrive(() -> System.out.println("guin"));
        //Thread.sleep(1000);
        e.shutdown();
    }
}
