package com.lemuel.ganamrs.entity;

import java.util.ArrayList;

public class GankResponse<T> {

    private boolean error;
    private ArrayList<T> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public ArrayList<T> getResults() {
        return results;
    }

    public void setResults(ArrayList<T> results) {
        this.results = results;
    }
}