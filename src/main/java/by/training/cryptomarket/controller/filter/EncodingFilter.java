package by.training.cryptomarket.controller.filter;


import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


/**
 * This filter sets of encoding.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */


@WebFilter(urlPatterns = "/*")
@Order(1)
public class EncodingFilter implements Filter {

    /**
     * The empty method.
     * @param filterConfig filterConfig
     * @throws ServletException
     */
    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {

    }



    /**
     *  The method for setting of encoding.
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

        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * The empty method.
     */
    @Override
    public void destroy() {

    }
}
