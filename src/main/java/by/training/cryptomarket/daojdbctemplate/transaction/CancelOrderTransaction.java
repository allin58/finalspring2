package by.training.cryptomarket.daojdbctemplate.transaction;




import by.training.cryptomarket.daojdbctemplate.sql.OrderDaoImpl;
import by.training.cryptomarket.daojdbctemplate.sql.WalletDaoImpl;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.entity.qualifier.WalletQualifier;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * This class is responsible for canceling of order.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
public class CancelOrderTransaction extends DataBaseTransaction {

    /**
     * The field for storage a order.
     */
    private Order order;

    @Autowired
    WalletDaoImpl walletDao;


    @Autowired
    OrderDaoImpl orderDao;

    /**
     * The constructor with a parameter.
     */
    public CancelOrderTransaction() {
    }


    /**
     * The getter for order.
     * @return order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * The setter for order.
     * @param order order
     */
    public void setOrder(final Order order) {
        this.order = order;
    }

    /**
     * Transaction method that cancels orders.
     * @throws PersistentException
     */
    @Override
    @Transactional
    public void commit() throws PersistentException {


        try {

            Wallet wallet = walletDao.read(order.getUserId());
            String[] stringArr = order.getPair().split("-");
            WalletQualifier walletQualifier = new WalletQualifier();

            switch (order.getType()) {
                case "Ask":    walletQualifier.increaseCurrency(order.getAmount(), stringArr[0], wallet);
                    break;

                case "Bid":    walletQualifier.increaseCurrency(order.getAmount() * order.getPrice(), stringArr[1], wallet);
                    break;
            }
            order.setState("canceled");
            walletDao.update(wallet);
            orderDao.update(order);
            LOGGER.info("Order " + order.getIdentity() + " is canceled");
        } catch (Exception e) {
            LOGGER.info("PersistentException in CancelOrderTransaction, method commit()");
            throw new PersistentException();
        }
    }
}
