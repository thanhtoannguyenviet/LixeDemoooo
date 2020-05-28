package Server.model.DB;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Album", schema = "dbo", catalog = "ProjectMusicFilm")
public class AlbumEntity {
    private long id;
    private String albumname;
    private Timestamp datereleased;
    private int index_;
    private int range;
    private int singerid;

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
    @Column(name = "datereleased", nullable = false)
    public Timestamp getDatereleased() {
        return datereleased;
    }

    public void setDatereleased(Timestamp datereleased) {
        this.datereleased = datereleased;
    }

    @Basic
    @Column(name = "index_", nullable = false)
    public int getIndex() {
        return index_;
    }

    public void setIndex(int index) {
        this.index_ = index;
    }

    @Basic
    @Column(name = "range", nullable = false)
    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Basic
    @Column(name = "singerid", nullable = false)
    public int getSingerid() {
        return singerid;
    }

    public void setSingerid(int singerid) {
        this.singerid = singerid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumEntity that = (AlbumEntity) o;
        return id == that.id &&
                index_ == that.index_ &&
                range == that.range &&
                singerid == that.singerid &&
                Objects.equals(albumname, that.albumname) &&
                Objects.equals(datereleased, that.datereleased);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumname, datereleased, index_, range, singerid);
    }
}
