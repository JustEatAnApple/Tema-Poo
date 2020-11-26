package main;

import java.util.List;
import java.util.ArrayList;

public class Season {
    private int currentSeason;
    private int duration;
    private ArrayList<Double> ratings;
    private double rating;


    public Season(int currentSeason, int duration) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        ratings = new ArrayList<Double>();
        rating = 0;
    }

    public void addR(double r) {
        ratings.add(r);
    }

    public int getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(int currentSeason) {
        this.currentSeason = currentSeason;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<Double> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Double> ratings) {
        this.ratings = ratings;
    }

    public void calcRating() {
        rating = 0;
        for (double r : ratings) {
            rating += r;
        }
        if (!ratings.isEmpty()) {
            rating /= ratings.size();
        }
    }

    public double getRating() {
        calcRating();
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
