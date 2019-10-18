package by.training.cryptomarket.command.user;

import by.training.cryptomarket.command.Command;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Component("towithdraw")
@Qualifier("ToWithdrawCommand")
public class ToWithdrawCommand implements Command {




    /**
     * This method is to redirect to the page of withdraw.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response, ModelMap model) throws Exception {
        String coin = request.getParameter("coin");
        request.getSession().setAttribute("coin", coin);
        request.getSession().setAttribute("transactionerror", "");
        return "withdraw";
    }
}
