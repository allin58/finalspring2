package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.entity.Coin;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CoinDaoImpl extends BaseDao{




    public List<Coin> read() throws Exception {


        List<Coin> list = null;
        try {
            list = entityManager.createQuery("from Coin").getResultList();
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CoinDaoImpl, method read()");
           throw new PersistentException();
        }

        return list;
    }

    public void create(Coin coin) throws Exception {
        try {
            entityManager.persist(coin);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CoinDaoImpl, method create()");
            throw new PersistentException();
        }
    }

    public void delete(Coin coin) throws Exception{
        try {
            entityManager.remove(coin);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CoinDaoImpl, method delete()");
            throw new PersistentException();
        }
    }


    public void delete(Integer identity) throws PersistentException {
        try {
            Coin coin = new Coin();
            coin.setIdentity(identity);
            entityManager.remove(coin);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CoinDaoImpl, method delete()");
            throw new PersistentException();
        }
    }

    public void update(Coin coin) throws Exception {
        try {
            entityManager.merge(coin);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CoinDaoImpl, method update()");
            throw new PersistentException();
        }
    }

    public Coin read(Integer id) throws Exception{

        Coin coin = null;
        try {
            coin = entityManager.find(Coin.class, id);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CoinDaoImpl, method read()");
            throw new PersistentException();
        }
        return coin;
    }


}
