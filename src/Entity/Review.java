package src.Entity;

import java.io.Serializable;

public class Review implements Serializable{
    private String statement;

    private Integer rate;

    private MovieGoer movieGoer;


    public Review(String statement, Integer rate, MovieGoer movieGoer) {
        this.statement = statement;
        this.rate = rate;
        this.movieGoer = movieGoer;
    }
    
    public String getStatement() {
        return this.statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public Integer getRate() {
        return this.rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public MovieGoer getMovieGoer() {
        return this.movieGoer;
    }

    public void setMovieGoer(MovieGoer movieGoer) {
        this.movieGoer = movieGoer;
    }
}
