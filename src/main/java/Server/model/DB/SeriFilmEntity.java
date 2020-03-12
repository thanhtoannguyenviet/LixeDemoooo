package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "SeriFilm", schema = "public", catalog = "ProjectMusicFilm")
public class SeriFilmEntity {
    private long id;
    private String listfilm;
    private String ext;
    private Long index;
    private Long range;

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
    @Column(name = "listfilm", nullable = true, length = -1)
    public String getListfilm() {
        return listfilm;
    }

    public void setListfilm(String listfilm) {
        this.listfilm = listfilm;
    }

    @Basic
    @Column(name = "ext", nullable = true, length = -1)
    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Basic
    @Column(name = "index", nullable = true)
    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    @Basic
    @Column(name = "range", nullable = true)
    public Long getRange() {
        return range;
    }

    public void setRange(Long range) {
        this.range = range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeriFilmEntity that = (SeriFilmEntity) o;
        return id == that.id &&
                Objects.equals(listfilm, that.listfilm) &&
                Objects.equals(ext, that.ext) &&
                Objects.equals(index, that.index) &&
                Objects.equals(range, that.range);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listfilm, ext, index, range);
    }
}
