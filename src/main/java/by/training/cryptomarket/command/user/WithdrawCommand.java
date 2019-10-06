package by.training.cryptomarket.command.user;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.entity.mapping.MappingTransaction;
import by.training.cryptomarket.service.TransactionService;
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
public class WithdrawCommand implements Command {

    @Autowired
    TransactionService transactionService;

    @Autowired
    WalletService walletService;

    /**
     * This method which allows to do withdraw.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response, ModelMap model) throws Exception {
        request.getSession().setAttribute("transactionerror","");

        try {


            Double amount = null;
            try {
                amount = Double.valueOf(request.getParameter("amount"));
                if (amount <= 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                request.getSession().setAttribute("transactionerror","incorrectamount");
                return "withdraw";
            }


            String coin = (String) request.getSession().getAttribute("coin");
            Integer userId = ((User)request.getSession().getAttribute("user")).getIdentity();



            transactionService.setWithdrawTransaction(userId,amount,coin);

            User user = (User)request.getSession().getAttribute("user");
            List<MappingTransaction> transactionList = transactionService.getTransactionsByUser(user.getUserName());
            request.getSession().setAttribute("transactions",transactionList);


            Wallet wallet = walletService.getWalletByUserId(user.getIdentity());
            request.getSession().setAttribute("wallet",wallet);

            return "wallet";
        } catch (Exception e) {

            request.getSession().setAttribute("transactionerror","insufficientfunds");
            return "withdraw";

        }
    }
}
