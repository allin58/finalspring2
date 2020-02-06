package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.dao.CoinDao;
import by.training.cryptomarket.entity.Coin;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

//@Repository
@Component
@Transactional
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CoinDaoImpl extends BaseDao implements CoinDao {




    public List<Coin> read() throws PersistentException {


        List<Coin> list = null;
        try {
            list = entityManager.createQuery("from Coin").getResultList();
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CoinDaoImpl, method read()");
           throw new PersistentException();
        }

        return list;
    }

    public Integer create(Coin coin) throws PersistentException {
        try {
            entityManager.persist(coin);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CoinDaoImpl, method create()");
            throw new PersistentException();
        }
        return 0;
    }

    public void delete(Coin coin) throws PersistentException{
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

    public void update(Coin coin) throws PersistentException {
        try {
            entityManager.merge(coin);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CoinDaoImpl, method update()");
            throw new PersistentException();
        }
    }

    public Coin read(Integer id) throws PersistentException{

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
