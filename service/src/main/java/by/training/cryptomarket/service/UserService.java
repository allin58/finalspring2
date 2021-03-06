package by.training.cryptomarket.service;

import by.training.cryptomarket.entity.User;

import java.util.List;

public interface UserService {

    User getUser(String login) throws Exception;
    Integer addUser(final User user) throws Exception;
    boolean userIsExist(final String name) throws Exception;
    Integer getIdByUserNameAndPassword(final String userName)throws Exception;
    User getUserById(final Integer id)throws Exception;
    List getAllUsers() throws Exception;


}
