package by.training.cryptomarket.command.general;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.entity.mapping.TraidingCouple;
import by.training.cryptomarket.service.CryptoPairService;
import by.training.cryptomarket.service.OrderService;
import by.training.cryptomarket.service.TransactionService;
import by.training.cryptomarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import javax.annotation.PostConstruct;
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
@Qualifier("LoginCommand")
public class LoginCommand implements Command {


    public UserService getUserService() {
        return userService;
    }

    @Autowired
    UserService userService;

    @Autowired
    TransactionService transactionService;

    @Autowired
    OrderService orderService;

    @Autowired
    CryptoPairService cryptoPairService;



    /**
     * This method allows to do logging.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response, ModelMap model) throws Exception {




        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        model.addAttribute("loginmessage","");



            if (username != null ) {

               Integer id = userService.getIdByUserNameAndPassword(username);

                User user = userService.getUserById(id);



                if (user != null) {

                    request.getSession().setAttribute("user", user);
                  switch (user.getRole()) {
                      case "admin" :
                         //request.getSession().setAttribute("transactionData",  transactionService.getPendingTransactions());
                          model.addAttribute("transactionData",transactionService.getPendingTransactions());

                          return "admin";

                      case "sec" :
                          request.getSession().setAttribute("secData", cryptoPairService.getAllPairs());
                         // model.addAttribute("secData", cryptoPairService.getAllPairs());
                          return "sec";

                      case "user" :
                          String pair = request.getParameter("pair");
                          if (pair == null || "".equals(pair)) {


                              List<TraidingCouple> activePairs = cryptoPairService.getActivePairs();
                              if (activePairs.size() > 0) {

                                  pair = activePairs.get(0).getPair();
                              } else {
                                  model.addAttribute("activepairs", "");
                                  model.addAttribute("pair", "");
                                  model.addAttribute("marketerror", "marketerror");

                                 /* request.getSession().setAttribute("activepairs", null);
                                  request.getSession().setAttribute("pair", null);
                                  request.getSession().setAttribute("marketerror", "marketerror");*/
                                  return "market";
                              }
                          }
                          request.getSession().setAttribute("pair", pair);
                          request.getSession().setAttribute("typeoforder", "limit");
                         // model.addAttribute("pair", pair);
                         // model.addAttribute("typeoforder", "limit");


                          List activePairs = cryptoPairService.getActivePairs();
                          request.getSession().setAttribute("activepairs", activePairs);
                         // model.addAttribute("activepairs", activePairs);


                          List askList = orderService.getAskOrdersByPair(pair.trim());
                          request.getSession().setAttribute("asklist", askList);
                         // model.addAttribute("asklist", askList);

                          List bidList = orderService.getBidOrdersByPair(pair.trim());
                          request.getSession().setAttribute("bidlist", bidList);
                         // model.addAttribute("bidlist", bidList);

                          request.getSession().setAttribute("ordermessage", "");
                          return "market";
                  }
                }
            }

        model.addAttribute("loginmessage","loginfailed");

        //request.getSession().setAttribute("loginmessage", "loginfailed");

        return "login";




        }




    }

