package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categoryfilm", schema = "public", catalog = "test12345")
public class CategoryfilmEntity {
    private long id;
    private String categoryfilm;
    private int range;

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
    @Column(name = "categoryfilm", nullable = false, length = 255)
    public String getCategoryfilm() {
        return categoryfilm;
    }

    public void setCategoryfilm(String categoryfilm) {
        this.categoryfilm = categoryfilm;
    }

    @Basic
    @Column(name = "range", nullable = false)
    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryfilmEntity that = (CategoryfilmEntity) o;
        return id == that.id &&
                range == that.range &&
                Objects.equals(categoryfilm, that.categoryfilm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryfilm, range);
    }
}
