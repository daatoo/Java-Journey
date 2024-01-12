package w12space;


import java.util.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Spaceuin extends Thread {

    private Beacon start;
    private Beacon destination;
    private FlightRecorder flightRecorder;
    private final Set<String> set = new HashSet<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private static final List<Thread> threads = new LinkedList<>();
    private static final Map<Beacon, Boolean> occupiedBeacons = new HashMap<>();
    public Spaceuin(Beacon start, Beacon destination, FlightRecorder flightRecorder) {
        this.start = start;
        this.destination = destination;
        this.flightRecorder = flightRecorder;
    }
    private void dfs(BeaconConnection beaconConnection) {
        if(Thread.currentThread().isInterrupted()){
            return;
        }

        Beacon current = beaconConnection.beacon();
        synchronized (occupiedBeacons) {
            if (occupiedBeacons.getOrDefault(current, false)) {
                return; // If the beacon is already occupied, this Spaceuin can't visit it.
            }
            occupiedBeacons.put(current, true); // Mark the beacon as occupied.
        }
        try{
            flightRecorder.recordArrival(current);
            set.add(current.name());

            if(current.equals(destination)){
                lock.readLock().lock();
                interruptAllSpaceuins();
                flightRecorder.tellStory();
                lock.readLock().unlock();
                return;
            }

            List<BeaconConnection> connections = current.connections();
            for(BeaconConnection connection : connections){
                if(!set.contains(connection.beacon().name())){

                    if(connection.type() == ConnectionType.NORMAL){
                        dfs(connection);
                    }else{
                        Thread newThread = new Spaceuin(connection.beacon(), destination, flightRecorder.createCopy());
                        newThread.start();
                    }
                }
            }
        } finally {
            synchronized (occupiedBeacons) {
                occupiedBeacons.remove(current); // Free up the beacon when leaving.
            }
        }
    }
    public void interruptAllSpaceuins() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
        threads.clear();
    }
    @Override
    public void run() {
        synchronized (threads){
            threads.add(this);
        }
        BeaconConnection first = new BeaconConnection(start, ConnectionType.NORMAL);
        dfs(first);
        synchronized (threads){
            threads.remove(this);
        }
    }

    @Override
    public String toString() {
        // changing that might be useful for testing
        return super.toString();
    }
}

