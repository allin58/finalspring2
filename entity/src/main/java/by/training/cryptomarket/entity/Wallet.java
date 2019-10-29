package by.training.cryptomarket.entity;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "wallets")
public class Wallet extends by.training.cryptomarket.entity.Entity {

    @Id
    @Column(name = "user_id")
    private Integer identity;

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "identity")
    private User user;


    @Column(name = "btc")
    private  Double btc;

    @Column(name = "eth")
    private  Double eth;

    @Column(name = "usdt")
    private  Double usdt;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getBtc() {
        return btc;
    }

    public void setBtc(Double btc) {
        this.btc = btc;
    }

    public Double getEth() {
        return eth;
    }

    public void setEth(Double eth) {
        this.eth = eth;
    }

    public Double getUsdt() {
        return usdt;
    }

    public void setUsdt(Double usdt) {
        this.usdt = usdt;
    }
}
