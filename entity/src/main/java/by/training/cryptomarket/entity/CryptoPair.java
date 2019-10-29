package by.training.cryptomarket.entity;


import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "cryptocurrency_pairs")
public class CryptoPair extends by.training.cryptomarket.entity.Entity  {

    @Id
    @Column(name = "identity")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identity;

    @Column(name = "first_currency")
    private Integer firstCurrency;


    @Column(name = "second_currency")
      private Integer secondCurrency;


    @Column(name = "active")
    private Boolean active;


    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Integer getFirstCurrency() {
        return firstCurrency;
    }

    public void setFirstCurrency(Integer firstCurrency) {
        this.firstCurrency = firstCurrency;
    }

    public Integer getSecondCurrency() {
        return secondCurrency;
    }

    public void setSecondCurrency(Integer secondCurrency) {
        this.secondCurrency = secondCurrency;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
