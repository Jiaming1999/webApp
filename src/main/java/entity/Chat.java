package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;


@Entity
public class Chat {
    private int id;
    private String message;
    private String user;
    private Timestamp date;
    private String movie;
    private Double movieRating;
    private String movieActors;

    public Chat(String message, String user, Timestamp date,String movie,Double movieRating,String movieActors) {
        setMessage(message);
        setUser(user);
        setDate(date);
        setMovie(movie);
        setMovieRating(movieRating);
        setMovieActors(movieActors);
    }

    public Chat(){
        //Date date = new Date(); --can also use-- date.getTime();
        setDate(new Timestamp(System.currentTimeMillis()));
    }

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "MESSAGE")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Basic
    @Column(name = "USER")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Basic
    @Column(name = "DATE")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "MOVIE")
    public String getMovie() {
        return movie;
    }

    public void setMovie(String movie) {
        this.movie = movie;
    }

    @Basic
    @Column(name = "MOVIERATING")
    public Double getMovieRating() {
        return movieRating;
    }

    public void setMovieRating(Double rating) {
        this.movieRating = rating;
    }

    @Basic
    @Column(name = "MOVIEACTORS")
    public String getMovieActors() {
        return movieActors;
    }

    public void setMovieActors(String actors) {
        this.movieActors = actors;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Chat chat = (Chat) o;

        if (id != chat.id) return false;
        if (message != null ? !message.equals(chat.message) : chat.message != null) return false;
        if (user != null ? !user.equals(chat.user) : chat.user != null) return false;
        if (date != null ? !date.equals(chat.date) : chat.date != null) return false;
        if (movie != null ? !movie.equals(chat.movie) : chat.movie != null) return false;
        if (movieRating != null ? !movieRating.equals(chat.movieRating) : chat.movieRating != null) return false;
        if (movieActors != null ? !movieActors.equals(chat.movieActors) : chat.movieActors != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (movie != null ? movie.hashCode() : 0);
        result = 31 * result + (movieRating != null ? movieRating.hashCode() : 0);
        result = 31 * result + (movieActors != null ? movieActors.hashCode() : 0);
        return result;
    }
}
