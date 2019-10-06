package by.training.cryptomarket.command.user;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

@Component
public class ToMyOrdersCommand implements Command {

   @Autowired
    OrderService orderService;

    /**
     * This method is to redirect to the page of orders.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response, ModelMap model) throws Exception {

        User user = (User) request.getSession().getAttribute("user");

        List<Order> orderList = orderService.getOrdersByUserId(user.getIdentity());



        request.getSession().setAttribute("orders", orderList);

        return "orders";
    }
}
