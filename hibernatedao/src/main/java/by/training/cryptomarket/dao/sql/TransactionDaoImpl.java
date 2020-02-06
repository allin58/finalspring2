package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.dao.TransactionDao;
import by.training.cryptomarket.entity.Transaction;
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
public class TransactionDaoImpl extends BaseDao implements TransactionDao {




    public List<Transaction> read() throws PersistentException {
        List<Transaction> list = null;
        try {
            list = entityManager.createQuery("from Transaction").getResultList();
        } catch (Exception e) {
            LOGGER.info("DataAccessException in TransactionDaoImpl, method read()");
            throw new PersistentException();
        }

        return list;
    }

    public Integer create(Transaction transaction) throws PersistentException {
        try {
            LOGGER.info("Transaction is created by hibernate");
            entityManager.persist(transaction);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in TransactionDaoImpl, method create()");
            throw new PersistentException();
        }
        return 0;
    }

    public void delete(Integer transaction) throws PersistentException {

        try {
            entityManager.remove(read(transaction));
        } catch (Exception e) {
            LOGGER.info("DataAccessException in TransactionDaoImpl, method delete()");
            throw new PersistentException();
        }
    }

    public void update(Transaction transaction) throws PersistentException {

        try {
            entityManager.merge(transaction);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in TransactionDaoImpl, method update()");
            throw new PersistentException();
        }
    }

    public Transaction read(Integer id) throws PersistentException {

        Transaction transaction = null;
        try {
            transaction = entityManager.find(Transaction.class, id);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in TransactionDaoImpl, method read()");
            throw new PersistentException();
        }
        return transaction;
    }







}
