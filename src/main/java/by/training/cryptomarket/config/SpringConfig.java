package by.training.cryptomarket.config;

import by.training.cryptomarket.controller.interceptor.LanguageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.servlet.ServletConfig;
import java.io.IOException;
import java.util.Properties;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = "by.training.cryptomarket")
@EnableTransactionManagement
@EnableAspectJAutoProxy
@PropertySource("classpath:db.properties")
public class SpringConfig implements WebMvcConfigurer {

  /*  @Autowired
    ServletConfig config;*/

    @Autowired
    Environment env;


/*

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/uui");
    }
*/

    @Bean
    public FreeMarkerViewResolver freemarkerViewResolver() {
        FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
        resolver.setCache(true);
        resolver.setPrefix("");
        resolver.setSuffix(".ftl");
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/templates/");

    /*    freemarker.template.Configuration configuration = null;
        try {
            configuration = freeMarkerConfigurer.createConfiguration();
        } catch (Exception e) {
            e.printStackTrace();
        }

        configuration.setTemplateExceptionHandler(new MyTemplateExceptionHandler());
        freeMarkerConfigurer.setConfiguration(configuration);*/

        return freeMarkerConfigurer;
    }




    @Autowired
    DriverManagerDataSource driverManagerDataSource;


   @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(driverManagerDataSource);


    }




    @Bean
    public DriverManagerDataSource driverManagerDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
      //  SingleConnectionDataSource driverManagerDataSource = new SingleConnectionDataSource();




           /* Properties prop = new Properties();
            prop.load(config.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties"));
            driverManagerDataSource.setDriverClassName(prop.getProperty("db.drivername"));
            driverManagerDataSource.setUsername(prop.getProperty("db.username"));
            driverManagerDataSource.setPassword(prop.getProperty("db.password"));
            driverManagerDataSource.setUrl(prop.getProperty("db.url"));
              */



        driverManagerDataSource.setDriverClassName(env.getProperty("db.drivername"));
        driverManagerDataSource.setUsername(env.getProperty("db.username"));
        driverManagerDataSource.setPassword(env.getProperty("db.password"));
        driverManagerDataSource.setUrl(env.getProperty("db.url"));


        return driverManagerDataSource;
    }


    @Bean
    @Qualifier("jdbcTemplate")
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(driverManagerDataSource);
        return jdbcTemplate;
    }


    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LanguageInterceptor())
                .addPathPatterns("/**");


    }



    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
