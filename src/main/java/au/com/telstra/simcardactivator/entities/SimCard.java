package au.com.telstra.simcardactivator.entities;

import javax.persistence.*;

@Entity
@Table(name = "simcard")
public class SimCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String icicid;
    private String customerEmail;
    private boolean active;

    public SimCard() {
    }

    public SimCard(Long id, String icicid, String customerEmail, boolean active) {
        this.id = id;
        this.icicid = icicid;
        this.customerEmail = customerEmail;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcicid() {
        return icicid;
    }

    public void setIcicid(String icicid) {
        this.icicid = icicid;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "SimCard{" +
                "id=" + id +
                ", icicid='" + icicid + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", active=" + active +
                '}';
    }
}
