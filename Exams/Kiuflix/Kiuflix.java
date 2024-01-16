import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Kiuflix<V extends Video> {
    private Stream<V> stream;
    private List<V> downloads;
    private int budget;
    private int cost;

    public Kiuflix(Stream<V> stream, int budget, int cost){
        this.budget = budget;
        this.stream = stream;
        this.downloads = new LinkedList<>();
        this.cost = cost;
    }
    
    public void bulkView(Predicate<V> predicate){
        downloads = stream.filter(predicate).limit(budget/cost).collect(Collectors.toList());
        view(downloads);

    }
    public void view(List<V> list){
        for(V video : list){
            if(video == null){
                break;
            }else{
                budget -= cost;
            }
        }

        System.out.println("\nRemaining budget:\t" + budget);
    }

    public static void main(String[] args) {
        class MyVideo implements Video{
            private String title;

            public MyVideo(String title){
                this.title = title;
            }


            @Override
            public void view() {
                System.out.println(title);
            }

            @Override
            public void skip() {

            }

            @Override
            public String title() {
                return title;
            }
        }
        String filename = args[0];

        Stream<MyVideo> videoStream = null;
        try{
            videoStream = Files
                    .lines(Path.of(filename))
                    .map(MyVideo::new);
        } catch (IOException e) {
            System.out.println("Error reading titles from a file");
            return;
        }
        Kiuflix<MyVideo> flix = new Kiuflix<>(videoStream, 100, 15);

        flix.bulkView(video -> video.title.length() % 4 == 1);
    }
}
