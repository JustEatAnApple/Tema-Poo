package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String username;
    private String subscriptionType;
    private Map<String, Integer> history;
    private ArrayList<String> favoriteMovies;
    private ArrayList<String> hasRatedMovie;
    private Map<String, Integer> hasRatedShow = new HashMap<String, Integer>();
    private int nrrates = 0;

    public void addHasRatedMovie(String title) {
        hasRatedMovie.add(title);
    }

    public boolean didRateMovie(String title) {
        for (String s : hasRatedMovie) {
            if (s.equals(title)) {
                return true;
            }
        }
        return false;
    }

    public void addHasRatedShowS(String title, int nr) {
        hasRatedShow.put(title, nr);
    }

    public boolean didRateShowS(String title, int nr) {
        for (Map.Entry<String, Integer> entry: hasRatedShow.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            if ((k.equals(title)) && (v == nr)) {
                return true;
            }
        }
        return false;
    }

    public User(){}

    public User(String username, String subscriptionType,
                         Map<String, Integer> history,
                         ArrayList<String> favoriteMovies) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.favoriteMovies = favoriteMovies;
        this.history = history;
        hasRatedMovie = new ArrayList<String>();
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Map<String, Integer> getHistory() {
        return history;
    }

    public void setHistory(Map<String, Integer> history) {
        this.history = history;
    }

    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(ArrayList<String> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    public int getNrrates() {
        return nrrates;
    }

    public void addNrrates(int abc) {
        nrrates += abc;
    }

    public void setNrrates(int nrrates) {
        this.nrrates = nrrates;
    }
}
