package Server.model.DB;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "film", schema = "public", catalog = "test12345")
public class FilmEntity {
    private long id;
    private String filmname;
    private String country;
    private long directorid;
    private int yearreleased;
    private String uploadsource;
    private String img;
    private Timestamp createdate;
    private int length;
    private String info;
    private long seriid;
    private int index;
    private int range;
    private Boolean active;

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
    @Column(name = "filmname", nullable = false, length = 255)
    public String getFilmname() {
        return filmname;
    }

    public void setFilmname(String filmname) {
        this.filmname = filmname;
    }

    @Basic
    @Column(name = "country", nullable = false, length = 255)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "directorid", nullable = false)
    public long getDirectorid() {
        return directorid;
    }

    public void setDirectorid(long directorid) {
        this.directorid = directorid;
    }

    @Basic
    @Column(name = "yearreleased", nullable = false)
    public int getYearreleased() {
        return yearreleased;
    }

    public void setYearreleased(int yearreleased) {
        this.yearreleased = yearreleased;
    }

    @Basic
    @Column(name = "uploadsource", nullable = false, length = -1)
    public String getUploadsource() {
        return uploadsource;
    }

    public void setUploadsource(String uploadsource) {
        this.uploadsource = uploadsource;
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
    @Column(name = "createdate", nullable = false)
    public Timestamp getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Timestamp createdate) {
        this.createdate = createdate;
    }

    @Basic
    @Column(name = "length", nullable = false)
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Basic
    @Column(name = "info", nullable = false, length = -1)
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Basic
    @Column(name = "seriid", nullable = false)
    public long getSeriid() {
        return seriid;
    }

    public void setSeriid(long actorid) {
        this.seriid = actorid;
    }

    @Basic
    @Column(name = "index", nullable = false)
    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Basic
    @Column(name = "range", nullable = false)
    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    @Basic
    @Column(name = "active", nullable = true)
    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmEntity that = (FilmEntity) o;
        return id == that.id &&
                directorid == that.directorid &&
                yearreleased == that.yearreleased &&
                length == that.length &&
                seriid == that.seriid &&
                index == that.index &&
                range == that.range &&
                Objects.equals(filmname, that.filmname) &&
                Objects.equals(country, that.country) &&
                Objects.equals(uploadsource, that.uploadsource) &&
                Objects.equals(img, that.img) &&
                Objects.equals(createdate, that.createdate) &&
                Objects.equals(info, that.info) &&
                Objects.equals(active, that.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filmname, country, directorid, yearreleased, uploadsource, img, createdate, length, info, seriid, index, range, active);
    }
}
