package actionclasses;

import auxiliaryentities.User;
import auxiliarylists.MovieList;
import auxiliarylists.SerialList;
import auxiliarylists.UserList;

import java.util.Map;

public class Command {
    private String title;
    private int ssnr;
    private MovieList m;
    private SerialList s;
    private UserList u;

    public Command(final String title, final MovieList m, final SerialList s,
                   final UserList u, final int ssnr) {
        this.title = title;
        this.m = m;
        this.s = s;
        this.u = u;
        this.ssnr = ssnr;
    }

    /**
     * Function that checks if the show is a movie or serial
     * @return Type of the show
     */
    public String checkShowType() {
        String typ = "none";
        for (int i = 0; i < m.getSize(); i++) {
            if (title.equals(m.getTitle(i))) {
                typ = "movie";
            }
        }
        for (int i = 0; i < s.getSize(); i++) {
            if (title.equals(s.getTitle(i))) {
                typ = "serial";
            }
        }
        return typ;
    }

    /**
     * Function that adds a rating given by an user to a specific show
     * @param user The user that rates
     * @param r Rating to be added
     * @param nr If it is a serial we also give the season number
     * @return The output message
     */
    public String addRating(final User user, final double r, final int nr) {
        String output = "error -> " + title + " is not seen";
        int viewsrated = 0;
        for (Map.Entry<String, Integer> entry: user.getHistory().entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            if (k.equals(title)) {
                if (checkShowType().equals("movie")) {
                    if (!user.didRateMovie(title)) {
                        user.addHasRatedMovie(title);
                        m.getMoviebyTitle(title).addRating(r);
                        viewsrated++;
                        user.addNrrates(viewsrated);
                        return "success -> " + title + " was rated with " + r
                                + " by " + user.getUsername();
                    } else {
                        return "error -> " + title + " has been already rated";
                    }
                } else if (checkShowType().equals("serial")) {
                    if (!user.didRateShowS(title, ssnr)) {
                        user.addHasRatedShowS(title, ssnr);
                        s.getSerialbyTitle(title).addR(r, nr);
                        viewsrated++;
                        user.addNrrates(viewsrated);
                        return "success -> " + title + " was rated with "
                                + r + " by " + user.getUsername();
                    } else {
                        return "error -> " + title + " has been already rated";
                    }
                }
            }
        }
        return output;
    }

    /**
     * Function that adds a view to the history of the user while also adding a view
     * to the number of total views of the show
     * @param user The user that views the show
     * @return The output message
     */
    public String addView(final User user) {
        for (Map.Entry<String, Integer> entry: user.getHistory().entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            if (k.equals(title)) {
                user.getHistory().put(k, v + 1);
                return "success -> " + title + " was viewed with total views of " + (v + 1);
            }
        }
        user.getHistory().put(title, 1);
        return "success -> " + title + " was viewed with total views of 1";
    }

    /**
     * Function that adds a video to the favourite list for user
     * @param user The user that tries to add a show to favourite
     * @return The output message
     */
    public String addFavourite(final User user) {
        int yes = 0;
        if (user.getHistory().containsKey(title)) {
                yes = 1;
            }
        if (yes == 0) {
            return "error -> " + title + " is not seen";
        }
        for (String fvs : user.getFavoriteMovies()) {
            if (title.equals(fvs)) {
                return "error -> " + title + " is already in favourite list";
            }
        }
        user.getFavoriteMovies().add(title);
        return "success -> " + title + " was added as favourite";
    }
}
