package auxiliaryentities;

import entertainment.Season;

import java.util.ArrayList;

public class Serial {
    private String title;
    private int year;
    private ArrayList<String> cast;
    private ArrayList<String> genres;
    private int numberOfSeasons;
    private ArrayList<auxiliaryentities.Season> seasons;
    private double rating;
    private int nrviews;
    private int duration;
    private int favsappearances;


    /**
     * Method toString to ensure prints
     * @return A string with the elements of the entity
     */
    @Override
    public String toString() {
        return "Serial{" + "title='" + title + '\'' + ", year=" + year
                + ", cast=" + cast + ", genres=" + genres
                + ", numberOfSeasons=" + numberOfSeasons + ", seasons=" + seasons + '}';
    }



    public Serial(final String title, final ArrayList<String> cast,
                  final ArrayList<String> genres,
                  final int numberOfSeasons, final ArrayList<Season> seasons,
                  final int year) {
        this.title = title;
        this.cast = cast;
        this.genres = genres;
        this.year = year;
        this.numberOfSeasons = numberOfSeasons;
        this.seasons = new ArrayList<auxiliaryentities.Season>();
        getInputSeasons(seasons);
        nrviews = 0;
        favsappearances = 0;
    }

    /**
     * Function that returns the season found at the index nr
     * @param nr The number of the season
     * @return The season of the serial
     */
    public auxiliaryentities.Season getcertainSeason(final int nr) {
        return seasons.get(nr - 1);
    }

    /**
     * Getter for the title of the serial
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the title of the serial
     * @param title Title to be set
     */
    public void setTitle(final String title) {
        this.title = title;
    }

    /**
     * Getter for the year of the serial
     * @return The year of the serial
     */
    public int getYear() {
        return year;
    }

    /**
     * Setter for the year of the serial
     * @param year Year to be set
     */
    public void setYear(final int year) {
        this.year = year;
    }

    /**
     * Getter for the cast of the serial
     * @return List of the cast
     */
    public ArrayList<String> getCast() {
        return cast;
    }

    /**
     * Setter for the cast of the serial
     * @param cast Cast to be set
     */
    public void setCast(final ArrayList<String> cast) {
        this.cast = cast;
    }

    /**
     * Getter for the genres of the serial
     * @return List of genres
     */
    public ArrayList<String> getGenres() {
        return genres;
    }

    /**
     * Setter for the genres of the serial
     * @param genres Genres to be set
     */
    public void setGenres(final ArrayList<String> genres) {
        this.genres = genres;
    }

    /**
     * Getter for the number of seasons the show has
     * @return Number of seasons
     */
    public int getNumberOfSeasons() {
        return numberOfSeasons;
    }

    /**
     * Setter for the number of seasons the show has
     * @param numberOfSeasons Number of seasons to be set
     */
    public void setNumberOfSeasons(final int numberOfSeasons) {
        this.numberOfSeasons = numberOfSeasons;
    }

    /**
     * Getter for the seasons list
     * @return List of season entities
     */
    public ArrayList<auxiliaryentities.Season> getSeasons() {
        return seasons;
    }

    /**
     * Setter for the seasons list
     * @param seasons Seasons to be set
     */
    public void setSeasons(final ArrayList<auxiliaryentities.Season> seasons) {
        this.seasons = seasons;
    }

    /**
     * Making a copy of the seasons given in the input to assure that we do not toy with the input
     * while also initialising the list of seasons
     * @param seazons Seasons of show that were given in input
     */
    public void getInputSeasons(final ArrayList<Season> seazons) {
        if (seazons.size() != 0) {
            for (int i = 0; i < seazons.size(); i++) {
                auxiliaryentities.Season szn =
                        new auxiliaryentities.Season(seazons.get(i).getCurrentSeason(),
                        seazons.get(i).getDuration());
                this.seasons.add(szn);
            }
        }
    }

    /**
     * Function that adds a rating to a certain season of the show
     * @param r Rating to be added
     * @param nr The season's number
     */
    public void addR(final double r, final int nr) {
        seasons.get(nr - 1).addR(r);
    }

    /**
     * Function that calculates the general rating of the show
     * @return The general rating
     */
    public double calcRating() {
        int coef = 0;
        rating = 0;
        for (auxiliaryentities.Season s : seasons) {
            rating += s.getRating();
            coef++;
        }
        if (coef != 0) {
            rating /= coef;
        }
        return rating;
    }

    /**
     * Setter for the length of the serial
     * @param duration
     */
    public void setDuration(final int duration) {
        this.duration = duration;
    }

    /**
     * Setter for the number of appearances in the users' favourite lists
     * @param favsappearances Number of appearances to be set
     */
    public void setFavsappearances(final int favsappearances) {
        this.favsappearances = favsappearances;
    }

    /**
     * Function that generates the total duration of the serial
     */
    public void makeDuration() {
        for (auxiliaryentities.Season s : seasons) {
            this.duration += s.getDuration();
        }
    }

    /**
     * Setter for the number of times the serial has been viewed
     * @param nrviews Views number to be set
     */
    public void setNrviews(final int nrviews) {
        this.nrviews = nrviews;
    }

    /**
     * Getter for the number of times the serial has been viewed
     * @return Number of times the serial has been viewed
     */
    public int getNrviews() {
        return nrviews;
    }

    /**
     * Function that increments the number of favourite appearances
     */
    public void addFavsappearance() {
        this.favsappearances++;
    }

    /**
     * Function that adds views to the show
     * @param n Number of views to be added
     */
    public void addnofviews(final int n) {
        nrviews += n;
    }

    /**
     * Getter for the number of appearances in users' favourite lists
     * @return The number of favourite appearances
     */
    public int getFavappearances() {
        return favsappearances;
    }

    /**
     * Getter for the duration of the serial
     * @return Duration of the serial
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Getter for the general rating of the serial
     * @return Rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Setter for the general rating of the serial
     * @param rating Rating to be set
     */
    public void setRating(final double rating) {
        this.rating = rating;
    }
}
