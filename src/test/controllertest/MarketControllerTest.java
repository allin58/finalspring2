package controllertest;


import by.training.cryptomarket.config.SpringConfig;
import by.training.cryptomarket.config.SpringSecurityConfig;
import by.training.cryptomarket.config.StandaloneMvcTestViewResolver;
import by.training.cryptomarket.config.TestSpringConfig;
import by.training.cryptomarket.controller.MarketController;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.service.UserService;
import by.training.cryptomarket.service.WalletService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringJUnitWebConfig(TestSpringConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MarketControllerTest {
@Autowired
    MarketController marketController;

    @Autowired
    MockHttpSession session;

    @Autowired
    MockHttpServletRequest request;

    private MockMvc mockMvc;

    @BeforeAll
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(marketController).setViewResolvers(new StandaloneMvcTestViewResolver()).build();
    }



    @Test
    public void testChangeLanguage() throws Exception {

      mockMvc.perform(post("/changelanguage").param("language","ru")).andExpect(status().isFound());
    }

    @Autowired
    UserService userService;

    @Test
    public void testRegistration() throws Exception {

      /*  session.setAttribute("sessionParm",user);
        this.mockMvc.perform(get("/mysessiontest").session(session)
                .accept(MediaType.TEXT_HTML))
                .andExpect(status().isOk())
                .andExpect(view().name("test"));
        */


/*        mvc.perform(get(“/index.do?id={id}”), 1)[/sourcecode]*/

        mockMvc.perform(post("/registration?command=registration").param("username","Alex")
                                                                        .param("name","Alex")
                                                                        .param("surname","Alex")
                                                                        .param("password","password"))
                                                                        .andExpect(status().isFound())
                                                                        .andExpect(view().name("redirect:/"));

        User user = userService.getUser("Alex");
        assertNotNull(user);

    }



    @Test
    public void testDeposit() throws Exception {

        User user = new User();
        user.setIdentity(3);
        String coin = "BTC";
        session.setAttribute("user",user);
        session.setAttribute("coin",coin);
        mockMvc.perform(post("/wallet?command=deposit").session(session).param("amount","1")).andExpect(status().isOk());

    }

@Autowired
    WalletService walletService;


    @Test
    public void testWithdraw() throws Exception {

        Wallet wallet = walletService.getWalletByUserId(3);

       Double beforeValue = wallet.getBtc();

        User user = new User();
        user.setIdentity(3);
        String coin = "BTC";
        session.setAttribute("user",user);
        session.setAttribute("coin",coin);
        mockMvc.perform(post("/wallet?command=withdraw").session(session).param("amount","1")).andExpect(status().isOk());

        wallet = walletService.getWalletByUserId(3);
        Double afterValue = wallet.getBtc();
        afterValue++;
        assertEquals(beforeValue,afterValue);

    }






}
