package actionclasses;

import auxiliaryentities.User;
import auxiliaryentities.Video;
import auxiliarylists.ActorList;
import auxiliarylists.UserList;
import auxiliarylists.VideoList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

public class Recommendation {
    private ActorList a;
    private VideoList v;
    private UserList u;
    private Map<String, Integer> g;

    public Recommendation(final ActorList a, final VideoList v, final UserList u,
                          final Map<String, Integer> g) {
        this.a = a;
        this.v = v;
        this.u = u;
        this.g = g;
    }

    /**
     * Function that returns the first show not watched by an user
     * @param usr User for whom we search the show
     * @return First show that is not seen by the user
     * (Criteria being the order in which the shows were added)
     */
    public String recommendStandard(final User usr) {
        String checkthis = "StandardRecommendation cannot be applied!";
        for (Video vd : v.getList()) {
            int ok = 1;
            for (Map.Entry<String, Integer> entry : usr.getHistory().entrySet()) {
                String k = entry.getKey();
                if (k.equals(vd.getName())) {
                    ok = 0;
                    break;
                }
            }
            if (ok == 1) {
                checkthis = vd.getName();
                return "StandardRecommendation result: " + checkthis;
            }
        }
        return checkthis;
    }

    /**
     * Function that creates a sorted list of shows by rating (ascending)
     * @return The sorted list
     */
    public ArrayList<Video> getBestVidz() {
        ArrayList<Video> vidz = new ArrayList<Video>();
        for (Video vod : v.getList()) {
            vidz.add(vod);
        }
        Comparator<Video> cmpprio = Comparator.comparing(Video::getPriority);
        Collections.sort(vidz, cmpprio);
        Comparator<Video> cmpvdR = Comparator.comparing(Video::getRating);
        Collections.sort(vidz, cmpvdR);
        return vidz;
    }

    /**
     * Function that grabs the best rated show that the user has not yet seen
     * @param usr The user for whom we do the recommendation
     * @return The title of the specified show (the output message)
     */
    public String recommendBestUnseen(final User usr) {
        String checkthis = "BestRatedUnseenRecommendation cannot be applied!";
        ArrayList<Video> videos = getBestVidz();
        for (int i = videos.size() - 1; i >= 0; i--) {
            int ok = 0;
            for (Map.Entry<String, Integer> entry : usr.getHistory().entrySet()) {
                String k = entry.getKey();
                if (k.equals(videos.get(i).getName())) {
                    ok = 1;
                    break;
                }
            }
            if (ok == 0) {
                checkthis = videos.get(i).getName();
                return "BestRatedUnseenRecommendation result: " + checkthis;
            }
        }
        return checkthis;
    }

    /**
     * Function that makes a map of movies with their number of appearances in
     * the users favourite list
     * @return The specified map
     */
    public Map<String, Integer> getBestMVFavs() {
        Map<String, Integer> aux = new HashMap<String, Integer>();
        for (Video vidy : v.getmvList()) {
            aux.put(vidy.getName(), 0);
        }
        for (Map.Entry<String, Integer> entry : aux.entrySet()) {
            String k = entry.getKey();
            Integer val = entry.getValue();
            for (User uzer : u.getList()) {
                for (String strng : uzer.getFavoriteMovies()) {
                    if (k.equals(strng)) {
                        aux.put(k, ++val);
                    }
                }
            }
        }
        return aux;
    }

    /**
     * Function that makes a map of serials with their number of appearances in
     * the users favourite list
     * @return The specified map
     */
    public Map<String, Integer> getBestSRFavs() {
        Map<String, Integer> aux = new HashMap<String, Integer>();
        for (Video vidy : v.getsrList()) {
            aux.put(vidy.getName(), 0);
        }
        for (Map.Entry<String, Integer> entry : aux.entrySet()) {
            String k = entry.getKey();
            Integer val = entry.getValue();
            for (User uzer : u.getList()) {
                for (String strng : uzer.getFavoriteMovies()) {
                    if (k.equals(strng)) {
                        aux.put(k, ++val);
                    }
                }
            }
        }
        return aux;
    }

    /**
     * Function that combines two maps into one
     * @param first The first map
     * @param second The second map
     * @return The merged map (first + second)
     */
    public Map<String, Integer> mapmerger(final Map<String, Integer> first,
                                          final Map<String, Integer> second) {
        Map<String, Integer> themerger = new HashMap<>();
        for (Map.Entry<String, Integer> entry : first.entrySet()) {
            String k = entry.getKey();
            Integer val = entry.getValue();
            themerger.put(k, val);
        }
        for (Map.Entry<String, Integer> entry : second.entrySet()) {
            String k = entry.getKey();
            Integer val = entry.getValue();
            themerger.put(k, val);
        }
        return themerger;
    }

