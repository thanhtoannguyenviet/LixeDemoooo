package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Film_Categoryfilm", schema = "dbo", catalog = "ProjectMusicFilm")
public class FilmCategoryfilmEntity {
    private long id;
    private long filmid;
    private long categoryid;

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
    @Column(name = "filmid", nullable = false)
    public long getFilmid() {
        return filmid;
    }

    public void setFilmid(long filmid) {
        this.filmid = filmid;
    }

    @Basic
    @Column(name = "categoryid", nullable = false)
    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCategoryfilmEntity that = (FilmCategoryfilmEntity) o;
        return id == that.id &&
                filmid == that.filmid &&
                categoryid == that.categoryid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filmid, categoryid);
    }
}
