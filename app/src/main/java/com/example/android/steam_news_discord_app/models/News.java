package com.example.android.steam_news_discord_app.models;

import java.util.Date;

/**
 * An {@link News} object contains information related to a single news.
 */

public class News {

    /** Title of the article */
    private String mTitle;


    /** Author name in the article */
    private String mAuthor;

    /** Web publication date of the article */
    private Date mDate;

    /** Website URL of the article */
    private String mUrl;

    /** TrailText of the article with string type Html */
    private String mTrailTextHtml;

    /**
     * Constructs a new {@link News} object.
     *
     * @param title is the title of the article
     * @param author is author name in article
     * @param date is the web publication date of the article
     * @param url is the website URL to find more details about the article
     * @param trailText is trail text of the article with string type Html
     */
    public News(String title, String author, int date, String url, String trailText) {
        mTitle = title;
        mAuthor = author;
        mDate = new Date(date);
        mUrl = url;
        mTrailTextHtml = trailText;
    }

    /**
     * Returns the title of the article
     */
    public String getTitle() {
        return mTitle;
    }

    /**
     * Returns the author name of the article.
     */
    public String getAuthor() {
        return mAuthor;
    }
    /**
     * Returns the web publication date of the article.
     */
    public Date getDate() {
        return mDate;
    }

    /**
     * Returns the website URL to find more information about the news.
     */
    public String getUrl() {
        return mUrl;
    }

    /**
     * Returns the TrailText of the article with string type Html
     */
    public String getTrailTextHtml() {
        return mTrailTextHtml;
    }
}
