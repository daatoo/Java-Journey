public class Machine extends Thread{
    private PenguinVoters voters;
    public Machine(PenguinVoters voters){
        this.voters = voters;
    }

    @Override
    public void run() {
       while(!Thread.currentThread().isInterrupted()){
           synchronized (voters){
               while(voters.getPenguins().isEmpty()){
                   try {
                       voters.wait();
                   } catch (InterruptedException e) {
                       System.out.println("Machine interrupted");
                   }
               }
               while(!voters.getPenguins().isEmpty()){
                   try {
                       voters.admit().vote();

                   } catch (Exception ignored) {}
               }
           }
       }

    }
}
