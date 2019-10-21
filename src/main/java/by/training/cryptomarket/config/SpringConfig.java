package by.training.cryptomarket.config;

import by.training.cryptomarket.controller.interceptor.LanguageInterceptor;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
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



    @Autowired
    Environment env;



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

        return freeMarkerConfigurer;
    }




    @Autowired
    DriverManagerDataSource driverManagerDataSource;

/*
   @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(driverManagerDataSource);


    }*/


    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(driverManagerDataSource());
        sessionFactory.setPackagesToScan("by.training.cryptomarket.entity");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }




    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }


    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
      //  properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        return properties;
    }



    @Bean
    public DriverManagerDataSource driverManagerDataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
      //  SingleConnectionDataSource driverManagerDataSource = new SingleConnectionDataSource();


        driverManagerDataSource.setDriverClassName(env.getProperty("db.drivername"));
        driverManagerDataSource.setUsername(env.getProperty("db.username"));
        driverManagerDataSource.setPassword(env.getProperty("db.password"));
        driverManagerDataSource.setUrl(env.getProperty("db.url"));


        return driverManagerDataSource;
    }


/*
    @Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(driverManagerDataSource);
        return jdbcTemplate;
    }
*/


    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LanguageInterceptor())
                .addPathPatterns("/**");


    }



    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
