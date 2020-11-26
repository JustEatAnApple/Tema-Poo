package main;

import actor.ActorsAwards;

import java.util.*;

import static utils.Utils.stringToAwards;

public class Query {
    private MovieList m;
    private SerialList s;
    private ActorList a;
    private UserList u;
    private VideoList v;

    public Query(MovieList m, SerialList s, ActorList a, UserList u, VideoList v) {
        this.m = m;
        this.s = s;
        this.a = a;
        this.u = u;
        this.v = v;
    }

    public boolean strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return false;
        }
        int hLength = haystack.length();
        int nLength = needle.length();
        if (hLength < nLength) {
            return false;
        }
        if (nLength == 0) {
            return false;
        }
        for (int i = 0; i <= hLength - nLength; i++) {
            if ((int) haystack.charAt(i) == (int) needle.charAt(0)) {
                int j = 0;
                for (; j < nLength; j++) {
                    if ((int) haystack.charAt(i + j) != (int) needle.charAt(j)) {
                        break;
                    }
                }
                if (j == nLength) {
                    return true;
                }
            }
        }
        return false;
    }

    public void getactorRating() {
        for (Actor aaux : a.getList()) {
            for (int i = 0; i < aaux.getFilmography().size(); i++) {
                int check = 0;
                double guard = 0;
                for (int j = 0; j < m.getSize(); j++) {
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
                aaux.setRating(aaux.getRating() / aaux.getNrofratings());
            }
        }

    }

    public String sortActorRating(String type, int nr, String start) {
        int k = 0;
        int gettonr = 0;
        getactorRating();
        Comparator<Actor> cmpActN = Comparator.comparing(Actor::getName);
        Collections.sort(a.getList(), cmpActN);
        Comparator<Actor> cmpAct = Comparator.comparing(Actor::getRating);
        Collections.sort(a.getList(), cmpAct);
        switch (type) {
            case "asc" -> {
                for (int i = 0; i < a.getList().size(); i++) {
                    if (a.getList().get(i).getRating() != 0) {
                        k = 1;
                        start += a.getList().get(i).getName() + ", ";
                        gettonr++;
                    }
                    if (gettonr == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = a.getList().size() - nr; i < a.getList().size() - 1; i++) {
                    if (a.getList().get(i).getRating() != 0) {
                        start += a.getList().get(i).getName() + ", ";
                        k = 1;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
        }
        start += "]";
        return start;
    }

    //awards={BEST_PERFORMANCE=3, BEST_SUPPORTING_ACTOR=4, BEST_DIRECTOR=1}
    public void getActorAwards(List<String> awds) {
        for (Actor aaux : a.getList()) {
            int awardscore = 0;
            int count = awds.size();
            for (int i = 0; i < awds.size(); i++) {
                for (Map.Entry<ActorsAwards, Integer> entry : aaux.getAwards().entrySet()) {
                    ActorsAwards k = entry.getKey();
                    Integer v = entry.getValue();
                    ActorsAwards placeholder = stringToAwards(awds.get(i));
                    if (placeholder.equals(k)) {
                        count--;
                    }
                    awardscore += v;
                }
            }
            if (count == 0) {
                aaux.setAwdscounter(awardscore);
            }
        }
    }

    public String sortActorAwards(String type, List<String> awds, String start) {
        int k = 0;
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
                        start += a.getList().get(i).getName() + ", ";
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = a.getList().size() - 1; i >= 0; i--) {
                    if (a.getList().get(i).getAwdscounter() != 0) {
                        k = 1;
                        start += a.getList().get(i).getName() + ", ";
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
        }
        for (Actor clear : a.getList()) {
            clear.setAwdscounter(0);
        }
        start += "]";
        return start;
    }

    public void getActorDescription(List<String> descwords) {
        for (Actor aaux : a.getList()) {
            int count = descwords.size();
            for (int i = 0; i < descwords.size(); i++) {
                if (strStr(aaux.getCareerDescription(), descwords.get(i))) {
                    count--;
                }
            }
            if (count == 0) {
                aaux.setHaswords(true);
            }
        }
    }

    public String sortActorDescription(String type, List<String> decswords, String start) {
        int k = 0;
        getActorDescription(decswords);
        Comparator<Actor> cmpActN = Comparator.comparing(Actor::getName);
        Collections.sort(a.getList(), cmpActN);
        switch (type) {
            case "asc" -> {
                for (int i = 0; i < a.getList().size(); i++) {
                    if (a.getList().get(i).isHaswords()) {
                        k = 1;
                        start += a.getList().get(i).getName() + ", ";
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = a.getList().size(); i < a.getList().size() - 1; i++) {
                    if (a.getList().get(i).getRating() != 0) {
                        start += a.getList().get(i).getName() + ", ";
                        k = 1;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
        }
        start += "]";
        return start;
    }

    public ArrayList<Video> getMovieListFav(List<List<String>> filter) {
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

    public String sortMovieFavorite(String type, int nr, String start, List<List<String>> filter) {
        int k = 0, count = 0;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getMovieListFav(filter);
        Comparator<Video> cmpmovF = Comparator.comparing(Video::getFavsappearances);
        Collections.sort(filteredmovs, cmpmovF);
        switch (type) {
            case "asc" -> {
                for (Video v : filteredmovs) {
                    if (v.getFavsappearances() != 0) {
                        k = 1;
                        count++;
                        start += v.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getFavsappearances() != 0) {
                        k = 1;
                        count++;
                        start += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
        }
        start += "]";
        return start;
    }

    public String sortMovieRatings(String type, int nr, String start, List<List<String>> filter) {
        int k = 0, count = 0;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getMovieListFav(filter);
        Comparator<Video> cmpmovF = Comparator.comparing(Video::getRating);
        Collections.sort(filteredmovs, cmpmovF);
        switch (type) {
            case "asc" -> {
                for (Video v : filteredmovs) {
                    if (v.getRating() != 0) {
                        k = 1;
                        count++;
                        start += v.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getRating() != 0) {
                        k = 1;
                        count++;
                        start += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
        }
        start += "]";
        return start;
    }

    public String sortMovieView(String type, int nr, String start, List<List<String>> filter) {
        int k = 0, count = 0;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getMovieListFav(filter);
        Comparator<Video> cmpmovvws = Comparator.comparing(Video::getNrviews);
        Collections.sort(filteredmovs, cmpmovvws);
        switch (type) {
            case "asc" -> {
                for (Video v : filteredmovs) {
                    if (v.getNrviews() != 0) {
                        k = 1;
                        count++;
                        start += v.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getNrviews() != 0) {
                        k = 1;
                        count++;
                        start += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
        }
        start += "]";
        return start;
    }

    public String sortMovieDuration(String type, int nr, String start, List<List<String>> filter) {
        int k = 0, count = 0;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getMovieListFav(filter);
        Comparator<Video> cmpmovlth = Comparator.comparing(Video::getDuration);
        Collections.sort(filteredmovs, cmpmovlth);
        switch (type) {
            case "asc" -> {
                for (Video v : filteredmovs) {
                    if (v.getDuration() != 0) {
                        k = 1;
                        count++;
                        start += v.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getDuration() != 0) {
                        k = 1;
                        count++;
                        start += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
        }
        start += "]";
        return start;
    }

    public ArrayList<Video> getSerialListFav(List<List<String>> filter) {
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

    public String sortSerialFavorite(String type, int nr, String start, List<List<String>> filter) {
        int k = 0, count = 0;
        ArrayList<Video> filteredsers = new ArrayList<Video>();
        filteredsers = getSerialListFav(filter);
        Comparator<Video> cmpmovF = Comparator.comparing(Video::getFavsappearances);
        Collections.sort(filteredsers, cmpmovF);
        switch (type) {
            case "asc" -> {
                for (Video v : filteredsers) {
                    if (v.getFavsappearances() != 0) {
                        k = 1;
                        count++;
                        start += v.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredsers.size() - 1; i >= 0; i--) {
                    if (filteredsers.get(i).getFavsappearances() != 0) {
                        k = 1;
                        count++;
                        start += filteredsers.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
        }
        start += "]";
        return start;
    }

    public String sortSerialDuration(String type, int nr, String start, List<List<String>> filter) {
        int k = 0, count = 0;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getSerialListFav(filter);
        Comparator<Video> cmpmovlth = Comparator.comparing(Video::getDuration);
        Collections.sort(filteredmovs, cmpmovlth);
        switch (type) {
            case "asc" -> {
                for (Video v : filteredmovs) {
                    if (v.getDuration() != 0) {
                        k = 1;
                        count++;
                        start += v.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getDuration() != 0) {
                        k = 1;
                        count++;
                        start += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
        }
        start += "]";
        return start;
    }

    public String sortSerialView(String type, int nr, String start, List<List<String>> filter) {
        int k = 0, count = 0;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getSerialListFav(filter);
        Comparator<Video> cmpmovvws = Comparator.comparing(Video::getNrviews);
        Collections.sort(filteredmovs, cmpmovvws);
        switch (type) {
            case "asc" -> {
                for (Video v : filteredmovs) {
                    if (v.getNrviews() != 0) {
                        k = 1;
                        count++;
                        start += v.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getNrviews() != 0) {
                        k = 1;
                        count++;
                        start += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
        }
        start += "]";
        return start;
    }

    public String sortSerialRatings(String type, int nr, String start, List<List<String>> filter) {
        int k = 0, count = 0;
        ArrayList<Video> filteredmovs = new ArrayList<Video>();
        filteredmovs = getSerialListFav(filter);
        Comparator<Video> cmpmovF = Comparator.comparing(Video::getRating);
        Collections.sort(filteredmovs, cmpmovF);
        switch (type) {
            case "asc" -> {
                for (Video v : filteredmovs) {
                    if (v.getRating() != 0) {
                        k = 1;
                        count++;
                        start += v.getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = filteredmovs.size() - 1; i >= 0; i--) {
                    if (filteredmovs.get(i).getRating() != 0) {
                        k = 1;
                        count++;
                        start += filteredmovs.get(i).getName() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
        }
        start += "]";
        return start;
    }

    public ArrayList<User> SortUsrs() {
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

    public String sortUsersNrrates(String type, int nr, String start) {
        int k = 0, count = 0;
        ArrayList<User> users = SortUsrs();
        switch (type) {
            case "asc" -> {
                for (User ux : users) {
                    if (ux.getNrrates() != 0) {
                        k = 1;
                        count++;
                        start += ux.getUsername() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
            case "desc" -> {
                for (int i = users.size() - 1; i >= 0; i--) {
                    if (users.get(i).getNrrates() != 0) {
                        k = 1;
                        count++;
                        start += users.get(i).getUsername() + ", ";
                    }
                    if (count == nr) {
                        break;
                    }
                }
                if (k == 1) {
                    start = start.substring(0, start.length() - 2);
                }
            }
        }
        start += "]";
        return start;
    }

}


