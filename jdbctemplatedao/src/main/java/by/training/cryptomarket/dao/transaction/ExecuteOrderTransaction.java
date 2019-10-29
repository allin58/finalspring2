package by.training.cryptomarket.dao.transaction;




import by.training.cryptomarket.dao.sql.OrderDaoImpl;
import by.training.cryptomarket.dao.sql.WalletDaoImpl;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.entity.qualifier.WalletQualifier;
import by.training.cryptomarket.enums.StateOfOrder;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * This class is responsible for execution of order.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
public class ExecuteOrderTransaction extends DataBaseTransaction {

    @Autowired
    WalletDaoImpl walletDao;


    @Autowired
    OrderDaoImpl orderDao;


    /**
     * The field for storage a amount.
     */
   private Double amount;

    /**
     * The field for storage a user.
     */
   private User user;

    /**
     * The field for storage a order.
     */
   private Order order;


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
     * The getter for user.
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     * The setter for user.
     * @param user user
     */
    public void setUser(final User user) {
        this.user = user;
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
     * The constructor with a parameter.

     */
    public ExecuteOrderTransaction() {

    }


    /**
     * Transaction method that executes orders.
     * @throws PersistentException
     */
    @Override
    @Transactional
    public void commit() throws PersistentException {


            try {

                Wallet wallet1 = walletDao.read(user.getIdentity());
                Wallet wallet2 = walletDao.read(order.getUserId());
                String pair = order.getPair();
                String firstCurrency = pair.split("-")[0];
                String secondCurrency = pair.split("-")[1];

                WalletQualifier walletQualifier = new WalletQualifier();


                if (amount == null) {


                    if ("Bid".equals(order.getType().toString())) {

                        walletQualifier.reduceCurrency(order.getAmount(), firstCurrency, wallet1);
                        walletQualifier.increaseCurrency(order.getAmount(), firstCurrency, wallet2);
                        walletQualifier.increaseCurrency(order.getAmount() * order.getPrice(), secondCurrency, wallet1);
                    }

                    if ("Ask".equals(order.getType().toString())) {

                        walletQualifier.reduceCurrency(order.getAmount() * order.getPrice(), secondCurrency, wallet1);
                        walletQualifier.increaseCurrency(order.getAmount() * order.getPrice(), secondCurrency, wallet2);
                        walletQualifier.increaseCurrency(order.getAmount(), firstCurrency, wallet1);
                    }



                } else {

                    if ("Bid".equals(order.getType().toString())) {

                        walletQualifier.reduceCurrency(amount, firstCurrency, wallet1);
                        walletQualifier.increaseCurrency(amount, firstCurrency, wallet2);
                        walletQualifier.increaseCurrency(amount * order.getPrice(), secondCurrency, wallet1);

                        Order newOrder = new Order();
                        newOrder.setState(order.getState());
                        newOrder.setAmount(order.getAmount() - amount);
                        newOrder.setPrice(order.getPrice());
                        newOrder.setPair(order.getPair());
                        newOrder.setUserId(order.getUserId());
                        newOrder.setType(order.getType());
                        orderDao.create(newOrder);
                    }
                    if ("Ask".equals(order.getType().toString())) {

                        walletQualifier.reduceCurrency(amount * order.getPrice(), secondCurrency, wallet1);
                        walletQualifier.increaseCurrency(amount * order.getPrice(), secondCurrency, wallet2);
                        walletQualifier.increaseCurrency(amount, firstCurrency, wallet1);

                        Order newOrder = new Order();
                        newOrder.setState(order.getState());
                        newOrder.setAmount(order.getAmount() - amount);
                        newOrder.setPrice(order.getPrice());
                        newOrder.setPair(order.getPair());
                        newOrder.setUserId(order.getUserId());
                        newOrder.setType(order.getType());
                        orderDao.create(newOrder);
                    }


                }

                order.setState(StateOfOrder.executed);
                orderDao.update(order);
                walletDao.update(wallet1);
                walletDao.update(wallet2);
                LOGGER.info("Order " + order.getIdentity() + " is executed");
            } catch (Exception e) {
                LOGGER.info("PersistentException in ExecuteOrderTransaction, method commit()");
                 throw new PersistentException();
            }
    }
}
