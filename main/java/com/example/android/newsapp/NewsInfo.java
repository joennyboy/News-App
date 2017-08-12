package com.example.android.newsapp;

/**
 * Created by Etumusei on 7/14/2017.
 */

public class NewsInfo {
    private String webTitle;
    private String sectionName;
    private String webPublicationDate;
    private String webUrl;

    public NewsInfo(String webTitle, String sectionName, String webPublicationDate, String webUrl) {

        this.setWebTitle(webTitle);
        this.setSectionName(sectionName);
        this.setWebUrl(webUrl);
        this.setWebPublicationDate(webPublicationDate);
    }

    public String getWebTitle() {
        return webTitle;
    }

    private void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getSectionName() {
        return sectionName;
    }

    private void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getWebUrl() {
        return webUrl;
    }

    private void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    private void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }
}