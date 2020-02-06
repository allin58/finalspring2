package by.training.cryptomarket.command.general;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.enums.Role;
import by.training.cryptomarket.service.UserService;
import by.training.cryptomarket.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
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
@Component("registration")
@Qualifier("RegistrationCommand")
public class RegistrationCommand implements Command {


    @Autowired
    UserService userService;

    @Autowired
    WalletService walletService;

    /**
     * This method allows to do registration.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response, ModelMap model) throws Exception {



        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");




        //request.getSession().setAttribute("registrationmessage", null);
        model.addAttribute("registrationmessage","");

        if (!"".equals(username) && !"".equals(name)
                && !"".equals(surname)
                && !"".equals(password)) {


            if (userService.userIsExist(username)) {

              //  request.getSession().setAttribute("registrationmessage","useralredyexist");
                model.addAttribute("registrationmessage","useralredyexist");
                return "registration";
            } else {


                if (username.contains("script")) {

                    //request.getSession().setAttribute("registrationmessage","jsinjection");
                    model.addAttribute("registrationmessage","jsinjection");
                    return "registration";
                }


                User user = new User();
                user.setUserName(username);
                user.setName(name);
                user.setSurname(surname);
                //user.setRole("user");
                user.setRole(Role.user);
                user.setHashOfPassword(password);


                userService.addUser(user);

                Integer userId = userService.getUser(username).getIdentity();
                Wallet wallet = new Wallet();
                wallet.setIdentity(userId);
                wallet.setBtc(0.0);
                wallet.setEth(0.0);
                wallet.setUsdt(0.0);
                walletService.addNewWallet(wallet);


             //   model.addAttribute("loginmessage","successful");

                request.getSession().setAttribute("loginmessage","successful");
                return "redirect:/";

            }

        } else {

           // request.getSession().setAttribute("registrationmessage","allfield");
            model.addAttribute("registrationmessage","allfield");
            return "registration";

        }







    }
}
