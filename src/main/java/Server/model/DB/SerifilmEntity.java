package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "serifilm", schema = "public", catalog = "test12345")
public class SerifilmEntity {
    private long id;
    private String listfilm;
    private String ext;
    private long index;
    private long range;

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
    @Column(name = "listfilm", nullable = false, length = -1)
    public String getListfilm() {
        return listfilm;
    }

    public void setListfilm(String listfilm) {
        this.listfilm = listfilm;
    }

    @Basic
    @Column(name = "ext", nullable = false, length = -1)
    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Basic
    @Column(name = "index", nullable = false)
    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    @Basic
    @Column(name = "range", nullable = false)
    public long getRange() {
        return range;
    }

    public void setRange(long range) {
        this.range = range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerifilmEntity that = (SerifilmEntity) o;
        return id == that.id &&
                index == that.index &&
                range == that.range &&
                Objects.equals(listfilm, that.listfilm) &&
                Objects.equals(ext, that.ext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, listfilm, ext, index, range);
    }
}
