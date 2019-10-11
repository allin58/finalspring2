package by.training.cryptomarket.controller;

import by.training.cryptomarket.command.Command;
import by.training.cryptomarket.command.CommandFactory;
import by.training.cryptomarket.command.general.LoginCommand;
import by.training.cryptomarket.command.general.RegistrationCommand;
import by.training.cryptomarket.service.CryptoPairService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

//@RestController
@Controller
public class MarketController {


    /**
     * The field for storage a logger.
     */
    static final Logger LOGGER = LogManager.getLogger("by.training.final.ServletLogger");

  /*  @Autowired
    ServletConfig config;*/

    @Autowired
    CommandFactory commandFactory;



    @PostConstruct
    public void init() {

    }




    @RequestMapping(path = "/registration**")
    public String getRegistration(@RequestParam(value = "command", required = false)String command,
                                  ModelMap model,HttpServletRequest request,  HttpServletResponse response) {


        model.addAttribute("registrationmessage","");

        if (command != null) {
            Command command1  = commandFactory.createCommand("registration");

            try {
                String path = command1.execute(request,response,model);
                return path;
            } catch (Exception e) {
                return "registration";
            }
        }

        return "registration";
    }


    @RequestMapping(path = "/changelanguage")
    public String languageHandler(ModelMap model,HttpServletRequest request,  HttpServletResponse response) {

        model.addAttribute("loginmessage","");

        Command command1  = commandFactory.createCommand("changelanguage");
        try {
            commandFactory.createCommand("changelanguage").execute(request,response,model);
        } catch (Exception e) {
            LOGGER.info("failed to switch language");
        }


        return "redirect:/";
    }





    @RequestMapping(path = "/login**")
    public String getLogin(@RequestParam(value = "error", required = false)String error,
                           @RequestParam(value = "logout", required = false)String logout,
                           @RequestParam(value = "command", required = false)String command,
                           ModelMap model,HttpServletRequest request,  HttpServletResponse response) {


        model.addAttribute("loginmessage","");

        if (error != null)
            model.addAttribute("loginmessage","loginfailed");

        return "login";
    }



    @RequestMapping(path = "/")
    public String logining(ModelMap model,HttpServletRequest request,  HttpServletResponse response) {

        Command command  = commandFactory.createCommand("login");
        String path;
        try {
            path = command.execute(request,response,model);

        } catch (Exception e) {
            path = "error";
        }

        return path;
    }




    @RequestMapping(path = "/sec**")
    public String secHandler(ModelMap model,HttpServletRequest request,  HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Command command  = commandFactory.createCommand("togglepair");
        String path;
        try {

            path = command.execute(request,response,model);

        } catch (Exception e) {
            e.printStackTrace();
            path = "error";
        }
        return path;
    }

    @RequestMapping(path = "/market")
    public String marketHandler( @RequestParam(value = "command", required = false)String scommand,ModelMap model,HttpServletRequest request,  HttpServletResponse response) {
        Command command  = commandFactory.createCommand(scommand);
        String path;
        try {
            path = command.execute(request,response,model);
        } catch (Exception e) {
            path = "error";
        }
        return path;
    }


    @RequestMapping(path = "/order")
    public String orderHandler( @RequestParam(value = "command", required = false)String scommand,ModelMap model,HttpServletRequest request,  HttpServletResponse response) {
        Command command  = commandFactory.createCommand(scommand);
        String path;
        try {
            path = command.execute(request,response,model);
        } catch (Exception e) {
            path = "error";
        }
        return path;
    }



    @RequestMapping(path = "/wallet**")
    public String walletHandler( @RequestParam(value = "command", required = false)String scommand,ModelMap model,HttpServletRequest request,  HttpServletResponse response) {

        Command command  = commandFactory.createCommand(scommand);
        String path;
        try {

               path = command.execute(request,response,model);
        } catch (Exception e) {
            path = "error";
        }
        return path;
    }


    @RequestMapping(path = "/admin**")
    public String adminHandler( @RequestParam(value = "command", required = false)String scommand,ModelMap model,HttpServletRequest request,  HttpServletResponse response) {
        Command command  = commandFactory.createCommand(scommand);
        String path;
        try {
            path = command.execute(request,response,model);
        } catch (Exception e) {
            path = "error";
        }
        return path;
    }



    @RequestMapping(path = "/test")
    public String getTest(@RequestParam(value = "command", required = false)String command,
                                  ModelMap model,HttpServletRequest request,  HttpServletResponse response) throws Exception {

        if (true) {
            throw new Exception();
        }

        model.addAttribute("tedtatrib","asd");

      return "test";
        }






}