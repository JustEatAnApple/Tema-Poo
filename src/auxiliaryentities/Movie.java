package auxiliaryentities;

import java.util.ArrayList;

public class Movie {
    private String title;
    private int year;
    private ArrayList<String> cast;
    private ArrayList<String> genres;
    private int duration;
    private ArrayList<Double> ratings;
    private double rating;
    private int favsappearance;
    private int nviews;

    /**
     * Getter for the rating of the movie
     * @return Rating of movie
     */
    public double getRating() {
        return rating;
    }

    /**
     * Setter for the rating of the movie
     * @param rating Rating to be set
     */
    public void setRating(final double rating) {
        this.rating = rating;
    }

    /**
     * Setter for the number of appearances in the favourite lists of the users
     * @param favsappearance Number of appearances to be set
     */
    public void setFavsappearance(final int favsappearance) {
        this.favsappearance = favsappearance;
    }

    public Movie() {
    }


    public Movie(final String title, final ArrayList<String> cast,
                 final ArrayList<String> genres, final int year,
                 final int duration) {
        this.title = title;
        this.cast = cast;
        this.genres = genres;
        this.year = year;
        this.duration = duration;
        this.ratings = new ArrayList<Double>();
        rating = 0;
        favsappearance = 0;
        nviews = 0;
    }

    /**
     * Getter for the list of ratings
     * @return
     */
    public ArrayList<Double> getRatings() {
        return ratings;
    }

    /**
     * Setter for the list of ratings
     * @param ratings Ratings to be set
     */
    public void setRatings(final ArrayList<Double> ratings) {
        this.ratings = ratings;
    }

    /**
     * Setter for the number of times the movie has been seen
     * @param nviews Number of views to be set
     */
    public void setNviews(final int nviews) {
        this.nviews = nviews;
    }

    /**
     * Method toString to ensure prints
     * @return A string with the elements of the entity
     */
    @Override
    public String toString() {
        return "Movie{" + "title='" + title + '\''
                + ", year=" + year + ", cast=" + cast
                + ", genres=" + genres + ", duration=" + duration + '}';
    }

    /**
     * Function to add a rating in the list of ratings
     * @param r Rating to be added in the list
     */
    public void addRating(final double r) {
        this.ratings.add(r);
    }

    /**
     * Getter for the title
     * @return The title of the movie
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the title
     * @param title Title to be set for the movie
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Getter for the year
     * @return Year of the movie
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter for the year
     * @param year Year to be set
     */
    public void setYear(final int year) {
        this.year = year;
    }

    /**
     * Getter for the cast of the movie
     * @return The cast of the movie
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * Setter for the cast of the movie
     * @param cast Cast to be set
     */
    public void setCast(final ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * Getter for the genres of the film
     * @return Genres of the film
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Setter for the genres of the film
     * @param genres Genres to be set
     */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * Getter for the duration of the movie
     * @return Duration of the movie
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Setter for the duration of the movie
     * @param duration Duration to be set
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * Function that calculates the general rating of the movie
     * @return The rating of the movie
     */
    public double calcRating() {
        rating = 0;
        for (double r : ratings) {
            rating += r;
        }
        if (!ratings.isEmpty()) {
            rating = rating / ratings.size();
        }
        return rating;
    }

    /**
     * Getter for the general rating of the movie
     * @return The rating of the movie
     */
    public double getRate() {
        return rating;
    }

    /**
     * Direct Setter for the general rating of the movie
     * @param rt The rating of the movie
     */
    public void setRate(final double rt) {
        this.rating = rt;
    }

    /**
     * Function that increments the number of appearances of the movie in the favourite lists
     * of all users
     */
    public void addFavsappearance() {
        this.favsappearance++;
    }

    /**
     * Function that adds views to the movie
     * @param n Number of views to be added
     */
    public void addnofviews(final int n) {
        nviews += n;
    }

    /**
     * Getter for the number of appearances of the movie in the favourite lists
     * @return Number of appearances in the favourite lists
     */
    public int getFavsappearance() {
        return favsappearance;
    }

    /**
     * Getter for the number of views the movie has
     * @return Number of views
     */
    public int getNviews() {
        return nviews;
    }
}
