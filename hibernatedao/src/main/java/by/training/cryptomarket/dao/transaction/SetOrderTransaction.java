package by.training.cryptomarket.dao.transaction;


import by.training.cryptomarket.dao.sql.OrderDaoImpl;
import by.training.cryptomarket.dao.sql.WalletDaoImpl;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.entity.qualifier.WalletQualifier;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * This class is responsible for setting of order.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
public class SetOrderTransaction extends DataBaseTransaction {

    @Autowired
    WalletDaoImpl walletDao;

    @Autowired
    OrderDaoImpl orderDao;

    /**
     * The field for storage a sql query.
     */
    private static  String createSql = "INSERT INTO orders (user_id,pair,amount,price,type,state) VALUES (?, ?::currencyPairs, ?, ?, ?::typesoforder, ?::statesOfOrder )";


    /**
     * The constructor with a parameter.

     */
    public SetOrderTransaction() {


    }

    /**
     * The field for storage a order.
     */
    private Order order;

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
     * Transaction method that sets orders.
     * @throws PersistentException
     */
    @Override
    @Transactional
    public void commit() throws Exception {

        Wallet wallet = walletDao.read(order.getUserId());
        orderDao.create(order);



            String[] stringArr = order.getPair().split("-");
            WalletQualifier walletQualifier = new WalletQualifier();

            switch (order.getType().toString()) {
                case "Ask": walletQualifier.reduceCurrency(order.getAmount(), stringArr[0], wallet);
                    break;

                case "Bid": walletQualifier.reduceCurrency(order.getAmount() * order.getPrice(), stringArr[1], wallet);
                    break;
            }

        walletDao.update(wallet);

    }
}
