package by.training.cryptomarket.config;

import by.training.cryptomarket.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.sql.DataSource;


@Configuration
@ComponentScan(basePackages = "by.training.cryptomarket")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired()
    private UserDetailsServiceImpl userDetailsService;



    @Override
    protected void configure(HttpSecurity http) throws Exception {

                CharacterEncodingFilter filter = new CharacterEncodingFilter();
                filter.setEncoding("UTF-8");
                filter.setForceEncoding(true);
                http.addFilterBefore(filter, CsrfFilter.class);



        http
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/uui/**","/login**","/registration**","/changelanguage","/test").permitAll()
                .antMatchers("/").hasAnyAuthority("admin","sec","user")
                .antMatchers("/sec**").hasAuthority("sec")
                .antMatchers("/market**","/wallet**","/order**").hasAuthority("user")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(getShaPasswordEncoder());

    }


    @Bean
    public MessageDigestPasswordEncoder getShaPasswordEncoder(){
        return new MessageDigestPasswordEncoder("SHA-256");
    }
}


