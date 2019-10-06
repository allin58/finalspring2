package by.training.cryptomarket.command.user;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.entity.mapping.TraidingCouple;
import by.training.cryptomarket.service.CryptoPairService;
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
public class ToMarketCommand implements Command {

    @Autowired
    CryptoPairService cryptoPairService;

    @Autowired
    OrderService orderService;


    /**
     * This method is to redirect to the market.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response, ModelMap model) throws Exception {



        request.getSession().setAttribute("marketerror", "");
        request.getSession().setAttribute("ordermessage", "");
        request.getSession().setAttribute("typeoforder", "limit");
       String pair = request.getParameter("pair");

       if (pair == null || "".equals(pair)) {



           List<TraidingCouple> activePairs = cryptoPairService.getActivePairs();
if (activePairs.size() > 0) {

    pair = activePairs.get(0).getPair();

} else {

    request.getSession().setAttribute("activepairs", "");
    request.getSession().setAttribute("pair", "");
    request.getSession().setAttribute("marketerror", "marketerror");
    return "market";
}



       }




        request.getSession().setAttribute("pair", pair);



        List activePairs = cryptoPairService.getActivePairs();

        request.getSession().setAttribute("activepairs", activePairs);

        List askList = orderService.getAskOrdersByPair(pair.trim());
        request.getSession().setAttribute("asklist", askList);

        List bidList = orderService.getBidOrdersByPair(pair.trim());
        request.getSession().setAttribute("bidlist", bidList);

        return "market";
    }
}
