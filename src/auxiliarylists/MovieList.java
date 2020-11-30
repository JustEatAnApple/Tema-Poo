package auxiliarylists;

import auxiliaryentities.Movie;
import fileio.MovieInputData;

import java.util.ArrayList;
import java.util.List;

public class MovieList {
    private ArrayList<Movie> list = new ArrayList<>();

    public MovieList() {
    }

    /**
     * Getter for the list of movies
     * @return List of movies
     */
    public ArrayList<Movie> getList() {
        return list;
    }

    /**
     * Setter for the list of movies
     * @param list The list of movies
     */
    public void setList(final ArrayList<Movie> list) {
        this.list = list;
    }

    /**
     * Method toString to ensure prints
     * @return A string with the elements of the entity
     */
    @Override
    public String toString() {
        return "MovieList{" + "list=" + list + '}';
    }

    /**
     * Getter for the title of certain film
     * @param i Index of movie
     * @return Movie's title at the i position
     */
    public String getTitle(final int i) {
        return list.get(i).getTitle();
    }

    /**
     * Getter for the size of the movie list
     * @return Size of the movie list
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Getter for the a movie based by its position in the list
     * @param i Index of certain movie
     * @return The movie at the index's position
     */
    public Movie getMovie(final int i) {
        return list.get(i);
    }

    /**
     * Grab a movie from the list based by its title
     * @param title Title of the movie to be found
     * @return The movie entity with the title that matches
     */
    public Movie getMoviebyTitle(final String title) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().equals(title)) {
                return list.get(i);
            }
        }
        return null;
    }

    /**
     * Making a copy of the movies given in the input to assure that we do not mess up the input
     * while also initialising the list for the movies
     * @param a Movies given in input
     */
    public void getInputMovies(final List<MovieInputData> a) {
        if (a.size() != 0) {
            for (int i = 0; i < a.size(); i++) {
                Movie film = new Movie(a.get(i).getTitle(),  a.get(i).getCast(),
                        a.get(i).getGenres(), a.get(i).getYear(), a.get(i).getDuration());
                list.add(film);
            }
        }
    }
}
