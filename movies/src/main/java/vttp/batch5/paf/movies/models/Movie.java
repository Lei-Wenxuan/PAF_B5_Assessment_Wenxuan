package vttp.batch5.paf.movies.models;

import java.util.Date;

public class Movie {
    
    private String imdbId = "";
    private float voteAverage = 0f;
    private int voteCount = 0;
    private Date releaseDate;
    private float revenue = 0f;
    private float budget = 0f;
    private int runtime = 0;
    private String title = "";
    private String director = "";
    private String overview = "";
    private String tagline = "";
    private String genres = "";
    private int imdbRating = 0;
    private int imdbVotes = 0;

    public String getImdbId() {
        return imdbId;
    }
    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }
    public float getVoteAverage() {
        return voteAverage;
    }
    public void setVoteAverage(float voteAverage) {
        this.voteAverage = voteAverage;
    }
    public int getVoteCount() {
        return voteCount;
    }
    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
    public float getRevenue() {
        return revenue;
    }
    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }
    public float getBudget() {
        return budget;
    }
    public void setBudget(float budget) {
        this.budget = budget;
    }
    public int getRuntime() {
        return runtime;
    }
    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public String getOverview() {
        return overview;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }
    public String getTagline() {
        return tagline;
    }
    public void setTagline(String tagline) {
        this.tagline = tagline;
    }
    public String getGenres() {
        return genres;
    }
    public void setGenres(String genres) {
        this.genres = genres;
    }
    public int getImdbRating() {
        return imdbRating;
    }
    public void setImdbRating(int imdbRating) {
        this.imdbRating = imdbRating;
    }
    public int getImdbVotes() {
        return imdbVotes;
    }
    public void setImdbVotes(int imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

}
