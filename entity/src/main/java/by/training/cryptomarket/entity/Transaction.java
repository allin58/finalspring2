package by.training.cryptomarket.entity;


import by.training.cryptomarket.enums.PostgreSQLEnumType;
import by.training.cryptomarket.enums.TransactionStatus;
import by.training.cryptomarket.enums.TransactionType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
@TypeDef( name = "t_enum",  typeClass = PostgreSQLEnumType.class)
public class Transaction extends by.training.cryptomarket.entity.Entity {

    @Id
    @Column(name = "identity")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identity;


    @Column(name = "amount")
    private Double amount;



    @Column(name = "date")
    private Timestamp timestamp;


/*
    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private String status;
*/



    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @Type( type = "t_enum")
    private TransactionType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Type( type = "t_enum")
    private TransactionStatus status;


    @Column(name ="user_id")
    private Integer userId;

    @Column(name ="coin_id")
    private Integer coinId;





   /* @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "identity")
    private User user;

    @ManyToOne
    @JoinColumn(name = "coin_id", referencedColumnName = "identity")
    private Coin coin;*/


    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCoinId() {
        return coinId;
    }

    public void setCoinId(Integer coinId) {
        this.coinId = coinId;
    }
}
