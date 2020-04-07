package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categorysong", schema = "public", catalog = "test12345")
public class CategorysongEntity {
    private long id;
    private String categoryname;
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
    @Column(name = "categoryname", nullable = false, length = 255)
    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
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
        CategorysongEntity that = (CategorysongEntity) o;
        return id == that.id &&
                range == that.range &&
                Objects.equals(categoryname, that.categoryname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryname, range);
    }
}
