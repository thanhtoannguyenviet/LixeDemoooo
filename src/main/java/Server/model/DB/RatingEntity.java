package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Rating", schema = "dbo", catalog = "ProjectMusicFilm")
public class RatingEntity {
    private long id;
    private String model;
    private int point;
    private int votelike;
    private int votedislike;
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
    @Column(name = "model", nullable = false, length = 255)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Basic
    @Column(name = "point", nullable = false)
    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Basic
    @Column(name = "votelike", nullable = false)
    public int getVotelike() {
        return votelike;
    }

    public void setVotelike(int votelike) {
        this.votelike = votelike;
    }

    @Basic
    @Column(name = "votedislike", nullable = false)
    public int getVotedislike() {
        return votedislike;
    }

    public void setVotedislike(int votedislike) {
        this.votedislike = votedislike;
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
        RatingEntity that = (RatingEntity) o;
        return id == that.id &&
                point == that.point &&
                votelike == that.votelike &&
                votedislike == that.votedislike &&
                entryid == that.entryid &&
                Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, point, votelike, votedislike, entryid);
    }
}
