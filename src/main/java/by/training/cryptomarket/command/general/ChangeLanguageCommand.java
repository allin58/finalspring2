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
public class ChangeLanguageCommand implements Command {


    /**
     * This method allows change language.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response, ModelMap model) throws Exception {
        request.getSession().setAttribute("language", request.getParameter("language"));

      //  model.addAttribute("language",request.getParameter("language"));
       // model.addAttribute("loginmessage","null");

        request.getSession().setAttribute("loginmessage", "");
        return "login";
    }
}
