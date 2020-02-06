import by.training.cryptomarket.entity.mapping.TraidingCouple;
import by.training.cryptomarket.service.CryptoPairService;
import config.TestSpringConfig;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitWebConfig(TestSpringConfig.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CryptoPairServiceTest {


    @Autowired
    CryptoPairService cryptoPairService;


    @BeforeAll
    public void init() {

    }







    @ParameterizedTest
    @MethodSource("dataPairProvider")
    void testPairService(Boolean argument1,Boolean argument2) {

        assertEquals(argument1,argument2);
    }



    Stream<Arguments> dataPairProvider()throws Exception {


        List<TraidingCouple> allPairsBefore = cryptoPairService.getAllPairs();

        for (TraidingCouple traidingCouple : allPairsBefore) {
            cryptoPairService.togglePair(traidingCouple.getIdentity().toString());
            cryptoPairService.togglePair(traidingCouple.getIdentity().toString());

        }

        List<TraidingCouple> allPairsAfter = cryptoPairService.getAllPairs();
        Boolean[][] objects = new Boolean[allPairsBefore.size()][2];
        for (int i = 0; i < allPairsBefore.size(); i++) {
            objects[i][0] = allPairsAfter.get(i).getActive();
            objects[i][1] = allPairsBefore.get(i).getActive();
        }



        return Stream.of( Arguments.of(objects[0]),
                Arguments.of(objects[1]),
                Arguments.of(objects[2]));


    }


}
