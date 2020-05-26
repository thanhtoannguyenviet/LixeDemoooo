package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Album_Categorymusic", schema = "dbo", catalog = "ProjectMusicFilm")
public class AlbumCategorymusicEntity {
    private long id;
    private long albumid;
    private long catagoryid;

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
    @Column(name = "albumid", nullable = false)
    public long getAlbumid() {
        return albumid;
    }

    public void setAlbumid(long albumid) {
        this.albumid = albumid;
    }

    @Basic
    @Column(name = "catagoryid", nullable = false)
    public long getCatagoryid() {
        return catagoryid;
    }

    public void setCatagoryid(long catagoryid) {
        this.catagoryid = catagoryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumCategorymusicEntity that = (AlbumCategorymusicEntity) o;
        return id == that.id &&
                albumid == that.albumid &&
                catagoryid == that.catagoryid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumid, catagoryid);
    }
}
