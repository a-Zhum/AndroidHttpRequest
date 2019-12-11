package fr.zhum.lp3tp23;

// POJO MOVIE
public class Movie {

    private String idMovie;
    private String title;
    private String year;

    public Movie(String idMovie, String title, String year) {
        this.idMovie = idMovie;
        this.title = title;
        this.year = year;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
