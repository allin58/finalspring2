package by.training.cryptomarket.command;

import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for command template.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public interface Command {


    /**
     * Abstract method for getting appropriate jsp.
     * @param request request
     * @param response response
     * @return jsp
     * @throws Exception Exception
     */
    String execute(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception;
}
