import by.training.cryptomarket.dao.OrderDao;
import by.training.cryptomarket.dao.WalletDao;
import by.training.cryptomarket.entity.Order;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.enums.StateOfOrder;
import by.training.cryptomarket.enums.TypeOfOrder;
import by.training.cryptomarket.service.OrderService;
import by.training.cryptomarket.service.UserService;
import by.training.cryptomarket.service.WalletService;
import config.TestSpringConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringJUnitWebConfig(TestSpringConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExchangeServiceTest {

    @Autowired
    WalletService walletService;

    @Autowired
    WalletDao walletDao;

    @Autowired
    OrderDao orderDao;

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
            order.setState(StateOfOrder.executed);
            orderDao.update(order);
        }



    }






    @Autowired
    UserService userService;

   @Test
    void testExchangeService() throws Exception {



       Order order1 = new Order();
       order1.setUserId(4);
       order1.setAmount(0.5);
       order1.setPair("BTC-USDT");
       order1.setPrice(10000.0);
       order1.setType(TypeOfOrder.Bid);
       order1.setState(StateOfOrder.active);


       orderService.createNewOrder(order1);

       User user1 = userService.getUserById(3);

       orderService.executeOrder((Order)orderService.getBidOrdersByPair("BTC-USDT").get(0),user1);

       Wallet wallet1 = walletService.getWalletByUserId(3);

       Wallet wallet2 = walletService.getWalletByUserId(4);

       assertEquals(wallet1.getUsdt(),wallet2.getUsdt());


   }




}
