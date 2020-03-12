package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Song_Singer", schema = "public", catalog = "ProjectMusicFilm")
public class SongSingerEntity {
    private long id;
    private Long songid;
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
    @Column(name = "songid", nullable = true)
    public Long getSongid() {
        return songid;
    }

    public void setSongid(Long songid) {
        this.songid = songid;
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
        SongSingerEntity that = (SongSingerEntity) o;
        return id == that.id &&
                Objects.equals(songid, that.songid) &&
                Objects.equals(singerid, that.singerid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, songid, singerid);
    }
}
