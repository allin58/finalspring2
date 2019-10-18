package by.training.cryptomarket.controller.interceptor;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


public class LanguageInterceptor extends HandlerInterceptorAdapter {





    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {


     HttpServletRequest httpRequest =  request;
     HttpSession session = httpRequest.getSession();

     if ((session != null && session.getAttribute("language") == null)) {
      httpRequest.getSession().setAttribute("language", "en");
     }



           Locale locale = new Locale((String) httpRequest.getSession().getAttribute("language"));
            ResourceBundle resourceBundle = ResourceBundle.getBundle("text", locale);
            modelAndView.addObject("login", resourceBundle.getString("button.login"));
            modelAndView.addObject("registration", resourceBundle.getString("button.registration"));
            modelAndView.addObject("logout", resourceBundle.getString("button.logout"));
            modelAndView.addObject("approve", resourceBundle.getString("button.approve"));
            modelAndView.addObject("cabinet", resourceBundle.getString("button.cabinet"));
            modelAndView.addObject("reject", resourceBundle.getString("button.reject"));
            modelAndView.addObject("toggle", resourceBundle.getString("button.toggle"));
            modelAndView.addObject("myorders", resourceBundle.getString("button.myorders"));
            modelAndView.addObject("mywallet", resourceBundle.getString("button.mywallet"));
            modelAndView.addObject("market", resourceBundle.getString("button.market"));
            modelAndView.addObject("buy", resourceBundle.getString("button.buy"));
            modelAndView.addObject("sell", resourceBundle.getString("button.sell"));
            modelAndView.addObject("cancel", resourceBundle.getString("button.cancel"));
            modelAndView.addObject("deposit", resourceBundle.getString("button.deposit"));
            modelAndView.addObject("withdraw", resourceBundle.getString("button.withdraw"));
            modelAndView.addObject("amount", resourceBundle.getString("text.amount"));

            modelAndView.addObject("username", resourceBundle.getString("text.username"));
            modelAndView.addObject("name", resourceBundle.getString("text.name"));
            modelAndView.addObject("surname", resourceBundle.getString("text.surname"));
            modelAndView.addObject("password", resourceBundle.getString("text.password"));

            modelAndView.addObject("administrator", resourceBundle.getString("text.administrator"));

            modelAndView.addObject("sec", resourceBundle.getString("text.sec"));

            modelAndView.addObject("usertext", resourceBundle.getString("text.usertext"));

            modelAndView.addObject("ask", resourceBundle.getString("text.ask"));
            modelAndView.addObject("bid", resourceBundle.getString("text.bid"));
            modelAndView.addObject("volume", resourceBundle.getString("text.volume"));
            modelAndView.addObject("price", resourceBundle.getString("text.price"));

            modelAndView.addObject("cryptocurrencymarket", resourceBundle.getString("text.cryptocurrencymarket"));

            modelAndView.addObject("applicationfordw", resourceBundle.getString("text.applicationfordw"));

            modelAndView.addObject("accessiblemarkets", resourceBundle.getString("text.accessiblemarkets"));






            HashMap<String, String> orderState = new HashMap<>();
            orderState.put("executed", resourceBundle.getString("text.executed"));
            orderState.put("canceled", resourceBundle.getString("text.canceled"));
            orderState.put("active", resourceBundle.getString("text.active"));

            HashMap<String, String> transactionType = new HashMap<>();
            transactionType.put("withdraw", resourceBundle.getString("button.withdraw"));
            transactionType.put("deposit", resourceBundle.getString("button.deposit"));

            HashMap<String, String> transactionStatus = new HashMap<>();
            transactionStatus.put("pending", resourceBundle.getString("text.pending"));
            transactionStatus.put("approved", resourceBundle.getString("text.approved"));
            transactionStatus.put("rejected", resourceBundle.getString("text.rejected"));

            HashMap<String, String> loginFailed = new HashMap<>();
            loginFailed.put("loginfailed", resourceBundle.getString("text.loginfailed"));
            loginFailed.put("protection", resourceBundle.getString("text.protection"));
            loginFailed.put("successful", resourceBundle.getString("text.successful"));
            loginFailed.put("", "");


            HashMap<String, String> registrationFailed = new HashMap<>();
            registrationFailed.put("allfield", resourceBundle.getString("text.allfield"));
            registrationFailed.put("useralredyexist", resourceBundle.getString("text.useralredyexist"));
            registrationFailed.put("jsinjection", resourceBundle.getString("text.jsinjection"));
            registrationFailed.put("", "");

            HashMap<String, String> transactionError = new HashMap<>();
            transactionError.put("incorrectamount", resourceBundle.getString("text.incorrectamount"));
            transactionError.put("insufficientfunds", resourceBundle.getString("text.insufficientfunds"));
            transactionError.put("yourorderisaccepted", resourceBundle.getString("text.yourorderisaccepted"));
            transactionError.put("yourapplicationiscompleted", resourceBundle.getString("text.yourapplicationiscompleted"));
            transactionError.put("", "");

            HashMap<String, String> marketError = new HashMap<>();
            marketError.put("marketerror", resourceBundle.getString("text.marketerror"));
            marketError.put("", "");

            HashMap<String, String> typeOfOrder = new HashMap<>();
            typeOfOrder.put("limit", resourceBundle.getString("text.limit"));
            typeOfOrder.put("market", resourceBundle.getString("text.market"));


            modelAndView.addObject("orderState", orderState);
            modelAndView.addObject("transactionType", transactionType);
            modelAndView.addObject("transactionStatus", transactionStatus);
            modelAndView.addObject("loginFailed", loginFailed);
            modelAndView.addObject("registrationFailed", registrationFailed);
            modelAndView.addObject("transactionError", transactionError);
            modelAndView.addObject("marketError", marketError);
            modelAndView.addObject("typeOfOrder", typeOfOrder);




     Properties props = new Properties();


     String version;
     String buildDate;

     try {

      props.load(request.getServletContext().getResourceAsStream("/WEB-INF/classes/build.properties"));
      version = props.getProperty("build.version");
      buildDate = props.getProperty("build.date");

     } catch (Exception e) {
      e.printStackTrace();
      version = "-";
      buildDate = "-";;
     }


     modelAndView.addObject("buildVersion", version);
     modelAndView.addObject("buildDate", buildDate);



     super.postHandle(request, response, handler, modelAndView);
    }
}
