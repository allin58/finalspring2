package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.dao.UserDao;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

@Transactional
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {






    public List<User> read() throws PersistentException {
        List<User> list = null;
        try {
            list = entityManager.createQuery("from User").getResultList();
        } catch (Exception e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method read()");
            throw new PersistentException();
        }


        return list;
    }

    public Integer create(User user) throws PersistentException {


        try {
            entityManager.persist(user);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method create()");
            throw new PersistentException();
        }

        return 0;
    }



    public void delete(Integer userId) throws PersistentException {


        User user = read(userId);
        try {
            user = entityManager.merge(user);
            entityManager.remove(user);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method delete()");
            throw new PersistentException();
        }


    }

    public void update(User user) throws PersistentException {

        try {
            entityManager.merge(user);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method update()");
            throw new PersistentException();
        }

    }

    public User read(Integer id) throws PersistentException {


        User user = null;
        try {
            user = entityManager.find(User.class, id);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method read()");
            throw new PersistentException();
        }
        return user;

    }




}
