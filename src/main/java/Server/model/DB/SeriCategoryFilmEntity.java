package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Seri_CategoryFilm", schema = "public", catalog = "ProjectMusicFilm")
public class SeriCategoryFilmEntity {
    private long id;
    private Long categoryid;
    private Long seriid;

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
    @Column(name = "categoryid", nullable = true)
    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    @Basic
    @Column(name = "seriid", nullable = true)
    public Long getSeriid() {
        return seriid;
    }

    public void setSeriid(Long seriid) {
        this.seriid = seriid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeriCategoryFilmEntity that = (SeriCategoryFilmEntity) o;
        return id == that.id &&
                Objects.equals(categoryid, that.categoryid) &&
                Objects.equals(seriid, that.seriid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryid, seriid);
    }
}
