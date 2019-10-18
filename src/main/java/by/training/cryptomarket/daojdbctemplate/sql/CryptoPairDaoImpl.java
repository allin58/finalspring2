package by.training.cryptomarket.daojdbctemplate.sql;


import by.training.cryptomarket.daojdbctemplate.CryptoPairDao;
import by.training.cryptomarket.entity.CryptoPair;
import by.training.cryptomarket.exception.PersistentException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

/**
 * The class of CryptoPairDao implamentation.
 * @author Nikita Karchahin
 * @version 1.0
 */
@Repository
public class CryptoPairDaoImpl extends BaseDao implements CryptoPairDao {

    /**
     * The field to store the sql create request.
     */
    private static  String createSql = "INSERT INTO cryptocurrency_pairs (identity,first_currency,second_currency,active) VALUES (?,?,?,?)";

    /**
     * The field to store the sql readList request.
     */
    private static  String readListSql = "SELECT identity,first_currency, second_currency, active FROM cryptocurrency_pairs";

    /**
     * The field to store the sql read request.
     */
    private static  String readSql = "SELECT identity,first_currency,second_currency,active FROM cryptocurrency_pairs WHERE identity = ?";

    /**
     * The field to store the sql update request.
     */
    private static  String updateSql = "UPDATE cryptocurrency_pairs SET first_currency = ?, second_currency = ? , active = ? WHERE identity = ?";

    /**
     * The field to store the sql delete request.
     */
    private static  String deleteSql = "DELETE FROM cryptocurrency_pairs WHERE identity = ?";




    /**
     * The method that returns collection of CryptoPair.
     * @return collection of CryptoPair
     * @throws PersistentException
     */
    @Override
    public List<CryptoPair> read() throws PersistentException {


        List<CryptoPair> cryptoPairs = null;
        try {
            cryptoPairs = jdbcOperations.query(readListSql, new RowMapper<CryptoPair>() {
                @Override
                public CryptoPair mapRow(ResultSet resultSet, int i) throws SQLException {

                    CryptoPair cryptoPair = new CryptoPair();
                    cryptoPair.setIdentity((resultSet.getInt("identity")));
                    cryptoPair.setFirstCurrency(resultSet.getInt("first_currency"));
                    cryptoPair.setSecondCurrency(resultSet.getInt("second_currency"));
                    cryptoPair.setActive(resultSet.getBoolean("active"));

                    return cryptoPair;
                }
            });
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method read()");
        }


        return cryptoPairs;


    }


    /**
     * The method that creates a new record.
     * @param cryptoPair cryptoPair
     * @return number of record
     * @throws PersistentException PersistentException
     */
    @Override
    public Integer create(final CryptoPair cryptoPair) throws PersistentException {

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        try {
            jdbcOperations.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                    PreparedStatement ps =
                            connection.prepareStatement(createSql,Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, cryptoPair.getIdentity());
                    ps.setInt(2, cryptoPair.getFirstCurrency());
                    ps.setInt(3, cryptoPair.getSecondCurrency());
                    ps.setBoolean(4, cryptoPair.getActive());


                    return ps;
                }
            }, keyHolder);
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method create()");
        }

        return (Integer) keyHolder.getKeys().get("identity");


    }

    /**
     * The method that returns cryptoPair by id.
     * @param identity
     * @return cryptoPair
     * @throws PersistentException PersistentException
     */
    @Override
    public CryptoPair read(final Integer identity) throws PersistentException {


        CryptoPair cryptoPair = null;
        try {
            cryptoPair = jdbcOperations.queryForObject(readSql, new Object[]{identity}, new RowMapper<CryptoPair>() {
                @Override
                public CryptoPair mapRow(ResultSet resultSet, int i) throws SQLException {
                    CryptoPair cryptoPair = new CryptoPair();
                    cryptoPair.setIdentity(identity);
                    cryptoPair.setFirstCurrency(resultSet.getInt("first_currency"));
                    cryptoPair.setSecondCurrency(resultSet.getInt("second_currency"));
                    cryptoPair.setActive(resultSet.getBoolean("active"));
                    return cryptoPair;
                }
            });
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method read()");
        }
        return cryptoPair;
    }

    /**
     * The method that updates cryptoPair.
     * @param cryptoPair cryptoPair
     * @throws PersistentException
     */
    @Override
    public void update(final CryptoPair cryptoPair)  {

        try {
            jdbcOperations.update(updateSql,cryptoPair.getFirstCurrency(),cryptoPair.getSecondCurrency(),cryptoPair.getActive(),cryptoPair.getIdentity());
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method update()");
        }
    }



    /**
     *  The method that deletes cryptoPair by id.
     * @param identity identity
     * @throws PersistentException
     */
    @Override
    public void delete(final Integer identity)  {
        try {
            jdbcOperations.update(deleteSql, identity );
        } catch (DataAccessException e) {
            LOGGER.info("DataAccessException in CryptoPairDaoImpl, method delete()");
        }

    }
}
