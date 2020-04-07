package Server.model.DB;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "comment", schema = "public", catalog = "test12345")
public class CommentEntity {
    private long id;
    private String content;
    private Timestamp commentdate;
    private String model;
    private long entryid;
    private String usernamecomment;

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
    @Column(name = "content", nullable = false, length = 500)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "commentdate", nullable = false)
    public Timestamp getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Timestamp commentdate) {
        this.commentdate = commentdate;
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

    @Basic
    @Column(name = "usernamecomment", nullable = false, length = 255)
    public String getUsernamecomment() {
        return usernamecomment;
    }

    public void setUsernamecomment(String usernamecomment) {
        this.usernamecomment = usernamecomment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return id == that.id &&
                entryid == that.entryid &&
                Objects.equals(content, that.content) &&
                Objects.equals(commentdate, that.commentdate) &&
                Objects.equals(model, that.model) &&
                Objects.equals(usernamecomment, that.usernamecomment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, commentdate, model, entryid, usernamecomment);
    }
}
