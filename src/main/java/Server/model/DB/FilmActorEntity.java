package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Film_Actor", schema = "public", catalog = "ProjectMusicFilm")
public class FilmActorEntity {
    private long id;
    private Long actorid;
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
    @Column(name = "actorid", nullable = true)
    public Long getActorid() {
        return actorid;
    }

    public void setActorid(Long actorid) {
        this.actorid = actorid;
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
        FilmActorEntity that = (FilmActorEntity) o;
        return id == that.id &&
                Objects.equals(actorid, that.actorid) &&
                Objects.equals(filmid, that.filmid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actorid, filmid);
    }
}
