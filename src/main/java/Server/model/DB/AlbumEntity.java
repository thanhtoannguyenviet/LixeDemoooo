package Server.model.DB;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "album", schema = "public", catalog = "test12345")
public class AlbumEntity {
    private long id;
    private String albumname;
    private String listsongid;
    private Timestamp datereleased;
    private int index;
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
    @Column(name = "albumname", nullable = false, length = 255)
    public String getAlbumname() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname = albumname;
    }

    @Basic
    @Column(name = "listsongid", nullable = false, length = -1)
    public String getListsongid() {
        return listsongid;
    }

    public void setListsongid(String listsongid) {
        this.listsongid = listsongid;
    }

    @Basic
    @Column(name = "datereleased", nullable = false)
    public Timestamp getDatereleased() {
        return datereleased;
    }

    public void setDatereleased(Timestamp datereleased) {
        this.datereleased = datereleased;
    }

    @Basic
    @Column(name = "index", nullable = false)
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
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
        AlbumEntity that = (AlbumEntity) o;
        return id == that.id &&
                index == that.index &&
                range == that.range &&
                Objects.equals(albumname, that.albumname) &&
                Objects.equals(listsongid, that.listsongid) &&
                Objects.equals(datereleased, that.datereleased);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumname, listsongid, datereleased, index, range);
    }
}
