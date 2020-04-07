package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "seri_categoryfilm", schema = "public", catalog = "test12345")
public class SeriCategoryfilmEntity {
    private long id;
    private long categoryid;
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
    @Column(name = "categoryid", nullable = false)
    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    @Basic
    @Column(name = "seriid", nullable = false)
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
        SeriCategoryfilmEntity that = (SeriCategoryfilmEntity) o;
        return id == that.id &&
                categoryid == that.categoryid &&
                seriid == that.seriid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryid, seriid);
    }
}
