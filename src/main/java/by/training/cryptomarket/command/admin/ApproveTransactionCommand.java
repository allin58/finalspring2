package by.training.cryptomarket.command.admin;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.service.TransactionService;
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
public class ApproveTransactionCommand implements Command {


    @Autowired
    TransactionService transactionService;

    /**
     * This method allows approve of transaction.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response, ModelMap model) throws Exception {
       Integer idintity = Integer.valueOf(
               request.getParameter("identity").trim());

       transactionService.approveTransaction(idintity);

       request.getSession().setAttribute("transactionData",
               transactionService.getPendingTransactions());
        return "admin";
    }
}
