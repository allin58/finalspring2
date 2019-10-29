package by.training.cryptomarket.dao.transaction;


import by.training.cryptomarket.dao.sql.WalletDaoImpl;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.entity.qualifier.WalletQualifier;
import by.training.cryptomarket.enums.TypeOfOrder;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class is responsible for setting of order.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
public class SetOrderTransaction extends DataBaseTransaction {

    @Autowired
    WalletDaoImpl walletDao;


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
    public void commit() throws PersistentException {

        Wallet wallet = walletDao.read(order.getUserId());


        jdbcOperations.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

               // PreparedStatement ps = connection.prepareStatement(createSql,Statement.RETURN_GENERATED_KEYS);
                PreparedStatement ps = connection.prepareStatement(createSql);

                ps.setInt(1, order.getUserId());
                ps.setString(2, order.getPair());
                ps.setBigDecimal(3, new BigDecimal(order.getAmount()));
                ps.setBigDecimal(4, new BigDecimal(order.getPrice()));
                ps.setString(5,order.getType().toString());
                ps.setString(6, order.getState().toString());

                return ps;
            }
        });



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
