package com.example.cdi182.model.bean;

public class CityBean {
    private String ville, cp;
    private String url;

    public String getVille() {
        return ville;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }
}
