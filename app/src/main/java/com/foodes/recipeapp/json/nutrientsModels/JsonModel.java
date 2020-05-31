package com.foodes.recipeapp.json.nutrientsModels;

import java.util.List;

public class JsonModel {
    String q;
    int from, to ;
    int count;
    List<HitModel> hits;

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

    public List<HitModel> getHit() {
        return hits;
    }

    public void setHit(List<HitModel> hint) {
        this.hits = hits;
    }
}
