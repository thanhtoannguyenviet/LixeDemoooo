package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Album_Song", schema = "dbo", catalog = "ProjectMusicFilm")
public class AlbumSongEntity {
    private long id;
    private Long albumid;
    private Long songid;

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
    @Column(name = "songid", nullable = true)
    public Long getSongid() {
        return songid;
    }

    public void setSongid(Long songid) {
        this.songid = songid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumSongEntity that = (AlbumSongEntity) o;
        return id == that.id &&
                Objects.equals(albumid, that.albumid) &&
                Objects.equals(songid, that.songid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, albumid, songid);
    }
}
