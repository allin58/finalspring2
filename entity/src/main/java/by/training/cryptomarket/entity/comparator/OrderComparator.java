package by.training.cryptomarket.entity.comparator;

import by.training.cryptomarket.entity.Order;

import java.util.Comparator;

/**
 * Interface for comparators.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public interface OrderComparator extends Comparator<Order> {


    /**
     * The method of comparing.
     * @param o1 o1
     * @param o2 o2
     * @return int
     */
    @Override
    int compare(Order o1, Order o2);
}
