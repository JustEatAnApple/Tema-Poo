package main;

import java.util.*;

public class Recommendation {
    private ActorList a;
    private VideoList v;
    private UserList u;
    private Map<String, Integer> g;

    public Recommendation(ActorList a, VideoList v, UserList u, Map<String, Integer> g) {
        this.a = a;
        this.v = v;
        this.u = u;
        this.g = g;
    }

    public String recommendStandard(User u) {
        String checkthis = "StandardRecommendation cannot be applied!";
        for (Video vd : v.getList()) {
            int ok = 1;
            for (Map.Entry<String, Integer> entry : u.getHistory().entrySet()) {
                String k = entry.getKey();
                Integer v = entry.getValue();
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

    public ArrayList<Video> getBestVidz(User u) {
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

    public String recommendBestUnseen(User u) {
        String checkthis = "BestRatedUnseenRecommendation cannot be applied!";
        ArrayList<Video> videos = getBestVidz(u);
        for (int i = videos.size() - 1; i >= 0; i--) {
            int ok = 0;
            for (Map.Entry<String, Integer> entry : u.getHistory().entrySet()) {
                String k = entry.getKey();
                Integer v = entry.getValue();
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

    public Map<String, Integer> getBestMVFavs() {
        Map<String, Integer> aux = new HashMap<String, Integer>();
        for (Video vidy : v.getmvList()) {
            aux.put(vidy.getName(), 0);
        }
        for (Map.Entry<String, Integer> entry : aux.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            for (User uzer : u.getList()) {
                for (String strng : uzer.getFavoriteMovies()) {
                    if (k.equals(strng)) {
                        aux.put(k, ++v);
                    }
                }
            }
        }
        return aux;
    }

    public Map<String, Integer> getBestSRFavs() {
        Map<String, Integer> aux = new HashMap<String, Integer>();
        for (Video vidy : v.getsrList()) {
            aux.put(vidy.getName(), 0);
        }
        for (Map.Entry<String, Integer> entry : aux.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            for (User uzer : u.getList()) {
                for (String strng : uzer.getFavoriteMovies()) {
                    if (k.equals(strng)) {
                        aux.put(k, ++v);
                    }
                }
            }
        }
        return aux;
    }

    public Map<String, Integer> mapmerger(Map<String, Integer> first, Map<String, Integer> second) {
        Map<String, Integer> themerger = new HashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : first.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            themerger.put(k, v);
        }
        for (Map.Entry<String, Integer> entry : second.entrySet()) {
            String k = entry.getKey();
            Integer v = entry.getValue();
            themerger.put(k, v);
        }
        return themerger;
    }

    public String recommendFavorite(User user) {
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

    public VideoList getMVListoftitles(String gen) {
        VideoList lmgen = new VideoList();
        for (Video m : v.getmvList()) {
            for (String g : m.getGenres()) {
                if (g.equals(gen)) {
                    lmgen.addVideo(m);
                }
            }
        }
        return lmgen;
    }

    public VideoList getSRListoftitles(String gen) {
        VideoList lsgen = new VideoList();
        for (Video m : v.getsrList()) {
            for (String g : m.getGenres()) {
                if (g.equals(gen)) {
                    lsgen.addVideo(m);
                }
            }
        }
        return lsgen;
    }

    public VideoList combineSRMV(VideoList mv, VideoList sr) {
        VideoList gnlist = new VideoList();
        for (Video m : mv.getList()) {
            gnlist.addVideo(m);
        }
        for (Video s : sr.getList()) {
            gnlist.addVideo(s);
        }
        return gnlist;
    }

    public String recommendSearch(User user, String gen) {
        int checkm, checks;
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
                Integer v = entry.getValue();
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

    public String recommendPopular(User user) {
        int max = -1, check, j = 0;
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
                System.out.println("Genul: " + title);
                for (String genre : video.getGenres()) {
                    check = 0;
                    System.out.println("Daniel: " + hierarcy.get(j));
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
