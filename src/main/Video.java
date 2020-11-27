package main;

import java.util.ArrayList;

public class Video {
    private String name;
    private int year;
    private double rating;
    private int favsappearances;
    private int duration;
    private int nrviews;
    private ArrayList<String> genres;
    private int priority;

    public Video(String name, int year, double rating, int favsappearances, int duration,
                 int nrviews, ArrayList<String> genres, int priority) {
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.favsappearances = favsappearances;
        this.duration = duration;
        this.nrviews = nrviews;
        this.genres = genres;
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getFavsappearances() {
        return favsappearances;
    }

    public void setFavsappearances(int favsappearances) {
        favsappearances = favsappearances;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNrviews() {
        return nrviews;
    }

    public void setNrviews(int nrviews) {
        this.nrviews = nrviews;
    }
}
