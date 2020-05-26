package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Serifilm_Film", schema = "dbo", catalog = "ProjectMusicFilm")
public class SerifilmFilmEntity {
    private long id;
    private long filmid;
    private long seriid;

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
    @Column(name = "filmid", nullable = true)
    public long getFilmid() {
        return filmid;
    }

    public void setFilmid(long name) {
        this.filmid = name;
    }

    @Basic
    @Column(name = "seriid", nullable = true)
    public long getSeriid() {
        return seriid;
    }

    public void setSeriid(long seriid) {
        this.seriid = seriid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerifilmFilmEntity that = (SerifilmFilmEntity) o;
        return id == that.id &&
                Objects.equals(seriid, that.seriid) &&
                Objects.equals(filmid, that.filmid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seriid, filmid);
    }
}
