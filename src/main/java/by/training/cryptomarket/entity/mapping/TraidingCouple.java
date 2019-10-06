package by.training.cryptomarket.entity.mapping;

import by.training.cryptomarket.entity.Entity;


/**
 * The class for mapping of CryptoPair.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class TraidingCouple extends Entity {

    /**
     * The text view of pair.
     */
    private String pair;

    /**
     * Activity flag.
     */
    private Boolean active;

    /**
     * Getter for pair.
     * @return pair
     */
    public String getPair() {
        return pair;
    }

    /**
     * Setter for pair.
     * @param pair pair
     */
    public void setPair(final String pair) {
        this.pair = pair;
    }

    /**
     * Getter for active.
     * @return active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * Setter for active.
     * @param active active
     */
    public void setActive(final Boolean active) {
        this.active = active;
    }






}
