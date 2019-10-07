package by.training.cryptomarket.command.sec;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.service.CryptoPairService;
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
@Component
@Qualifier("TogglePairCommand")
public class TogglePairCommand implements Command {

    @Autowired
    CryptoPairService cryptoPairService;

    /**
     * This method for toggling state of markets.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response, ModelMap model) throws Exception {



        String identity = request.getParameter("identity");

        cryptoPairService.togglePair(identity);

       // request.getSession().setAttribute("secData", cryptoPairService.getAllPairs());
        model.addAttribute("secData", cryptoPairService.getAllPairs());
        return "sec";
    }
}
