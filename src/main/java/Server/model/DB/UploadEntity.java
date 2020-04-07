package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "upload", schema = "public", catalog = "test12345")
public class UploadEntity {
    private long id;
    private String source;
    private String model;
    private long entryid;

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
    @Column(name = "source", nullable = false, length = 500)
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Basic
    @Column(name = "model", nullable = false, length = 255)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "entryid", nullable = false)
    public long getEntryid() {
        return entryid;
    }

    public void setEntryid(long entryid) {
        this.entryid = entryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadEntity that = (UploadEntity) o;
        return id == that.id &&
                entryid == that.entryid &&
                Objects.equals(source, that.source) &&
                Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source, model, entryid);
    }
}
