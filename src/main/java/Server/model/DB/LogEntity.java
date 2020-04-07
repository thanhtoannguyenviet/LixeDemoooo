package Server.model.DB;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "log", schema = "public", catalog = "test12345")
public class LogEntity {
    private long id;
    private String messageError;
    private String codeerror;
    private String nameapierror;

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
    @Column(name = "messageError", nullable = true, length = -1)
    public String getMessageError() {
        return messageError;
    }

    public void setMessageError(String messageError) {
        this.messageError = messageError;
    }

    @Basic
    @Column(name = "codeerror", nullable = true, length = -1)
    public String getCodeerror() {
        return codeerror;
    }

    public void setCodeerror(String codeerror) {
        this.codeerror = codeerror;
    }

    @Basic
    @Column(name = "nameapierror", nullable = true, length = -1)
    public String getNameapierror() {
        return nameapierror;
    }

    public void setNameapierror(String nameapierror) {
        this.nameapierror = nameapierror;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogEntity logEntity = (LogEntity) o;
        return id == logEntity.id &&
                Objects.equals(messageError, logEntity.messageError) &&
                Objects.equals(codeerror, logEntity.codeerror) &&
                Objects.equals(nameapierror, logEntity.nameapierror);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, messageError, codeerror, nameapierror);
    }
}
