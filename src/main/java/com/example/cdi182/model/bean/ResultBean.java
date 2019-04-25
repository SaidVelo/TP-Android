package com.example.cdi182.model.bean;

import java.util.ArrayList;

public class ResultBean {
    private ArrayList<CityBean> results;
    private ErrorBean errors;

    public ArrayList<CityBean> getResults() {
        return results;
    }

    public void setResults(ArrayList<CityBean> results) {
        this.results = results;
    }

    public ErrorBean getErrors() {
        return errors;
    }

    public void setErrors(ErrorBean errors) {
        this.errors = errors;
    }
}
