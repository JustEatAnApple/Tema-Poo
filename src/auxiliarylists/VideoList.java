package auxiliarylists;

import auxiliaryentities.Movie;
import auxiliaryentities.Serial;
import auxiliaryentities.User;
import auxiliaryentities.Video;

import java.util.ArrayList;
import java.util.Map;

public class VideoList {
    private ArrayList<Video> list = new ArrayList<Video>();
    private ArrayList<Video> listofmv = new ArrayList<Video>();
    private ArrayList<Video> listofsr = new ArrayList<Video>();


    public VideoList() {
    }

    /**
     * Function to add a video to the video list
     * @param vod Video to be added
     */
    public void addVideo(final Video vod) {
        list.add(vod);
    }

    /**
     * Function to get only the movies
     * @return Movies from video list
     */
    public ArrayList<Video> getmvList() {
        return listofmv;
    }

    /**
     * Function to get only the serials
     * @return Serials from video list
     */
    public ArrayList<Video> getsrList() {
        return listofsr;
    }

    /**
     * Getter for the whole video list
     * @return The video list
     */
    public ArrayList<Video> getList() {
        return list;
    }

    /**
     * Setter for the video list
     * @param list List to be set
     */
    public void setList(final ArrayList<Video> list) {
        this.list = list;
    }

    /**
     * Function that adds a video to the video list while also adding it to the sublist of movies
     * @param m The list of all movies
     * @param u The list of all users
     */
    public void addMVideo(final MovieList m, final UserList u) {
        int priority = 10000;
        for (Movie mv : m.getList()) {
            mv.setNviews(0);
            mv.setFavsappearance(0);
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
                        mv.addnofviews(v);
                    }
                }
            }
            Video vdo = new Video(mv.getTitle(), mv.getYear(), mv.calcRating(),
                     mv.getFavsappearance(), mv.getDuration(), mv.getNviews(),
                     mv.getGenres(), priority--);
            list.add(vdo);
            listofmv.add(vdo);
        }
    }

    /**
     * Function that adds a video to the video list while also adding it to the sublist of serials
     * @param s The list of all serials
     * @param u The list of all users
     */
    public void addSVideo(final SerialList s, final UserList u) {
        int priority = 0;
        for (Serial sr : s.getList()) {
            sr.setNrviews(0);
            sr.setFavsappearances(0);
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
                        sr.addnofviews(v);
                    }
                }
            }
            sr.makeDuration();
            Video vdo = new Video(sr.getTitle(), sr.getYear(), sr.calcRating(),
                    sr.getFavappearances(), sr.getDuration(), sr.getNrviews(),
                    sr.getGenres(), priority++);
            list.add(vdo);
            listofsr.add(vdo);
        }
    }

    /**
     * Function to clear the video list
     */
    public void destroyVideos() {
        list.clear();
        listofsr.clear();
        listofmv.clear();
    }

}
