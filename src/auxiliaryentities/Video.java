package auxiliaryentities;

import java.util.ArrayList;

/**
 * Class that has both movies and serials so that queries and recommendation can have all shows
 */
public class Video {
    private String name;
    private int year;
    private double rating;
    private int favsappearances;
    private int duration;
    private int nrviews;
    private ArrayList<String> genres;
    private int priority;

    public Video(final String name, final int year, final double rating,
                 final int favsappearances, final int duration, final int nrviews,
                 final ArrayList<String> genres, final int priority) {
        this.name = name;
        this.year = year;
        this.rating = rating;
        this.favsappearances = favsappearances;
        this.duration = duration;
        this.nrviews = nrviews;
        this.genres = genres;
        this.priority = priority;
    }

    /**
     * Getter for an auxiliary variable that ensures that the movies are first
     * in a sorted list (followed by the serials)
     * @return Priority of the show
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Setter for the priority of the show
     * @param priority Priority to be set
     */
    public void setPriority(final int priority) {
        this.priority = priority;
    }

    /**
     * Getter for the list of genres a video has
     * @return List of genres
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Setter for the list of genres a video has
     * @param genres
     */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * Getter for the year of the video
     * @return Year of the video
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter for the year of the video
     * @param year Year to be set
     */
    public void setYear(final int year) {
        this.year = year;
    }

    /**
     * Getter for the name (title) of the video
     * @return The title of the video
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name (title) of the video
     * @param name Title to be set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Getter for the rating of the video
     * @return Video's rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Setter for the rating of the video
     * @param rating Rating to be set
     */
    public void setRating(final double rating) {
        this.rating = rating;
    }

    /**
     * Getter for the number of favourite appearances of a video
     * @return Number of times a video has appeared in a favourite list
     */
    public int getFavsappearances() {
        return favsappearances;
    }

    /**
     * Setter for the number of favourite appearances
     * @param favsappearances Number of appearances to be set
     */
    public void setFavsappearances(final int favsappearances) {
        this.favsappearances = favsappearances;
    }

    /**
     * Getter for the length of the video
     * @return Duration of video
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Setter for the length of the video
     * @param duration Duration to be set
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * Getter for the number of views a video has been seen
     * @return Number of views
     */
    public int getNrviews() {
        return nrviews;
    }

    /**
     * Setter for the number of views of a video
     * @param nrviews Number to be set
     */
    public void setNrviews(final int nrviews) {
        this.nrviews = nrviews;
    }
}
