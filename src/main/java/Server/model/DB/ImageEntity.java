package Server.model.DB;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "image", schema = "public", catalog = "test12345")
public class ImageEntity {
    private long id;
    private String path;
    private BigInteger size;
    private String fileextension;
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
    @Column(name = "path", nullable = false, length = 255)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "size", nullable = false, precision = 0)
    public BigInteger getSize() {
        return size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }

    @Basic
    @Column(name = "fileextension", nullable = false, length = 50)
    public String getFileextension() {
        return fileextension;
    }

    public void setFileextension(String fileextension) {
        this.fileextension = fileextension;
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
        ImageEntity that = (ImageEntity) o;
        return id == that.id &&
                entryid == that.entryid &&
                Objects.equals(path, that.path) &&
                Objects.equals(size, that.size) &&
                Objects.equals(fileextension, that.fileextension) &&
                Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, size, fileextension, model, entryid);
    }
}
