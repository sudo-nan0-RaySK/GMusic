package sample;

import java.io.Serializable;

public class MusicItem implements Serializable{
    String name;
    float duration;
    String artist;
    String genre;


    public MusicItem(String name, float duration, String artist, String genre) {
        this.name = name;
        this.duration = duration;
        this.artist = artist;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public float getDuration() {
        return duration;
    }

    public String getArtist() {
        return artist;
    }

    @Override
    public String toString()
    {
        return this.getName();
    }

    public String getGenre() {
        return genre;
    }
}
