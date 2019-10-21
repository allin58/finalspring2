package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.exception.PersistentException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Transactional
@Repository
public class UserDaoImpl {

    @Autowired
    private SessionFactory sessionFactory;


    public List<User> read() {


       Session session = sessionFactory.getCurrentSession();


       List<User> list = session.createQuery("from User").list();


        return list;
    }

    public void create(User user) {

        Session session = sessionFactory.getCurrentSession();
        session.persist(user);

    }

    public void delete(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(user);
    }

    public void update(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.update(user);
    }

    public User read(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }




}
