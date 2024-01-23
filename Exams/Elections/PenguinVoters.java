import java.util.ArrayList;
import java.util.List;

public class PenguinVoters {
    private List<Penguin> penguins = new ArrayList<>();

    public List<Penguin> getPenguins() {
        return penguins;
    }

    public synchronized void register(Penguin p){
        penguins.add(p);
        notify();
    }


    public synchronized Penguin admit() throws InterruptedException {
        while(penguins.isEmpty()){
            wait();
        }
        return penguins.remove(0);
    }
}
