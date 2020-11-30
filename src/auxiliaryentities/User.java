package auxiliaryentities;

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

    /**
     * Function that adds a title to a list which contains all the shows the user has rated
     * @param title Title to be added
     */
    public void addHasRatedMovie(final String title) {
        hasRatedMovie.add(title);
    }

    /**
     * Boolean function that checks if a movie has already been rated by the user
     * @param title Title to search
     * @return True of false
     */
    public boolean didRateMovie(final String title) {
        for (String s : hasRatedMovie) {
            if (s.equals(title)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function that adds a serial's title and season number in a map to keep a score
     * of which serial has been already rated
     * @param title Title to be added
     * @param nr Season's number
     */
    public void addHasRatedShowS(final String title, final int nr) {
        hasRatedShow.put(title, nr);
    }

    /**
     * Boolean function that checks if a serial's season has already been rated by the user
     * @param title Title to search
     * @param nr Season's number
     * @return True of false
     */
    public boolean didRateShowS(final String title, final int nr) {
        for (Map.Entry<String, Integer> entry: hasRatedShow.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            if ((k.equals(title)) && (v == nr)) {
                return true;
            }
        }
        return false;
    }

    public User() {
    }

    public User(final String username, final String subscriptionType,
                         final Map<String, Integer> history,
                         final ArrayList<String> favoriteMovies) {
        this.username = username;
        this.subscriptionType = subscriptionType;
        this.favoriteMovies = favoriteMovies;
        this.history = history;
        hasRatedMovie = new ArrayList<String>();
    }

    /**
     * Getter for the username of the user
     * @return Username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for the username of the user
     * @param username Username to be set
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Getter for the type of subscription an user has
     * @return Type of subscription
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * Setter for the subscription type of an user
     * @param subscriptionType Subscription type to be set
     */
    public void setSubscriptionType(final String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    /**
     * Getter for the watch history of an user
     * @return History of user
     */
    public Map<String, Integer> getHistory() {
        return history;
    }

    /**
     * Setter for the watch history of an user
     * @param history History to be set
     */
    public void setHistory(final Map<String, Integer> history) {
        this.history = history;
    }

    /**
     * Getter for the list of favourite movies (and serials) of an user
     * @return The list
     */
    public ArrayList<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    /**
     * Setter for the favourite list of an user
     * @param favoriteMovies List to be set
     */
    public void setFavoriteMovies(final ArrayList<String> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    /**
     * Getter for the number of ratings an user has given
     * @return Number of ratings given
     */
    public int getNrrates() {
        return nrrates;
    }

    /**
     * Add how many times an user has rated a show
     * @param abc Number to be added
     */
    public void addNrrates(final int abc) {
        nrrates += abc;
    }

    /**
     * Setter for the number of ratings an user has given
     * @param nrrates Number of ratings to be set
     */
    public void setNrrates(final int nrrates) {
        this.nrrates = nrrates;
    }
}
