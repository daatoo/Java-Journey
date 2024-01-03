package pgdp;

public class Song {
    private String title;
    private int releaseYear;
    private int duration;
    private int likes;
    public Song(String title, int releaseYear, int duration, int likes){
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = duration;
        this.likes = likes;
    }
    public Song(String title, int releaseYear){
        this.title = title;
        this.releaseYear = releaseYear;
        this.duration = 60;
        this.likes = 0;
    }
    public String getTitle(){
        return title;
    }
    public int getReleaseYear(){
        return releaseYear;
    }
    public int getDuration(){
        return duration;
    }
    public int getLikes(){
        return  likes;
    }
    public boolean changeDuration(int newDuration){
        if(newDuration == 0 || newDuration > 720 || newDuration == duration){
            return false;
        }else{
            duration = newDuration;
            return true;
        }
    }
    public void like(){
        likes++;
    }
    public void unlike(){
        likes--;
    }
    public String toString(){
        double result = (double) duration / 60;
        return "Title: " + title + ", Duration: " + result + ", Release year: " + releaseYear + ", Likes: " + likes;
    }
    public boolean isEqual(Song other){
        if(title == other.title && releaseYear == other.releaseYear && duration == other.duration){
            return true;
        }else{
            return false;
        }
    }
}
