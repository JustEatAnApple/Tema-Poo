package main;

import java.util.*;

public class Recommendation {
    private ActorList a;
    private VideoList v;
    private UserList u;

    public Recommendation(ActorList a, VideoList v, UserList u) {
        this.a = a;
        this.v = v;
        this.u = u;
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

    public static <K extends Comparable, V> Map<K,V> sortByKeys(Map<K,V> map)
    {
        Map<K, V> treeMap = new TreeMap<>(new Comparator<K>() {
            @Override
            public int compare(K a, K b) {
                return b.compareTo(a);
            }
        });

        treeMap.putAll(map);

        return treeMap;
    }

    public String recommendFavorite(User user) {
        Map<String, Integer> mbfavs = getBestMVFavs();
        Map<String, Integer> sbfavz = getBestSRFavs();
        Map<String, Integer> fvsmvMap = sortByKeys(mbfavs);
        Map<String, Integer> fvssrMap = sortByKeys(sbfavz);
        int aux_size = 0;
        if (fvsmvMap.size() > fvssrMap.size()) {
            aux_size = fvsmvMap.size();
        } else {
            aux_size = fvssrMap.size();
        }
        for (int i = 0; i < aux_size; i++) {
            int maxs = -1, maxm = -1;
            String titles = "", titlem = "";
            for (Map.Entry<String, Integer> entry : fvsmvMap.entrySet()) {
                String kmaux = entry.getKey();
                Integer vmaux = entry.getValue();
                if (vmaux > maxm) {
                    maxm = vmaux;
                    titlem = kmaux;
                }
            }
            for (Map.Entry<String, Integer> entry : fvssrMap.entrySet()) {
                String ksaux = entry.getKey();
                Integer vsaux = entry.getValue();
                if (vsaux > maxs) {
                    maxs = vsaux;
                    titles = ksaux;
                }
            }
            int contors = 1, contorm = 1;
            for (Map.Entry<String, Integer> entry: user.getHistory().entrySet()) {
                String k = entry.getKey();
                if (k.equals(titlem)) {
                    contorm = 0;
                    contors = 0;
                    break;
                }
                if (k.equals(titles)) {
                    contorm = 0;
                    contors = 0;
                    break;
                }
            }
            if(contorm == 1) {
                return "FavoriteRecommendation result: " + titlem;
            }
            if(contors == 1) {
                return "FavoriteRecommendation result: " + titles;
            }
            fvsmvMap.replace(titlem, -1);
            fvssrMap.replace(titles, -1);
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

    public String recommendSearch(User user, String gen) {
        int checkm, checks;
        String start = "SearchRecommendation result: [";
        VideoList movs = getMVListoftitles(gen);
        VideoList serls = getSRListoftitles(gen);
        Comparator<Video> cmpMovN = Comparator.comparing(Video::getName);
        Collections.sort(movs.getList(), cmpMovN);
        Comparator<Video> cmpMov = Comparator.comparing(Video::getRating);
        Collections.sort(movs.getList(), cmpMov);
        Comparator<Video> cmpSrsN = Comparator.comparing(Video::getName);
        Collections.sort(serls.getList(), cmpSrsN);
        Comparator<Video> cmpSrs = Comparator.comparing(Video::getRating);
        Collections.sort(serls.getList(), cmpSrs);
        for (Video mv : movs.getList()) {
            checkm = 1;
            for (Map.Entry<String, Integer> entry: user.getHistory().entrySet()) {
                String k = entry.getKey();
                Integer v = entry.getValue();
                if (k.equals(mv.getName())) {
                    checkm = 0;
                    break;
                }
            }
            if (checkm == 1) {
                start += ( mv.getName() + ", " );
            }
        }
        for (Video sr : serls.getList()) {
            checks = 1;
            for (Map.Entry<String, Integer> entry: user.getHistory().entrySet()) {
                String k = entry.getKey();
                Integer v = entry.getValue();
                if (k.equals(sr.getName())) {
                    checks = 0;
                    break;
                }
            }
            if (checks == 1) {
                start += ( sr.getName() + ", " );
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

        return "PopularRecommendation cannot be applied!";
    }

}
