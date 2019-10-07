package by.training.cryptomarket.command.admin;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.entity.mapping.MappingTransaction;
import by.training.cryptomarket.service.TransactionService;
import by.training.cryptomarket.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
@Qualifier("RejectTransactionCommand")
public class RejectTransactionCommand implements Command {

    @Autowired
    TransactionService transactionService;


    @Autowired
    WalletService walletService;


    /**
     * This method allows reject of transaction.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response, ModelMap model) throws Exception {



        Integer idintity = Integer.valueOf(request.getParameter("identity").
                trim());


        transactionService.rejectTransaction(idintity);

        request.getSession().setAttribute("transactionData",
                transactionService.getPendingTransactions());
        String from = request.getParameter("from");

        switch (from) {
            case "admin": return "admin";

            case "wallet":

                User user = (User) request.getSession().getAttribute("user");
                List<MappingTransaction> transactionList = transactionService.getTransactionsByUser(user.getUserName());
                request.getSession().setAttribute("transactions", transactionList);

                Wallet wallet = walletService.getWalletByUserId(user.getIdentity());
                request.getSession().setAttribute("wallet", wallet);
                return "wallet";

                default:   return "admin";
       }
    }
}
