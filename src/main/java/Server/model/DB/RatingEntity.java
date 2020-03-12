package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Rating", schema = "public", catalog = "ProjectMusicFilm")
public class RatingEntity {
    private long id;
    private String model;
    private Integer point;
    private Integer votelike;
    private Integer votedislike;
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
    @Column(name = "model", nullable = true, length = 255)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "point", nullable = true)
    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Basic
    @Column(name = "votelike", nullable = true)
    public Integer getVotelike() {
        return votelike;
    }

    public void setVotelike(Integer votelike) {
        this.votelike = votelike;
    }

    @Basic
    @Column(name = "votedislike", nullable = true)
    public Integer getVotedislike() {
        return votedislike;
    }

    public void setVotedislike(Integer votedislike) {
        this.votedislike = votedislike;
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
        RatingEntity that = (RatingEntity) o;
        return id == that.id &&
                Objects.equals(model, that.model) &&
                Objects.equals(point, that.point) &&
                Objects.equals(votelike, that.votelike) &&
                Objects.equals(votedislike, that.votedislike) &&
                Objects.equals(entryid, that.entryid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, point, votelike, votedislike, entryid);
    }
}
