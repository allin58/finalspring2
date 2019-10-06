package by.training.cryptomarket.command.user;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.entity.mapping.MappingTransaction;
import by.training.cryptomarket.service.CryptoPairService;
import by.training.cryptomarket.service.TransactionService;
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
public class ToCabinetCommand implements Command {

    @Autowired
    CryptoPairService cryptoPairService;

    @Autowired
    TransactionService transactionService;


    /**
     * This method is to redirect to the cabinet.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response, ModelMap model) throws Exception {
        String role = ((User) request.getSession().getAttribute("user")).getRole();

        switch (role) {
            case "admin" :

                List<MappingTransaction> transactionList = transactionService.getPendingTransactions();

                request.getSession().setAttribute("transactionData", transactionList);
                return "admin";

            case "sec" :
                request.getSession().setAttribute("secData", cryptoPairService.getAllPairs());
                return "sec";

            case "user" :   return "market";
        }

        return "error";
    }
}
