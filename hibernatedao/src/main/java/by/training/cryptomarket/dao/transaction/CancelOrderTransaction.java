package by.training.cryptomarket.dao.transaction;




import by.training.cryptomarket.dao.sql.OrderDaoImpl;
import by.training.cryptomarket.dao.sql.WalletDaoImpl;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.entity.qualifier.WalletQualifier;
import by.training.cryptomarket.enums.StateOfOrder;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;


/**
 * This class is responsible for canceling of order.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
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

            switch (order.getType().toString()) {
                case "Ask":    walletQualifier.increaseCurrency(order.getAmount(), stringArr[0], wallet);
                    break;

                case "Bid":    walletQualifier.increaseCurrency(order.getAmount() * order.getPrice(), stringArr[1], wallet);
                    break;
            }
            order.setState(StateOfOrder.canceled);
            walletDao.update(wallet);
            orderDao.update(order);
            LOGGER.info("Order " + order.getIdentity() + " is canceled");
        } catch (Exception e) {
            LOGGER.info("PersistentException in CancelOrderTransaction, method commit()");
            throw new PersistentException();
        }
    }
}
