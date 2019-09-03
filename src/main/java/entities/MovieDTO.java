package entities;

public class MovieDTO {
    private String title;
    private String director;

    public MovieDTO(Movie movie) {
        this.title = movie.getTitle();
        this.director = movie.getDirector();
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
}
