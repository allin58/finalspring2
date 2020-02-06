package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.dao.CryptoPairDao;
import by.training.cryptomarket.entity.CryptoPair;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Transactional
@Repository
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CryptoPairDaoImpl extends BaseDao implements CryptoPairDao {





    public List<CryptoPair> read() throws PersistentException{



        List<CryptoPair> list = null;
        try {
            list = entityManager.createQuery("from CryptoPair").getResultList();
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method read()");
            throw new PersistentException();
        }

        return list;
    }

    public Integer create(CryptoPair cryptoPair) throws PersistentException{

        try {
            entityManager.persist(cryptoPair);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method create()");
            throw new PersistentException();
        }

        return 0;
    }



    public void delete(final Integer identity) throws PersistentException {

        try {
            entityManager.remove(read(identity));
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method delete()");
            throw new PersistentException();
        }
    }

    public void update(CryptoPair cryptoPair) throws PersistentException{

        try {
            entityManager.merge(cryptoPair);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method update()");
            throw new PersistentException();
        }
    }

    public CryptoPair read(Integer id) throws PersistentException {


        CryptoPair cryptoPair = null;
        try {
            cryptoPair = entityManager.find(CryptoPair.class, id);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method read()");
            throw new PersistentException();
        }
        return cryptoPair;
    }



}
