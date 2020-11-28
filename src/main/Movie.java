package main;

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setFavsappearance(int favsappearance) {
        this.favsappearance = favsappearance;
    }

    public Movie() {}

    public Movie(String title, ArrayList<String> cast,
                 ArrayList<String> genres, int year,
                 int duration) {
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

    public ArrayList<Double> getRatings() {
        return ratings;
    }

    public void setRatings(ArrayList<Double> ratings) {
        this.ratings = ratings;
    }

    public void setNviews(int nviews) {
        this.nviews = nviews;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", cast=" + cast +
                ", genres=" + genres +
                ", duration=" + duration +
                '}';
    }

    public void addRating(double r) {
        this.ratings.add(r);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<String> getCast() {
        return cast;
    }

    public void setCast(ArrayList<String> cast) {
        this.cast = cast;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

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

    public double getRate() {
        return rating;
    }

    public void setRate(double rating) {
        this.rating = rating;
    }

    public void addFavsappearance() {
        this.favsappearance++;
    }

    public void addnofviews(int n) {
        nviews += n;
    }

    public int getFavsappearance() {
        return favsappearance;
    }

    public int getNviews() {
        return nviews;
    }
}
