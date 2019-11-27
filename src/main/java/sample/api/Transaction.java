package sample.api;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(name="Transaction")
public class Transaction {
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");

    @Id
    private String reference_id;
    private String external_id;
    private String status;
    private Date created;

    protected Transaction() {
    }

    public Transaction(String reference_id, String external_id, String status, String created) {
        this.reference_id = reference_id;
        this.external_id = external_id;
        this.status = status;
        try {
            this.created = SIMPLE_DATE_FORMAT.parse(created);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public String getReference_id() {
        return reference_id;
    }

    public String getExternal_id() {
        return external_id;
    }

    public String getStatus() {
        return status;
    }

    public Date getCreated() {
        return created;
    }
}
