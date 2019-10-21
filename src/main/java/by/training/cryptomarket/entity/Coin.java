package by.training.cryptomarket.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "coins")
public class Coin {



    @Id
    @Column(name = "identity")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identity;


    @Column(name = "coin")
    private  String ticker;;

    @Column(name = "full_name")
    private  String fullName;;

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
