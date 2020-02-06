package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.dao.WalletDao;
import by.training.cryptomarket.entity.Wallet;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Transactional
@Repository
@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WalletDaoImpl extends BaseDao implements WalletDao {



    public List<Wallet> read() throws PersistentException {
        List<Wallet> list = null;
        try {
            list = entityManager.createQuery("from Wallet").getResultList();
        } catch (Exception e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method read()");
            throw new PersistentException();
        }

        return list;
    }

    public Integer create(Wallet wallet) throws PersistentException {
        try {
            entityManager.persist(wallet);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method create()");
            throw new PersistentException();
        }
        return 0;
    }

    public void delete(Wallet wallet) throws PersistentException {
        try {
            entityManager.remove(wallet);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method delete()");
            throw new PersistentException();
        }
    }

    public void update(Wallet wallet) throws PersistentException {
        try {
            entityManager.merge(wallet);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method update()");
            throw new PersistentException();
        }
    }

    public Wallet read(Integer id) throws PersistentException {
        Wallet wallet = null;
        try {
            wallet = entityManager.find(Wallet.class, id);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in WalletDaoImpl, method read()");
            throw new PersistentException();
        }
        return wallet;
    }





    public void delete(Integer identity) throws PersistentException {
        Wallet wallet = new Wallet();
        wallet.setIdentity(identity);
        delete(wallet);
    }




}
