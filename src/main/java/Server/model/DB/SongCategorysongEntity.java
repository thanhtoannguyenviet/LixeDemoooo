package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "song_categorysong", schema = "public", catalog = "test12345")
public class SongCategorysongEntity {
    private long id;
    private long songid;
    private long categoryid;

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
    @Column(name = "categoryid", nullable = false)
    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongCategorysongEntity that = (SongCategorysongEntity) o;
        return id == that.id &&
                songid == that.songid &&
                categoryid == that.categoryid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, songid, categoryid);
    }
}
