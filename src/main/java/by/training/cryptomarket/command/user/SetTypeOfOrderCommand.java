package by.training.cryptomarket.command.user;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.service.CryptoPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
public class SetTypeOfOrderCommand implements Command {
    @Autowired
    CryptoPairService cryptoPairService;
    /**
     * This method which allows to toggle type of order.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response, ModelMap model) throws Exception {

       request.getSession().setAttribute("typeoforder", request.getParameter("typeoforder"));
       request.getSession().setAttribute("ordermessage", "");


        return "market";
    }
}
