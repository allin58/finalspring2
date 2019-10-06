package by.training.cryptomarket.controller.filter;




import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;


/**
 * This filter sets of language.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

/*@WebFilter(urlPatterns = "/*")
@Order(2)*/
public class LanguageFilter implements Filter {

    /**
     * The empty method.
     * @param filterConfig filterConfig
     * @throws ServletException
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }



    /**
     *  The method for setting of language depend on parameter.
     * @param servletRequest servletRequest
     * @param servletResponse servletResponse
     * @param filterChain filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession();




        if ((session != null && session.getAttribute("language") == null)) {


            httpRequest.getSession().setAttribute("language", "en");

        }

        if (session != null) {

            Locale locale = new Locale((String) httpRequest.getSession().getAttribute("language"));



            //ResourceBundle resourceBundle = ResourceBundle.getBundle("text", locale, new UTF8Control());
            ResourceBundle resourceBundle = ResourceBundle.getBundle("text", locale);
            httpRequest.getSession().setAttribute("login", resourceBundle.getString("button.login"));
            httpRequest.getSession().setAttribute("registration", resourceBundle.getString("button.registration"));
            httpRequest.getSession().setAttribute("logout", resourceBundle.getString("button.logout"));
            httpRequest.getSession().setAttribute("approve", resourceBundle.getString("button.approve"));
            httpRequest.getSession().setAttribute("cabinet", resourceBundle.getString("button.cabinet"));
            httpRequest.getSession().setAttribute("reject", resourceBundle.getString("button.reject"));
            httpRequest.getSession().setAttribute("toggle", resourceBundle.getString("button.toggle"));
            httpRequest.getSession().setAttribute("myorders", resourceBundle.getString("button.myorders"));
            httpRequest.getSession().setAttribute("mywallet", resourceBundle.getString("button.mywallet"));
            httpRequest.getSession().setAttribute("market", resourceBundle.getString("button.market"));
            httpRequest.getSession().setAttribute("buy", resourceBundle.getString("button.buy"));
            httpRequest.getSession().setAttribute("sell", resourceBundle.getString("button.sell"));
            httpRequest.getSession().setAttribute("cancel", resourceBundle.getString("button.cancel"));
            httpRequest.getSession().setAttribute("deposit", resourceBundle.getString("button.deposit"));
            httpRequest.getSession().setAttribute("withdraw", resourceBundle.getString("button.withdraw"));
            httpRequest.getSession().setAttribute("amount", resourceBundle.getString("text.amount"));

            httpRequest.getSession().setAttribute("username", resourceBundle.getString("text.username"));
            httpRequest.getSession().setAttribute("name", resourceBundle.getString("text.name"));
            httpRequest.getSession().setAttribute("surname", resourceBundle.getString("text.surname"));
            httpRequest.getSession().setAttribute("password", resourceBundle.getString("text.password"));

            httpRequest.getSession().setAttribute("administrator", resourceBundle.getString("text.administrator"));

            httpRequest.getSession().setAttribute("sec", resourceBundle.getString("text.sec"));

            httpRequest.getSession().setAttribute("usertext", resourceBundle.getString("text.usertext"));

            httpRequest.getSession().setAttribute("ask", resourceBundle.getString("text.ask"));
            httpRequest.getSession().setAttribute("bid", resourceBundle.getString("text.bid"));
            httpRequest.getSession().setAttribute("volume", resourceBundle.getString("text.volume"));
            httpRequest.getSession().setAttribute("price", resourceBundle.getString("text.price"));

            httpRequest.getSession().setAttribute("cryptocurrencymarket", resourceBundle.getString("text.cryptocurrencymarket"));

            httpRequest.getSession().setAttribute("applicationfordw", resourceBundle.getString("text.applicationfordw"));

            httpRequest.getSession().setAttribute("accessiblemarkets", resourceBundle.getString("text.accessiblemarkets"));



            HashMap<String, String> orderState = new HashMap<>();
            orderState.put("executed", resourceBundle.getString("text.executed"));
            orderState.put("canceled", resourceBundle.getString("text.canceled"));
            orderState.put("active", resourceBundle.getString("text.active"));

            HashMap<String, String> transactionType = new HashMap<>();
            transactionType.put("withdraw", resourceBundle.getString("button.withdraw"));
            transactionType.put("deposit", resourceBundle.getString("button.deposit"));

            HashMap<String, String> transactionStatus = new HashMap<>();
            transactionStatus.put("pending", resourceBundle.getString("text.pending"));
            transactionStatus.put("approved", resourceBundle.getString("text.approved"));
            transactionStatus.put("rejected", resourceBundle.getString("text.rejected"));

            HashMap<String, String> loginFailed = new HashMap<>();
            loginFailed.put("loginfailed", resourceBundle.getString("text.loginfailed"));
            loginFailed.put("protection", resourceBundle.getString("text.protection"));
            loginFailed.put("successful", resourceBundle.getString("text.successful"));


            HashMap<String, String> registrationFailed = new HashMap<>();
            registrationFailed.put("allfield", resourceBundle.getString("text.allfield"));
            registrationFailed.put("useralredyexist", resourceBundle.getString("text.useralredyexist"));
            registrationFailed.put("jsinjection", resourceBundle.getString("text.jsinjection"));

            HashMap<String, String> transactionError = new HashMap<>();
            transactionError.put("incorrectamount", resourceBundle.getString("text.incorrectamount"));
            transactionError.put("insufficientfunds", resourceBundle.getString("text.insufficientfunds"));
            transactionError.put("yourorderisaccepted", resourceBundle.getString("text.yourorderisaccepted"));
            transactionError.put("yourapplicationiscompleted", resourceBundle.getString("text.yourapplicationiscompleted"));

            HashMap<String, String> marketError = new HashMap<>();
            marketError.put("marketerror", resourceBundle.getString("text.marketerror"));

            HashMap<String, String> typeOfOrder = new HashMap<>();
            typeOfOrder.put("limit", resourceBundle.getString("text.limit"));
            typeOfOrder.put("market", resourceBundle.getString("text.market"));


            httpRequest.getSession().setAttribute("orderState", orderState);
            httpRequest.getSession().setAttribute("transactionType", transactionType);
            httpRequest.getSession().setAttribute("transactionStatus", transactionStatus);
            httpRequest.getSession().setAttribute("loginFailed", loginFailed);
            httpRequest.getSession().setAttribute("registrationFailed", registrationFailed);
            httpRequest.getSession().setAttribute("transactionError", transactionError);
            httpRequest.getSession().setAttribute("marketError", marketError);
            httpRequest.getSession().setAttribute("typeOfOrder", typeOfOrder);
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    /**
     * The empty method.
     */
    @Override
    public void destroy() {

    }
}
