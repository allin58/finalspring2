package servicetest;

import by.training.cryptomarket.config.TestSpringConfig;
import by.training.cryptomarket.daojdbctemplate.sql.OrderDaoImpl;
import by.training.cryptomarket.daojdbctemplate.sql.UserDaoImpl;
import by.training.cryptomarket.daojdbctemplate.sql.WalletDaoImpl;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.service.CryptoPairService;
import by.training.cryptomarket.service.OrderService;
import by.training.cryptomarket.service.UserService;
import by.training.cryptomarket.service.WalletService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitWebConfig(TestSpringConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)  // эта аннотация позволяет не создавать новый экземпляр теста для каждого теста
public class ExchangeServiceTest {

    @Autowired
    UserDaoImpl userDao;


    @Autowired
    CryptoPairService cryptoPairService;

    @Autowired
    WalletService walletService;

    @Autowired
    JdbcOperations jdbcOperations;

    @Autowired
    WalletDaoImpl walletDao;

    @Autowired
    OrderDaoImpl orderDao;

    @Autowired
    OrderService orderService;

    @BeforeAll
    public void init() throws Exception{




        Wallet wallet1 = walletService.getWalletByUserId(3);
        Wallet wallet2 = walletService.getWalletByUserId(4);
        wallet1.setBtc(1.0);
        wallet1.setEth(1.0);
        wallet1.setUsdt(10000.0);
        wallet2.setBtc(1.0);
        wallet2.setEth(1.0);
        wallet2.setUsdt(20000.0);
        walletDao.update(wallet1);
        walletDao.update(wallet2);
        List<Order> orders = orderDao.read();
        for (Order order : orders) {
            order.setState("executed");
            orderDao.update(order);
        }




    }






    @Autowired
    UserService userService;

   @Test
    void testPairService() throws Exception {


       Order order1 = new Order();
       order1.setUserId(4);
       order1.setAmount(0.5);
       order1.setPair("BTC-USDT");
       order1.setPrice(10000.0);
       order1.setType("Bid");
       order1.setState("active");
       orderService.createNewOrder(order1);
       User user1 = userService.getUserById(3);
       orderService.executeOrder((Order)orderService.getBidOrdersByPair("BTC-USDT").get(0),user1);

       Wallet wallet1 = walletService.getWalletByUserId(3);
       Wallet wallet2 = walletService.getWalletByUserId(4);

       assertEquals(wallet1.getUsdt(),wallet2.getUsdt());


   }




}
