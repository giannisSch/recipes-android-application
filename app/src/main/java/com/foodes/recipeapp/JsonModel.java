package com.foodes.recipeapp;

import java.util.List;

public class JsonModel {
    String q;
    int from, to ;
    int count;
    List<Hit> hit;

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public int isCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Hit> getHit() {
        return hit;
    }

    public void setHit(List<Hit> hint) {
        this.hit = hit;
    }
}
