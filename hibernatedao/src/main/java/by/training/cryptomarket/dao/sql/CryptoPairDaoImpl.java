package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.entity.CryptoPair;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class CryptoPairDaoImpl extends BaseDao {





    public List<CryptoPair> read() throws Exception{

        List<CryptoPair> list = null;
        try {
            list = entityManager.createQuery("from CryptoPair").getResultList();
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method read()");
            throw new PersistentException();
        }

        return list;
    }

    public void create(CryptoPair cryptoPair) throws Exception{

        try {
            entityManager.persist(cryptoPair);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method create()");
            throw new PersistentException();
        }
    }

    public void delete(CryptoPair cryptoPair) throws Exception {

        try {
            entityManager.remove(cryptoPair);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method delete()");
            throw new PersistentException();
        }
    }

    public void update(CryptoPair cryptoPair) throws Exception{

        try {
            entityManager.merge(cryptoPair);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method update()");
            throw new PersistentException();
        }
    }

    public CryptoPair read(Integer id) throws Exception {


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
