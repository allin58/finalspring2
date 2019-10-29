package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public class UserDaoImpl extends BaseDao {






    public List<User> read() throws Exception {
        List<User> list = null;
        try {
            list = entityManager.createQuery("from User").getResultList();
        } catch (Exception e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method read()");
            throw new PersistentException();
        }


        return list;
    }

    public void create(User user) throws Exception {


        try {
            entityManager.persist(user);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method create()");
            throw new PersistentException();
        }


    }



    public void delete(User user) throws Exception {
//em.remove(em.contains(entity) ? entity : em.merge(entity));

        try {
            user = entityManager.merge(user);
            entityManager.remove(user);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method delete()");
            throw new PersistentException();
        }


    }

    public void update(User user) throws Exception {

        try {
            entityManager.merge(user);
        } catch (Exception e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method update()");
            throw new PersistentException();
        }

    }

    public User read(Integer id) throws Exception {


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
