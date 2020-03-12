package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Film_CategoryFilm", schema = "public", catalog = "ProjectMusicFilm")
public class FilmCategoryFilmEntity {
    private long id;
    private Long filmid;
    private Long categoryid;

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
    public Long getFilmid() {
        return filmid;
    }

    public void setFilmid(Long filmid) {
        this.filmid = filmid;
    }

    @Basic
    @Column(name = "categoryid", nullable = true)
    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmCategoryFilmEntity that = (FilmCategoryFilmEntity) o;
        return id == that.id &&
                Objects.equals(filmid, that.filmid) &&
                Objects.equals(categoryid, that.categoryid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filmid, categoryid);
    }
}
