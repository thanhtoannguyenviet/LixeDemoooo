package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "serifilm_film", schema = "public", catalog = "test12345")
public class SerifilmFilmEntity {
    private long id;
    private String name;
    private String ext;

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
    @Column(name = "name", nullable = true, length = 250)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "ext", nullable = true, length = -1)
    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SerifilmFilmEntity that = (SerifilmFilmEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(ext, that.ext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ext);
    }
}
