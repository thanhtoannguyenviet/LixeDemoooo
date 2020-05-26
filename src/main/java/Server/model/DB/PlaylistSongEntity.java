package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Playlist_Song", schema = "dbo", catalog = "ProjectMusicFilm")
public class PlaylistSongEntity {
    private long id;
    private Long playlistid;
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
    @Column(name = "playlistid", nullable = true)
    public Long getPlaylistid() {
        return playlistid;
    }

    public void setPlaylistid(Long playlistid) {
        this.playlistid = playlistid;
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
        PlaylistSongEntity that = (PlaylistSongEntity) o;
        return id == that.id &&
                Objects.equals(playlistid, that.playlistid) &&
                Objects.equals(songid, that.songid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, playlistid, songid);
    }
}
