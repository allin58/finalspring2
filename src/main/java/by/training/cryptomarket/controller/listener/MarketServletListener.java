package by.training.cryptomarket.controller.listener;

import by.training.cryptomarket.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;


/**
 * This class exists for logging of command from users.
 */


@WebListener
public class MarketServletListener implements ServletRequestListener {

    /**
     * The field for storage a logger.
     */
    static final Logger LOGGER = LogManager.getLogger("by.training.final.ServletLogger");

    /**
     * The empty method.
     * @param servletRequestEvent servletRequestEvent
     */
    @Override
    public void requestDestroyed(final ServletRequestEvent servletRequestEvent) {

    }


    /**
     * This method  logs of command from users.
     * @param servletRequestEvent servletRequestEvent
     */
    @Override
    public void requestInitialized(final ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
       if (request.getParameter("command") != null) {
           String userName = "unknown";
           String role = "unknown";
           if (request.getSession().getAttribute("user") != null) {
               User user = (User) request.getSession().getAttribute("user");
               userName = user.getUserName();
               role = user.getRole();
           }
           LOGGER.info("username-" + userName + ", role-" + role + ", command-" +  request.getParameter("command"));


       }

    }
}
