package by.training.cryptomarket.command.general;

import by.training.cryptomarket.command.Command;
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
public class ToRegistrationCommand implements Command {


    /**
     * This method which redirect to page of registration.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
       // request.getSession().setAttribute("registrationmessage",null);
        model.addAttribute("registrationmessage","");
        return "registration";
    }
}
