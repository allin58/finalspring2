package servicetest;

import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.service.UserService;
import config.TestSpringConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringJUnitWebConfig(TestSpringConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {



    @BeforeAll
    public void init() {


    }






    @Autowired
    UserService userService;

    @ParameterizedTest
    @MethodSource("dataUserProvider")
    void testUserService(String  name) throws Exception {

        Boolean actual = userService.userIsExist(name);

        assertTrue(actual);

    }



    Stream<Object> dataUserProvider()throws Exception {


        List<User> users = userService.getAllUsers();

        Object[] objects = new Object[users.size()];
        for (int i = 0; i < users.size(); i++) {
            objects[i] = users.get(i).getUserName();
        }


        return Stream.of(objects);
    }


}
