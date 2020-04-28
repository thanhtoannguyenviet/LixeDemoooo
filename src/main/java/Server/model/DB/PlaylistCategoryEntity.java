package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "playlist_category", schema = "public", catalog = "test12345")
public class PlaylistCategoryEntity {
    private long id;
    private Long playlistid;
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
    @Column(name = "playlistid", nullable = true)
    public Long getPlaylistid() {
        return playlistid;
    }

    public void setPlaylistid(Long playlistid) {
        this.playlistid = playlistid;
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
        PlaylistCategoryEntity that = (PlaylistCategoryEntity) o;
        return id == that.id &&
                Objects.equals(playlistid, that.playlistid) &&
                Objects.equals(categoryid, that.categoryid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playlistid, categoryid);
    }
}
