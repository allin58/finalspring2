package by.training.cryptomarket.command.user;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.entity.qualifier.WalletQualifier;
import by.training.cryptomarket.service.OrderService;
import by.training.cryptomarket.service.WalletService;
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
public class SetLimitOrderCommand implements Command {

    @Autowired
    OrderService orderService;

    @Autowired
    OrderService orderService2;

    @Autowired
    WalletService walletService;



    /**
     * This method which allows to set limit order.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response, ModelMap model) throws Exception {



        String typeOfOrder = null;
        request.getSession().setAttribute("ordermessage", "");

        if (request.getParameter("buybutton") != null) {
            typeOfOrder = request.getParameter("buybutton").trim();
             }
        if (request.getParameter("sellbutton") != null) {
            typeOfOrder = request.getParameter("sellbutton").trim();
        }

        String pair = ((String) request.getSession().getAttribute("pair")).trim();
        String firstCoin = pair.split("-")[0];
        String secondCoin = pair.split("-")[1];
        Double price = 0.0;
        Double amount = 0.0;

        try {
             price = Double.valueOf(request.getParameter("price"));
             amount = Double.valueOf(request.getParameter("amount"));
            if (price < 0 || amount < 0) {
                throw new Exception();
            }

        } catch (Exception e) {
            request.getSession().setAttribute("ordermessage", "incorrectamount");
            return "market";
        }

        User user = (User) request.getSession().getAttribute("user");

        Wallet wallet = walletService.getWalletByUserId(user.getIdentity());
        WalletQualifier walletQualifier = new WalletQualifier();

        if (typeOfOrder != null) {
            switch (typeOfOrder) {
                case "buy" :
                    Double sum = amount * price;
                    if (walletQualifier.getAmountByTicker(wallet, secondCoin) < sum) {
                        request.getSession().setAttribute("ordermessage", "insufficientfunds");
                        return "market";
                    }


                    Order order = new Order();
                    order.setType("Bid");
                    order.setState("active");
                    order.setPair(pair);
                    order.setUserId(user.getIdentity());
                    order.setPrice(price);
                    order.setAmount(amount);
                    orderService.createNewOrder(order);
                    break;

                case "sell" :

                    if (walletQualifier.getAmountByTicker(wallet, firstCoin) < amount) {
                        request.getSession().setAttribute("ordermessage", "insufficientfunds");
                        return "market";
                    }


                    Order order2 = new Order();
                    order2.setType("Ask");
                    order2.setState("active");
                    order2.setPair(pair);
                    order2.setUserId(user.getIdentity());
                    order2.setPrice(price);
                    order2.setAmount(amount);

                    orderService2.createNewOrder(order2);

                    break;
            }
        }



        List askList = orderService.getAskOrdersByPair(pair.trim());
        request.getSession().setAttribute("asklist", askList);

        List bidList = orderService.getBidOrdersByPair(pair.trim());
        request.getSession().setAttribute("bidlist", bidList);

        request.getSession().setAttribute("ordermessage", "yourorderisaccepted");

        return "market";
    }
}