    /**
     * Function that recommends the show with the most favourite appearances that the
     * specified user has not seen
     * @param user Specified user
     * @return The output message
     */
    public String recommendFavorite(final User user) {
        Map<String, Integer> mbfavs = getBestMVFavs();
        Map<String, Integer> sbfavz = getBestSRFavs();
        Map<String, Integer> mergedmap = mapmerger(mbfavs, sbfavz);
        Integer replacer = -1;
        int max = -1, check = 1;
        String title = "";
        for (int i = 0; i < mergedmap.size(); i++) {
            max = -1;
            check = 1;
            for (Map.Entry<String, Integer> entry : mergedmap.entrySet()) {
                String ttl = entry.getKey();
                Integer scr = entry.getValue();
                if (scr > max) {
                    max = scr;
                    title = ttl;
                }
            }
            for (Map.Entry<String, Integer> entry : user.getHistory().entrySet()) {
                String k = entry.getKey();
                if (k.equals(title)) {
                    check = 0;
                    break;
                }
            }
            if (check == 1) {
                return "FavoriteRecommendation result: " + title;
            }
            mergedmap.replace(title, replacer);
        }
        return "FavoriteRecommendation cannot be applied!";
    }

    /**
     * Function that makes a list of all the movies that are of a specified genre
     * @param gen The required genre
     * @return The list of movies
     */
    public VideoList getMVListoftitles(final String gen) {
        VideoList lmgen = new VideoList();
        for (Video m : v.getmvList()) {
            for (String str : m.getGenres()) {
                if (str.equals(gen)) {
                    lmgen.addVideo(m);
                }
            }
        }
        return lmgen;
    }

    /**
     * Function that makes a list of all the serials that are of a specified genre
     * @param gen The specified genre
     * @return The list of serials
     */
    public VideoList getSRListoftitles(final String gen) {
        VideoList lsgen = new VideoList();
        for (Video m : v.getsrList()) {
            for (String str : m.getGenres()) {
                if (str.equals(gen)) {
                    lsgen.addVideo(m);
                }
            }
        }
        return lsgen;
    }

    /**
     * Function that merges two videoLists
     * @param mv The first videoList
     * @param sr The second videoList
     * @return The merged videoList
     */
    public VideoList combineSRMV(final VideoList mv, final VideoList sr) {
        VideoList gnlist = new VideoList();
        for (Video m : mv.getList()) {
            gnlist.addVideo(m);
        }
        for (Video s : sr.getList()) {
            gnlist.addVideo(s);
        }
        return gnlist;
    }

    /**
     * Function that gets all the shows from a genre that an user has not seen sorted
     * by rating and then by name
     * @param user The user in question
     * @param gen The genre specified
     * @return The list of shows (The output message)
     */
    public String recommendSearch(final User user, final String gen) {
        int checkm;
        String start = "SearchRecommendation result: [";
        VideoList movs = getMVListoftitles(gen);
        VideoList serls = getSRListoftitles(gen);
        VideoList fulllist = combineSRMV(movs, serls);
        Comparator<Video> cmplN = Comparator.comparing(Video::getName);
        Collections.sort(fulllist.getList(), cmplN);
        Comparator<Video> cmpl = Comparator.comparing(Video::getRating);
        Collections.sort(fulllist.getList(), cmpl);
        for (Video vdo : fulllist.getList()) {
            checkm = 1;
            for (Map.Entry<String, Integer> entry : user.getHistory().entrySet()) {
                String k = entry.getKey();
                if (k.equals(vdo.getName())) {
                    checkm = 0;
                    break;
                }
            }
            if (checkm == 1) {
                start += (vdo.getName() + ", ");
            }
        }
        if (!start.equals("SearchRecommendation result: [")) {
            start = start.substring(0, start.length() - 2);
            start += "]";
            return start;
        }
        return "SearchRecommendation cannot be applied!";
    }

    /**
     * Function that returns the first show from the most popular genre
     * (the one with the most views) that the user has not seen
     * If the user has seen all the shows from the most popular genre, we check
     * the next most popular genre and so on and so forth
     * @param user The specified user
     * @return The first most popular genre show (The output message)
     */
    public String recommendPopular(final User user) {
        int max, check, j = 0;
        String title = "";
        Integer replacer = -1;
        ArrayList<String> hierarcy = new ArrayList<>();
        for (int i = 0; i < g.size(); i++) {
            max = -1;
            for (Map.Entry<String, Integer> entry : g.entrySet()) {
                String ttl = entry.getKey();
                Integer scr = entry.getValue();
                if (scr > max) {
                    max = scr;
                    title = ttl;
                }
            }
            hierarcy.add(title);
            g.replace(title, replacer);
        }
        for (int i = 0; i < hierarcy.size(); i++) {
            if ((j + 1) >= hierarcy.size()) {
                break;
            }
            for (Video video : v.getList()) {
                for (String genre : video.getGenres()) {
                    check = 0;
                    if (genre.equals(hierarcy.get(j))) {
                        for (Map.Entry<String, Integer> sentry : user.getHistory().entrySet()) {
                            String k = sentry.getKey();
                            if (k.equals(video.getName())) {
                                check = 1;
                                break;
                            }
                        }
                        if (check == 0) {
                            return "PopularRecommendation result: " + video.getName();
                        }
                    }
                }
            }
            j++;
        }
        return "PopularRecommendation cannot be applied!";
    }
}
