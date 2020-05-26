package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Film_Actor", schema = "dbo", catalog = "ProjectMusicFilm")
public class FilmActorEntity {
    private long id;
    private long actorid;
    private long filmid;

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
    @Column(name = "actorid", nullable = false)
    public long getActorid() {
        return actorid;
    }

    public void setActorid(long actorid) {
        this.actorid = actorid;
    }

    @Basic
    @Column(name = "filmid", nullable = false)
    public long getFilmid() {
        return filmid;
    }

    public void setFilmid(long filmid) {
        this.filmid = filmid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmActorEntity that = (FilmActorEntity) o;
        return id == that.id &&
                actorid == that.actorid &&
                filmid == that.filmid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actorid, filmid);
    }
}
