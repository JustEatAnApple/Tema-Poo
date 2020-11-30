package actionclasses;

import actor.ActorsAwards;
import auxiliaryentities.Actor;
import auxiliaryentities.User;
import auxiliaryentities.Video;
import auxiliarylists.ActorList;
import auxiliarylists.MovieList;
import auxiliarylists.SerialList;
import auxiliarylists.UserList;
import auxiliarylists.VideoList;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import static utils.Utils.stringToAwards;

public class Query {
    private MovieList m;
    private SerialList s;
    private ActorList a;
    private UserList u;
    private VideoList v;

    public Query(final MovieList m, final SerialList s, final ActorList a,
                 final UserList u, final VideoList v) {
        this.m = m;
        this.s = s;
        this.a = a;
        this.u = u;
        this.v = v;
    }

    /**
     * Function to find out if set substring is in another string (needle in the haystack)
     * @param hyastack The string in which we search for the substring
     * @param niidl The substring to be found
     * @return True or False
     */
    public boolean strStr(final String hyastack, final String niidl) {
        String haystack = hyastack.toLowerCase(), needle = niidl.toLowerCase();
        String[] listedhaystack = haystack.split("\\W+", -1);
        for (String str : listedhaystack) {
            if (str.equals(needle)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Function that calculates each actor's individual rating
     */
    public void getactorRating() {
        for (Actor aaux : a.getList()) {
            for (int i = 0; i < aaux.getFilmography().size(); i++) {
                int check = 0;
                double guard = 0;
                for (int j = 0; j < m.getSize(); j++) {
                    guard = 0;
                    if (aaux.getFilmography().get(i).equals(m.getMovie(j).getTitle())) {
                        guard = m.getMovie(j).calcRating();
                        if (guard != 0) {
                            aaux.addNrofratings();
                            aaux.addRating(guard);
                        }
                        check = 1;
                        break;
                    }
                }
                if (check == 0) {
                    for (int j = 0; j < s.getSize(); j++) {
                        guard = 0;
                        if (aaux.getFilmography().get(i).equals(s.getSerial(j).getTitle())) {
                            guard = s.getSerial(j).calcRating();
                            if (guard != 0) {
                                aaux.addNrofratings();
                                aaux.addRating(guard);
                            }
                            break;
                        }
                    }
                }
            }
            if (aaux.getNrofratings() != 0) {
                aaux.setRating((aaux.getRating() / aaux.getNrofratings()));
            }
        }

    }

    /**
     * Function that sorts the actors in given order and returns a set number of them
     * @param type The given order (asc/desc)
     * @param nr Number of actors to be returned
     * @param start Auxiliary string to help with the output of the function
     * @return The output message
     */
    public String sortActorRating(final String type, final int nr, final String start) {
        int k = 0;
        int gettonr = 0;
        String auxstart = start;
        getactorRating();
        Comparator<Actor> cmpActN = Comparator.comparing(Actor::getName);
        Collections.sort(a.getList(), cmpActN);
        Comparator<Actor> cmpAct = Comparator.comparing(Actor::getRating);
        Collections.sort(a.getList(), cmpAct);
        switch (type) {
            case "asc" -> {
                for (int i = 0; i < a.getList().size(); i++) {
                    // for (int i = a.getList().size() - nr - 1; i < a.getList().size(); i++)
                    if (a.getList().get(i).getRating() != 0) {
                        k = 1;
                        auxstart += a.getList().get(i).getName() + ", ";
                        gettonr++;
                    }
                    if (gettonr == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = a.getList().size() - 1; i >= 0; i--) {
                    if (a.getList().get(i).getRating() != 0.0) {
                        auxstart += a.getList().get(i).getName() + ", ";
                        k = 1;
                        gettonr++;
                    }
                    if (gettonr == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            default -> {
                return "[]";
            }
        }
        for (Actor act : a.getList()) {
            act.setNrofratings(0);
            act.setRating(0);
        }
        auxstart += "]";
        return auxstart;
    }

    //awards={BEST_PERFORMANCE=3, BEST_SUPPORTING_ACTOR=4, BEST_DIRECTOR=1}

    /**
     * Function to get the actors that have the specified awards and the number of total awards
     * @param awds Awards to be found
     */
    public void getActorAwards(final List<String> awds) {
        for (Actor aaux : a.getList()) {
            int awardscore = 0;
            int count = awds.size();
            for (int i = 0; i < awds.size(); i++) {
                for (Map.Entry<ActorsAwards, Integer> entry : aaux.getAwards().entrySet()) {
                    ActorsAwards k = entry.getKey();
                    Integer vle = entry.getValue();
                    ActorsAwards placeholder = stringToAwards(awds.get(i));
                    if (placeholder.equals(k)) {
                        count--;
                    }
                    awardscore += vle;
                }
            }
            if (count == 0) {
                aaux.setAwdscounter(awardscore);
            }
        }
    }

    /**
     * Function that returns the query of actors with regards to their awards
     * @param type Order of the query (asc/desc)
     * @param awds Awards to look out for
     * @param start String that aids with the output
     * @return The output message
     */
    public String sortActorAwards(final String type, final List<String> awds, final String start) {
        int k = 0;
        String auxstart = start;
        getActorAwards(awds);
        Comparator<Actor> cmpActN = Comparator.comparing(Actor::getName);
        Collections.sort(a.getList(), cmpActN);
        Comparator<Actor> cmpAct = Comparator.comparing(Actor::getAwdscounter);
        Collections.sort(a.getList(), cmpAct);
        switch (type) {
            case "asc" -> {
                for (int i = 0; i < a.getList().size(); i++) {
                    if (a.getList().get(i).getAwdscounter() != 0) {
                        k = 1;
                        auxstart += a.getList().get(i).getName() + ", ";
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = a.getList().size() - 1; i >= 0; i--) {
                    if (a.getList().get(i).getAwdscounter() != 0) {
                        k = 1;
                        auxstart += a.getList().get(i).getName() + ", ";
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            default -> {
                return null;
            }
        }
        for (Actor clear : a.getList()) {
            clear.setAwdscounter(0);
        }
        auxstart += "]";
        return auxstart;
    }

    /**
     * Function to mark actors that have the given set of words in their description
     * @param descwords Words that need to be in the actor's description
     */
    public void getActorDescription(final List<String> descwords) {
        int j = 0;
        for (Actor aaux : a.getList()) {
            int count = descwords.size();
            for (int i = 0; i < descwords.size(); i++) {
                String sufletu = aaux.getCareerDescription();
                if (strStr(sufletu, descwords.get(i))) {
                    count--;
                }
            }
            if (count == 0) {
                aaux.setHaswords(true);
            }
        }
    }

    /**
     * Funtion that gets the actors that have the given words in their description in a
     * particular order
     * @param type Type of sorting (asc/desc)
     * @param decswords Words to be found in the descriptions of the actors
     * @param start String that helps with the output
     * @return The output message
     */
    public String sortActorDescription(final String type, final List<String> decswords,
                                       final String start) {
        int k = 0;
        String auxstart = start;
        getActorDescription(decswords);
        Comparator<Actor> cmpActN = Comparator.comparing(Actor::getName);
        Collections.sort(a.getList(), cmpActN);
        switch (type) {
            case "asc" -> {
                for (int i = 0; i < a.getList().size(); i++) {
                    if (a.getList().get(i).isHaswords()) {
                        k = 1;
                        auxstart += a.getList().get(i).getName() + ", ";
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = a.getList().size() - 1; i >= 0; i--) {
                    if (a.getList().get(i).isHaswords()) {
                        auxstart += a.getList().get(i).getName() + ", ";
                        k = 1;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            default -> {
                return null;
            }
        }
        for (Actor act : a.getList()) {
            act.setHaswords(false);
        }
        auxstart += "]";
        return auxstart;
    }

    /**
     * Function that gets a list of movies that match the filter criteria
     * @param filter The criteria that movies must have
     * @return An ArrayList of movies that match the criteria
     */
    public ArrayList<Video> getMovieListFav(final List<List<String>> filter) {
        ArrayList<Video> matches = new ArrayList<Video>();
        for (int i = 0; i < v.getmvList().size(); i++) {
            matches.add(v.getmvList().get(i));
        }
        int ok = 0;
        for (int j = 0; j < v.getmvList().size(); j++) {
            int check = 0;
            Video vd = v.getmvList().get(j);
            if (filter.get(0).get(0) != null) {
                int aux = Integer.parseInt(filter.get(0).get(0));
                if (vd.getYear() != aux) {
                    matches.remove(vd);
                    check = 1;

                }
            }
            if (check == 0) {
                if (filter.get(1).get(0) != null) {
                    ok = 0;
                    for (int i = 0; i < vd.getGenres().size(); i++) {
                        if (vd.getGenres().get(i).equals(filter.get(1).get(0))) {
                            ok = 1;
                        }
                    }
                    if (ok == 0) {
                        matches.remove(vd);
                    }
                }
            }
        }
        return matches;
    }

    /**
     * Function that returns a set of movies in an order that have the most favourite appearances
     * @param type Ascending or Descending
     * @param nr Size of the set
     * @param start String that helps with the output
     * @param filter Criteria that movies must have
     * @return The output message
     */
    public String sortMovieFavorite(final String type, final int nr, final String start,
                                    final List<List<String>> filter) {
        int k = 0, count = 0;
        String auxstart = start;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getMovieListFav(filter);
        Comparator<Video> cmpmovA = Comparator.comparing(Video::getName);
        Collections.sort(filteredmovs, cmpmovA);
        Comparator<Video> cmpmovF = Comparator.comparing(Video::getFavsappearances);
        Collections.sort(filteredmovs, cmpmovF);
        switch (type) {
            case "asc" -> {
                for (Video vdy : filteredmovs) {
                    if (vdy.getFavsappearances() != 0) {
                        k = 1;
                        count++;
                        auxstart += vdy.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getFavsappearances() != 0) {
                        k = 1;
                        count++;
                        auxstart += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            default -> {
                return null;
            }
        }
        auxstart += "]";
        return auxstart;
    }

    /**
     * Function that returns a set of movies in an order by their rating
     * @param type Ascending or Descending
     * @param nr Size of the set
     * @param start Auxiliary string that helps with the output
     * @param filter Criteria the movies must have
     * @return The output message
     */
    public String sortMovieRatings(final String type, final int nr, final String start,
                                   final List<List<String>> filter) {
        int k = 0, count = 0;
        String auxstart = start;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getMovieListFav(filter);
        Comparator<Video> cmpmovF = Comparator.comparing(Video::getRating);
        Collections.sort(filteredmovs, cmpmovF);
        switch (type) {
            default -> {
                return null;
            }
            case "asc" -> {
                for (Video vdy : filteredmovs) {
                    if (vdy.getRating() != 0) {
                        k = 1;
                        count++;
                        auxstart += vdy.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getRating() != 0) {
                        k = 1;
                        count++;
                        auxstart += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
        }
        auxstart += "]";
        return auxstart;
    }

    /**
     * Function that returns a set of movies in an order by their number of views
     * @param type Ascending or Descending
     * @param nr Size of the set
     * @param start Auxiliary string that helps with the output
     * @param filter Criteria the movies must have
     * @return The output message
     */
    public String sortMovieView(final String type, final int nr, final String start,
                                final List<List<String>> filter) {
        int k = 0, count = 0;
        String auxstart = start;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getMovieListFav(filter);
        Comparator<Video> cmpmovA = Comparator.comparing(Video::getName);
        Collections.sort(filteredmovs, cmpmovA);
        Comparator<Video> cmpmovvws = Comparator.comparing(Video::getNrviews);
        Collections.sort(filteredmovs, cmpmovvws);
        switch (type) {
            default -> {
                return null;
            }
            case "asc" -> {
                for (Video vdy : filteredmovs) {
                    if (vdy.getNrviews() != 0) {
                        k = 1;
                        count++;
                        auxstart += vdy.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getNrviews() != 0) {
                        k = 1;
                        count++;
                        auxstart += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
        }
        auxstart += "]";
        return auxstart;
    }

    /**
     * Function that returns a set of movies in an order by their duration
     * @param type Ascending or Descending
     * @param nr Size of the set
     * @param start Auxiliary string that helps with the output
     * @param filter Criteria the movies must have
     * @return The output message
     */
    public String sortMovieDuration(final String type, final int nr, final String start,
                                    final List<List<String>> filter) {
        int k = 0, count = 0;
        String auxstart = start;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getMovieListFav(filter);
        Comparator<Video> cmpmovN = Comparator.comparing(Video::getName);
        Collections.sort(filteredmovs, cmpmovN);
        Comparator<Video> cmpmovlth = Comparator.comparing(Video::getDuration);
        Collections.sort(filteredmovs, cmpmovlth);
        switch (type) {
            default -> {
                return null;
            }
            case "asc" -> {
                for (Video vdy : filteredmovs) {
                    if (vdy.getDuration() != 0) {
                        k = 1;
                        count++;
                        auxstart += vdy.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getDuration() != 0) {
                        k = 1;
                        count++;
                        auxstart += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
        }
        auxstart += "]";
        return auxstart;
    }

    /**
     * Function that gets a list of serials that match the filter criteria
     * @param filter The criteria that serials must have
     * @return An ArrayList of movies that match the criteria
     */
    public ArrayList<Video> getSerialListFav(final List<List<String>> filter) {
        ArrayList<Video> matches = new ArrayList<Video>();
        for (int i = 0; i < v.getsrList().size(); i++) {
            matches.add(v.getsrList().get(i));
        }
        int ok = 0;
        for (int j = 0; j < v.getsrList().size(); j++) {
            int check = 0;
            Video vd = v.getsrList().get(j);
            if (filter.get(0).get(0) != null) {
                int aux = Integer.parseInt(filter.get(0).get(0));
                if (vd.getYear() != aux) {
                    matches.remove(vd);
                    check = 1;
                }
            }
            if (check == 0) {
                if (filter.get(1).get(0) != null) {
                    ok = 0;
                    for (int i = 0; i < vd.getGenres().size(); i++) {
                        if (vd.getGenres().get(i).equals(filter.get(1).get(0))) {
                            ok = 1;
                        }
                    }
                    if (ok == 0) {
                        matches.remove(vd);
                    }
                }
            }
        }
        return matches;
    }

    /**
     * Function that returns a set of serials in an order by their number of appearances in
     * the favourite lists of the users
     * @param type Ascending or Descending
     * @param nr Size of the set
     * @param start Auxiliary string that helps with the output
     * @param filter Criteria the serials must meet
     * @return The output message
     */
    public String sortSerialFavorite(final String type, final int nr,
                                     final String start, final List<List<String>> filter) {
        int k = 0, count = 0;
        String auxstart = start;
        ArrayList<Video> filteredsers = new ArrayList<Video>();
        filteredsers = getSerialListFav(filter);
        Comparator<Video> cmpmovA = Comparator.comparing(Video::getName);
        Collections.sort(filteredsers, cmpmovA);
        Comparator<Video> cmpmovF = Comparator.comparing(Video::getFavsappearances);
        Collections.sort(filteredsers, cmpmovF);
        switch (type) {
            default -> {
                return null;
            }
            case "asc" -> {
                for (Video vdy : filteredsers) {
                    if (vdy.getFavsappearances() != 0) {
                        k = 1;
                        count++;
                        auxstart += vdy.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredsers.size() - 1; i >= 0; i--) {
                    if (filteredsers.get(i).getFavsappearances() != 0) {
                        k = 1;
                        count++;
                        auxstart += filteredsers.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
        }
        auxstart += "]";
        return auxstart;
    }

    /**
     * Function that returns a set of serials in an order by their duration
     * @param type Ascending or Descending
     * @param nr Size of the set
     * @param start Auxiliary string that helps with the output
     * @param filter Criteria the serials must meet
     * @return The output message
     */
    public String sortSerialDuration(final String type, final int nr, final String start,
                                     final List<List<String>> filter) {
        int k = 0, count = 0;
        String auxstart = start;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getSerialListFav(filter);
        Comparator<Video> cmpmovN = Comparator.comparing(Video::getName);
        Collections.sort(filteredmovs, cmpmovN);
        Comparator<Video> cmpmovlth = Comparator.comparing(Video::getDuration);
        Collections.sort(filteredmovs, cmpmovlth);
        switch (type) {
            default -> {
                return null;
            }
            case "asc" -> {
                for (Video vdy : filteredmovs) {
                    if (vdy.getDuration() != 0) {
                        k = 1;
                        count++;
                        auxstart += vdy.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getDuration() != 0) {
                        k = 1;
                        count++;
                        auxstart += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
        }
        auxstart += "]";
        return auxstart;
    }

    /**
     * Function that returns a set of serials in an order by the number of the views they have
     * @param type Ascending or Descending
     * @param nr Size of the set
     * @param start Auxiliary string that helps with the output
     * @param filter Criteria the serials must meet
     * @return The output message
     */
    public String sortSerialView(final String type, final int nr, final String start,
                                 final List<List<String>> filter) {
        int k = 0, count = 0;
        String auxstart = start;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getSerialListFav(filter);
        Comparator<Video> cmpmovA = Comparator.comparing(Video::getName);
        Collections.sort(filteredmovs, cmpmovA);
        Comparator<Video> cmpmovvws = Comparator.comparing(Video::getNrviews);
        Collections.sort(filteredmovs, cmpmovvws);
        switch (type) {
            default -> {
                return null;
            }
            case "asc" -> {
                for (Video vdy : filteredmovs) {
                    if (vdy.getNrviews() != 0) {
                        k = 1;
                        count++;
                        auxstart += vdy.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getNrviews() != 0) {
                        k = 1;
                        count++;
                        auxstart += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
        }
        auxstart += "]";
        return auxstart;
    }

    /**
     * Function that returns a set of serials in an order by their rating
     * @param type Ascending or Descending
     * @param nr Size of the set
     * @param start Auxiliary string that helps with the output
     * @param filter Criteria the serials must meet
     * @return The output message
     */
    public String sortSerialRatings(final String type, final int nr, final String start,
                                    final List<List<String>> filter) {
        int k = 0, count = 0;
        String auxstart = start;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getSerialListFav(filter);
        Comparator<Video> cmpmovF = Comparator.comparing(Video::getRating);
        Collections.sort(filteredmovs, cmpmovF);
        switch (type) {
            default -> {
                return null;
            }
            case "asc" -> {
                for (Video vdy : filteredmovs) {
                    if (vdy.getRating() != 0) {
                        k = 1;
                        count++;
                        auxstart += vdy.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getRating() != 0) {
                        k = 1;
                        count++;
                        auxstart += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
        }
        auxstart += "]";
        return auxstart;
    }

    /**
     * Function that returns a sorted list of users by the number of ratings they have given
     * @return The sorted list
     */
    public ArrayList<User> sortUsrs() {
        ArrayList<User> filteredusrs = new ArrayList<>();
        for (int i = 0; i < u.getSize(); i++) {
            filteredusrs.add(u.getUser(i));
        }
        Comparator<User> cmpUN = Comparator.comparing(User::getUsername);
        Collections.sort(filteredusrs, cmpUN);
        Comparator<User> cmpvws = Comparator.comparing(User::getNrrates);
        Collections.sort(filteredusrs, cmpvws);
        return filteredusrs;
    }

    /**
     * Function that returns a set of users in an order based on the number of shows
     * they have rated
     * @param type Ascending or Descending
     * @param nr Size of the set
     * @param start Auxiliary string that aids with the outpu
     * @return The output message
     */
    public String sortUsersNrrates(final String type, final int nr, final String start) {
        int k = 0, count = 0;
        String auxstart = start;
        ArrayList<User> users = sortUsrs();
        switch (type) {
            default -> {
                return null;
            }
            case "asc" -> {
                for (User ux : users) {
                    if (ux.getNrrates() != 0) {
                        k = 1;
                        count++;
                        auxstart += ux.getUsername() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = users.size() - 1; i >= 0; i--) {
                    if (users.get(i).getNrrates() != 0) {
                        k = 1;
                        count++;
                        auxstart += users.get(i).getUsername() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    auxstart = auxstart.substring(0, auxstart.length() - 2);
                }
            }
        }
        auxstart += "]";
        return auxstart;
    }

}


