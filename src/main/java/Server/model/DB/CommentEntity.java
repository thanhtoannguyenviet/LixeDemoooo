package Server.model.DB;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Comment", schema = "public", catalog = "ProjectMusicFilm")
public class CommentEntity {
    private long id;
    private String content;
    private Timestamp commentdate;
    private String model;
    private Long entryid;
    private String userNameComment;

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
    @Column(name = "content", nullable = true, length = 500)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "commentdate", nullable = true)
    public Timestamp getCommentdate() {
        return commentdate;
    }

    public void setCommentdate(Timestamp commentdate) {
        this.commentdate = commentdate;
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

    @Basic
    @Column(name = "userNameComment", nullable = true, length = 255)
    public String getUserNameComment() {
        return userNameComment;
    }

    public void setUserNameComment(String userNameComment) {
        this.userNameComment = userNameComment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentEntity that = (CommentEntity) o;
        return id == that.id &&
                Objects.equals(content, that.content) &&
                Objects.equals(commentdate, that.commentdate) &&
                Objects.equals(model, that.model) &&
                Objects.equals(entryid, that.entryid) &&
                Objects.equals(userNameComment, that.userNameComment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, commentdate, model, entryid, userNameComment);
    }
}
