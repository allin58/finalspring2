package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class WalletDaoImpl extends BaseDao {



    public List<Wallet> read() throws Exception {
        List<Wallet> list = null;
        try {
            list = entityManager.createQuery("from Wallet").getResultList();
        } catch (Exception e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method read()");
            throw new PersistentException();
        }

        return list;
    }

    public void create(Wallet wallet) throws Exception {
        try {
            entityManager.persist(wallet);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method create()");
            throw new PersistentException();
        }
    }

    public void delete(Wallet wallet) throws Exception {
        try {
            entityManager.remove(wallet);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method delete()");
            throw new PersistentException();
        }
    }

    public void update(Wallet wallet) throws Exception {
        try {
            entityManager.merge(wallet);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method update()");
            throw new PersistentException();
        }
    }

    public Wallet read(Integer id) throws Exception {
        Wallet wallet = null;
        try {
            wallet = entityManager.find(Wallet.class, id);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method read()");
            throw new PersistentException();
        }
        return wallet;
    }





    public void delete(Integer identity) throws Exception {
        Wallet wallet = new Wallet();
        wallet.setIdentity(identity);
        delete(wallet);
    }




}
