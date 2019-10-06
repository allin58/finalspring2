package by.training.cryptomarket.entity;

/**
 * The wallet class entity.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class Wallet extends Entity {

    /**
     * The field for storage a amount of bitcoin.
     */
    private  Double btc;

    /**
     * The field for storage a amount of ethereum.
     */
    private  Double eth;

    /**
     * The field for storage a amount of tether.
     */
    private  Double usdt;

    /**
     * The getter for btc.
     * @return btc
     */
    public Double getBtc() {
        return btc;
    }

    /**
     * The setter for btc.
     * @param btc btc
     */
    public void setBtc(Double btc) {
        this.btc = btc;
    }

    /**
     * The getter for eth.
     * @return eth
     */
    public Double getEth() {
        return eth;
    }

    /**
     * The setter for eth.
     * @param eth eth
     */
    public void setEth(Double eth) {
        this.eth = eth;
    }

    /**
     * The getter for usdt.
     * @return usdt
     */
    public Double getUsdt() {
        return usdt;
    }

    /**
     * The setter for usdt.
     * @param usdt usdt
     */
    public void setUsdt(Double usdt) {
        this.usdt = usdt;
    }




}
