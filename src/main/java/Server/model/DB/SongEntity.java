package Server.model.DB;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Song", schema = "public", catalog = "ProjectMusicFilm")
public class SongEntity {
    private long id;
    private String songName;
    private Long authorid;
    private Timestamp createdate;
    private String modifiedUser;
    private Timestamp modifieddate;
    private Boolean active;
    private String uploadsource;
    private String img;
    private Long albumid;

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
    @Column(name = "songName", nullable = true, length = 255)
    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
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
    @Column(name = "createdate", nullable = true)
    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    @Basic
    @Column(name = "modifiedUser", nullable = true, length = 255)
    public String getModifiedUser() {
        return modifiedUser;
    }

    public void setModifiedUser(String modifiedUser) {
        this.modifiedUser = modifiedUser;
    }

    @Basic
    @Column(name = "modifieddate", nullable = true)
    public Timestamp getModifieddate() {
        return modifieddate;
    }

    public void setModifieddate(Timestamp modifieddate) {
        this.modifieddate = modifieddate;
    }

    @Basic
    @Column(name = "active", nullable = true)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "uploadsource", nullable = true, length = -1)
    public String getUploadsource() {
        return uploadsource;
    }

    public void setUploadsource(String uploadsource) {
        this.uploadsource = uploadsource;
    }

    @Basic
    @Column(name = "img", nullable = true, length = -1)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "albumid", nullable = true)
    public Long getAlbumid() {
        return albumid;
    }

    public void setAlbumid(Long albumid) {
        this.albumid = albumid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SongEntity that = (SongEntity) o;
        return id == that.id &&
                Objects.equals(songName, that.songName) &&
                Objects.equals(authorid, that.authorid) &&
                Objects.equals(createdate, that.createdate) &&
                Objects.equals(modifiedUser, that.modifiedUser) &&
                Objects.equals(modifieddate, that.modifieddate) &&
                Objects.equals(active, that.active) &&
                Objects.equals(uploadsource, that.uploadsource) &&
                Objects.equals(img, that.img) &&
                Objects.equals(albumid, that.albumid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, songName, authorid, createdate, modifiedUser, modifieddate, active, uploadsource, img, albumid);
    }
}
