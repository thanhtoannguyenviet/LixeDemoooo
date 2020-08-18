
package Server.model.DB;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "User_", schema = "dbo", catalog = "ProjectMusicFilm")
public class UserEntity {
    private long id;
    private String username;
    private String password;
    private String email;
    private String ext;
    private String follow;
    private long roleid;
    private String img;
    private String displayname;
    private Boolean active;
    private Timestamp createDate;
    private long createUser;
    private Timestamp updateDate;
    private long updateUser;
    private String userWebToken;
    private Timestamp webTokenCreateDate;
    private String userMbToken;
    private Timestamp mbTokenCreateDate;

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
    @Column(name = "username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "ext", nullable = false, length = -1)
    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    @Basic
    @Column(name = "follow", nullable = false, length = -1)
    public String getFollow() {
        return follow;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    @Basic
    @Column(name = "roleid", nullable = false)
    public long getRoleid() {
        return roleid;
    }

    public void setRoleid(long roleid) {
        this.roleid = roleid;
    }

    @Basic
    @Column(name = "img", nullable = false, length = -1)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Basic
    @Column(name = "displayname", nullable = false, length = 255)
    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    @Basic
    @Column(name = "active", nullable = true)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Basic
    @Column(name = "createDate", nullable = true)
    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "createUser", nullable = true)
    public long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(long createUser) {
        this.createUser = createUser;
    }

    @Basic
    @Column(name = "updateDate", nullable = true)
    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    @Basic
    @Column(name = "updateUser", nullable = true)
    public long getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(long updateUser) {
        this.updateUser = updateUser;
    }

    @Basic
    @Column(name = "userWebToken", nullable = true, length = 35)
    public String getUserWebToken() {
        return userWebToken;
    }

    public void setUserWebToken(String userWebToken) {
        this.userWebToken = userWebToken;
    }

    @Basic
    @Column(name = "webTokenCreateDate", nullable = true)
    public Timestamp getWebTokenCreateDate() {
        return webTokenCreateDate;
    }

    public void setWebTokenCreateDate(Timestamp webTokenCreateDate) {
        this.webTokenCreateDate = webTokenCreateDate;
    }

    @Basic
    @Column(name = "userMbToken", nullable = true, length = 35)
    public String getUserMbToken() {
        return userMbToken;
    }

    public void setUserMbToken(String userMbToken) {
        this.userMbToken = userMbToken;
    }

    @Basic
    @Column(name = "mbTokenCreateDate", nullable = true)
    public Timestamp getMbTokenCreateDate() {
        return mbTokenCreateDate;
    }

    public void setMbTokenCreateDate(Timestamp mbTokenCreateDate) {
        this.mbTokenCreateDate = mbTokenCreateDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                roleid == that.roleid &&
                createUser == that.createUser &&
                updateUser == that.updateUser &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(ext, that.ext) &&
                Objects.equals(follow, that.follow) &&
                Objects.equals(img, that.img) &&
                Objects.equals(displayname, that.displayname) &&
                Objects.equals(active, that.active) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(updateDate, that.updateDate) &&
                Objects.equals(userWebToken, that.userWebToken) &&
                Objects.equals(webTokenCreateDate, that.webTokenCreateDate) &&
                Objects.equals(userMbToken, that.userMbToken) &&
                Objects.equals(mbTokenCreateDate, that.mbTokenCreateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, ext, follow, roleid, img, displayname, active, createDate, createUser, updateDate, updateUser, userWebToken, webTokenCreateDate, userMbToken, mbTokenCreateDate);
    }

}