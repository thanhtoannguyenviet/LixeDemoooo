package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Album_CategoryMusic", schema = "public", catalog = "ProjectMusicFilm")
public class AlbumCategoryMusicEntity {
    private long id;
    private Long albumid;
    private Long catagoryid;

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
    @Column(name = "catagoryid", nullable = true)
    public Long getCatagoryid() {
        return catagoryid;
    }

    public void setCatagoryid(Long catagoryid) {
        this.catagoryid = catagoryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumCategoryMusicEntity that = (AlbumCategoryMusicEntity) o;
        return id == that.id &&
                Objects.equals(albumid, that.albumid) &&
                Objects.equals(catagoryid, that.catagoryid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumid, catagoryid);
    }
}
