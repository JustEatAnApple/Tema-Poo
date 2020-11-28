package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class Action {
    private int actionId;
    private String actionType;
    private String type;
    private String username;
    private String objectType;
    private String sortType;
    private String criteria;
    private String title;
    private String genre;
    private int number;
    private double grade;
    private int seasonNumber;
    private List<List<String>> filters = new ArrayList<>();
    private UserList usrs;
    private MovieList movs;
    private ActorList actrs;
    private SerialList srls;
    private VideoList vds;
    private Map<String, Integer> gnr;
    private User usr;
    private Movie mov;
    private Serial ser;
    private String output;
    private final int j = 9;
    private final int k = 3;

    public Action(final int actionId, final String actionType, final String type,
                  final String username, final String objectType, final String sortType,
                  final String criteria, final String title, final String genre,
                  final int number, final double grade, final int seasonNumber,
                  final List<List<String>> filters, final UserList usrs, final MovieList movs,
                  final ActorList actrs, final SerialList srls) {
        this.actionId = actionId;
        this.actionType = actionType;
        this.type = type;
        this.username = username;
        this.objectType = objectType;
        this.sortType = sortType;
        this.criteria = criteria;
        this.title = title;
        this.genre = genre;
        this.number = number;
        this.grade = grade;
        this.seasonNumber = seasonNumber;
        this.filters = filters;

        this.usrs = usrs;
        this.movs = movs;
        this.actrs = actrs;
        this.srls = srls;
    }

    /**
     * Function that generates the genres that exist in all shows and the number of views of each
     * It is stored in the local variable gnr
      */
    public void getGnr() {
        gnr = new HashMap<>();
        for (Video vyd : vds.getList()) {
            for (String gen : vyd.getGenres()) {
                if (!gnr.containsKey(gen)) {
                    gnr.put(gen, vyd.getNrviews());
                } else {
                    gnr.replace(gen, gnr.get(gen) + vyd.getNrviews());
                }
            }
        }
    }

    /**
     * Function that gathers both movies and serials into a separate class (VideoList)
     * It is stored in the local variable vds
     */
    public void getVdslist() {
        vds = new VideoList();
        vds.addMVideo(movs, usrs);
        vds.addSVideo(srls, usrs);
    }

    /**
     * Function that grabs a user from the UserList class to make changes upon the entity
     * It is stored in the local variable usr
     */
    public void grabUser() {
        for (int i = 0; i < usrs.getSize(); i++) {
            if (usrs.getUsername(i).equals(username)) {
                 this.usr = usrs.getUser(i);
            }
        }
    }

    /**
     * Function that grabs a movie from the MovieList class to make changes upon the entity
     * It is stored in the local variable mov
      */
    public void grabMovie() {
        for (int i = 0; i < movs.getSize(); i++) {
            if (movs.getTitle(i).equals(title)) {
                this.mov = movs.getMovie(i);
            }
        }
    }

    /**
     * Function that grabs a serial from the SerialList class to make changes upon the entity
     * It is stored in the local variable ser.
     */
    public void grabSerial() {
        for (int i = 0; i < srls.getSize(); i++) {
            if (srls.getTitle(i).equals(title)) {
                this.ser = srls.getSerial(i);
            }
        }
    }

    /**
     * This is the function in which we check which action ( command, query or recommendation )
     * we are dealing with.
     * @return is the output that has to be written in a JSON file as requested
     */
    public String checkActionType() {
        if (actionType.equals("command")) {
            grabUser();
            grabSerial();
            grabMovie();
            Command c = new Command(title, movs, srls, usrs, seasonNumber);
            switch (type) {
                case "favorite" -> {
                    return c.addFavourite(usr);
                }
                case "view" -> {
                    return c.addView(usr);
                }
                case "rating" -> {
                    return c.addRating(usr, grade, seasonNumber);
                }
                default -> {
                    return null;
                }
            }
        } else if (actionType.equals("query")) {
            if (vds != null) {
                vds.destroyVideos();
            }
            getVdslist();
            Query q = new Query(movs, srls, actrs, usrs, vds);
            String start = "Query result: [";
            switch (objectType) {
                case "actors" -> {
                    switch (criteria) {
                        case "average" -> {
                            return q.sortActorRating(sortType, number, start);
                        }
                        case "awards" -> {
                            int i = j / k;
                            return q.sortActorAwards(sortType, filters.get(i), start);
                        }
                        case "filter_description" -> {
                            int i = 2;
                            return q.sortActorDescription(sortType, filters.get(i), start);
                        }
                        default -> {
                            return null;
                        }
                    }
                }
                case "movies" -> {
                    switch (criteria) {
                        case "favorite" -> {
                            return q.sortMovieFavorite(sortType, number, start, filters);
                        }
                        case "ratings" -> {
                            return q.sortMovieRatings(sortType, number, start, filters);
                        }
                        case "longest" -> {
                            return q.sortMovieDuration(sortType, number, start, filters);
                        }
                        case "most_viewed" -> {
                            return q.sortMovieView(sortType, number, start, filters);
                        }
                        default -> {
                            return null;
                        }
                    }
                }
                case "shows" -> {
                    switch (criteria) {
                        case "favorite" -> {
                            return q.sortSerialFavorite(sortType, number, start, filters);
                        }
                        case "ratings" -> {
                            return q.sortSerialRatings(sortType, number, start, filters);
                        }
                        case "longest" -> {
                            return q.sortSerialDuration(sortType, number, start, filters);
                        }
                        case "most_viewed" -> {
                            return q.sortSerialView(sortType, number, start, filters);
                        }
                        default -> {
                            return null;
                        }
                    }
                }
                case "users" -> {
                    switch (criteria) {
                        case "num_ratings" -> {
                            return q.sortUsersNrrates(sortType, number, start);
                        }
                        default -> {
                            return null;
                        }
                    }
                }
                default -> {
                    return null;
                }
            }
        } else if (actionType.equals("recommendation")) {
            grabUser();
            if (vds != null) {
                vds.destroyVideos();
            }
            if (gnr != null) {
                gnr.clear();
            }
            getVdslist();
            getGnr();
            for (Map.Entry<String, Integer> entry : gnr.entrySet()) {
                System.out.println("Genrezzz: " + entry.getKey() + " " + entry.getValue());
            }
            Recommendation r = new Recommendation(actrs, vds, usrs, gnr);
            switch (type) {
                case "standard" -> {
                    return r.recommendStandard(usr);
                }
                case "best_unseen" -> {
                    return r.recommendBestUnseen(usr);
                }
                case "popular" -> {
                    if (usr.getSubscriptionType().equals("BASIC")) {
                        return "PopularRecommendation cannot be applied!";
                    }
                    return r.recommendPopular(usr);
                }
                case "favorite" -> {
                    if (usr.getSubscriptionType().equals("BASIC")) {
                        return "FavoriteRecommendation cannot be applied!";
                    }
                    return r.recommendFavorite(usr);
                }
                case "search" -> {
                    if (usr.getSubscriptionType().equals("BASIC")) {
                        return "SearchRecommendation cannot be applied!";
                    }
                    return r.recommendSearch(usr, genre);
                }
                default -> {
                    return null;
                }
            }
        }
        return null;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(final int actionId) {
        this.actionId = actionId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(final String actionType) {
        this.actionType = actionType;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(final String objectType) {
        this.objectType = objectType;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(final String sortType) {
        this.sortType = sortType;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(final String criteria) {
        this.criteria = criteria;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String genre) {
        this.genre = genre;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(final int number) {
        this.number = number;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(final double grade) {
        this.grade = grade;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(final int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public List<List<String>> getFilters() {
        return filters;
    }

    public void setFilters(final List<List<String>> filters) {
        this.filters = filters;
    }

}
