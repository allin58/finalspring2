package by.training.cryptomarket.entity;

/**
 * The order class entity.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class Order extends Entity {

    /**
     * The field for storage a userId.
     */
    private Integer userId;

    /**
     * The field for storage a pair.
     */
    private String pair;

    /**
     * The field for storage a amount.
     */
    private  Double amount;

    /**
     * The field for storage a price.
     */
    private Double price;

    /**
     * The field for storage a type.
     */
    private  String type;

    /**
     * The field for storage a state.
     */
    private  String state;

    /**
     * The getter for userId.
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * The setter for userId.
     * @param userId userId
     */
    public void setUserId(final Integer userId) {
        this.userId = userId;
    }

    /**
     * The getter for pair.
     * @return pair
     */
    public String getPair() {
        return pair;
    }

    /**
     * The setter for pair.
     * @param pair pair
     */
    public void setPair(final String pair) {
        this.pair = pair;
    }

    /**
     * The getter for amount.
     * @return amount
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * The setter for amount.
     * @param amount amount
     */
    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    /**
     * The getter for price.
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * The setter for price.
     * @param price price
     */
    public void setPrice(final Double price) {
        this.price = price;
    }

    /**
     * The getter for type.
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * The setter for type.
     * @param type type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * The getter for state.
     * @return state
     */
    public String getState() {
        return state;
    }

    /**
     * The setter for state.
     * @param state state
     */
    public void setState(final String state) {
        this.state = state;
    }
}
