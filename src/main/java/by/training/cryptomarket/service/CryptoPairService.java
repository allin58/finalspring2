package by.training.cryptomarket.service;


import by.training.cryptomarket.daojdbctemplate.sql.CoinDaoImpl;
import by.training.cryptomarket.daojdbctemplate.sql.CryptoPairDaoImpl;
import by.training.cryptomarket.entity.CryptoPair;
import by.training.cryptomarket.entity.mapping.TraidingCouple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


/**
 * This service ensures interact with cryptocurrency pairs.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
@Component
public class CryptoPairService {

    @Autowired
    CryptoPairDaoImpl cryptoPairDao;

    @Autowired
    CoinDaoImpl coinDao;

    /**
     * The method for getting all pairs.
     * @return collection of all pairs
     * @throws Exception Exception
     */
   public List<TraidingCouple> getAllPairs() throws Exception {


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

       List<TraidingCouple> traidingCouplesSorted = new ArrayList<>();

       for (int i = 1; i <=  traidingCouples.size(); i++) {

           for (TraidingCouple traidingCouple : traidingCouples) {
               if (traidingCouple.getIdentity() == i)
                   traidingCouplesSorted.add(traidingCouple);

           }


       }




      return  traidingCouplesSorted;

   }


    /**
     * The method for getting active pairs.
     * @return collection of active pairs
     * @throws Exception Exception
     */
    public List<TraidingCouple> getActivePairs() throws Exception {


        List<TraidingCouple> traidingCouples = new ArrayList<>();
        for (TraidingCouple traidingCouple : getAllPairs()) {
            if (traidingCouple.getActive()) {
                traidingCouples.add(traidingCouple);
            }
        }

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
