package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "banner", schema = "public", catalog = "test12345")
public class BannerEntity {
    private long id;
    private String imagename;
    private String idlink;

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
    @Column(name = "imagename", nullable = true, length = -1)
    public String getImagename() {
        return imagename;
    }

    public void setImagename(String imagename) {
        this.imagename = imagename;
    }

    @Basic
    @Column(name = "idlink", nullable = true, length = -1)
    public String getIdlink() {
        return idlink;
    }

    public void setIdlink(String idlink) {
        this.idlink = idlink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BannerEntity that = (BannerEntity) o;
        return id == that.id &&
                Objects.equals(imagename, that.imagename) &&
                Objects.equals(idlink, that.idlink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imagename, idlink);
    }
}
