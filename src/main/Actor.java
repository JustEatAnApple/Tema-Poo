package main;

import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.Map;

public class Actor {
    private String name;
    private String careerDescription;
    private ArrayList<String> filmography;
    private Map<ActorsAwards, Integer> awards;
    private double rating = 0;
    private int nrofratings = 0;
    private int awdscounter = 0;
    private boolean haswords = false;

    public Actor() {}

    public Actor(final String name, final String careerDescription,
                 final ArrayList<String> filmography,
                 final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCareerDescription() {
        return careerDescription;
    }

    public void setCareerDescription(String careerDescription) {
        this.careerDescription = careerDescription;
    }

    public ArrayList<String> getFilmography() {
        return filmography;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "name='" + name + '\'' +
                ", careerDescription='" + careerDescription + '\'' +
                ", filmography=" + filmography +
                ", awards=" + awards +
                '}';
    }

    public void setFilmography(ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    public void setAwards(Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }

    public int getNrofratings() {
        return nrofratings;
    }

    public void setNrofratings(int nrofratings) {
        this.nrofratings = nrofratings;
    }

    public void addNrofratings() {
        this.nrofratings += 1;
    }

    public void addRating(double r) {
        this.rating += r;
    }

    public int getAwdscounter() {
        return awdscounter;
    }

    public void setAwdscounter(int awdscounter) {
        this.awdscounter = awdscounter;
    }

    public boolean isHaswords() {
        return haswords;
    }

    public void setHaswords(boolean haswords) {
        this.haswords = haswords;
    }
}
