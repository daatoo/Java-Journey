package pgdp;

import java.util.ArrayList;
import java.util.Arrays;

public class SpotiJy {
    private Artist[] artists;
    public SpotiJy(){
        artists = null;
    }
    public Artist[] getArtists(){
        return  artists;
    }
    public void addArtists(Artist[] artists){
        ArrayList<Artist> newArtists = new ArrayList<Artist>(Arrays.asList(this.artists));
        boolean equals = false;
        for(Artist newArtist : artists){
            for(Artist oldArtist : this.artists){
                if(!newArtist.isEqual(oldArtist)){
                    newArtists.add(newArtist);
                }
            }
        }
        this.artists = newArtists.toArray(new Artist[0]);
    }

    public String[] getTopTrendingArtist(){
        Artist mostLiked = null;
        for(int i = 0; i < artists.length; i++){
            if(mostLiked == null || artists[i].totalLikes() > mostLiked.totalLikes()){
                mostLiked = artists[i];
            }
        }
        String[] result = new String[2];
        result[0] = mostLiked.getFirstName();
        result[1] = mostLiked.getLastName();
        return result;
    }

    public String getTopTrendingAlbum(){
        Album topAlbum = null;
        int topAlbumTotalLikes = 0;
        int currentAlbumTotalLikes = 0;

        for (Artist artist: this.artists) {
            for (Album album : artist.getAlbums()) {

                currentAlbumTotalLikes = 0;

                for (Song song: album.getSongs())
                    currentAlbumTotalLikes += song.getLikes();


                if (topAlbum == null || currentAlbumTotalLikes > topAlbumTotalLikes) {
                    topAlbum = album;
                    topAlbumTotalLikes = currentAlbumTotalLikes;
                }
            }
        }
        return topAlbum.getTitle();
    }

    public String getTopTrendingSong(){
        Song currentTrend = null, currentArtistTrend;

        for (Artist artist: this.artists) {
            currentArtistTrend = artist.mostLikedSong();

            if (currentTrend == null || currentArtistTrend.getLikes() > currentTrend.getLikes())
                currentTrend = currentArtistTrend;
        }

        return currentTrend.getTitle();
    }
}
