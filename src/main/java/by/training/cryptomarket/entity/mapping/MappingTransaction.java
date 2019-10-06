package by.training.cryptomarket.entity.mapping;

import by.training.cryptomarket.entity.Entity;

/**
 * The class for mapping of Transaction.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class MappingTransaction extends Entity {


    /**
     * The field for userName.
     */
    private String user;

    /**
     * The field for name of coin.
     */
    private String coin;

    /**
     * The field for amount.
     */
    private Double amount;

    /**
     * The field for type.
     */
    private String type;

    /**
     * The field for status.
     */
    private String status;


    /**
     * Getter for status.
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter for status.
     * @param status status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * Getter for user.
     * @return user
     */
    public String getUser() {
        return user;
    }

    /**
     * Setter for user.
     * @param user user
     */
    public void setUser(final String user) {
        this.user = user;
    }

    /**
     * Getter for coin.
     * @return coin
     */
    public String getCoin() {
        return coin;
    }

    /**
     * Setter for coin.
     * @param coin coin
     */
    public void setCoin(final String coin) {
        this.coin = coin;
    }

    /**
     * Getter for amount.
     * @return amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Setter for amount.
     * @param amount amount
     */
    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    /**
     * Getter for type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type.
     * @param type type
     */
    public void setType(final String type) {
        this.type = type;
    }
}
