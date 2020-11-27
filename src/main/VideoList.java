package main;

import java.util.ArrayList;
import java.util.Map;

public class VideoList {
    private ArrayList<Video> list = new ArrayList<Video>();
    private ArrayList<Video> listofmv = new ArrayList<Video>();
    private ArrayList<Video> listofsr = new ArrayList<Video>();


    public VideoList() {}

    public void addVideo(Video vod) {
        list.add(vod);
    }

    public ArrayList<Video> getmvList() {
        return listofmv;
    }

    public ArrayList<Video> getsrList() {
        return listofsr;
    }

    public ArrayList<Video> getList() {
        return list;
    }

    public void setList(ArrayList<Video> list) {
        this.list = list;
    }

    public void addMVideo(MovieList m, UserList u) {
        int priority = 10000;
        for (Movie mv : m.getList()) {
            for (User us : u.getList()) {
                for (int i = 0; i < us.getFavoriteMovies().size(); i++) {
                    if (us.getFavoriteMovies().get(i).equals(mv.getTitle())) {
                        mv.addFavsappearance();
                    }
                }
                for (Map.Entry<String, Integer> entry: us.getHistory().entrySet()) {
                    String k = entry.getKey();
                    Integer v = entry.getValue();
                    if (k.equals(mv.getTitle())) {
                        mv.addnofviews(1);
                    }
                }
            }
            Video vdo = new Video(mv.getTitle(), mv.getYear(), mv.calcRating(), mv.getFavsappearance(),
                     mv.getDuration(), mv.getNviews(), mv.getGenres(), priority--);
            list.add(vdo);
            listofmv.add(vdo);
        }
    }

    public void addSVideo(SerialList s, UserList u) {
        int priority = 0;
        for (Serial sr : s.getList()) {
            for (User us : u.getList()) {
                for (int i = 0; i < us.getFavoriteMovies().size(); i++) {
                    if (us.getFavoriteMovies().get(i).equals(sr.getTitle())) {
                        sr.addFavsappearance();
                    }
                }
                for (Map.Entry<String, Integer> entry: us.getHistory().entrySet()) {
                    String k = entry.getKey();
                    Integer v = entry.getValue();
                    if (k.equals(sr.getTitle())) {
                        sr.addnofviews(1);
                    }
                }
            }
            sr.makeDuration();
            Video vdo = new Video(sr.getTitle(), sr.getYear(),sr.calcRating(), sr.getFavappearances(),
                    sr.getDuration(), sr.getNrviews(), sr.getGenres(), priority++);
            list.add(vdo);
            listofsr.add(vdo);
        }
    }

}
