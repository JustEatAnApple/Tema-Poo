package main;

import fileio.MovieInputData;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    private ArrayList<Movie> list = new ArrayList<>();

    public MovieList() {}

    public ArrayList<Movie> getList() {
        return list;
    }

    public void setList(ArrayList<Movie> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MovieList{" +
                "list=" + list +
                '}';
    }

    public String getTitle(int i) {
        return list.get(i).getTitle();
    }

    public int getSize() {
        return list.size();
    }

    public Movie getMovie(int i) {
        return list.get(i);
    }

    public Movie getMoviebyTitle(String title) {
        for (int i = 0; i < list.size(); i ++) {
            if (list.get(i).getTitle().equals(title)) {
                return list.get(i);
            }
        }
        return null;
    }

    public void getInputMovies(List<MovieInputData> a) {
        if (a.size() != 0) {
            for (int i = 0; i < a.size(); i ++) {
                Movie film = new Movie(a.get(i).getTitle(),  a.get(i).getCast(),
                        a.get(i).getGenres(), a.get(i).getYear(), a.get(i).getDuration());
                list.add(film);
            }
        }
    }
}
