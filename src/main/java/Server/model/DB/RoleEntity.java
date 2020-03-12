package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Role_", schema = "public", catalog = "ProjectMusicFilm")
public class RoleEntity {
    private long id;
    private String roleName;
    private String scope;

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
    @Column(name = "roleName", nullable = true, length = 255)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "scope", nullable = true, length = -1)
    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return id == that.id &&
                Objects.equals(roleName, that.roleName) &&
                Objects.equals(scope, that.scope);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roleName, scope);
    }
}
