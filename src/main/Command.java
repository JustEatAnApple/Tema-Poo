package main;

import java.util.ArrayList;
import java.util.Map;

public class Command {
    String title;
    int ssnr;
    private MovieList m;
    private SerialList s;
    private UserList u;

    public Command(String title, MovieList m, SerialList s, UserList u, int ssnr) {
        this.title = title;
        this.m = m;
        this.s = s;
        this.u = u;
        this.ssnr = ssnr;
    }

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

    public String addRating(User user, double r, int nr) {
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
                        return "success -> " + title + " was rated with " + r + " by " + user.getUsername();
                    } else {
                        return "error -> " + title + " has been already rated";
                    }
                } else if (checkShowType().equals("serial")) {
                    if(!user.didRateShowS(title, ssnr)) {
                        user.addHasRatedShowS(title, ssnr);
                        s.getSerialbyTitle(title).addR(r, nr);
                        viewsrated++;
                        user.addNrrates(viewsrated);
                        return "success -> " + title + " was rated with " + r + " by " + user.getUsername();
                    } else {
                        return "error -> " + title + " has been already rated";
                    }
                }
            }
        }
        return output;
    }

    public String addView(User user) {
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

    public String addFavourite(User user) {
        int yes = 0;
        if(user.getHistory().containsKey(title)) {
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
//action type query/ rr/ command
//type favourite bla bla