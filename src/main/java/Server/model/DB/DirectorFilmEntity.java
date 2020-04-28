package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "director_film", schema = "public", catalog = "test12345")
public class DirectorFilmEntity {
    private long id;
    private Long directorid;
    private Long filmid;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "directorid", nullable = true)
    public Long getDirectorid() {
        return directorid;
    }

    public void setDirectorid(Long directorid) {
        this.directorid = directorid;
    }

    @Basic
    @Column(name = "filmid", nullable = true)
    public Long getFilmid() {
        return filmid;
    }

    public void setFilmid(Long filmid) {
        this.filmid = filmid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectorFilmEntity that = (DirectorFilmEntity) o;
        return id == that.id &&
                Objects.equals(directorid, that.directorid) &&
                Objects.equals(filmid, that.filmid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, directorid, filmid);
    }
}
