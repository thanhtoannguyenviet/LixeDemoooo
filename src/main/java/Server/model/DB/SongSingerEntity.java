package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Song_Singer", schema = "dbo", catalog = "ProjectMusicFilm")
public class SongSingerEntity {
    private long id;
    private long songid;
    private long singerid;

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
    @Column(name = "songid", nullable = false)
    public long getSongid() {
        return songid;
    }

    public void setSongid(long songid) {
        this.songid = songid;
    }

    @Basic
    @Column(name = "singerid", nullable = false)
    public long getSingerid() {
        return singerid;
    }

    public void setSingerid(long singerid) {
        this.singerid = singerid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongSingerEntity that = (SongSingerEntity) o;
        return id == that.id &&
                songid == that.songid &&
                singerid == that.singerid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, songid, singerid);
    }
}
