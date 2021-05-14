package com.example.diplom.model.web;

import com.example.diplom.model.Vote;

public class VoteTo {
    private String voted;
    private String user;
    private String restaurant;

    public VoteTo(Vote vote) {
        this.voted = vote.getVoited().toString();
        this.user = vote.getUser().getName();
        this.restaurant = vote.getRestaurant().getName();
    }

    public String getVoted() {
        return voted;
    }

    public void setVoted(String voted) {
        this.voted = voted;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }
}
