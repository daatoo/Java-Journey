public class Line<T extends Thesis> {
    private Thesis.State state;
    private String content = "";
    private Thread[] writers = new Thread[5];
    private final T ghost;

    public Line(T ghost, String title){
        content = title;
        state = Thesis.State.INTRO;
        this.ghost = ghost;

        Runnable introThread = () -> {
            content += ghost.intro();
            state = Thesis.State.SETUP;
            synchronized (ghost){
                ghost.notifyAll();
            }
        };

        Runnable setupThread = () -> {
            waitForState(Thesis.State.SETUP);
            content += ghost.setup();
            state = Thesis.State.EXPERIMENTS;
            synchronized (ghost){
                ghost.notifyAll();
            }
        };

        Runnable experimentsThread = () -> {
            waitForState(Thesis.State.EXPERIMENTS);
            content += ghost.experiments();
            state = Thesis.State.CONCLUSION;
            synchronized (ghost){
                ghost.notifyAll();
            }
        };

        Runnable conclusionThread = () -> {
            waitForState(Thesis.State.CONCLUSION);
            content += ghost.conclusion();
            state = Thesis.State.REFS;
            synchronized (ghost){
                ghost.notifyAll();
            }
        };

        Runnable refsThread = () -> {
            waitForState(Thesis.State.REFS);
            content += ghost.refs();
            state = Thesis.State.FINISHED;
            synchronized (ghost){
                ghost.notifyAll();
            }
        };

        writers[0] = new Thread(introThread);
        writers[1] = new Thread(setupThread);
        writers[2] = new Thread(experimentsThread);
        writers[3] = new Thread(conclusionThread);
        writers[4] = new Thread(refsThread);

        for (Thread writer : writers) {
            writer.start();
        }

        if(state == Thesis.State.FINISHED){
            for (Thread writer : writers) {
                writer.interrupt();
            }
        }
    }

    public void waitForState(Thesis.State desired){
            while(state != desired){
                try{
                    synchronized (ghost){
                        ghost.wait();
                    }
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                    return;
                }
            }

    }
    public String result() throws InterruptedException {
        waitForAllWriters();
        return content;
    }

    public void waitForAllWriters() throws InterruptedException {
        for (Thread writer : writers) {
            writer.join();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        class MyThesis implements Thesis{

            @Override
            public String intro() {
                return args[1];
            }

            @Override
            public String setup() {
                return args[2];
            }

            @Override
            public String experiments() {
                return args[3];
            }

            @Override
            public String conclusion() {
                return args[4];
            }

            @Override
            public String refs() {
                return args[5];
            }
        }
        MyThesis myThesis = new MyThesis();
        Line<MyThesis> line = new Line<>(myThesis, args[0]);
        System.out.println(line.result());
    }

}
