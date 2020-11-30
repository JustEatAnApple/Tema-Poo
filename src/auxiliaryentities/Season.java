package auxiliaryentities;

import java.util.List;
import java.util.ArrayList;

public class Season {
    private int currentSeason;
    private int duration;
    private ArrayList<Double> ratings;
    private double rating;


    public Season(final int currentSeason, final int duration) {
        this.currentSeason = currentSeason;
        this.duration = duration;
        ratings = new ArrayList<>();
        rating = 0;
    }

    /**
     * Function that adds a rating to the list of ratings for the season
     * @param r The rating to be added
     */
    public void addR(final double r) {
        ratings.add(r);
    }

    /**
     * Getter for the index of the season
     * @return The number of the season
     */
    public int getCurrentSeason() {
        return currentSeason;
    }

    /**
     * Setter for the current season
     * @param currentSeason The number to be set
     */
    public void setCurrentSeason(final int currentSeason) {
        this.currentSeason = currentSeason;
    }

    /**
     * Getter for the duration of the season
     * @return The duration of the season
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Setter for the duration of the season
     * @param duration Duration to be set
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * Function that gets the list of ratings of the season
     * @return
     */
    public List<Double> getRatings() {
        return ratings;
    }

    /**
     * Setter for the list of ratings for the season
     * @param ratings Ratings to be set
     */
    public void setRatings(final ArrayList<Double> ratings) {
        this.ratings = ratings;
    }

    /**
     * Function that calculates the general rating of the season
     */
    public void calcRating() {
        rating = 0;
        for (double r : ratings) {
            rating += r;
        }
        if (!ratings.isEmpty()) {
            rating /= ratings.size();
        }
    }

    /**
     * Function that first calculates the general rating then returns it
     * @return The general rating
     */
    public double getRating() {
        calcRating();
        return rating;
    }

    /**
     * Setter for the general rating
     * @param rating Rating to be set
     */
    public void setRating(final double rating) {
        this.rating = rating;
    }
}
