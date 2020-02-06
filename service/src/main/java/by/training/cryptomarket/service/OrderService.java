package by.training.cryptomarket.service;

import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.entity.User;

import java.util.List;

public interface OrderService {

    List getAskOrdersByPair(final String pair) throws Exception;

    List getBidOrdersByPair(final String pair) throws Exception;

    void createNewOrder(final Order order) throws Exception;

    void executeOrder(final Order order, final User user) throws Exception;

    void executePartlyOrder(final Order order, final User user, final Double amount) throws Exception;

    List getOrdersByUserId(final Integer userId) throws Exception;

    void rejectOrderById(final Integer identity) throws Exception;

}
