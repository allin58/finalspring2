package by.training.cryptomarket.controller.filter;

import by.training.cryptomarket.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This filter checks access rights.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */

/*@WebFilter(urlPatterns = "/*")
@Order(4)*/
public class AccessFilter implements Filter {

    /**
     * The empty method.
     * @param filterConfig filterConfig
     * @throws ServletException
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }

    /**
     *  The method for filtering.
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
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();



        if (session != null && session.getAttribute("user") != null) {
            if (checkRequest(httpRequest)) {

                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                session.setAttribute("loginmessage", "protection");
                session.setAttribute("user", null);
                RequestDispatcher rd = servletRequest.getRequestDispatcher("login.ftl");
                rd.forward(httpRequest, httpResponse);
            }



        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }






    }

    /**
     * This method checks access rights.
     * @param httpRequest httpRequest
     * @return true if access matches role
     */
    public boolean checkRequest(final HttpServletRequest httpRequest) {

        User user = (User) httpRequest.getSession().getAttribute("user");

        String role = user.getRole();


        String servletPath = httpRequest.getServletPath();


        if ("admin".equals(role) && (!servletPath.contains("admin"))
                && !("logout".equals(httpRequest.getParameter("command"))
                ||  "approvetransaction".equals(httpRequest.getParameter("command"))
                || "rejectransaction".equals(httpRequest.getParameter("command")))) {

            return false;
        }


        if ("sec".equals(role)
                && (!servletPath.contains("sec"))
                && !("logout".equals(httpRequest.getParameter("command"))
                || "togglepair".equals(httpRequest.getParameter("command")))) {

            return false;
        }


        if ("/views/registration.ftl".equals(servletPath.trim())
                || "/views/login.ftl".equals(servletPath.trim())) {
            return false;
        }

if ("user".equals(role)
        && (servletPath.contains("sec")
        || servletPath.contains("admin"))) {
    return false;
}

        return true;
    }


    /**
     * The empty method.
     */
    @Override
    public void destroy() {

    }
}
