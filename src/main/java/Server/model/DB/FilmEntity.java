package Server.model.DB;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "Film", schema = "public", catalog = "ProjectMusicFilm")
public class FilmEntity {
    private long id;
    private String filmName;
    private String country;
    private Long directorid;
    private Integer yearreleased;
    private String uploadsource;
    private String img;
    private Boolean active;
    private Timestamp createdate;
    private Integer length;
    private String info;
    private Long actorid;
    private Integer index;
    private Integer range;

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
    @Column(name = "filmName", nullable = true, length = 255)
    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    @Basic
    @Column(name = "country", nullable = true, length = 255)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "directorid", nullable = true)
    public Long getDirectorid() {
        return directorid;
    }

    public void setDirectorid(Long directorid) {
        this.directorid = directorid;
    }

    @Basic
    @Column(name = "yearreleased", nullable = true)
    public Integer getYearreleased() {
        return yearreleased;
    }

    public void setYearreleased(Integer yearreleased) {
        this.yearreleased = yearreleased;
    }

    @Basic
    @Column(name = "uploadsource", nullable = true, length = -1)
    public String getUploadsource() {
        return uploadsource;
    }

    public void setUploadsource(String uploadsource) {
        this.uploadsource = uploadsource;
    }

    @Basic
    @Column(name = "img", nullable = true, length = -1)
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
    @Column(name = "createdate", nullable = true)
    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    @Basic
    @Column(name = "length", nullable = true)
    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    @Basic
    @Column(name = "info", nullable = true, length = -1)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "actorid", nullable = true)
    public Long getActorid() {
        return actorid;
    }

    public void setActorid(Long actorid) {
        this.actorid = actorid;
    }

    @Basic
    @Column(name = "index", nullable = true)
    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Basic
    @Column(name = "range", nullable = true)
    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity that = (FilmEntity) o;
        return id == that.id &&
                Objects.equals(filmName, that.filmName) &&
                Objects.equals(country, that.country) &&
                Objects.equals(directorid, that.directorid) &&
                Objects.equals(yearreleased, that.yearreleased) &&
                Objects.equals(uploadsource, that.uploadsource) &&
                Objects.equals(img, that.img) &&
                Objects.equals(active, that.active) &&
                Objects.equals(createdate, that.createdate) &&
                Objects.equals(length, that.length) &&
                Objects.equals(info, that.info) &&
                Objects.equals(actorid, that.actorid) &&
                Objects.equals(index, that.index) &&
                Objects.equals(range, that.range);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filmName, country, directorid, yearreleased, uploadsource, img, active, createdate, length, info, actorid, index, range);
    }
}
