package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Moviehistory {
    private int id;
    private String user;
    private String movietitle;
    private Double movierating;
    private String movieactors;
    private Timestamp date;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "MOVIETITLE")
    public String getMovietitle() {
        return movietitle;
    }

    public void setMovietitle(String movietitle) {
        this.movietitle = movietitle;
    }

    @Basic
    @Column(name = "MOVIERATING")
    public Double getMovierating() {
        return movierating;
    }

    public void setMovierating(Double movierating) {
        this.movierating = movierating;
    }

    @Basic
    @Column(name = "MOVIEACTORS")
    public String getMovieactors() {
        return movieactors;
    }

    public void setMovieactors(String movieactors) {
        this.movieactors = movieactors;
    }

    @Basic
    @Column(name = "DATE")
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Moviehistory that = (Moviehistory) o;

        if (id != that.id) return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        if (movietitle != null ? !movietitle.equals(that.movietitle) : that.movietitle != null) return false;
        if (movierating != null ? !movierating.equals(that.movierating) : that.movierating != null) return false;
        if (movieactors != null ? !movieactors.equals(that.movieactors) : that.movieactors != null) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (movietitle != null ? movietitle.hashCode() : 0);
        result = 31 * result + (movierating != null ? movierating.hashCode() : 0);
        result = 31 * result + (movieactors != null ? movieactors.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
