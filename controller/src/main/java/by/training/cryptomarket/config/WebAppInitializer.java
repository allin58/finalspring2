package by.training.cryptomarket.config;

import by.training.cryptomarket.dao.config.RepositoryConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebAppInitializer implements WebApplicationInitializer  {

    public void onStartup(ServletContext ctx) throws ServletException {

        AnnotationConfigWebApplicationContext webCtx = new AnnotationConfigWebApplicationContext();
        webCtx.register(SpringConfig.class);
     // webCtx.register(SpringSecurityConfig.class);
        webCtx.setServletContext(ctx);
        webCtx.register(RepositoryConfig.class);
        ServletRegistration.Dynamic servlet = ctx.addServlet("dispatcher", new DispatcherServlet(webCtx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }










}