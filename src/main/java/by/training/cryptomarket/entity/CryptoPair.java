package by.training.cryptomarket.entity;



/**
 * The pair class entity.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class CryptoPair extends  Entity {

    /**
     * The field for storage a firstCurrency.
     */
    private Integer firstCurrency;

    /**
     * The field for storage a secondCurrency.
     */
    private Integer secondCurrency;

    /**
     * The field for storage a active.
     */
    private Boolean active;

    /**
     * The getter for firstCurrency.
     * @return firstCurrency
     */
    public Integer getFirstCurrency() {
        return firstCurrency;
    }

    /**
     * The setter for firstCurrency.
     * @param firstCurrency firstCurrency
     */
    public void setFirstCurrency(final Integer firstCurrency) {
        this.firstCurrency = firstCurrency;
    }


    /**
     * The getter for secondCurrency.
     * @return secondCurrency
     */
    public Integer getSecondCurrency() {
        return secondCurrency;
    }

    /**
     * The setter for secondCurrency.
     * @param secondCurrency secondCurrency
     */
    public void setSecondCurrency(final Integer secondCurrency) {
        this.secondCurrency = secondCurrency;
    }



    /**
     * The getter for active.
     * @return ticker
     */
    public Boolean getActive() {
        return active;
    }


    /**
     * The setter for active.
     * @param active active
     */
    public void setActive(final Boolean active) {
        this.active = active;
    }
}
