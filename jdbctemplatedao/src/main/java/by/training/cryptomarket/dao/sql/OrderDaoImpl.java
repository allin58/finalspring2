package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.dao.OrderDao;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.enums.StateOfOrder;
import by.training.cryptomarket.enums.TypeOfOrder;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The class of OrderDao implamentation.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Repository
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderDaoImpl extends BaseDao implements OrderDao {



    /**
     * The field to store the sql create request.
     */
    private static  String createSql = "INSERT INTO orders (user_id,pair,amount,price,type,state) VALUES (?, ?, ?, ?, ?::typesoforder, ?::statesOfOrder)";


    /**
     * The field to store the sql readList request.
     */
    private static  String readListSql = "SELECT identity,user_id,pair,amount,price,type, state FROM orders";

    /**
     * The field to store the sql read request.
     */
    private static  String readSql = "SELECT user_id,pair,amount,price,type,state FROM orders WHERE identity = ?";

    /**
     * The field to store the sql update request.
     */
    private static  String updateSql = "UPDATE orders SET user_id = ?, pair = ?, amount = ?, price = ?, type = ?::typesoforder, state = ?::statesOfOrder WHERE identity = ?";

    /**
     * The field to store the sql delete request.
     */
    private static  String deleteSql = "DELETE FROM orders WHERE identity = ?";



    /**
     * The method that returns collection of orders.
     * @return collection of orders
     * @throws PersistentException
     */
    @Override
    public List<Order> read()  {

        List<Order> orders = null;
        try {
            orders = jdbcOperations.query(readListSql, new RowMapper<Order>() {
                @Override
                public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                    Order order = new Order();
                    order.setIdentity(resultSet.getInt("identity"));
                    order.setUserId(resultSet.getInt("user_id"));
                    order.setPair((resultSet.getString("pair")));
                    order.setAmount((resultSet.getBigDecimal("amount")).doubleValue());
                    order.setPrice((resultSet.getBigDecimal("price")).doubleValue());
                    order.setType(TypeOfOrder.valueOf(resultSet.getString("type")));
                    order.setState(StateOfOrder.valueOf(resultSet.getString("state")));

                    return order;
                }
            });
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method read()");
        }
        return orders;



    }


    /**
     * The method that creates a new record.
     * @param order order
     * @return number of record
     * @throws PersistentException
     */
    @Override
    public Integer create(final Order order)  {

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcOperations.update(new PreparedStatementCreator() {

                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                    PreparedStatement ps =
                            connection.prepareStatement(createSql);

                    ps.setInt(1, order.getUserId());
                    ps.setString(2, order.getPair());
                    ps.setBigDecimal(3, new BigDecimal(order.getAmount()));
                    ps.setBigDecimal(4, new BigDecimal(order.getPrice()));
                    ps.setString(5, order.getType().toString());
                    ps.setString(6, order.getState().toString());
                    return ps;
                }
            }, keyHolder);
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method create()");
        }


        return 0;


     }

    /**
     * The method that returns order by id.
     * @param identity
     * @return order
     * @throws PersistentException
     */
    @Override
    public Order read(final Integer identity)  {
        Order order = null;
        try {
            order = jdbcOperations.queryForObject(readSql, new Object[]{identity}, new RowMapper<Order>() {
                @Override
                public Order mapRow(ResultSet resultSet, int i) throws SQLException {
                    Order order = new Order();
                    order.setIdentity(identity);
                    order.setUserId(resultSet.getInt("user_id"));
                    order.setPair(resultSet.getString("pair"));
                    order.setAmount(resultSet.getBigDecimal("amount").doubleValue());
                    order.setPrice(resultSet.getBigDecimal("price").doubleValue());
                    order.setType(TypeOfOrder.valueOf(resultSet.getString("type")));
                    order.setState(StateOfOrder.valueOf(resultSet.getString("state")));
                    return order;
                }
            });
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method read()");
        }
        return order;
    }


    /**
     * The method that updates order.
     * @param order order
     * @throws PersistentException PersistentException
     */
    @Override
    public void update(final Order order) {
        try {
            jdbcOperations.update(updateSql,order.getUserId(),order.getPair(),order.getAmount(),order.getPrice(),order.getType().toString(),order.getState().toString(),order.getIdentity());
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method update()");
        }
    }

    /**
     *  The method that deletes order by id.
     * @param identity identity
     * @throws PersistentException
     */
    @Override
    public void delete(final Integer identity) {

        try {
            jdbcOperations.update(deleteSql, identity );
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in OrderDaoImpl, method delete()");
        }
    }
}
