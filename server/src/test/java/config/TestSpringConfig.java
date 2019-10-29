package config;



import by.training.cryptomarket.config.SpringConfig;
import by.training.cryptomarket.controller.interceptor.LanguageInterceptor;
import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.util.Properties;


@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"by.training.cryptomarket"}, excludeFilters={
        @ComponentScan.Filter(type=FilterType.ASSIGNABLE_TYPE, value= SpringConfig.class)})
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class TestSpringConfig implements WebMvcConfigurer {

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
    EmbeddedPostgres embeddedPostgres;

    @Bean(destroyMethod = "close")
    protected EmbeddedPostgres embeddedPostgres() throws IOException {
        return EmbeddedPostgres.builder().setCleanDataDirectory(true).start();
    }


    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        //  properties.put("hibernate.show_sql", env.getRequiredProperty("hibernate.show_sql"));
        return properties;
    }


    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }




    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(embeddedPostgres.getPostgresDatabase());
        em.setPackagesToScan("by.training.cryptomarket.entity");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties());





        return em;
    }



    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }



    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LanguageInterceptor())
                .addPathPatterns("/**");
    }



    @Bean
    protected JdbcTemplate jdbcTemplate() throws IOException {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(embeddedPostgres().getPostgresDatabase());


      jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS \"coins\" (\n" +

                "    \"identity\" INTEGER PRIMARY KEY,\n" +
                "    \"coin\" VARCHAR(5),\n" +
                "    \"full_name\" VARCHAR(20) NOT NULL);\n" +
                "\n" +
                "\n" +
                "CREATE TYPE roles AS ENUM (\n" +
                "  'user',\n" +
                "  'sec', \n" +
                "  'admin');\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS \"users\" (\n" +
                "    \"identity\"  SERIAL PRIMARY KEY,\n" +
                "    \"user_name\" VARCHAR(30) NOT NULL unique,\n" +
                "    \"name\" VARCHAR(30) NOT NULL,\n" +
                "    \"surname\" VARCHAR(30) NOT NULL,\n" +
                "    \"hash_of_password\" VARCHAR(64) NOT NULL,\n" +
                "    \"role\" roles NOT NULL\n" +
                ");\n" +
                "\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS \"cryptocurrency_pairs\" (\n" +
                "    \"identity\" INTEGER PRIMARY KEY,\n" +
                "    \"first_currency\" INTEGER NOT NULL,\n" +
                "    \"second_currency\" INTEGER NOT NULL,\n" +
                "    \"active\" BOOL NOT NULL,\n" +
                "    FOREIGN KEY (\"first_currency\")\n" +
                "    REFERENCES \"coins\" (\"identity\")\n" +
                "    ON UPDATE CASCADE\n" +
                "    ON DELETE RESTRICT,\n" +
                "\n" +
                "    FOREIGN KEY (\"second_currency\")\n" +
                "    REFERENCES \"coins\" (\"identity\")\n" +
                "    ON UPDATE CASCADE\n" +
                "    ON DELETE RESTRICT\n" +
                "\n" +
                ");\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS \"wallets\" (\n" +
                "    \"user_id\" INTEGER NOT NULL,\n" +
                "    \"btc\" DECIMAL(12,6) NOT NULL,\n" +
                "    \"eth\"  DECIMAL(12,6) NOT NULL,\n" +
                "    \"usdt\"  DECIMAL(12,6) NOT NULL,\n" +
                "\n" +
                "\n" +
                "      FOREIGN KEY (\"user_id\")\n" +
                "    REFERENCES \"users\" (\"identity\")\n" +
                "    ON UPDATE CASCADE\n" +
                "    ON DELETE RESTRICT\n" +
                ");\n" +
                "\n" +
                "\n" +
                "CREATE TYPE typesOfOrder AS ENUM (\n" +
                "  'Ask',\n" +
                "  'Bid' );\n" +
                "\n" +
                "CREATE TYPE statesOfOrder AS ENUM (\n" +
                "  'active',\n" +
                "  'executed',\n" +
                "  'canceled');\n" +
                "\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS \"orders\" (\n" +
                "  \"identity\" SERIAL PRIMARY KEY,\n" +
                "  \"user_id\" INTEGER NOT NULL,\n" +
                "  \"pair\"  VARCHAR(30)   NOT NULL,\n" +
                "  \"amount\"  decimal(12, 6)  NOT NULL,\n" +
                "  \"price\"  decimal(12, 6) NOT NULL,\n" +
                "  \"type\" typesOfOrder  NOT NULL,\n" +
                "  \"state\"  statesOfOrder  NOT NULL,\n" +
                "\n" +
                "FOREIGN KEY (\"user_id\")\n" +
                "REFERENCES \"users\" (\"identity\")\n" +
                "ON UPDATE CASCADE ON DELETE RESTRICT\n" +
                ");\n" +
                "\n" +
                "CREATE TYPE transactionType AS ENUM (\n" +
                "  'deposit',\n" +
                "  'withdraw');\n" +
                "\n" +
                "\n" +
                "CREATE TYPE transactionStatus AS ENUM (\n" +
                "  'pending',\n" +
                "  'approved',\n" +
                "   'rejected');\n" +
                "\n" +
                "CREATE TABLE IF NOT EXISTS \"transactions\" (\n" +
                "  \"identity\" SERIAL PRIMARY KEY,\n" +
                "  \"user_id\" INTEGER NOT NULL,\n" +
                "  \"coin_id\" INTEGER NOT NULL,\n" +
                "  \"amount\"  decimal(12, 6) NOT NULL,\n" +
                "  \"type\"  transactionType NOT NULL,\n" +
                "  \"date\" TIMESTAMP,\n" +
                "  \"status\"  transactionStatus NOT NULL,\n" +
                "\n" +
                "FOREIGN KEY (\"user_id\")\n" +
                "REFERENCES \"users\" (\"identity\")\n" +
                "ON UPDATE CASCADE ON DELETE RESTRICT,\n" +
                "\n" +
                "    FOREIGN KEY (\"coin_id\")\n" +
                "REFERENCES \"coins\" (\"identity\")\n" +
                "ON UPDATE CASCADE ON DELETE RESTRICT\n" +
                "\n" +
                ");\n" +
                "\n" +
                "\n" +
                "\n" +
                "INSERT INTO public.coins(\n" +
                "\tidentity, coin, full_name)\n" +
                "\tVALUES (1, 'BTC', 'Bitcoin'),(2,'ETH', 'Ethereum'),(3,'USDT', 'Tether');\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "INSERT INTO public.users (user_name, name, surname, hash_of_password, role)\n" +
                "VALUES\n" +
                "('Alice', 'Alice','Williams','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','admin'),\n" +
                "('Bob', 'Bob','Johnson','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','sec'),\n" +
                "('Eve', 'Eve','Jones','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','user'),\n" +
                "('Peggy', 'Peggy','Brown','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','user'),\n" +
                "('Craig', 'Craig','Miller','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','user'),\n" +
                "('Mallory', 'Mallory','Anderson','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','user'),\n" +
                "('Walter', 'Walter','Jackson','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8','user');\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "INSERT INTO public.wallets(user_id, \"btc\", \"eth\", \"usdt\")\n" +
                "VALUES\n" +
                "(1,10,10,10),\n" +
                "(2,  20,15,18),\n" +
                "(3,  10,10,10),\n" +
                "(4,  5,10,10),\n" +
                "(5,  10,7,10),\n" +
                "(6,  10,10,10),\n" +
                "(7,  10,10,10);\n" +
                "\n" +
                "\n" +
                "\n" +
                "INSERT INTO public.cryptocurrency_pairs (\"identity\", \"first_currency\", \"second_currency\", \"active\")\n" +
                "VALUES\n" +
                "(1, 1,3,TRUE),\n" +
                "(2, 2,3,TRUE),\n" +
                "(3, 1,2,TRUE);\n" +
                "\n" +
                "\n" +
                "INSERT INTO public.orders (\"user_id\", \"pair\", \"amount\",\"price\",\"type\",\"state\")\n" +
                "VALUES\n" +
                "(1, 'BTC-USDT',100,15000,'Ask','executed'),\n" +
                "(2, 'BTC-USDT',50,14000,'Ask','executed'),\n" +
                "(3, 'BTC-USDT',50,13000,'Ask','active'),\n" +
                "(4, 'BTC-USDT',50,12000,'Ask','active'),\n" +
                "(5, 'BTC-USDT',50,11000,'Ask','active'),\n" +
                "(5, 'BTC-USDT',50,16000,'Ask','active'),\n" +
                "(1, 'BTC-USDT',50,10500,'Ask','active'),\n" +
                "(1, 'BTC-USDT',100,15000,'Bid','executed'),\n" +
                "(2, 'BTC-USDT',50,14000,'Bid','executed'),\n" +
                "(3, 'BTC-USDT',50,10000,'Bid','active'),\n" +
                "(4, 'BTC-USDT',50,9585,'Bid','active'),\n" +
                "(5, 'BTC-USDT',50,8000,'Bid','active'),\n" +
                "(1, 'BTC-USDT',50,8538,'Bid','active'),\n" +
                "\n" +
                "(1, 'ETH-USDT',100,15000,'Ask','executed'),\n" +
                "(2, 'ETH-USDT',50,14000,'Ask','executed'),\n" +
                "(3, 'ETH-USDT',50,350,'Ask','active'),\n" +
                "(4, 'ETH-USDT',50,320,'Ask','active'),\n" +
                "(5, 'ETH-USDT',50,310,'Ask','active'),\n" +
                "(1, 'ETH-USDT',50,300,'Ask','active'),\n" +
                "(1, 'ETH-USDT',100,290,'Bid','executed'),\n" +
                "(2, 'ETH-USDT',50,280,'Bid','executed'),\n" +
                "(3, 'ETH-USDT',50,285,'Bid','active'),\n" +
                "(4, 'ETH-USDT',50,270,'Bid','active'),\n" +
                "(5, 'ETH-USDT',50,250,'Bid','active'),\n" +
                "(1, 'ETH-USDT',50,270,'Bid','active'),\n" +
                "\n" +
                "(1, 'BTC-ETH',100,15000,'Ask','executed'),\n" +
                "(2, 'BTC-ETH',50,14000,'Ask','executed'),\n" +
                "(3, 'BTC-ETH',50,50,'Ask','active'),\n" +
                "(4, 'BTC-ETH',50,49,'Ask','active'),\n" +
                "(5, 'BTC-ETH',50,47,'Ask','active'),\n" +
                "(1, 'BTC-ETH',50,43,'Ask','active'),\n" +
                "(1, 'BTC-ETH',100,290,'Bid','executed'),\n" +
                "(2, 'BTC-ETH',50,280,'Bid','executed'),\n" +
                "(3, 'BTC-ETH',50,40,'Bid','active'),\n" +
                "(4, 'BTC-ETH',50,41,'Bid','active'),\n" +
                "(5, 'BTC-ETH',50,35,'Bid','active'),\n" +
                "(1, 'BTC-ETH',50,17,'Bid','active'),\n" +
                "\n" +
                "(1, 'BTC-USDT',1,1,'Ask','executed');");

        return jdbcTemplate;

    }


}
