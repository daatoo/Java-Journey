package pgdp.adventuin;

import pgdp.color.ExceptionUtil;
import pgdp.color.RgbColor;

import java.util.concurrent.ExecutionException;

public class Adventuin {
    private String name;
    private int height;
    private RgbColor color;
    private HatType hat;
    private Language language;

    public Adventuin(String name, int size,
                     RgbColor color, HatType hat,
                     Language language){
        this.name = name;
        if(size <= 0){
            throw new IllegalArgumentException("size must be greater than 0");
        }
        this.height = size;
        this.color = color;
        this.hat = hat;
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public HatType getHat() {
        return hat;
    }

    public int getHeight() {
        return height;
    }

    public Language getLanguage() {
        return language;
    }

    public RgbColor getColor() {
        return color;
    }
}
