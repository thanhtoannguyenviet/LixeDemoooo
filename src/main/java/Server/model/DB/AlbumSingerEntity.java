package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Album_Singer", schema = "dbo", catalog = "ProjectMusicFilm")
public class AlbumSingerEntity {
    private long id;
    private Long albumid;
    private Long singerid;

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
    @Column(name = "albumid", nullable = true)
    public Long getAlbumid() {
        return albumid;
    }

    public void setAlbumid(Long albumid) {
        this.albumid = albumid;
    }

    @Basic
    @Column(name = "singerid", nullable = true)
    public Long getSingerid() {
        return singerid;
    }

    public void setSingerid(Long singerid) {
        this.singerid = singerid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumSingerEntity that = (AlbumSingerEntity) o;
        return id == that.id &&
                Objects.equals(albumid, that.albumid) &&
                Objects.equals(singerid, that.singerid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumid, singerid);
    }
}
