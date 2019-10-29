package by.training.cryptomarket.entity;

import by.training.cryptomarket.enums.PostgreSQLEnumType;
import by.training.cryptomarket.enums.Role;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.*;


@Entity
@Table(name = "users")
@TypeDef( name = "role_enum",  typeClass = PostgreSQLEnumType.class)
public class User extends by.training.cryptomarket.entity.Entity  {


    @Id
    @Column(name = "identity")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer identity;

    @Column(name = "user_name")
        private  String userName;

    @Column(name = "name")
        private  String name;

    @Column(name = "surname")
        private  String surname;

    @Column(name = "hash_of_password")
        private   String hashOfPassword;

      @Column(name = "role")
      @Enumerated(EnumType.STRING)
      @Type( type = "role_enum")
              private Role role;


    @OneToOne(mappedBy = "user",fetch = FetchType.EAGER,cascade=CascadeType.REMOVE)
      Wallet wallet;

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }

    /* @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Transaction> transactions;*/


 /*   @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Order> orders;
*/




    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getHashOfPassword() {
        return hashOfPassword;
    }

    public void setHashOfPassword(String hashOfPassword) {
        this.hashOfPassword = hashOfPassword;
    }

    public Role getRole()
    {

        return role;
    }

    public void setRole(Role role) {



        this.role = role;


    }
}
