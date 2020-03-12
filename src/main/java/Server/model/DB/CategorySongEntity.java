package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "CategorySong", schema = "public", catalog = "ProjectMusicFilm")
public class CategorySongEntity {
    private long id;
    private String categoryName;
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
    @Column(name = "categoryName", nullable = true, length = 255)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
        CategorySongEntity that = (CategorySongEntity) o;
        return id == that.id &&
                Objects.equals(categoryName, that.categoryName) &&
                Objects.equals(range, that.range);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categoryName, range);
    }
}
