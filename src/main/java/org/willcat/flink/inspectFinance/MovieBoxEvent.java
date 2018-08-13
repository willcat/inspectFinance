package org.willcat.flink.inspectFinance;

/**
 * @author Administrator
 */
public class MovieBoxEvent {

    public Double getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(Double boxOffice) {
        this.boxOffice = boxOffice;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Double getBoxPer() {
        return boxPer;
    }

    public void setBoxPer(Double boxPer) {
        this.boxPer = boxPer;
    }

    public int getMovieDay() {
        return movieDay;
    }

    public void setMovieDay(int movieDay) {
        this.movieDay = movieDay;
    }

    public Double getSumBoxOffice() {
        return sumBoxOffice;
    }

    public void setSumBoxOffice(Double sumBoxOffice) {
        this.sumBoxOffice = sumBoxOffice;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }


    private Double boxOffice;
    private int rank;
    private String movieName;
    private Double boxPer;
    private int movieDay;
    private Double sumBoxOffice;
    private Long timeStamp;
}
