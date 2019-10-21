package by.training.cryptomarket.entity;


import by.training.cryptomarket.enums.PostgreSQLEnumType;
import by.training.cryptomarket.enums.StateOfOrder;
import by.training.cryptomarket.enums.TypeOfOrder;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "orders")
@TypeDef( name = "t_enum",  typeClass = PostgreSQLEnumType.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "identity")
    private Integer identity;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "pair")
    private String pair;

    @Column(name = "amount")
    private  Double amount;

    @Column(name = "price")
    private Double price;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @Type( type = "t_enum")
    private TypeOfOrder type;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    @Type( type = "t_enum")
    private  StateOfOrder state;



    /*@ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "identity")
    private User user;*/


   /* public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
*/
    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

/*    public Integer getUserId() {
        return user.getIdentity();
    }

    public void setUserId(Integer userId) {
        user.setIdentity(userId);
    }*/

    public String getPair() {
        return pair;
    }

    public void setPair(String pair) {
        this.pair = pair;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public TypeOfOrder getType() {
        return type;
    }

    public void setType(TypeOfOrder type) {
        this.type = type;
    }

    public StateOfOrder getState() {
        return state;
    }

    public void setState(StateOfOrder state) {
        this.state = state;
    }


    /* public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }*/
}
