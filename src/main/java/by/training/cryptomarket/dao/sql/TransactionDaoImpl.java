package by.training.cryptomarket.dao.sql;



import by.training.cryptomarket.entity.Transaction;
import by.training.cryptomarket.exception.PersistentException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class TransactionDaoImpl{

    @Autowired
    private SessionFactory sessionFactory;

    public List<Transaction> read() {
        Session session = sessionFactory.getCurrentSession();
        List<Transaction> list = session.createQuery("from Transaction").list();
        return list;
    }

    public void create(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(transaction);
    }

    public void delete(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(transaction);
    }

    public void update(Transaction transaction) {
        Session session = sessionFactory.getCurrentSession();
        session.update(transaction);
    }

    public Transaction read(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Transaction.class, id);
    }



    public void delete(Integer identity) throws PersistentException {
        Transaction transaction = new Transaction();
        transaction.setIdentity(identity);
        Session session = sessionFactory.getCurrentSession();
        session.delete(transaction);
    }



}
