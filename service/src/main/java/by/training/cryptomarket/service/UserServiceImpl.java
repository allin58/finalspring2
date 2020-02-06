package by.training.cryptomarket.service;


import by.training.cryptomarket.dao.UserDao;
import by.training.cryptomarket.entity.User;
import org.bouncycastle.util.encoders.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

/**
 * This service ensures interact with users.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */



@Service
public class UserServiceImpl implements UserService {



    @Autowired
    UserDao userDao;

    /**
     *  Method for adding of new user.
     * @param user user
     * @return identity of user
     * @throws Exception Exception
     */
          public Integer addUser(final User user) throws Exception {

        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest((user.getHashOfPassword()).getBytes(StandardCharsets.UTF_8));
        user.setHashOfPassword(new String(Hex.encode(hash)));
               userDao.create(user);
              return 0;

    }


    /**
     *
     * This method checks the presence of the user.
     * @param name name
     * @return boolean value
     * @throws Exception Exception
     */
    public boolean userIsExist(final String name) throws Exception {
         List<User> users;


         users = userDao.read();

        for (User user : users) {
            if (name.equals(user.getUserName())) {
                return true;
            }
        }
        return false;
    }

    /**
     *  This method checks the presence of the user by userName and password.
     * @param userName userName
     * @return identity of user
     * @throws Exception Exception
     */
  public Integer getIdByUserNameAndPassword(final String userName)throws Exception {
     // MessageDigest digest = MessageDigest.getInstance("SHA-256");
     // byte[] hash = digest.digest((password).getBytes(StandardCharsets.UTF_8));
      //password = new String(Hex.encode(hash));
      List<User> users;
      users = userDao.read();

      for (User user : users) {
          if (userName.equals(user.getUserName())) {
              return user.getIdentity();
          }
      }
      return 0;
  }

    /**
     * This method returns user by identity.
     * @param id id
     * @return user
     * @throws Exception Exception
     */
    public User getUserById(final Integer id)throws Exception {
       if (id == 0) return null;
        User user = userDao.read(id);
        return user;
    }


    /**
     * The method for getting all users.
     * @return collection of users
     * @throws Exception Exception
     */
    public List getAllUsers() throws Exception {

        List<User> users;

        users = userDao.read();

        return users;
    }



    @Override
    public User getUser(String login) throws Exception {


        List<User> users = null;

            users = userDao.read();

        User user =null;

        for (User user1 : users) {

            if(user1.getUserName().equals(login)) {
                user = user1;
            }
        }

        return user;
    }
}
