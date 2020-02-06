package by.training.cryptomarket.service;



import by.training.cryptomarket.dao.OrderDao;
import by.training.cryptomarket.dao.transaction.CancelOrderTransaction;
import by.training.cryptomarket.dao.transaction.ExecuteOrderTransaction;
import by.training.cryptomarket.dao.transaction.SetOrderTransaction;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.entity.comparator.OrderComparatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This service ensures interact with orderds.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    CancelOrderTransaction cancelOrderTransaction;

    @Autowired
    ExecuteOrderTransaction executeOrderTransaction;

    @Autowired
    SetOrderTransaction setOrderTransaction;


    /**
     * This method returns a collection of ask orders by text view of pair.
     * @param pair pair
     * @return ask orders by pair
     * @throws Exception Exception
     */
    public List getAskOrdersByPair(final String pair) throws Exception {


        List<Order> resultOrders = orderDao.read()
                .stream()
                .filter(order -> pair.equals(order.getPair())
                        && "Ask".equals(order.getType().toString())
                        && "active".equals(order.getState().toString()))
                .collect(Collectors.toList());


            Collections.sort(resultOrders, new OrderComparatorImpl());


        return resultOrders;
    }


    /**
     * This method returns a collection of bid orders by text view of pair.
     * @param pair pair
     * @return bid orders by pair
     * @throws Exception Exception
     */
    public List getBidOrdersByPair(final String pair) throws Exception {

        List<Order> resultOrders = orderDao.read()
                .stream()
                .filter(order -> pair.equals(order.getPair())
                        && "Bid".equals(order.getType().toString())
                        && "active".equals(order.getState().toString()))
                .collect(Collectors.toList());

        Collections.sort(resultOrders, new OrderComparatorImpl());
            return resultOrders;
    }


    /**
     *  This method creates a new order.
     * @param order order
     * @throws Exception Exception
     */
   public void createNewOrder(final Order order) throws Exception {

      setOrderTransaction.setOrder(order);

      setOrderTransaction.commit();


   }


    /**
     * This method executes orders.
     * @param order order to be processed
     * @param user user who requested order processing
     * @throws Exception Exception
     */
    public void executeOrder(final Order order, final User user) throws Exception {

            executeOrderTransaction.setOrder(order);
            executeOrderTransaction.setUser(user);
            executeOrderTransaction.commit();

    }


    /**
     * This method partly executed orders.
     * @param order order to be processed
     * @param user user who requested order processing
     * @param amount amount
     * @throws Exception Exception
     */
    public void executePartlyOrder(final Order order, final User user, final Double amount) throws Exception {


            executeOrderTransaction.setOrder(order);
            executeOrderTransaction.setUser(user);
            executeOrderTransaction.setAmount(amount);
            executeOrderTransaction.commit();

    }


    /**
     * This method returns a collection of orders userId.
     * @param userId identity of user
     * @return specific user orders
     * @throws Exception Exception
     */
    public List getOrdersByUserId(final Integer userId) throws Exception {

        List<Order> resultOrders = orderDao.read()
                .stream()
                .filter(order -> order.getUserId().equals(userId))
                .collect(Collectors.toList());


        return resultOrders;
    }


    /**
     * This method rejects order by id.
     * @param identity identity
     * @throws Exception Exception
     */
    public void rejectOrderById(final Integer identity) throws Exception {

            Order order = orderDao.read(identity);
            cancelOrderTransaction.setOrder(order);
            cancelOrderTransaction.commit();


    }
}
