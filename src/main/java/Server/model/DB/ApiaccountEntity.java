package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "APIAccount", schema = "dbo", catalog = "ProjectMusicFilm")
public class ApiaccountEntity {
    private long id;
    private String token;
    private int type;

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
    @Column(name = "token", nullable = false, length = 35)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiaccountEntity that = (ApiaccountEntity) o;
        return id == that.id &&
                type == that.type &&
                Objects.equals(token, that.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, type);
    }
}
