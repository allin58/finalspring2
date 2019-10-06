package by.training.cryptomarket.controller.filter;


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
@Order(3)*/
public class LoginFilter implements Filter {


    /**
     * The empty method.
     * @param config config
     * @throws ServletException ServletException
     */
    public void init(final FilterConfig config) throws ServletException {

    }


    /**
     *  This method checks access rights.
     * @param servletRequest servletRequest
     * @param servletResponse servletResponse
     * @param chain chain
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain chain) throws IOException, ServletException {


        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        String servletPath = httpRequest.getServletPath();

        if ((session != null && session.getAttribute("user") != null)
                || "/login.html".equals(servletPath)
                || "/views/registration.ftl".equals(servletPath)) {



         chain.doFilter(servletRequest, servletResponse);

        }  else {

            System.out.println(new java.io.File( "." ).getCanonicalPath());
            RequestDispatcher rd = servletRequest.getRequestDispatcher("/pages/login.ftl");
            rd.forward(httpRequest, httpResponse);

        }



    }


    /**
     * The empty method.
     */
    public void destroy() {

    }

}
