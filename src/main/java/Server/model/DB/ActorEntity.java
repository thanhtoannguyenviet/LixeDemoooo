package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Actor", schema = "public", catalog = "ProjectMusicFilm")
public class ActorEntity {
    private long id;
    private String actorName;
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
    @Column(name = "actorName", nullable = true, length = 255)
    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
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
        ActorEntity that = (ActorEntity) o;
        return id == that.id &&
                Objects.equals(actorName, that.actorName) &&
                Objects.equals(ext, that.ext);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, actorName, ext);
    }
}
