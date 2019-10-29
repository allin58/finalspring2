import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.service.UserService;
import config.TestSpringConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Stream;

import static junit.framework.Assert.assertTrue;

@SpringJUnitWebConfig(TestSpringConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest {

    @PersistenceContext
    private EntityManager entityManager;


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
