package main;

import entertainment.Season;

import java.util.ArrayList;

public class Serial {
    private String title;
    private int year;
    private ArrayList<String> cast;
    private ArrayList<String> genres;
    private int numberOfSeasons;
    private ArrayList<main.Season> seasons;
    private double rating;
    private int nrviews;
    private int duration;
    private int favsappearances;


    @Override
    public String toString() {
        return "Serial{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", cast=" + cast +
                ", genres=" + genres +
                ", numberOfSeasons=" + numberOfSeasons +
                ", seasons=" + seasons +
                '}';
    }



    public Serial(String title, ArrayList<String> cast,
                  ArrayList<String> genres,
                  int numberOfSeasons, ArrayList<Season> seasons,
                  int year) {
        this.title = title;
        this.cast = cast;
        this.genres = genres;
        this.year = year;
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = new ArrayList<main.Season>();
        getInputSeasons(seasons);
        nrviews = 0;
        favsappearances = 0;
    }

    public main.Season getcertainSeason(int nr) {
        return seasons.get(nr - 1);
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

    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    public void setNumberOfSeasons(int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    public ArrayList<main.Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(ArrayList<main.Season> seasons) {
        this.seasons = seasons;
    }

    public void getInputSeasons(ArrayList<Season> seasons) {
        if (seasons.size() != 0) {
            for (int i = 0; i < seasons.size(); i ++) {
                main.Season szn = new main.Season(seasons.get(i).getCurrentSeason(),
                        seasons.get(i).getDuration());
                this.seasons.add(szn);
            }
        }
    }

    public void addR(double r, int nr) {
        seasons.get(nr - 1).addR(r);
    }

    public double calcRating() {
        int coef = 0;
        rating = 0;
        for(main.Season s : seasons) {
            rating += s.getRating();
            coef++;
        }
        if (coef != 0) {
            rating /= coef;
        }
        return rating;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFavsappearances() {
        return favsappearances;
    }

    public void setFavsappearances(int favsappearances) {
        this.favsappearances = favsappearances;
    }

    public void makeDuration() {
        for (main.Season s : seasons) {
            this.duration += s.getDuration();
        }
    }

    public void setNrviews(int nrviews) {
        this.nrviews = nrviews;
    }

    public int getNrviews() {
        return nrviews;
    }

    public void addFavsappearance() {
        this.favsappearances++;
    }

    public void addnofviews(int n) {
        nrviews += n;
    }

    public int getFavappearances() {
        return favsappearances;
    }

    public int getDuration() {
        return duration;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
