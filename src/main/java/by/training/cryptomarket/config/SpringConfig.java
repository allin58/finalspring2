package by.training.cryptomarket.config;

import by.training.cryptomarket.controller.interceptor.LanguageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
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
public class SpringConfig implements WebMvcConfigurer {

    @Autowired
    ServletConfig config;



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
        //resolver.setContentType("UTF-8");

        return resolver;
    }

    @Bean
    public FreeMarkerConfigurer freemarkerConfig() {
        FreeMarkerConfigurer freeMarkerConfigurer = new FreeMarkerConfigurer();
        freeMarkerConfigurer.setTemplateLoaderPath("/WEB-INF/templates/");
       // freeMarkerConfigurer.setDefaultEncoding("UTF-8");

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

        try {
            Properties prop = new Properties();

            prop.load(config.getServletContext().getResourceAsStream("/WEB-INF/classes/db.properties"));
            driverManagerDataSource.setDriverClassName(prop.getProperty("db.drivername"));
            driverManagerDataSource.setUsername(prop.getProperty("db.username"));
            driverManagerDataSource.setPassword(prop.getProperty("db.password"));
            driverManagerDataSource.setUrl(prop.getProperty("db.url"));
        } catch (IOException e) {
         /*
        driverManagerDataSource.setDriverClassName("org.postgresql.Driver");
        driverManagerDataSource.setUsername("market5");
        driverManagerDataSource.setPassword("market");
        driverManagerDataSource.setUrl("jdbc:postgresql://localhost:5432/market");*/
        }




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
