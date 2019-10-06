package by.training.cryptomarket.entity;

/**
 * The user class entity.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class User extends Entity {

    /**
     * The field for storage a userName.
     */
    private  String userName;

    /**
     * The field for storage a name.
     */
    private  String name;

    /**
     * The field for storage a surname.
     */
    private  String surname;

    /**
     * The field for storage a hashOfPassword.
     */
    private   String hashOfPassword;

    /**
     * The field for storage a role.
     */
    private   String role;


    /**
     * The getter for name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * The setter for name.
     * @param name name
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * The getter for surname.
     * @return surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * The setter for surname.
     * @param surname surname
     */
    public void setSurname(final String surname) {
        this.surname = surname;
    }

    /**
     * The getter for userName.
     * @return userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * The setter for userName.
     * @param userName userName
     */
    public void setUserName(final String userName) {
        this.userName = userName;
    }

    /**
     * The getter for hashOfPassword.
     * @return hashOfPassword
     */
    public String getHashOfPassword() {
        return hashOfPassword;
    }

    /**
     * The setter for hashOfPassword.
     * @param hashOfPassword hashOfPassword
     */
    public void setHashOfPassword(final String hashOfPassword) {
        this.hashOfPassword = hashOfPassword;
    }

    /**
     * The getter for role.
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * The setter for role.
     * @param role role
     */
    public void setRole(final String role) {
        this.role = role;
    }
}
