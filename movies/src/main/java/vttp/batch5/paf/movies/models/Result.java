package vttp.batch5.paf.movies.models;

import java.util.List;

public class Result {
    
    private String director;
    private int count;
    private List<String> movieIds;
    private float totalRevenue;
    private float totalBudget;

    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    public List<String> getMovieIds() {
        return movieIds;
    }
    public void setMovieIds(List<String> movieIds) {
        this.movieIds = movieIds;
    }
    public float getTotalRevenue() {
        return totalRevenue;
    }
    public void setTotalRevenue(float totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
    public float getTotalBudget() {
        return totalBudget;
    }
    public void setTotalBudget(float totalBudget) {
        this.totalBudget = totalBudget;
    }
    
}
