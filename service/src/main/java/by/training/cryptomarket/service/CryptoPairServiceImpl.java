package by.training.cryptomarket.service;


import by.training.cryptomarket.dao.CoinDao;
import by.training.cryptomarket.dao.CryptoPairDao;
import by.training.cryptomarket.entity.CryptoPair;
import by.training.cryptomarket.entity.mapping.TraidingCouple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This service ensures interact with cryptocurrency pairs.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CryptoPairServiceImpl implements CryptoPairService {

    @Autowired
    CryptoPairDao cryptoPairDao;

    @Autowired
    CoinDao coinDao;

    /**
     * The method for getting all pairs.
     * @return collection of all pairs
     * @throws Exception Exception
     */
   public List<TraidingCouple> getAllPairs() throws Exception{


       List<CryptoPair> cryptoPairs;
       List<TraidingCouple> traidingCouples = new ArrayList<>();


       cryptoPairs = cryptoPairDao.read();



           for (CryptoPair cryptoPair : cryptoPairs) {
               TraidingCouple traidingCouple = new TraidingCouple();
               traidingCouple.setIdentity(cryptoPair.getIdentity());
               traidingCouple.setActive(cryptoPair.getActive());
               Integer first = cryptoPair.getFirstCurrency();
               Integer second = cryptoPair.getSecondCurrency();
               String pair = coinDao.read(first).getTicker() + "-" + coinDao.read(second).getTicker();
               traidingCouple.setPair(pair);
               traidingCouples.add(traidingCouple);

                 }


       traidingCouples.sort(new Comparator<TraidingCouple>() {
           @Override
           public int compare(TraidingCouple o1, TraidingCouple o2) {
               return o1.getIdentity() - o2.getIdentity();
           }
       });


       return traidingCouples;

   }



    /**
     * The method for getting active pairs.
     * @return collection of active pairs
     * @throws Exception Exception
     */
    public List<TraidingCouple> getActivePairs() throws Exception {



        List<TraidingCouple> traidingCouples = getAllPairs().stream()
                .filter(traidingCouple -> traidingCouple.getActive())
                .collect(Collectors.toList());

        return  traidingCouples;




    }

    /**
     * The method for toggling status of pair.
     * @param id id of pair
     * @throws Exception Exception
     */
    public void togglePair(final String id) throws Exception {


    Integer identity = Integer.parseInt(id.trim());

        CryptoPair cryptoPair = cryptoPairDao.read(identity);

        if (cryptoPair.getActive()) {
            cryptoPair.setActive(false);
        } else {
            cryptoPair.setActive(true);
        }
        cryptoPairDao.update(cryptoPair);

    }

    }
