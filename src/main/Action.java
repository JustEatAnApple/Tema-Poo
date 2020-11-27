package main;

import fileio.ActionInputData;
import fileio.SerialInputData;

import java.util.ArrayList;
import java.util.List;


public class Action {
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
    private User usr;
    private Movie mov;
    private Serial ser;
    private String output;

    public Action(int actionId, String actionType, String type, String username,
                  String objectType, String sortType, String criteria, String title,
                  String genre, int number, double grade, int seasonNumber,
                  List<List<String>> filters, UserList usrs, MovieList movs,
                  ActorList actrs, SerialList srls) {
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

    public void getVdslist() {
        vds = new VideoList();
        vds.addMVideo(movs, usrs);
        vds.addSVideo(srls, usrs);
    }

    public void grabUser() {
        for (int i = 0; i < usrs.getSize(); i++) {
            if (usrs.getUsername(i).equals(username)) {
                 this.usr = usrs.getUser(i);
            }
        }
    }

    public void grabMovie() {
        for (int i = 0; i < movs.getSize(); i++) {
            if (movs.getTitle(i).equals(title)) {
                this.mov = movs.getMovie(i);
            }
        }
    }

    public void grabSerial() {
        for (int i = 0; i < srls.getSize(); i++) {
            if (srls.getTitle(i).equals(title)) {
                this.ser = srls.getSerial(i);
            }
        }
    }


    public String checkActionType() {
        if (actionType.equals("command")) {
            grabUser();
            grabSerial();
            grabMovie();
            Command c = new Command(title, movs, srls, usrs,seasonNumber);
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
            }
        } else if (actionType.equals("query")) {
            // doar numele
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
                            return q.sortActorAwards(sortType, filters.get(3), start);
                        }
                        case "filter_description" -> {
                            return q.sortActorDescription(sortType, filters.get(2), start);
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
                    }
                }
                case "users" -> {
                    switch (criteria) {
                        case "num_ratings" -> {
                            return q.sortUsersNrrates(sortType, number, start);
                        }
                    }
                }
            }
        } else if(actionType.equals("recommendation")) {
            grabUser();
            getVdslist();
            Recommendation r = new Recommendation(actrs, vds, usrs);
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
            }
        }
        return null;
    }

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public String getCriteria() {
        return criteria;
    }

    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public List<List<String>> getFilters() {
        return filters;
    }

    public void setFilters(List<List<String>> filters) {
        this.filters = filters;
    }

}
