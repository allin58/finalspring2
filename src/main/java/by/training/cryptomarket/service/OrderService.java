package by.training.cryptomarket.service;



import by.training.cryptomarket.daojdbctemplate.sql.OrderDaoImpl;
import by.training.cryptomarket.daojdbctemplate.transaction.CancelOrderTransaction;
import by.training.cryptomarket.daojdbctemplate.transaction.ExecuteOrderTransaction;
import by.training.cryptomarket.daojdbctemplate.transaction.SetOrderTransaction;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.entity.comparator.OrderComparatorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This service ensures interact with orderds.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
public class OrderService {

    @Autowired
    OrderDaoImpl orderDao;

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
        List<Order> resultOrders = new ArrayList<>();



            List<Order> orders = orderDao.read();
            for (Order order : orders) {
              if (pair.equals(order.getPair())
                      && "Ask".equals(order.getType())
                      && "active".equals(order.getState())) {
                  resultOrders.add(order);
              }
            }
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
        List<Order> resultOrders = new ArrayList<>();




            List<Order> orders = orderDao.read();
            for (Order order : orders) {
                if (pair.equals(order.getPair())
                        && "Bid".equals(order.getType())
                        && "active".equals(order.getState())) {
                    resultOrders.add(order);
                }
            }
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
        List<Order> resultOrders = new ArrayList<>();


            List<Order> orders = orderDao.read();
            for (Order order : orders) {
               if (order.getUserId().equals(userId)) {
                   resultOrders.add(order);
               }
            }


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
