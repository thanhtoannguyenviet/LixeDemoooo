package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Singer", schema = "public", catalog = "ProjectMusicFilm")
public class SingerEntity {
    private long id;
    private String singerName;
    private String info;
    private String img;
    private Boolean active;

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
    @Column(name = "singerName", nullable = true, length = 255)
    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    @Basic
    @Column(name = "info", nullable = true, length = -1)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
    @Column(name = "active", nullable = true)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingerEntity that = (SingerEntity) o;
        return id == that.id &&
                Objects.equals(singerName, that.singerName) &&
                Objects.equals(info, that.info) &&
                Objects.equals(img, that.img) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, singerName, info, img, active);
    }
}
