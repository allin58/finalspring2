package by.training.cryptomarket.entity;


/**
 * The coin class entity.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class Coin extends Entity {

    /**
     * The field for storage a ticker.
     */
    private String ticker;


    /**
     * The field for storage a fullName.
     */
    private String fullName;

    /**
     * Getter for ticker.
     * @return ticker
     */
    public String getTicker() {
        return ticker;
    }

    /**
     * Setter for ticker.
     * @param ticker ticker
     */
    public void setTicker(final String ticker) {
        this.ticker = ticker;
    }

    /**
     * Getter for fullName.
     * @return fullName
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Setter for fullName.
     * @param fullName fullName
     */
    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }
}
