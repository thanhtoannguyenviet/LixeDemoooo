package Server.model.DB;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "Image", schema = "public", catalog = "ProjectMusicFilm")
public class ImageEntity {
    private long id;
    private String path;
    private BigInteger size;
    private String fileExtension;
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
    @Column(name = "path", nullable = true, length = 255)
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Basic
    @Column(name = "size", nullable = true, precision = 0)
    public BigInteger getSize() {
        return size;
    }

    public void setSize(BigInteger size) {
        this.size = size;
    }

    @Basic
    @Column(name = "fileExtension", nullable = true, length = 50)
    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
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
        ImageEntity that = (ImageEntity) o;
        return id == that.id &&
                Objects.equals(path, that.path) &&
                Objects.equals(size, that.size) &&
                Objects.equals(fileExtension, that.fileExtension) &&
                Objects.equals(model, that.model) &&
                Objects.equals(entryid, that.entryid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, path, size, fileExtension, model, entryid);
    }
}
