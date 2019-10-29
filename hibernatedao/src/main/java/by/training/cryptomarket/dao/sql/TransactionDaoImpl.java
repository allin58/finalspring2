package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.entity.Transaction;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class TransactionDaoImpl extends BaseDao {




    public List<Transaction> read() throws Exception {
        List<Transaction> list = null;
        try {
            list = entityManager.createQuery("from Transaction").getResultList();
        } catch (Exception e) {
            LOGGER.info("DataAccessException in TransactionDaoImpl, method read()");
            throw new PersistentException();
        }

        return list;
    }

    public void create(Transaction transaction) throws Exception {
        try {
            LOGGER.info("Transaction is created by hibernate");
            entityManager.persist(transaction);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in TransactionDaoImpl, method create()");
            throw new PersistentException();
        }
    }

    public void delete(Transaction transaction) throws Exception {

        try {
            entityManager.remove(transaction);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in TransactionDaoImpl, method delete()");
            throw new PersistentException();
        }
    }

    public void update(Transaction transaction) throws Exception {

        try {
            entityManager.merge(transaction);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in TransactionDaoImpl, method update()");
            throw new PersistentException();
        }
    }

    public Transaction read(Integer id) throws Exception {

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
