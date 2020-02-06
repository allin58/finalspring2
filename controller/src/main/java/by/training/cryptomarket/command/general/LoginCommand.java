package by.training.cryptomarket.command.general;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.entity.mapping.TraidingCouple;
import by.training.cryptomarket.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@Component("login")
@Qualifier("LoginCommand")
public class LoginCommand implements Command {


  /*  public UserService getUserService() {
        return userService;
    }*/

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
                  switch (user.getRole().toString()) {
                      case "admin" :

                          model.addAttribute("transactionData",transactionService.getPendingTransactions());

                          return "admin";

                      case "sec" :

                          request.getSession().setAttribute("secData", cryptoPairService.getAllPairs());

                          return "sec";

                      case "user" :

                          String pair = request.getParameter("pair");
                          if (pair == null || "".equals(pair)) {


                              List<TraidingCouple> activePairs = cryptoPairService.getActivePairs();

                              if (activePairs.size() > 0) {

                                  pair = activePairs.get(0).getPair();
                              } else {

                                  request.getSession().setAttribute("activepairs", "");
                                  request.getSession().setAttribute("pair", "");
                                  request.getSession().setAttribute("marketerror", "marketerror");
                                  request.getSession().setAttribute("ordermessage", "");
                                  return "market";
                              }
                          }
                          request.getSession().setAttribute("pair", pair);
                          request.getSession().setAttribute("typeoforder", "limit");


                          List activePairs = cryptoPairService.getActivePairs();
                          request.getSession().setAttribute("activepairs", activePairs);



                          List askList = orderService.getAskOrdersByPair(pair.trim());
                          request.getSession().setAttribute("asklist", askList);


                          List bidList = orderService.getBidOrdersByPair(pair.trim());
                          request.getSession().setAttribute("bidlist", bidList);


                          request.getSession().setAttribute("ordermessage", "");
                          return "market";
                  }
                }
            }

        model.addAttribute("loginmessage","loginfailed");

        return "login";




        }




    }

