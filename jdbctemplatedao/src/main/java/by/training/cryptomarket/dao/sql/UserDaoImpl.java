package by.training.cryptomarket.dao.sql;


import by.training.cryptomarket.dao.UserDao;
import by.training.cryptomarket.entity.User;
import by.training.cryptomarket.enums.Role;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * The class of UserDao implamentation.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Repository
public class UserDaoImpl extends BaseDao implements UserDao {

    /**
     * The field to store the sql create request.
     */
  private static  String createSql = "INSERT INTO users (user_name,name,surname,hash_of_password,role) VALUES (?, ?, ?, ?, ?::roles)";

    /**
     * The field to store the sql readList request.
     */
  private static  String readListSql = "SELECT identity,user_name,name, surname,hash_of_password,role FROM users";

    /**
     * The field to store the sql read request.
     */
  private static  String readSql = "SELECT user_name, name,surname, hash_of_password,role FROM users WHERE identity = ?";

    /**
     * The field to store the sql update request.
     */
  private static  String updateSql = "UPDATE users SET user_name = ?, name = ?, surname = ?, hash_of_password = ?, role = ?::roles WHERE identity = ?";

    /**
     * The field to store the sql delete request.
     */
  private static  String deleteSql = "DELETE FROM users WHERE identity = ?";





    /**
     * The method that returns collection of users.
     * @return collection of users
     * @throws PersistentException PersistentException
     */
    public List<User> read()  {



        List<User> users = null;
        try {
            users = jdbcOperations.query(readListSql, new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet resultSet, int i) throws SQLException {
                    User user = new User();
                    user.setIdentity(resultSet.getInt("identity"));
                    user.setUserName(resultSet.getString("user_name"));
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setHashOfPassword(resultSet.getString("hash_of_password"));
                    user.setRole(Role.valueOf(resultSet.getString("role")));


                    return user;
                }
            });
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method read()");
        }


        return users;


    }

    /**
     * The method that creates a new record.
     * @param user user
     * @return number of record
     * @throws PersistentException PersistentException
     */
    public Integer create(final User user) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcOperations.update(new PreparedStatementCreator() {
          @Override
          public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

              PreparedStatement ps =
                      connection.prepareStatement(createSql);
              ps.setString(1, user.getUserName());
              ps.setString(2, user.getName());
              ps.setString(3, user.getSurname());
              ps.setString(4, user.getHashOfPassword());
              ps.setString(5, user.getRole().toString());
              return ps;
          }
      }, keyHolder);
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method create()");
        }

        // return (Integer) keyHolder.getKeys().get("identity");
        return 0;
    }

    /**
     * The method that returns user by id.
     * @param identity identity
     * @return user
     * @throws PersistentException PersistentException
     */
    public User read(final Integer identity) {

        User user = null;
        try {
            user = jdbcOperations.queryForObject(readSql, new Object[]{identity}, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setIdentity(identity);
                user.setUserName(resultSet.getString("user_name"));
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setHashOfPassword(resultSet.getString("hash_of_password"));
                user.setRole(Role.valueOf(resultSet.getString("role")));
                return user;
            }
        });
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method read()");
        }
        return user;

    }


    /**
     * The method that updates user.
     * @param user user
     * @throws PersistentException PersistentException
     */
    public void update(final User user)  {

        try {
            jdbcOperations.update(updateSql,user.getUserName(),user.getName(),user.getSurname(),user.getHashOfPassword(),user.getRole().toString(),user.getIdentity());
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method update()");
        }

    }


    /**
     *  The method that deletes user by id.
     * @param identity identity
     * @throws PersistentException PersistentException
     */
    public void delete(final Integer identity) {

        try {
            jdbcOperations.update(deleteSql, identity );
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in UserDaoImpl, method delete()");
        }

    }
}
