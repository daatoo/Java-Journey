package pgdp;

public class Artist {
    private String firstName;
    private String lastName;
    private int birthYear;
    private Album[] albums;
    private Song[] singles;

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public int getBirthYear(){
        return birthYear;
    }
    public Album[] getAlbums(){
        return albums;
    }
    public Song[] getSingles(){
        return singles;
    }
    public Song mostLikedSong(){
        Song mostLiked = null;
        for(int i = 0; i < albums.length; i++){
            int length = albums[i].getSongs().length;
            Song mostLikedInAlbum = albums[i].sortByPopularity(true)[length];
            if (mostLiked == null || mostLikedInAlbum.getLikes() > mostLiked.getLikes()) {
                mostLiked = mostLikedInAlbum;
            }
        }
        for(int i = 0; i < singles.length; i++){
            if(singles[i].getLikes() > mostLiked.getLikes()){
                mostLiked = singles[i];
            }
        }
        return mostLiked;
    }

    public Song leastLikedSong(){
        Song leastLiked = null;
        for(int i = 0; i < albums.length; i++){
            Song mostLikedInAlbum = albums[i].sortByPopularity(true)[0];
            if (leastLiked == null || mostLikedInAlbum.getLikes() > leastLiked.getLikes()) {
                leastLiked = mostLikedInAlbum;
            }
        }
        for(int i = 0; i < singles.length; i++){
            if(singles[i].getLikes() < leastLiked.getLikes()){
                leastLiked = singles[i];
            }
        }
        return leastLiked;
    }

    public int totalLikes(){
        int likes = 0;
        for(int i = 0; i < singles.length; i++){
            likes += singles[i].getLikes();
        }
        for(int i = 0; i < albums.length; i++){
            for(int j = 0; j < albums[i].getSongs().length; j++){
                likes += albums[i].getSongs()[j].getLikes();
            }
        }
        return likes;
    }

    public boolean isEqual(Artist other){
        if(this.firstName == other.getFirstName() && this.lastName == other.getLastName() && this.birthYear == other.getBirthYear()){
            return true;
        }else{
            return false;
        }
    }

    public String toString(){
        String result = "Name: " + firstName + " " + lastName + ",Birth year:" + birthYear + ",Total likes:" + totalLikes();
        return result;
    }
}
