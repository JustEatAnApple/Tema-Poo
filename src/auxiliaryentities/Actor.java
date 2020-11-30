package auxiliaryentities;

import actor.ActorsAwards;

import java.util.ArrayList;
import java.util.Map;

public class Actor {
    private String name;
    private String careerDescription;
    private ArrayList<String> filmography;
    private Map<ActorsAwards, Integer> awards;
    private double rating = 0;
    private int nrofratings = 0;
    private int awdscounter = 0;
    private boolean haswords = false;

    public Actor() {
    }

    public Actor(final String name, final String careerDescription,
                 final ArrayList<String> filmography,
                 final Map<ActorsAwards, Integer> awards) {
        this.name = name;
        this.careerDescription = careerDescription;
        this.filmography = filmography;
        this.awards = awards;
    }

    /**
     * Getter for the name
     * @return Name of Actor
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name
     * @param name Name to be set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Getter for the career descritpion
     * @return The career description
     */
    public String getCareerDescription() {
        return careerDescription;
    }

    /**
     * Setter for the career description
     * @param careerDescription Career description to be set
     */
    public void setCareerDescription(final String careerDescription) {
        this.careerDescription = careerDescription;
    }

    /**
     * Getter for the filmography
     * @return Filmography of actor
     */
    public ArrayList<String> getFilmography() {
        return filmography;
    }

    /**
     * Getter for the actor rating
     * @return Actor's rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Setter for the actor's rating
     * @param rating Rating to be set
     */
    public void setRating(final double rating) {
        this.rating = rating;
    }

    /**
     * Method toString to ensure prints
     * @return A string with the elements of the entity
     */
    @Override
    public String toString() {
        return "Actor{" + "name='" + name + '\''
                + ", careerDescription='" + careerDescription + '\''
                + ", filmography=" + filmography
                + ", awards=" + awards
                + '}';
    }

    /**
     * Setter for the filmography
     * @param filmography Filmography to be set
     */
    public void setFilmography(final ArrayList<String> filmography) {
        this.filmography = filmography;
    }

    /**
     * Getter for the awards of the actor
     * @return Awards of actor
     */
    public Map<ActorsAwards, Integer> getAwards() {
        return awards;
    }

    /**
     * Setter for actor's awards
     * @param awards Awards to be set
     */
    public void setAwards(final Map<ActorsAwards, Integer> awards) {
        this.awards = awards;
    }

    /**
     * Getter for number of ratings the actor has
     * @return Number of times an actor has been rated (indirectly)
     */
    public int getNrofratings() {
        return nrofratings;
    }

    /**
     * Setter for the number of ratings
     * @param nrofratings Number of ratings to be set
     */
    public void setNrofratings(final int nrofratings) {
        this.nrofratings = nrofratings;
    }

    /**
     * Increment the number of times an actor has been rated
     */
    public void addNrofratings() {
        this.nrofratings += 1;
    }

    /**
     * Adds a sole rating to the sum of ratings
     * @param r Rating to be added
     */
    public void addRating(final double r) {
        this.rating += r;
    }

    /**
     * Getter for the number of awards
     * @return Number of awards
     */
    public int getAwdscounter() {
        return awdscounter;
    }

    /**
     * Setter for the number of awards
     * @param awdscounter Number of awards to be set
     */
    public void setAwdscounter(final int awdscounter) {
        this.awdscounter = awdscounter;
    }

    /**
     * Getter of a boolean variable
     * @return Boolean value (true/false)
     */
    public boolean isHaswords() {
        return haswords;
    }

    /**
     * Setter for the boolean variable
     * @param haswords Boolean value (true/false)
     */
    public void setHaswords(final boolean haswords) {
        this.haswords = haswords;
    }
}
