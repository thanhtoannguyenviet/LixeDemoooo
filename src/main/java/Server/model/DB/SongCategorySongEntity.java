package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Song_CategorySong", schema = "public", catalog = "ProjectMusicFilm")
public class SongCategorySongEntity {
    private long id;
    private Long songid;
    private Long categoryid;

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
    @Column(name = "categoryid", nullable = true)
    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongCategorySongEntity that = (SongCategorySongEntity) o;
        return id == that.id &&
                Objects.equals(songid, that.songid) &&
                Objects.equals(categoryid, that.categoryid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, songid, categoryid);
    }
}
