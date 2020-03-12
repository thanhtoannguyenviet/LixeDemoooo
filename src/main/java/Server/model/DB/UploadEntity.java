package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Upload", schema = "public", catalog = "ProjectMusicFilm")
public class UploadEntity {
    private long id;
    private String source;
    private String model;
    private Long entryid;

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
    @Column(name = "source", nullable = true, length = 500)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Basic
    @Column(name = "model", nullable = true, length = 255)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "entryid", nullable = true)
    public Long getEntryid() {
        return entryid;
    }

    public void setEntryid(Long entryid) {
        this.entryid = entryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadEntity that = (UploadEntity) o;
        return id == that.id &&
                Objects.equals(source, that.source) &&
                Objects.equals(model, that.model) &&
                Objects.equals(entryid, that.entryid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, model, entryid);
    }
}
