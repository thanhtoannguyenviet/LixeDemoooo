package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "author_song", schema = "public", catalog = "test12345")
public class AuthorSongEntity {
    private long id;
    private Long authorid;
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
    @Column(name = "authorid", nullable = true)
    public Long getAuthorid() {
        return authorid;
    }

    public void setAuthorid(Long authorid) {
        this.authorid = authorid;
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
        AuthorSongEntity that = (AuthorSongEntity) o;
        return id == that.id &&
                Objects.equals(authorid, that.authorid) &&
                Objects.equals(songid, that.songid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorid, songid);
    }
}
