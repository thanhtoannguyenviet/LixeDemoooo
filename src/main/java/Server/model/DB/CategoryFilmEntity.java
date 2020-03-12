package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CategoryFilm", schema = "public", catalog = "ProjectMusicFilm")
public class CategoryFilmEntity {
    private long id;
    private String categoryFilm;
    private Integer range;

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
    @Column(name = "categoryFilm", nullable = true, length = 255)
    public String getCategoryFilm() {
        return categoryFilm;
    }

    public void setCategoryFilm(String categoryFilm) {
        this.categoryFilm = categoryFilm;
    }

    @Basic
    @Column(name = "range", nullable = true)
    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryFilmEntity that = (CategoryFilmEntity) o;
        return id == that.id &&
                Objects.equals(categoryFilm, that.categoryFilm) &&
                Objects.equals(range, that.range);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryFilm, range);
    }
}
