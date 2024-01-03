package pgdp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Album {
    private String title;
    private int releaseYear;
    private Song[] songs;

    public String getTitle(){
        return title;
    }
    public int getReleaseYear(){
        return releaseYear;
    }
    public Song[] getSongs(){
        return songs;
    }
    public Album(String title, int releaseYear){
        this.title = title;
        this.releaseYear = releaseYear;
    }
    public int addSongs(Song[] songs){
        int count = 0;
        ArrayList<Song> newSongs = new ArrayList<Song>(Arrays.asList(this.songs));
        boolean equals = false;
        for(Song newSong : songs){
            for(Song oldSong : this.songs){
                if(newSong == oldSong){
                    equals = true;
                }
            }
            if(!equals){
                newSongs.add(newSong);
                count++;
            }
        }
        this.songs = newSongs.toArray(new Song[0]);
        return count;
    }
    public Song[] shuffle(){
        ArrayList<Song> oldOrder = new ArrayList<Song>(Arrays.asList(songs));
        ArrayList<Song> newOrder = new ArrayList<Song>();

        Random random = new Random();
        for(int i = 0; i < songs.length; i++){
            int randomNum = random.nextInt(songs.length-i);
            newOrder.add(oldOrder.get(randomNum));
            oldOrder.remove(randomNum);
        }
        return newOrder.toArray(new Song[0]);
    }

    // sort by title using selection sort
    public Song[] sortByTitle(boolean isAscending){
        Song[] newSongs = Arrays.copyOf(songs, songs.length); // Create a copy of the array
        for(int i = 0; i < newSongs.length-1; i++){
            int index = i;
            for(int j = i + 1; j < newSongs.length; j++){
                if(isAscending){
                    if(newSongs[i].getTitle().compareTo(newSongs[j].getTitle()) < 0){
                        index = j;
                    }
                }else{
                    if(newSongs[i].getTitle().compareTo(newSongs[j].getTitle()) > 0){
                        index = j;
                    }
                }
            }
            if(index != i){
                Song temp = newSongs[i];
                newSongs[i] = newSongs[index];
                newSongs[index] = temp;
            }
        }
        return newSongs;

    }

    // sort by duration using bubble sort
    public Song[] sortByDuration(boolean isAscending){
        Song[] newSongs = Arrays.copyOf(songs, songs.length); // Create a copy of the array
        for(int i = 0; i < newSongs.length; i++){
            boolean swap = false;
            for(int j = 0; j < newSongs.length - i - 1; j++){
                if(isAscending){
                    if(newSongs[j+1].getDuration() < newSongs[j].getDuration()){
                        Song temp = newSongs[j+1];
                        newSongs[j+1] = newSongs[j];
                        newSongs[j] = temp;
                        swap = true;
                    }
                }else{
                    if(newSongs[j+1].getDuration() > newSongs[j].getDuration()){
                        Song temp = newSongs[j+1];
                        newSongs[j+1] = newSongs[j];
                        newSongs[j] = temp;
                        swap = true;
                    }
                }
            }
            if(!swap){
                break;
            }
        }
        return newSongs;
    }

    public Song[] sortByReleaseYear(boolean isAscending){
        Song[] newSongs = Arrays.copyOf(songs, songs.length); // Create a copy of the array
        int l = 0;
        int r = newSongs.length-1;
        sort(newSongs, l, r, isAscending);
        return newSongs;
    }
    private void merge(Song[] arr, int l, int m, int r, boolean isAscending){
        int n1 = m - l + 1;
        int n2 = r - m;

        Song[] L = new Song[n1];
        Song[] R = new Song[n2];

        for(int i = 0; i < n1; i++){
            L[i] = arr[l+i];
        }
        for(int j = 0; j < n2; j++){
            R[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0;
        int k = l;
        while(i < n1 && j < n2){
            if(isAscending){
                if(L[i].getReleaseYear() <= R[j].getReleaseYear()){
                    arr[k] = L[i];
                    i++;
                }else{
                    arr[k] = R[j];
                    j++;
                }
            }else{
                if(L[i].getReleaseYear() > R[j].getReleaseYear()){
                    arr[k] = L[i];
                    i++;
                }else{
                    arr[k] = R[j];
                    j++;
                }
            }
        }

        while(i<n1){
            arr[k] = L[i];
            i++;
            k++;
        }

        while(j < n2){
            arr[k] = R[j];
            j++;
            k++;
        }
    }
    private void sort(Song[] arr, int l, int r, boolean isAscending){
        if(l < r){
            int m = (l + r) / 2;

            sort(arr, l, m, isAscending);
            sort(arr, m + 1, r, isAscending);

            merge(arr, l, m, r, isAscending);
        }
    }

    // sort by popularity using bubble sort
    public Song[] sortByPopularity(boolean isAscending){
        Song[] newSongs = Arrays.copyOf(songs, songs.length); // Create a copy of the array
        for(int i = 0; i < newSongs.length-1; i++){
            boolean swap = false;
            for(int j = 1; j < newSongs.length-i; j++){
                if(isAscending){
                    if(newSongs[j].getLikes() < newSongs[j-1].getLikes()){
                        Song temp = newSongs[j];
                        newSongs[j] = newSongs[j-1];
                        newSongs[j-1] = temp;
                        if(!swap){
                            swap = true;
                        }
                    }
                }else{
                    if(newSongs[j].getLikes() > newSongs[j-1].getLikes()){
                        Song temp = newSongs[j];
                        newSongs[j] = newSongs[j-1];
                        newSongs[j-1] = temp;
                        if(!swap){
                            swap = true;
                        }
                    }
                }
            }
            if(!swap){
                break;
            }
        }
        return newSongs;
    }

    // reverse method
    public static Song[] reverse(Song[] songs){
        int length = songs.length;
        for(int i = 0; i < songs.length/2; i++){
            Song temp = songs[i];
            songs[i] = songs[length-i-1];
            songs[length-i-1] = temp;
        }
        return songs;
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Title: " + title + ", Release year: " + releaseYear + " Songs: {");
        for (int i = 0; i < songs.length; i++) {
            result.append("Title: ").append(songs[i].getTitle())
                    .append(", Duration: ").append(songs[i].getDuration())
                    .append(", Release year: ").append(songs[i].getReleaseYear())
                    .append(", Likes: ").append(songs[i].getLikes());
            if (i < songs.length - 1) {
                result.append(" | "); // Separator for each song, except the last one
            }
        }
        result.append("}");
        return result.toString();
    }



}
