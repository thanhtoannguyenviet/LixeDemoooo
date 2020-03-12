package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "APIAccount", schema = "public", catalog = "ProjectMusicFilm")
public class ApiAccountEntity {
    private long id;
    private String token;
    private Integer type;

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
    @Column(name = "token", nullable = true, length = 35)
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApiAccountEntity that = (ApiAccountEntity) o;
        return id == that.id &&
                Objects.equals(token, that.token) &&
                Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, token, type);
    }
}
